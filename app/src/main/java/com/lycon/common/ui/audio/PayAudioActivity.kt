package com.lycon.common.ui.audio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R
import com.yzy.voice.VoiceBuilder
import com.yzy.voice.VoicePlay
import kotlinx.android.synthetic.main.activity_pay_autio.*


/**
 * Created by liuyicen on 2020/3/30 10:45 PM.
 */
class PayAudioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_autio)
        bt.setOnClickListener {
            VoicePlay.with(this)
                .play(VoiceBuilder.Builder().start("店小友碰一碰收款").money("100").unit("元").builder());
        }
        val speech = ChineseToSpeech(applicationContext)
        bt1.setOnClickListener {
            //speech.speech("店.. 小.. 友.. 碰. 一. 碰. 收. 款. 100. 元")
            speech.speech("店小友碰一碰收款100000元")
//            speech.speech("")
//            speech.speech(null)
        }
    }


}