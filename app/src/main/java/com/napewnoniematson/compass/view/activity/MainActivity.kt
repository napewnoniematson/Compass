package com.napewnoniematson.compass.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.napewnoniematson.compass.CompassImpl
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.Compass
import com.napewnoniematson.compass.logic.reader.location.PositiveDialogButtonHandler
import com.napewnoniematson.compass.view.PositiveDialogButtonHandlerProvider
import com.napewnoniematson.compass.view.widget.GeoCoordinateButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String? = MainActivity::class.simpleName

    private lateinit var compass: Compass
    private lateinit var positiveDialogButtonHandler: PositiveDialogButtonHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compass = CompassImpl(this)
        positiveDialogButtonHandler =
            (compass as PositiveDialogButtonHandlerProvider).getPositiveDialogButtonHandler()
        (latitudeButton as GeoCoordinateButton).geoDialog.positiveDialogButtonHandler =
            positiveDialogButtonHandler
        (longitudeButton as GeoCoordinateButton).geoDialog.positiveDialogButtonHandler =
            positiveDialogButtonHandler
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