package com.lycon.common.ui.transition

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.Transition
import android.transition.TransitionInflater
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R

/**
 * Created by liuyicen on 2019-10-11 15:24.
 */
class TransitionImageActivity :AppCompatActivity(){
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_image)
        window.sharedElementEnterTransition = ChangeBounds()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun initSharedElementEnterTransition(): Transition {
        val sharedTransition = TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform)
        sharedTransition.addListener(object : Transition.TransitionListener {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onTransitionStart(transition: Transition) {
//                val circularReveal = ViewAnimationUtils.createCircularReveal(
//                    image,
//                    image.width / 2,
//                    image.height / 2,
//                    image.width / 2f,
//                    Math.max(image.width, image.height).toFloat()
//                )
////                image_bg.setBackgroundColor(Color.BLACK)
//                circularReveal.duration = 600
//                circularReveal.start()
            }

            override fun onTransitionEnd(transition: Transition) {
                sharedTransition.removeListener(this)
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
        return sharedTransition
    }

}