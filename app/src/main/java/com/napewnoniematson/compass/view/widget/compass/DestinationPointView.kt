package com.napewnoniematson.compass.view.widget.compass

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.napewnoniematson.compass.R

class DestinationPointView(context: Context, attrs: AttributeSet) :
    AppCompatImageView(context, attrs), CompassElement {

    private val TAG: String? = DestinationPointView::class.simpleName

    init {
//        setImageResource(R.drawable.needle_compass)
        setImageResource(R.color.colorPrimaryDark)
//        visibility = View.INVISIBLE
    }

    override fun update(angle: Float) {
        Log.d(TAG, "Temporary: DestinationPoint updated")
    }


}