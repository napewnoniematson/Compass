package com.napewnoniematson.compass.viewmodel

import androidx.lifecycle.ViewModel
import com.napewnoniematson.compass.model.compass.NeedleRepository
import com.napewnoniematson.compass.model.geo.GeoPoint
import com.napewnoniematson.compass.model.geo.GeoPointRepository

class GeoPointViewModel(private val geoPointRepository: GeoPointRepository) : ViewModel() {

    private val TAG: String? = GeoPointViewModel::class.simpleName

    fun getUserLocation() = geoPointRepository.getUserLocation()
    fun getDestinationPoint() = geoPointRepository.getDestinationPoint()
}
