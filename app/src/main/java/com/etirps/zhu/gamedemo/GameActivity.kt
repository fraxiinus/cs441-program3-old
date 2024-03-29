package com.etirps.zhu.gamedemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private var gameView : GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gameView = GameView(this)
        setContentView(gameView)
    }

    override fun onPause() {
        super.onPause()
        gameView?.pause()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onResume() {
        super.onResume()
        gameView?.resume()
    }
}