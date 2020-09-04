package com.napewnoniematson.compass.view.activity

import android.content.Context
import android.hardware.GeomagneticField
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.napewnoniematson.compass.logic.CompassImpl
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.GeoPoint
import com.napewnoniematson.compass.GeoCoordinateButton
import com.napewnoniematson.compass.viewmodel.NeedleViewModel
import com.napewnoniematson.compass.GeoPointViewModel
import com.napewnoniematson.compass.ViewModelFactory
import com.napewnoniematson.compass.logic.Compass
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String? = MainActivity::class.simpleName

    private lateinit var compass: Compass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compass = CompassImpl(this)
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