package com.webmyne.fooddelivery.adapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.model.SaveCardDetails
import com.webmyne.fooddelivery.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_card.view.*
import kotlinx.android.synthetic.main.activity_savecard.view.*
/*
class CardAdapter :RecyclerView.Adapter<CardAdapter.cardVH>(){
    /*private val details = arrayOf("**** **** **** 2132", "**** **** **** 3245",
        "**** **** **** 1234")8*/

    private val images = intArrayOf(R.drawable.visa_card,
        R.drawable.visa_card, R.drawable.cash)
    override fun onBindViewHolder(holder: cardVH, i: Int) {
      /*  holder.itemTitle.text = details[i]
        holder.itemImage.setImageResource(images[i])*/

    }

    override fun getItemCount(): Int {
       return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): cardVH {
        return cardVH(LayoutInflater.from(parent.context).inflate(R.layout.activity_card, parent, false))
    }

    class cardVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            itemImage = itemView.findViewById(R.id.imgCardType)
            itemTitle = itemView.findViewById(R.id.txtCardNumber)
        }
    }

}
*/

