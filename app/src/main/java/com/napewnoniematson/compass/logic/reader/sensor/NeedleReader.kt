package com.napewnoniematson.compass.logic.reader.sensor

import androidx.lifecycle.LiveData
import com.napewnoniematson.compass.model.compass.Needle

interface NeedleReader {
    fun getNeedle() : LiveData<Needle>
}