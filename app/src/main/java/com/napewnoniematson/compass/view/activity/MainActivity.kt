package com.napewnoniematson.compass.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.napewnoniematson.compass.CompassImpl
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.Compass
import com.napewnoniematson.compass.view.DestinationPointListenerProvider

class MainActivity : AppCompatActivity() {

    private val TAG: String? = MainActivity::class.simpleName

    private lateinit var compass: Compass
    private lateinit var geoButtonOnClickListener: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compass = CompassImpl(this)
        geoButtonOnClickListener =
            (compass as DestinationPointListenerProvider).getDestinationPointOnClickListener()
    }

    fun onGeoButtonClick(v: View) {
        geoButtonOnClickListener.onClick(v)
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