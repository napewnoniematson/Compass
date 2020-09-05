package com.napewnoniematson.compass.viewmodel

import androidx.lifecycle.ViewModel
import com.napewnoniematson.compass.model.compass.NeedleRepository

class NeedleViewModel(private val needleRepository: NeedleRepository) : ViewModel() {

    private val TAG: String? = NeedleViewModel::class.simpleName

    fun getNeedle() = needleRepository.getNeedle()
}