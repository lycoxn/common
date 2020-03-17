package com.lycon.common.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

/**
 * Created by liuyicen on 2020-01-08 15:13.
 */
class BatteryStatusReceiver(private val tv: TextView) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val current: Int = intent?.extras?.getInt("level")!!
        val scale: Int = intent?.extras?.getInt("scale")!!
        val percent = current * 100 / scale
        tv.text = "level: $current scale:$scale 百分比: $percent%"
    }

}