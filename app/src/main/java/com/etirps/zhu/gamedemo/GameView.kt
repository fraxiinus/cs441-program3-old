package com.etirps.zhu.gamedemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), Runnable {

    @Volatile var playing : Boolean = false

    private var gameThread : Thread? = null

    private var rocks = mutableListOf<Rock>()

    private var rockBitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.rock1)

    override fun run() {

        val rock1 = Rock(0.0f, 500.0f, 300.0f)
        rock1.image = rockBitmap.resize(rock1.size, rock1.size)
        rock1.speedX = 5.0f
        rock1.speedY = 5.0f
        //rock1.rotation = 1.0f

        rocks.add(rock1)

        while(playing) {
            update()
            draw()
            control()
        }
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun update() {
        for (r: Rock in rocks) {
            r.update()
        }
    }

    private fun draw() {
        if(holder.surface.isValid) {
            val canvas =  holder.surface.lockCanvas(null)
            canvas.drawColor(Color.BLACK)

            val paint = Paint()
            paint.color = Color.WHITE
            paint.style = Paint.Style.FILL

            for (r: Rock in rocks) {
                if(r.image != null) {
                    val img = r.image as Bitmap
                    canvas.drawBitmap(img, r.posX, r.posY, paint)
                }
            }

            holder.surface.unlockCanvasAndPost(canvas)
        }
    }

    private fun control() {
        try {
            Thread.sleep(16)
        } catch (e:InterruptedException) {
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