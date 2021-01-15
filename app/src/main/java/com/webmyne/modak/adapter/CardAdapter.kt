package com.webmyne.modak.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.modak.R
import com.webmyne.modak.ui.BaseActivity
import com.webmyne.modak.ui.ReviewOrderActivity
import kotlinx.android.synthetic.main.item_card_details.view.*

class CardAdapter(val context: BaseActivity) : RecyclerView.Adapter<CardAdapter.cardVH>() {

    override fun onBindViewHolder(holder: cardVH, position: Int) {
        holder.itemView.setOnClickListener {
            ReviewOrderActivity.launchActivity(context)

        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): cardVH {
        return cardVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card_details,
                parent,
                false
            )
        )
    }

    class cardVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage = itemView.imgCardType
        var itemTitle = itemView.txtCardNumber
        var rbCheck = itemView.rbCheck

    }

}

