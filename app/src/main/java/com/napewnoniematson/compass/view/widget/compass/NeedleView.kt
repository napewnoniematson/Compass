package com.napewnoniematson.compass.view.widget.compass


import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.widget.AppCompatImageView
import com.napewnoniematson.compass.R
import kotlinx.android.synthetic.main.compass_view.view.*

class NeedleView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs),
    CompassElement {

    private val TAG: String? = NeedleView::class.simpleName

    private val ANIMATION_DURATION_TIME: Long = 50

    private var angle: Float = 0f

    init {
        setImageResource(R.drawable.needle)
    }

    override fun update(angle: Float) {
        Log.d(TAG, "Updated NeedleView | Angle = $angle")
        var a = angle
        if (a < 0)
            a += 360
        if (isNewAngle(a) && !(this.angle - a > 345 || this.angle - a < -345))
            this.startAnimation(rotateAnimation(a))
            this.angle = a
    }

    private fun isNewAngle(angle: Float) = !this.angle.equals(angle)

    private fun rotateAnimation(angle: Float) : RotateAnimation {
        val rotate = RotateAnimation(
            this.angle, angle,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = ANIMATION_DURATION_TIME
        rotate.interpolator = LinearInterpolator()
        rotate.fillAfter = true
        return rotate
    }
}