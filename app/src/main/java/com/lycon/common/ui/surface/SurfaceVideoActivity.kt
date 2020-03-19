package com.lycon.common.ui.surface

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R
import kotlinx.android.synthetic.main.activity_surface_video.*
import java.io.File
import java.lang.Exception

/**
 * Created by liuyicen on 2020/3/18 10:47 AM.
 */
class SurfaceVideoActivity : AppCompatActivity(), SurfaceHolder.Callback,
    MediaPlayer.OnPreparedListener {

    private var path: String? = null
    private var mediaPlayer: MediaPlayer? = null
    private var pause = false
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface_video)
        nameText.text = "/DCIM/Camera/e928a20a46e17bb292c4173acd759619.mp4"
        initSurface()
        mediaPlayer = MediaPlayer()
    }

    private fun initSurface() {
        //把输送给surfaceView的视频画面，直接显示到屏幕上，不要维持它自身的缓冲区
//        surface.holder.setFixedSize(176, 144)
//        surface.holder.setFixedSize(300, 1000)
        surface.holder.setKeepScreenOn(true)
        surface.holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        if (mediaPlayer!!.isPlaying) {
            position = mediaPlayer!!.currentPosition
            mediaPlayer!!.stop()
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        if (position > 0 && path != null) {
            play(position)
            position = 0
        }
    }

    private fun play(position: Int) {
        try {
            mediaPlayer?.let {
                it.reset()
                it.setDataSource(path)
                it.setDisplay(surface.holder)
                it.prepare()
                it.setOnPreparedListener(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer?.start()
        if (position > 0) mediaPlayer?.seekTo(position)
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer == null
        super.onDestroy()
    }

    fun play(view: View) {
        val fileName = nameText.text.toString()
        if (fileName.startsWith("http")) {
            path = fileName
            play(0)
        } else {
            val file = File(Environment.getExternalStorageDirectory(), fileName)
            if (file.exists()) {
                path = file.absolutePath
                play(0)
            } else {
                path = null
                Toast.makeText(this, "路径是空", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun pause(view: View) {
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer!!.pause()
//            pause = true
        } else {
            mediaPlayer!!.start()
//            pause = false
//            if (pause) {
//                mediaPlayer!!.start()
//                pause = false
//            }
        }
    }

    fun reset(view: View) {
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer!!.seekTo(0)
        } else {
            if (path != null) {
                play(0)
            }
        }
    }

    fun stop(view: View) {
        if (mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
        }
    }
}