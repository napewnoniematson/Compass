package com.napewnoniematson.compass

import android.content.Context
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.napewnoniematson.compass.logic.DirectionFinder
import com.napewnoniematson.compass.logic.reader.*
import com.napewnoniematson.compass.logic.reader.location.DestinationPointReader
import com.napewnoniematson.compass.logic.reader.location.DestinationPointReaderImpl
import com.napewnoniematson.compass.logic.reader.location.UserLocationReader
import com.napewnoniematson.compass.logic.reader.location.UserLocationReaderImpl
import com.napewnoniematson.compass.logic.reader.sensor.NeedleReader
import com.napewnoniematson.compass.logic.reader.sensor.SensorReaderImpl
import com.napewnoniematson.compass.model.compass.NeedleRepository
import com.napewnoniematson.compass.model.geo.GeoPoint
import com.napewnoniematson.compass.model.geo.GeoPointRepository
import com.napewnoniematson.compass.view.DestinationPointListenerProvider
import com.napewnoniematson.compass.view.activity.MainActivity
import com.napewnoniematson.compass.viewmodel.GeoPointViewModel
import com.napewnoniematson.compass.viewmodel.NeedleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CompassImpl(context: Context) : Compass, DestinationPointListenerProvider {

    private val TAG: String? = CompassImpl::class.simpleName

    private val userLocation = GeoPoint()
    private val destinationPoint = GeoPoint()

    private var isUserLocationUpdated = false
    private var isDestinationPointUpdated = false

    private val reader: Reader = SensorReaderImpl(context)
    private val userLocationReader: Reader = UserLocationReaderImpl(context)
    private val destinationPointReader: DestinationPointReader = DestinationPointReaderImpl(context)

    private val needleViewModel = NeedleViewModel(NeedleRepository(reader as NeedleReader))
    private val geoPointViewModel = GeoPointViewModel(
        GeoPointRepository(
            userLocationReader as UserLocationReader,
            destinationPointReader
        )
    )

    init {
        observeNeedle(context)
        observeGeoPoints(context)
    }

    override fun start() {
        reader.start()
        userLocationReader.start()
    }

    override fun stop() {
        reader.stop()
        userLocationReader.stop()
    }

    private fun observeNeedle(context: Context) {
        needleViewModel.getNeedle().observe(context as AppCompatActivity, Observer {
            updateNeedleView(context, it.angle)
        })
    }

    private fun updateNeedleView(context: Context, angle: Float) {
        (context as MainActivity).compassView.updateNeedle(angle)
    }

    private fun observeGeoPoints(context: Context) {
        observeUserLocation(context)
        observeDestinationPoint(context)
    }

    private fun observeUserLocation(context: Context) {
        geoPointViewModel.getUserLocation().observe(context as AppCompatActivity, Observer {
            if (areBothGeoPointUpdated()) {
                updateDestinationPointView(context)
            }
            userLocation.latitude = it.latitude
            userLocation.longitude = it.longitude
            isUserLocationUpdated = true
            Log.d(TAG, "User location updated")
        })
    }

    private fun observeDestinationPoint(context: Context) {
        geoPointViewModel.getDestinationPoint().observe(context as AppCompatActivity, Observer {
            if (areBothGeoPointUpdated()) {
                updateDestinationPointView(context)
            }
            destinationPoint.latitude = it.latitude
            destinationPoint.longitude = it.longitude
            isDestinationPointUpdated = true
        })
    }

    private fun areBothGeoPointUpdated() = isDestinationPointUpdated && isUserLocationUpdated

    private fun updateDestinationPointView(context: Context) {
        (context as MainActivity).compassView.updateDestinationPoint(
            DirectionFinder.findDirectionAngle(userLocation, destinationPoint)
        )
    }

    override fun getDestinationPointOnClickListener() =
        destinationPointReader as View.OnClickListener
}