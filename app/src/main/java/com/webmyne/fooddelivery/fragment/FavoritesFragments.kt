package com.webmyne.fooddelivery.fragment

import android.annotation.SuppressLint
import android.app.ListActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.adapter.HomeAdapter
import com.webmyne.fooddelivery.model.HomeDetails
import com.webmyne.fooddelivery.ui.AboutChefActivity
import com.webmyne.fooddelivery.ui.BaseActivity
import com.webmyne.fooddelivery.ui.BasketActivity
import com.webmyne.fooddelivery.ui.ChefDetailsActivity
import kotlinx.android.synthetic.main.home_item_list.*
import kotlinx.android.synthetic.main.home_recycler.*

@SuppressLint("ValidFragment")
class FavoritesFragments(baseActivity: BaseActivity) :BaseFragment(){
    private val ItemDetails = listOf(
        HomeDetails("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?cs=srgb&dl=beach-exotic-holiday-248797.jpg&fm=jpg","Donut","powa"),
        HomeDetails("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?cs=srgb&dl=beach-exotic-holiday-248797.jpg&fm=jpg","Donut","powa"),
        HomeDetails("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?cs=srgb&dl=beach-exotic-holiday-248797.jpg&fm=jpg","Donut","powa"),
        HomeDetails("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?cs=srgb&dl=beach-exotic-holiday-248797.jpg&fm=jpg","Donut","powa"),
        HomeDetails("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?cs=srgb&dl=beach-exotic-holiday-248797.jpg&fm=jpg","Donut","powa"),
        HomeDetails("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?cs=srgb&dl=beach-exotic-holiday-248797.jpg&fm=jpg","Donut","powa"),
        HomeDetails("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?cs=srgb&dl=beach-exotic-holiday-248797.jpg&fm=jpg","Donut","powa")
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_item_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val img = imgHomeItemImage
        var CountItem = 0
        val countMinus = imgHomeItemLeftArrow
        val countPlus = imgHomeItemRightArrow
        val Count=txtHomeNumberOfItem
        val chefImage=imgHomeChefImage
        chefImage.setOnClickListener {
            val intent = Intent(activity, BasketActivity::class.java)
            startActivity(intent)
        }
        countMinus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (CountItem > 0) {
                    CountItem--

                    Count.text = "$CountItem"
                }
            }

        })
        countPlus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CountItem++
                Count.text = "$CountItem"
            }

        })
        img.setOnClickListener {
            val intent = Intent(activity, ChefDetailsActivity::class.java)
            startActivity(intent)
        }

    }
    companion object{
        fun getInstance(baseActivity: BaseActivity): FavoritesFragments {
            return FavoritesFragments(baseActivity)
        }
    }
}
