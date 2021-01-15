package com.webmyne.fooddelivery.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.bumptech.glide.Glide
import com.webmyne.fooddelivery.R

import com.webmyne.fooddelivery.model.HomeDetails
import kotlinx.android.synthetic.main.home_item_list.view.*


class HomeAdapter(private val homeDetails: List<HomeDetails>,val context: Context):RecyclerView.Adapter<HomeAdapter.HomeVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HomeVH {
        return HomeVH(LayoutInflater.from(context).inflate(R.layout.home_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.txtHomeChefName.text = homeDetails.get(position).toString()
        holder.txtHomeItemDistabace.text = homeDetails.get(position).toString()
        holder.txtHomeItemPrice.text = homeDetails.get(position).toString()
        holder.txtHomeItemName.text = homeDetails.get(position).toString()
        holder.txtHomeItemFamousPlaceName.text = homeDetails.get(position).toString()
        Glide.with(context)
            .load(homeDetails.get(position).ItemImage)
            .into(holder.imgHomeItemImage)
    }

    class HomeVH (view: View) : RecyclerView.ViewHolder(view){
        val imgHomeItemImage = view.imgHomeItemImage
        val imgHomeChefImage = view.imgHomeChefImage
        val txtHomeChefName = view.txtHomeChefName
        val txtHomeItemDistabace = view.txtHomeItemDistabace
        val txtHomeItemPrice = view.txtHomeItemPrice
        val txtHomeItemName = view.txtHomeItemName
        val txtHomeItemFamousPlaceName=view.txtHomeItemFamousPlaceName

    }

    override fun getItemCount(): Int {
        return homeDetails.size

    }

}
