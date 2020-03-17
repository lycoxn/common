package com.lycon.common

import android.app.Application
import android.widget.Toast
import com.lycon.common.util.HookUtils

/**
 * Created by liuyicen on 2019-08-20 15:12.
 */
class CommonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        HookUtils().hookStartActivity(this)
    }
}