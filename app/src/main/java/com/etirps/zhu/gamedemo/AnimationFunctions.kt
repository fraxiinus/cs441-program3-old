package com.etirps.zhu.gamedemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Bitmap
import android.graphics.Matrix
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

/**
 * Blinks view [times] about of times, each blink taking [duration] ms, with a starting delay of [offset] ms.
 * [minAlpha] sets the lower bound and [maxAlpha] sets the upper bound.
 * [repeatMode] defines how to act when the blink reaches the end
 */
fun View.blink( times: Int = Animation.INFINITE,
                duration: Long = 50L,
                offset: Long = 20L,
                minAlpha: Float = 0.0f,
                maxAlpha: Float = 1.0f,
                repeatMode: Int = Animation.REVERSE ) {

    startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
        it.duration = duration
        it.startOffset = offset
        it.repeatMode = repeatMode
        it.repeatCount = times
    })
}

/**
 * Animates alpha change of a view to [toAlpha], taking [duration] ms about of time after [offset] ms delay,
 * and setting view visibility to [setVisibility] at the end of the animation.
 */
fun View.animateAlpha(duration: Long = 500L,
                      toAlpha: Float = 0.0f,
                      offset: Long = 0L,
                      setVisibility: Int = View.GONE) {
    animate()
        .setDuration(duration)
        .alpha(toAlpha)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = setVisibility
            }
        })
        .startDelay = offset
}

fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun Bitmap.resize(x: Float, y: Float): Bitmap {
    val scaleWidth : Float = x / width
    val scaleHeight : Float= y / height
    val matrix = Matrix().apply { postScale(scaleWidth, scaleHeight) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}