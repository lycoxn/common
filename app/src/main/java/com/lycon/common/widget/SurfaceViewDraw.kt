package com.lycon.common.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.lycon.common.R


/**
 * Created by liuyicen on 2020/3/17 5:35 PM.
 */
class SurfaceViewDraw @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {
    private var surfaceHolder: SurfaceHolder? = null
    private var paint: Paint? = null
    private var bitmap: Bitmap? = null

    init {
        surfaceHolder = holder
        surfaceHolder?.addCallback(this)
        paint = Paint()
        bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.bg2)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        val canvas = surfaceHolder?.lockCanvas()
        canvas?.drawBitmap(bitmap!!, Matrix(), paint)
        surfaceHolder?.unlockCanvasAndPost(canvas)
    }

}