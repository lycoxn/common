package com.lycon.common.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created by liuyicen on 2019-11-04 15:57.
 */
object BindUtil {

    @JvmStatic
    @BindingAdapter("testText")
    fun setTestText(tv: TextView, s: String) {
        tv.text = s
    }

//        @set:BindingAdapter("app:testText")
//        var TextView.testText
//            get() = text
//            set(value) {
//                text = value
//            }
}