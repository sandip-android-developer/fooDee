package com.webmyne.modak.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.webmyne.modak.R
import com.webmyne.modak.model.Item_Details
import kotlinx.android.synthetic.main.item_select_order.view.*

class SelectItemAdapter(
    val context: Context,
    var itemDetailsList: ArrayList<Item_Details>
) : RecyclerView.Adapter<SelectItemAdapter.AllUserVH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectItemAdapter.AllUserVH {
        return SelectItemAdapter.AllUserVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_select_order,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemDetailsList.size

    }

    override fun onBindViewHolder(holder: AllUserVH, position: Int) {
        holder.itemView.txtItemTotalPrice_Review.text=(itemDetailsList.get(position).itemCount.toDouble()*itemDetailsList.get(position).ItemPrice.toDouble()).toString()
        holder.itemView.txtItemQty_Review.text=itemDetailsList.get(position).itemCount.toString()
        holder.itemView.txtItemPrice_Review.text="$"+itemDetailsList.get(position).ItemPrice.toString()
        holder.itemView.txtItemName_Review.text=itemDetailsList.get(position).ItemName.toString()

        Glide.with(context)
            .load(itemDetailsList.get(position).ItemImage)
            .into(holder.itemView.ImgItem_Review)


    }

    class AllUserVH(view: View) : RecyclerView.ViewHolder(view)
}