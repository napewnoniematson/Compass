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
//    private var ctr: Int = 0

    init {
        inflate(context, R.layout.compass_view, this)
        Log.d(TAG, "View attached")
    }


    fun moveNeedle(angle: Float) {
        Log.d(TAG, "Needle moved by $angle degrees")
//        ctr++
//        if (ctr > 100) {
//        Log.d(TAG, "CTR = $ctr, ANGLE = $angle")
        needleImageView.startAnimation(rotateAnimation(angle))
        needleImageView.rotation = angle
//        ctr = 0
//        }
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

//    private fun animateProperty(
//        view: View,
//        property: String,
//        startValue: Float,
//        stopValue: Float,
//        duration: Long
//    ): ObjectAnimator {
//        val anim: ObjectAnimator = ObjectAnimator
//            .ofFloat(view, property, startValue, stopValue)
//        anim.duration = duration
////        anim.interpolator = AccelerateDecelerateInterpolator()
//        anim.repeatMode = ValueAnimator.REVERSE
//        anim.repeatCount = ValueAnimator.INFINITE
//        return anim
//    }
//
//    private fun animateScales(view: View): AnimatorSet {
//        val set = AnimatorSet()
//        set.playTogether(animateScaleX(view), animateScaleY(view))
//        return set
//    }
//
//    private fun animateScaleX(view: View): ObjectAnimator = animateProperty(
//        view, "scaleX", 1f, 1.3f, 1200
//    )
//
//    private fun animateScaleY(view: View): ObjectAnimator = animateProperty(
//        view, "scaleY", 1f, 1.1f, 1200
//    )


}