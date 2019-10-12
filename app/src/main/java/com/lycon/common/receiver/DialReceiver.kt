package com.lycon.common.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.lycon.common.ui.MainActivity


/**
 * Created by liuyicen on 2019-08-20 15:50.
 */
class DialReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        when (p1?.action) {
            Intent.ACTION_NEW_OUTGOING_CALL -> {
                //取得播出电话的号码
                val phoneNumber = p1?.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
                //匹配拨号指令
                if ("1000" == phoneNumber) {
                    //跳转到应用
                    val mIntent = Intent(p0, MainActivity::class.java)
                    mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    p0?.startActivity(mIntent)
                    // 掐断广播
                    resultData = null
                }
            }
        }
    }

}