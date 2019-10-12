package com.lycon.common.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lycon.common.R
import kotlinx.android.synthetic.main.activity_main_fragment.*

/**
 * Created by liuyicen on 2019-10-10 09:52.
 */
class MainFragmentActivity : AppCompatActivity() {
    private var lastFragment: Int = 0
    private lateinit var fragments: ArrayList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)
        setHalfTransparent()
        val one = OneFragment()
        val second = SecondFragment()
        val third = ThirdFragment()
        fragments = arrayListOf(one, second, third)
        supportFragmentManager.beginTransaction().add(R.id.mainFrame, one).show(one).commit()
        bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.one -> {
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0)
                        lastFragment = 0
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.second -> {
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1)
                        lastFragment = 1
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.third -> {
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2)
                        lastFragment = 2
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun switchFragment(lastFragment: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        //隐藏上个Fragment
        transaction.hide(fragments[lastFragment])
        if (!fragments[index].isAdded) {
            transaction.add(R.id.mainFrame, fragments[index])
        }
        transaction.show(fragments[index]).commitAllowingStateLoss()
    }

    /**
     * 半透明状态栏
     */
    private fun setHalfTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            val decorView = window.decorView
            val option = SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //虚拟键盘也透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}