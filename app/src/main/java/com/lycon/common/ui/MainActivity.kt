package com.lycon.common.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lycon.common.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var isLive = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lycon.common.R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this)
        val activities =
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities
        val adapter = MainAdapter(this, activities)
        recycler.adapter = adapter
        bt.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
//            startActivityForResult(
//                Intent(
//                    this@MainActivity,
//                    BackResultActivity::class.java
//                ), 1000
//            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(
            this,
            "requestCode: $requestCode  -- resultCode: $resultCode  ",
            Toast.LENGTH_SHORT
        ).show()
    }
}
