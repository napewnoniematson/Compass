package com.napewnoniematson.compass.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GeoPoint {

    private val coordinatesMap = mutableMapOf<String, Float>()
    private val coordinates = MutableLiveData<Map<String, Float>>()

    init {
        coordinates.value = coordinatesMap
    }

    fun addCoordinate(key: String, value: Float) {
        coordinatesMap[key] = value
        coordinates.value = coordinatesMap
    }

    fun getCoordinates() = coordinates as LiveData<Map<String, Float>>



}