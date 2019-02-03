package com.etirps.zhu.gamedemo

import android.graphics.Bitmap
import android.graphics.Matrix
import android.widget.ImageView

class Rock (var posX: Float, var posY: Float, var size: Float) {

    //var posX : Float = 0.0f
    //var posY : Float = 0.0f
    var speedX : Float = 0.0f
    var speedY : Float = 0.0f
    var angle : Float = 0.0f
    var rotation : Float = 0.0f

    //var size : Int = 0

    //var drawable : RotateDrawable = ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.rock_1, null) as RotateDrawable

    var image : Bitmap? = null
    //var image : ImageView? = null
    var listener : Rock? = null

    constructor(src: Rock): this(src.posX, src.posY, src.size) {
        speedX = src.speedX
        speedY = src.speedY
        angle = src.angle
        rotation = src.rotation
        //drawable = src.drawable

        image = src.image
    }

    fun update() {
        posX += speedX
        posY += speedY

        //drawable.level = drawable.level + angle
        //image = image?.rotate(angle + rotation)
    }
}