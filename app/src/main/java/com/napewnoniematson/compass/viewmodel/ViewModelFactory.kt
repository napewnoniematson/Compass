package com.napewnoniematson.compass.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.napewnoniematson.compass.Compass

class ViewModelFactory(private val compass: Compass): ViewModelProvider.Factory {

    private val illegalArgumentExceptionMessage: String = "Unknown Argument Exception"

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompassViewModel::class.java)) {
            return CompassViewModel(compass) as T
        }
        throw IllegalArgumentException(illegalArgumentExceptionMessage)
    }
}