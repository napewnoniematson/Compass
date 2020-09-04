package com.napewnoniematson.compass.model.geo

import com.napewnoniematson.compass.logic.reader.location.DestinationPointReader
import com.napewnoniematson.compass.logic.reader.location.UserLocationReader

class GeoPointRepository(
    private val userLocationReader: UserLocationReader,
    private val destinationPointReader: DestinationPointReader
) {

    private val TAG: String? = GeoPointRepository::class.simpleName

    fun getUserLocation() = userLocationReader.getLocation()
    fun getDestinationPoint() = destinationPointReader.getPoint()
}