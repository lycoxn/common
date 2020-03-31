package com.lycon.common.ui.audio

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyTTS private constructor(context: Context) : UtteranceProgressListener() {
    private lateinit var tts: TextToSpeech
    var isSupportCN = true
    override fun onStart(utteranceId: String) {
        Log.d("xulc", "onStart---utteranceId--->$utteranceId")
    }

    override fun onDone(utteranceId: String) {
        Log.d("xulc", "onDone---utteranceId--->$utteranceId")
    }

    override fun onError(utteranceId: String) {
        Log.d("xulc", "onError---utteranceId--->$utteranceId")
    }

    companion object {
        private var instance: MyTTS? = null
        fun getInstance(context: Context): MyTTS? {
            if (instance == null) {
                synchronized(
                    MyTTS::class.java
                ) {
                    instance =
                        MyTTS(context.applicationContext)
                }
            }
            return instance
        }
    }

    init {
        tts = TextToSpeech(context, OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.CHINA)
                tts.setPitch(1.0f)
                tts.setSpeechRate(1.0f)
                tts.setOnUtteranceProgressListener(this@MyTTS)
                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                    isSupportCN = false
                    Toast.makeText(context, "抱歉，不支持中文播放", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}