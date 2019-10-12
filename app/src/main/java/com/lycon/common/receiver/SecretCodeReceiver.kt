package com.lycon.common.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony.Sms.Intents.SECRET_CODE_ACTION
import com.lycon.common.ui.MainActivity

/**
 * Created by liuyicen on 2019-08-20 14:46.
 */
class SecretCodeReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p1?.let {
            if (SECRET_CODE_ACTION == p1.action) {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.setClass(p0!!, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                p0.startActivity(intent)
            }
        }
    }
}