package com.lycon.common.ui

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R
import com.lycon.common.receiver.BatteryStatusReceiver
import kotlinx.android.synthetic.main.activity_battery.*

/**
 * Created by liuyicen on 2020-01-08 15:12.
 */
class BatteryActivity : AppCompatActivity() {

    private var receiver: BatteryStatusReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery)
        bt.setOnClickListener { initReceiver() }
    }

    private fun initReceiver() {
        receiver = BatteryStatusReceiver(tv)
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        if (receiver != null)
            unregisterReceiver(receiver)
        super.onDestroy()
    }
}
