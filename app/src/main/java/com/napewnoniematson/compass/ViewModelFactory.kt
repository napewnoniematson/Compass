package com.napewnoniematson.compass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.napewnoniematson.compass.logic.CompassImpl
import com.napewnoniematson.compass.model.compass.NeedleRepository
import com.napewnoniematson.compass.viewmodel.NeedleViewModel

class ViewModelFactory(private val compassImpl: CompassImpl): ViewModelProvider.Factory {

    private val illegalArgumentExceptionMessage: String = "Unknown Argument Exception"

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(NeedleViewModel::class.java)) {
//            return NeedleViewModel(
//                NeedleRepository(compassImpl)
//            ) as T
//        }
//        throw IllegalArgumentException(illegalArgumentExceptionMessage)
        return null as T //remove
    }
}