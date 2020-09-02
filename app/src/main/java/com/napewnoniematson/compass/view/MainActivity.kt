package com.napewnoniematson.compass.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.napewnoniematson.compass.Compass
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.viewmodel.CompassViewModel
import com.napewnoniematson.compass.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String? = MainActivity::class.simpleName

    private lateinit var compass: Compass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compass = Compass(this)
        val compassViewModel = ViewModelProvider(this,ViewModelFactory(compass))
            .get(CompassViewModel::class.java)
        // Observe the sensor vale
        compassViewModel.getAngle().observe(this, Observer {
            compassView.moveNeedle(it)

        })
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