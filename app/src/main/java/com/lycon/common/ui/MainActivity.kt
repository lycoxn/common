package com.lycon.common.ui

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lycon.common.adapter.MainAdapter
import com.lycon.common.bean.Single
import com.lycon.common.network.NetBroadcastReceiver
import com.lycon.common.network.TestNull
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NetBroadcastReceiver.NetStatusMonitor {

    private var isLive = true
    var i = 1
    var single: Single? = Single("1212")
    protected var netBroadcastReceiver: NetBroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lycon.common.R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this)
        val activities =
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities
        val adapter = MainAdapter(this, activities)
        recycler.adapter = adapter
        bt.setOnClickListener {
            val testNull: TestNull? = null
            testNull?.testNull(this)
//            startActivity(Intent(this, WebViewActivity::class.java))
//            startActivityForResult(
//                Intent(
//                    this@MainActivity,
//                    BackResultActivity::class.java
//                ), 1000
//            )
        }
        registerBroadcastReceiver()

        single?.let {
            Toast.makeText(this, "111", Toast.LENGTH_SHORT).show()
        } ?: let {
            Toast.makeText(this, "222", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 注册网络状态广播
     */
    private fun registerBroadcastReceiver() { //注册广播
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = NetBroadcastReceiver()
            val filter = IntentFilter()
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            registerReceiver(netBroadcastReceiver, filter)
            //设置监听
            netBroadcastReceiver!!.setStatusMonitor(this)
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

    override fun onNetChange(netStatus: Boolean) {
        startActivity(Intent(this, SlideBottomActivity::class.java).putExtra("tag", i++))
    }
}
