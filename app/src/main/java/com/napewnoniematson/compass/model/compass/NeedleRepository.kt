package com.napewnoniematson.compass.model.compass

import com.napewnoniematson.compass.logic.reader.sensor.NeedleReader

class NeedleRepository(private val reader: NeedleReader) {

    private val TAG: String? = NeedleRepository::class.simpleName

    fun getNeedle() = reader.getNeedle()
}