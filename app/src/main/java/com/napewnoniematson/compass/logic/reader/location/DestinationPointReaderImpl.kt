package com.napewnoniematson.compass.logic.reader.location

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.view.widget.GeoCoordinateButton
import com.napewnoniematson.compass.model.geo.GeoPoint

class DestinationPointReaderImpl(context: Context) : DestinationPointReader, View.OnClickListener {

    private val TAG: String? = DestinationPointReaderImpl::class.simpleName

    private var isLatitudeUpdated = false
    private var isLongitudeUpdated = false

    private val LATITUDE = context.getString(R.string.latitude)
    private val LONGITUDE = context.getString(R.string.longitude)

    private val destinationPoint = GeoPoint()
    private val destinationPointLD = MutableLiveData<GeoPoint>()

    override fun onClick(v: View?) {
        (v as GeoCoordinateButton).geoDialog.dialog.show()
        when (v.geoCoordinateName) {
            LATITUDE -> {
                if (isLatitudeInRange(v.coordinate)) {
                    destinationPoint.latitude = v.coordinate
                    isLatitudeUpdated = true
                }
            }
            LONGITUDE -> {
                if (isLongitudeInRange(v.coordinate)) {
                    destinationPoint.longitude = v.coordinate
                    isLongitudeUpdated = true
                }
            }
        }

        if (areCoordinatesUpdated())
            destinationPointLD.value = destinationPoint
    }

    private fun isLatitudeInRange(latitude: Float) = -90f <= latitude && latitude <= 90f
    private fun isLongitudeInRange(latitude: Float) = -180f <= latitude && latitude <= 180f

    private fun areCoordinatesUpdated() = isLatitudeUpdated && isLongitudeUpdated

    override fun getPoint() = destinationPointLD as LiveData<GeoPoint>
}