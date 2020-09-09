package com.napewnoniematson.compass.view.widget.compass

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import com.napewnoniematson.compass.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.compass_view.view.*

class DestinationPointView(context: Context, attrs: AttributeSet) :
    AppCompatImageView(context, attrs), CompassElement {

    private val TAG: String? = DestinationPointView::class.simpleName

    private val ANIMATION_DURATION_TIME: Long = 50

    private var angle = 0f
    private var tmpAngle = 0f

    init {
        setImageResource(R.drawable.destination)
        visibility = View.INVISIBLE
    }

    override fun update(angle: Float) {
        tmpAngle = if (angle >= 360) angle-360 else angle
        if (isNewAngle(angle) && !(isChangingTroughTrueNorth(tmpAngle)))
            this.startAnimation(rotateAnimation(tmpAngle))
        if (isInvisible)
            visibility = View.VISIBLE
        this.angle = tmpAngle
    }

    private fun isNewAngle(angle: Float) = !this.angle.equals(angle)

    private fun isChangingTroughTrueNorth(angle: Float) =
        this.angle - angle > 345 || this.angle - angle < -345

    private fun rotateAnimation(angle: Float): RotateAnimation {
        val rotate = RotateAnimation(
            this.angle, angle,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_PARENT, 0.5f
        )
        rotate.duration = ANIMATION_DURATION_TIME
        rotate.interpolator = LinearInterpolator()
        rotate.fillAfter = true
        return rotate
    }
}