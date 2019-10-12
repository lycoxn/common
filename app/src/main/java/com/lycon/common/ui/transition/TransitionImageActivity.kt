package com.lycon.common.ui.transition

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.transition.*
import androidx.appcompat.app.AppCompatActivity
import com.lycon.common.R
import kotlinx.android.synthetic.main.activity_transition_image.*

/**
 * Created by liuyicen on 2019-10-11 15:24.
 */
class TransitionImageActivity : AppCompatActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_image)
        window.sharedElementsUseOverlay = true
        window.sharedElementEnterTransition = when (intent.getIntExtra("tag", 0)) {
            1 -> ChangeBounds()
            2 -> ChangeTransform()
            3 -> ChangeClipBounds()
            4 -> ChangeImageTransform()
            else -> null
        }
//        changeBounds.setOnClickListener {
//            window.sharedElementEnterTransition = ChangeBounds()
//        }
//        changeTransform.setOnClickListener {
//            window.sharedElementEnterTransition = ChangeTransform()
//        }
//        changeClipBounds.setOnClickListener {
//            window.sharedElementEnterTransition = ChangeClipBounds()
//        }
//        changeImageTransform.setOnClickListener {
//            window.sharedElementEnterTransition = ChangeImageTransform()
//        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun initSharedElementEnterTransition(): Transition {
        val sharedTransition =
            TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform)
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