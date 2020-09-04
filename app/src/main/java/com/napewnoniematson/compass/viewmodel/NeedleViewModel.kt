package com.napewnoniematson.compass.viewmodel

import androidx.lifecycle.ViewModel
import com.napewnoniematson.compass.model.compass.NeedleRepository

class NeedleViewModel(private val needleRepository: NeedleRepository) : ViewModel() {
    fun getNeedle() = needleRepository.getNeedle()
}