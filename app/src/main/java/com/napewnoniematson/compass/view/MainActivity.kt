package com.napewnoniematson.compass.view

import android.hardware.GeomagneticField
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.napewnoniematson.compass.Compass
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.model.GeoPoint
import com.napewnoniematson.compass.viewmodel.CompassViewModel
import com.napewnoniematson.compass.viewmodel.GeoPointViewModel
import com.napewnoniematson.compass.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String? = MainActivity::class.simpleName

    private lateinit var compass: Compass
    private lateinit var geoPoint: GeoPoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val geo = GeomagneticField(
            -100f, 100f, 0f, 1599138533187
        )
        Log.d(TAG, geo.declination.toString())
        Log.d(TAG, geo.fieldStrength.toString())
        Log.d(TAG, geo.horizontalStrength.toString())
        Log.d(TAG, geo.inclination.toString())
        Log.d(TAG, geo.x.toString())
        Log.d(TAG, geo.y.toString())
        Log.d(TAG, geo.z.toString())


        compass = Compass(this)
        geoPoint = GeoPoint()
        val compassViewModel = ViewModelProvider(this,ViewModelFactory(compass))
            .get(CompassViewModel::class.java)
        val geoPointViewModel = GeoPointViewModel(geoPoint)
        // Observe the sensor vale
        compassViewModel.getAngle().observe(this, Observer {
            compassView.moveNeedle(it)

        })
        geoPointViewModel.getLocation().observe(this, Observer {
            Log.d(TAG, it.toString())
        })
    }

    fun test(v: View) {
        val btn = v as GeoCoordinateButton
        btn.geoDialog.dialog.show()
        geoPoint.addCoordinate(btn.geoCoordinateName, btn.coordinate)
    }

    override fun onResume() {
        super.onResume()
        compass.start()
    }

    override fun onPause() {
        super.onPause()
        compass.stop()
    }
}