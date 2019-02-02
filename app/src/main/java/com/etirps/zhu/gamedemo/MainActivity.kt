package com.etirps.zhu.gamedemo

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.Shape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import top.defaults.drawabletoolbox.DrawableBuilder

class MainActivity : AppCompatActivity() {

    private var mTitleVisible : Boolean = true
    private var mAnimationDuration : Long = 0
    private var mInitialX : Float = 0.0f
    private var mInitialY : Float = 0.0f
    private var mInputLine : DrawableBuilder = DrawableBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInputLine.line()

        mAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    }

    override fun onBackPressed() {
        // if Title is gone, reappear title
        if(!mTitleVisible) {
            copyrightTitleText.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
            gameTitleButton.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
            gameTitleText.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x : Float = event.x
        val y : Float = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mInitialX = x
                mInitialY = y
            }

            MotionEvent.ACTION_MOVE -> {
                var dx : Float = x - mInitialX
                var dy : Float = y - mInitialY
            }
        }

        copyrightTitleText.text = "$x by $y"

        val what = DrawableBuilder()
            .oval()
            .solidColor(Color.TRANSPARENT)
            .strokeColor(Color.WHITE)
            .strokeWidth(5)
            .size(40)
            .build()

        val img = ImageView(this)

        img.maxHeight = 40
        img.maxWidth = 40
        img.x = x - 20
        img.y = y - 100
        img.setImageDrawable(what)
        img.setBackgroundColor(Color.GRAY)

        main_layout.addView(img)

        return true
    }

    fun startButtonPress(view: View) {
        // blink button
        gameTitleButton.blink(times = 10, offset = 0, duration = 50)
        // Disappear title
        copyrightTitleText.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)
        gameTitleButton.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)
        gameTitleText.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)

        mTitleVisible = false
    }

}
