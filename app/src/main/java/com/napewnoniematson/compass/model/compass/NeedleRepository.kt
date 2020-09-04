package com.napewnoniematson.compass.model.compass

import com.napewnoniematson.compass.logic.CompassImpl
import com.napewnoniematson.compass.logic.NeedleReader

class NeedleRepository(private val reader: NeedleReader) {
    fun getNeedle() = reader.getNeedle()
}