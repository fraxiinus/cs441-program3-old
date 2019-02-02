package com.etirps.zhu.gamedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mTitleVisible : Boolean = true
    private var mAnimationDuration : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    }

    override fun onBackPressed() {
        copyrightTitleText.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
        gameTitleButton.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
        gameTitleText.animateAlpha(duration = mAnimationDuration, toAlpha = 1.0f, setVisibility = View.VISIBLE)
    }

    fun startButtonPress(view: View) {
        // blink button
        gameTitleButton.blink(times = 10, offset = 0, duration = 50)
        // Disappear title
        copyrightTitleText.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)
        gameTitleButton.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)
        gameTitleText.animateAlpha(duration = mAnimationDuration, offset = 500, setVisibility = View.GONE)
    }

}
