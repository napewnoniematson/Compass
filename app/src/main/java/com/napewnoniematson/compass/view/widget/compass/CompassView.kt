package com.napewnoniematson.compass.view.widget.compass

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.napewnoniematson.compass.R
import kotlinx.android.synthetic.main.compass_view.view.*

class CompassView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val TAG: String? = CompassView::class.simpleName

    init {
        inflate(context, R.layout.compass_view, this)
    }

    fun update(needleAngle: Float, destinationAngle: Float) {
        updateNeedle(needleAngle)
        updateDestinationPoint(destinationAngle)
    }

    fun updateNeedle(needleAngle: Float) {
        (needleImageView as CompassElement).update(needleAngle)
    }

    fun updateDestinationPoint(destinationAngle: Float) {
        (destinationImageView as CompassElement).update(destinationAngle)
    }

}