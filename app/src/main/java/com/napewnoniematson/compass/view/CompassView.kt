package com.napewnoniematson.compass.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.util.Log

class CompassView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val TAG: String? = CompassView::class.simpleName

    fun moveNeedle(angle: Float) {
        Log.d(TAG, "Needle moved by $angle degrees")
    }


}