package com.lycon.common.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.lycon.common.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by liuyicen on 2019-10-10 10:06.
 */
class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ll.background = ContextCompat.getDrawable(context!!,R.mipmap.bg1)
    }
}