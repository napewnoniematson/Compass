package com.napewnoniematson.compass.view.widget.compass

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.napewnoniematson.compass.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.compass_view.view.*

class DestinationPointView(context: Context, attrs: AttributeSet) :
    AppCompatImageView(context, attrs), CompassElement {

    private val TAG: String? = DestinationPointView::class.simpleName
    private var angle = 0f

    init {
        setImageResource(R.drawable.destination)
//        visibility = View.INVISIBLE
    }

    override fun update(angle: Float) {
//        if (isNewAngle(angle))
//        destinationImageView.startAnimation(rotateAnimation(angle))
        this.startAnimation(rotateAnimation(360f))
        this.angle = 0f
//        this.angle = angle
    }

    private fun isNewAngle(angle: Float) = !this.angle.equals(angle)

    private fun rotateAnimation(angle: Float) : RotateAnimation {
        val rotate = RotateAnimation(
            this.angle, angle,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_PARENT, 0.5f
        )
        rotate.duration = 10000
        rotate.fillAfter = true
        Log.d(TAG, "Pivot x: $pivotX | y: $pivotY")

        val px = (parent as ConstraintLayout).pivotX
        val py = (parent as ConstraintLayout).pivotY
        Log.d(TAG, "Pivot parent x: $px | y: $py")
        return rotate
    }
}