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
            val scene2 = Scene.getSceneForLayout(rootView, com.lycon.common.R.layout.scene2, this)
            TransitionManager.go(scene2, ChangeBounds())
        }
        addTarget.setOnClickListener {
            val scene2 = Scene.getSceneForLayout(rootView, com.lycon.common.R.layout.scene2, this)
            val changeBounds = ChangeBounds()
            changeBounds.addTarget(com.lycon.common.R.id.image1)
            TransitionManager.go(scene2, changeBounds)
        }
        image1.setOnClickListener {
            //            val bundle = ActivityOptionsComgitpat.makeSceneTransitionAnimation(this@TransitionActivity,androidx.core.util.Pair(image1, "shareElement")).toBundle()
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@TransitionActivity,
                image1,
                "shareElement"
            ).toBundle()
            val intent = Intent(this@TransitionActivity, TransitionImageActivity::class.java)
            startActivity(intent, bundle)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAfterTransition(this)
    }
}