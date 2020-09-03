package com.napewnoniematson.compass.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import com.napewnoniematson.compass.R
import kotlinx.android.synthetic.main.compass_view.view.*

class CompassView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val TAG: String? = CompassView::class.simpleName

    init {
        inflate(context, R.layout.compass_view, this)
        Log.d(TAG, "View attached")
    }


    fun moveNeedle(angle: Float) {
        Log.d(TAG, "Needle moved by $angle degrees")
        needleImageView.startAnimation(rotateAnimation(angle))
        needleImageView.rotation = angle
    }

    private fun rotateAnimation(angle: Float) : RotateAnimation {

        val rotate = RotateAnimation(
            needleImageView.rotation, angle,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 100
        rotate.interpolator = LinearInterpolator()
        return rotate
    }
}