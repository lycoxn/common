package com.lycon.common.ui.transition

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.lycon.common.R
import kotlinx.android.synthetic.main.activity_transition.*
import kotlinx.android.synthetic.main.activity_transition_image.*
import kotlinx.android.synthetic.main.scene1.*

/**
 * Created by liuyicen on 2019-10-11 09:54.
 */
class TransitionActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)
        begin.setOnClickListener {
            val scene2 = Scene.getSceneForLayout(rootView, R.layout.scene2, this)
            TransitionManager.go(scene2, ChangeBounds())
        }
        addTarget.setOnClickListener {
            val scene2 = Scene.getSceneForLayout(rootView, R.layout.scene2, this)
            val changeBounds = ChangeBounds()
//            changeBounds.addTarget(R.id.image1)
//            changeBounds.addTarget(R.id.image2)
//            changeBounds.addTarget(R.id.image3)
            TransitionManager.go(scene2, changeBounds)
        }
        var tag = 0
        /**
         * ChangeBounds 捕获共享元素的layout bound，然后播放layout bound变化动画。ChangeBounds 是共享元素变换中用的最多的，因为前后两个activity中共享元素的大小和位置一般都是不同的。
         *
         * ChangeTransform -  捕获共享元素的缩放（scale）与旋转（rotation）属性 ，然后播放缩放（scale）与旋转（rotation）属性变化动画。
         *
         * ChangeClipBounds -  捕获共享元素clip bounds，然后播放clip bounds变化动画。
         *
         * ChangeImageTransform -  捕获共享元素（ImageView）的transform matrices 属性，然后播放ImageViewtransform matrices 属性变化动画。与ChangeBounds相结合，这个变换可以让ImageView在动画中高效实现大小，形状或者ImageView.ScaleType 属性平滑过度。
         */
        changeBounds.setOnClickListener { tag = 1 }
        changeTransform.setOnClickListener { tag = 2 }
        changeClipBounds.setOnClickListener { tag = 3 }
        changeImageTransform.setOnClickListener { tag = 4 }
        image1.setOnClickListener {
            //            val bundle = ActivityOptionsComgitpat.makeSceneTransitionAnimation(this@TransitionActivity,androidx.core.util.Pair(image1, "shareElement")).toBundle()
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@TransitionActivity,
                image1,
                "shareElement"
            ).toBundle()
            val intent = Intent(
                this@TransitionActivity,
                TransitionImageActivity::class.java
            ).putExtra("tag", tag)
            startActivity(intent, bundle)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAfterTransition(this)
    }
}