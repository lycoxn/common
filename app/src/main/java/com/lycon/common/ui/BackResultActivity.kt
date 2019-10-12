package com.lycon.common.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R
import kotlinx.android.synthetic.main.activity_back_result.*

/**
 * Created by liuyicen on 2019-09-11 11:42.
 */
class BackResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_result)
        bt.setOnClickListener {
            setResult(100)
            finish()
        }
    }
}