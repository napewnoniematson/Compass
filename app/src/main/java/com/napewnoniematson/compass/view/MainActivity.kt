package com.napewnoniematson.compass.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.napewnoniematson.compass.Compass
import com.napewnoniematson.compass.R

class MainActivity : AppCompatActivity() {

    private lateinit var compass: Compass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compass = Compass(this)
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