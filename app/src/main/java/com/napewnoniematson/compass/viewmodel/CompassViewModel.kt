package com.napewnoniematson.compass.viewmodel

import androidx.lifecycle.ViewModel
import com.napewnoniematson.compass.Compass

class CompassViewModel(val compass: Compass) : ViewModel() {
    fun getAngle() = compass.getAngle()
}