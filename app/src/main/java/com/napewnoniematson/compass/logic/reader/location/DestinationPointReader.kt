package com.napewnoniematson.compass.logic.reader.location

import androidx.lifecycle.LiveData
import com.napewnoniematson.compass.model.geo.GeoPoint

interface DestinationPointReader {
    fun getPoint() : LiveData<GeoPoint>
}