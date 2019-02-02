package com.etirps.zhu.gamedemo

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), Runnable {

    @Volatile var playing : Boolean = false

    private var gameThread : Thread? = null

    override fun run() {
        while(playing) {
            draw()
        }
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun update() {

    }

    private fun draw() {
        if(holder.surface.isValid) {
            val canvas =  holder.surface.lockCanvas(null)
            val paint = Paint()
            paint.color = Color.WHITE
            paint.style = Paint.Style.FILL
            canvas.drawCircle(500.0f, 500.0f, 100.0f, paint)

            holder.surface.unlockCanvasAndPost(canvas)
        }
    }

    fun pause() {
        playing = false
        try {
            gameThread?.join()
        } catch (e:InterruptedException) {

        }
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }
}