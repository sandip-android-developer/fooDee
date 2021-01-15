package com.webmyne.modak.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.webmyne.modak.R
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.item_notifation.view.*

class NotificationAdapter(
    val context: BaseActivity
): RecyclerView.Adapter<NotificationAdapter.AllUserVH>() {
    var rowIndex:Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUserVH {
        return AllUserVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_notifation,
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder:AllUserVH, position: Int) {
        Glide.with(context)
            .load(R.drawable.imgwomen)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.itemView.imgHomeChefImage)
    }
    override fun getItemCount(): Int {
        return 10

    }
    class AllUserVH(view: View): RecyclerView.ViewHolder(view)
}