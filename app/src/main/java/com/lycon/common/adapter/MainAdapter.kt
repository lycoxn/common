package com.lycon.common.adapter

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.lycon.common.ui.MainActivity
import kotlinx.android.synthetic.main.item_main.view.*


/**
 * Created by liuyicen on 2019-08-19 17:40.
 */
class MainAdapter(private var context: Context, private var list: Array<ActivityInfo>) :
    RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v =
            LayoutInflater.from(context).inflate(com.lycon.common.R.layout.item_main, parent, false)
        return MainViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.tv.text = list[position].name
        holder.itemView.tv.setOnClickListener {
            //            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context as MainActivity)
//            val intent = Intent(context, Class.forName(list[position].name))
//            context.startActivity(intent, activityOptionsCompat.toBundle())
            context.startActivity(Intent(context, Class.forName(list[position].name)))
        }
    }

}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
//        val tv = itemView.findViewById<Button>(R.id.tv)
    }
}