package com.webmyne.modak.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import com.webmyne.modak.R
import com.webmyne.modak.helper.PrefUtils
import kotlinx.android.synthetic.main.item_chef_reviews.view.*

class ChefReviewsAdapter(
    val context: Context
) : RecyclerView.Adapter<ChefReviewsAdapter.ViewVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewVH {
        return ViewVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_chef_reviews,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewVH, position: Int) {
        PrefUtils.setReviewsCount(context, 10)
        holder.itemView.Userrating.onRatingBarChangeListener =
            object : RatingBar.OnRatingBarChangeListener {
                override fun onRatingChanged(
                    ratingBar: RatingBar?,
                    rating: Float,
                    fromUser: Boolean
                ) {

                }

            }

    }

    override fun getItemCount(): Int {
        return 10
    }


    class ViewVH(view: View) : RecyclerView.ViewHolder(view)
}
