package com.webmyne.modak.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.Item_Details
import com.webmyne.modak.model.ResponsPojo.User_Favorite
import com.webmyne.modak.ui.AboutChefDetailsActivity
import com.webmyne.modak.ui.BaseActivity
import com.webmyne.modak.ui.ItemDetailsActivity
import kotlinx.android.synthetic.main.home_item_list.view.*
import java.util.*


class FavoriteAdapter(
    val context: BaseActivity,
    val item: ArrayList<Item_Details>,
    var onItemCount: onitemCount
) : RecyclerView.Adapter<FavoriteAdapter.ViewVH>() {
    var CItem: Int = 0
    var Count: Int = 0
    var basePriceItem: Double = 0.00
    val db: DatabaseHelper = DatabaseHelper(context)
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewVH {
        return ViewVH(
            LayoutInflater.from(context).inflate(
                com.webmyne.modak.R.layout.home_item_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewVH, position: Int) {
        var CountItem: Int = 0
        Count = PrefUtils.getAddItemCount(context)

        //onItemCount.onitemcount(Count)
        holder.itemView.imgHomeChefImage.setOnClickListener {
            AboutChefDetailsActivity.launchActivity(context, item.get(position))
        }
        holder.itemView.imgItem_Home.setOnClickListener {
            ItemDetailsActivity.launchActivity(context, CountItem, item.get(position))
            // OTPActivity.launchActivity(context,12344565767.toString())

        }
        Glide.with(context)
            .load(item.get(position).ItemImage)
            .into(holder.imgHomeItemImage_fav)

        Glide.with(context)
            .load(item.get(position).ChefImage)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imgHomeChefImage)

        holder.txtHomeChefName.text = item.get(position).ChefName
        holder.txtHomeItemDistabace.text = item.get(position).itemDistance + " Mile away"
        holder.txtHomeItemPrice.text = "$" + item.get(position).ItemPrice
        holder.txtHomeItemName.text = item.get(position).ItemName
        holder.itemView.txtHomeNumberOfItem.text = item.get(position).itemCount.toString()
        holder.txtHomeItemFamousPlaceName.text = item.get(position).ChefCuisineTye
        basePriceItem = item.get(position).ItemPrice.toDouble()
        CountItem = db.getItemCountByItemID(item.get(position).itemId)
        if (CountItem > 0) {
            var price = basePriceItem * CountItem
            holder.itemView.txtHomeItemPrice.text = "$" + "$price"
        }
        if (db.CheckFaviriteIdExist(item.get(position).itemId)) {
            holder.itemView.imgFavorite.setImageResource(com.webmyne.modak.R.drawable.ic_favorite)
        } else {
            holder.itemView.imgFavorite.setImageResource(com.webmyne.modak.R.drawable.ic_not_favorite)
        }

        holder.itemView.imgHomeItemLeftArrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CountItem = db.getItemCountByItemID(item.get(position).itemId)
                if (CountItem > 0) {

                    CountItem--
                    Count--
                    if (CountItem > 0) {
                        basePriceItem = item.get(position).ItemPrice.toDouble()
                        var price = basePriceItem * CountItem
                        holder.itemView.txtHomeItemPrice.text = "$" + "$price"
                    }
                    holder.itemView.txtHomeNumberOfItem.text = "$CountItem"
                    var itemDetails: Item_Details = Item_Details()
                    itemDetails.itemId = item.get(position).itemId
                    itemDetails.itemCount = CountItem
                    db.updateItemCountByItemId(itemDetails)
                    onItemCount.onitemcount()
                }


            }

        })

        holder.itemView.imgHomeItemRightArrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CountItem = db.getItemCountByItemID(item.get(position).itemId)
                CountItem++
                Count++
                basePriceItem = item.get(position).ItemPrice.toDouble()
                holder.itemView.txtHomeNumberOfItem.text = "$CountItem"
                var price = basePriceItem * CountItem
                holder.itemView.txtHomeItemPrice.text = "$" + "$price"
                var itemDetails: Item_Details = Item_Details()
                itemDetails.itemId = item.get(position).itemId
                itemDetails.itemCount = CountItem
                db.updateItemCountByItemId(itemDetails)
                onItemCount.onitemcount()
            }

        })
        holder.itemView.imgItemAdd_Home.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CountItem = db.getItemCountByItemID(item.get(position).itemId)
                CountItem++
                Count++
                basePriceItem = item.get(position).ItemPrice.toDouble()
                holder.itemView.txtHomeNumberOfItem.text = "$CountItem"
                var price = basePriceItem * CountItem
                holder.itemView.txtHomeItemPrice.text = "$" + "$price"
                // Count="$CountItem".toInt()
                var itemDetails: Item_Details = Item_Details()
                itemDetails.itemId = item.get(position).itemId
                itemDetails.itemCount = CountItem
                db.updateItemCountByItemId(itemDetails)
                onItemCount.onitemcount()

            }

        })
        holder.itemView.imgFavorite.setOnClickListener {
            if (db.CheckFaviriteIdExist(item.get(position).itemId)) {
                holder.itemView.imgFavorite.setImageResource(com.webmyne.modak.R.drawable.ic_not_favorite)

                db.deleteUserFavoriteItem(item.get(position).itemId)
            } else {
                var userFavorite: User_Favorite = User_Favorite()
                userFavorite.FavoriteUseruserId = PrefUtils.getUserID(context).toInt()
                userFavorite.FavoriteItemId = item.get(position).itemId
                userFavorite.FavoriteItemImage = item.get(position).ItemImage
                db.InsertUserFavorite(userFavorite)
                holder.itemView.imgFavorite.setImageResource(com.webmyne.modak.R.drawable.ic_favorite)

            }

        }

    }

    override fun getItemCount(): Int {
        return item.size

    }

    class ViewVH(view: View) : RecyclerView.ViewHolder(view) {
        var imgHomeItemImage_fav = view.imgItem_Home
        var imgHomeChefImage = view.imgHomeChefImage
        var txtHomeChefName = view.txtHomeChefName
        var txtHomeItemDistabace = view.txtHomeItemDistabace
        var txtHomeItemPrice = view.txtHomeItemPrice
        var txtHomeItemName = view.txtHomeItemName
        var txtHomeItemFamousPlaceName = view.txtHomeItemFamousPlaceName
    }

    interface onitemCount {
        fun onitemcount()
    }
}
