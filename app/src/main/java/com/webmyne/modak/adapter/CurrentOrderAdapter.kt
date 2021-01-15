package com.webmyne.modak.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.modak.R
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.item_current_order.view.*

class CurrentOrderAdapter(
    var context: BaseActivity
) : RecyclerView.Adapter<CurrentOrderAdapter.ViewVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int):ViewVH {
        return ViewVH(LayoutInflater.from(context).inflate(R.layout.item_current_order, parent, false))
    }
    override fun onBindViewHolder(holder: ViewVH, position: Int) {
       /* holder.itemView.btnCancel.setOnClickListener {
            ReviewOrderActivity.launchActivity(context!!)
        }*/
        holder.itemView.btnCancel.setBackgroundResource(R.drawable.btn_cancel)
        holder.itemView.txtStatus.text = "Pending"
        holder.itemView.txtStatus.setTextColor(context.resources.getColor(R.color.colorsplacebackground))
    }

    override fun getItemCount(): Int {
     return 10
    }


    class ViewVH (view: View) : RecyclerView.ViewHolder(view)
}
