package com.napewnoniematson.compass.logic.reader

import androidx.lifecycle.LiveData
import com.napewnoniematson.compass.model.compass.Needle

interface NeedleReader {
    fun getNeedle() : LiveData<Needle>
}