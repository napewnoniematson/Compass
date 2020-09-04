package com.napewnoniematson.compass.logic

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.napewnoniematson.compass.GeoPointViewModel
import com.napewnoniematson.compass.ViewModelFactory
import com.napewnoniematson.compass.model.compass.NeedleRepository
import com.napewnoniematson.compass.view.activity.MainActivity
import com.napewnoniematson.compass.viewmodel.NeedleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CompassImpl(context: Context) : Compass {

    private val TAG: String? = CompassImpl::class.simpleName

    private val sensorReader : SensorReader = SensorReaderImpl(context)
    private val needleViewModel = NeedleViewModel(NeedleRepository(sensorReader as NeedleReader))

    init {
        needleViewModel.getNeedle().observe(context as AppCompatActivity, Observer {
            (context as MainActivity).compassView.update(it.angle, 0f)

        })
    }

    override fun start() {
        sensorReader.start()
    }

    override fun stop() {
        sensorReader.stop()
    }
}