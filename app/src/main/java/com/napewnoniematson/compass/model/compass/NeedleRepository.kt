package com.napewnoniematson.compass.model.compass

import com.napewnoniematson.compass.logic.reader.NeedleReader

class NeedleRepository(private val reader: NeedleReader) {
    fun getNeedle() = reader.getNeedle()
}