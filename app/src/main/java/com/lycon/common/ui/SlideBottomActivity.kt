package com.lycon.common.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R
import kotlinx.android.synthetic.main.activity_slide_bottom.*

/**
 * Created by liuyicen on 2019-08-20 09:49.
 */
class SlideBottomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_bottom)
        tag.setText(intent.getIntExtra("tag", 0).toString())
    }
}