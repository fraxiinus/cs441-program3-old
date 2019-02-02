package com.etirps.zhu.gamedemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    override fun onPause() {
        if(!mTitleVisible) {
            copyrightTitleText.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
            gameTitleButton.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
            gameTitleText.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
        }
        super.onPause()
    }

    fun startButtonPress(view: View) {
        // blink button
        gameTitleButton.blink(times = 10, offset = 0, duration = 50)
        // Disappear title
        copyrightTitleText.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)
        gameTitleButton.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)

        gameTitleText.animate()
            .setDuration(mAnimationDuration)
            .alpha(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    gameTitleText.visibility = View.GONE
                    startGame()
                }
            })
            .startDelay = 500

        mTitleVisible = false

    }

    fun startGame() {
        startActivity(Intent(this, GameActivity::class.java))
    }
}
