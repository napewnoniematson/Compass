package com.napewnoniematson.compass

import androidx.lifecycle.ViewModel
import com.napewnoniematson.compass.GeoPoint

class GeoPointViewModel(private val geoPoint : GeoPoint) : ViewModel() {
    fun getLocation() = geoPoint.getCoordinates()
}
