package com.webmyne.modak.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.modak.R
import com.webmyne.modak.adapter.FavoriteAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.model.HomeDetails
import com.webmyne.modak.model.Item_Details
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.favorite_fragment.*

@SuppressLint("ValidFragment")
class DeliveryFragment(var activity: BaseActivity) : BaseFragment() {
    var item: List<HomeDetails> = ArrayList()
    var ItemCount: Int = 0
    val db: DatabaseHelper = DatabaseHelper(activity)

    companion object {
        fun getInstance(baseActivity: BaseActivity): FavoritesFragments {
            return FavoritesFragments(baseActivity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  txtitemCount.text=ItemCount.toString()
        initview()
        actionListner()

    }

    private fun actionListner() {
    }

    private fun initview() {

        var ItemDetailsDetails = ArrayList<Item_Details>()
        if (db.getAllItemDetails().size > 0) {
            ItemDetailsDetails.addAll(db.getAllItemDetails())
        }
        /* var item = HomeDetails()
         rvFavorite.layoutManager = LinearLayoutManager(context)
         for (i in 0 until 5)
         {
             item.ItemImage="https://gotaskme-root.s3.amazonaws.com/USA/120/PROFILE/user_120.png"
             homeDetails.add(item)
         }*/

        rvFavorite.layoutManager = LinearLayoutManager(context)
        val adapter =
            FavoriteAdapter(activity, ItemDetailsDetails, object : FavoriteAdapter.onitemCount {
                override fun onitemcount() {
                    /*PrefUtils.setAddItemCount(activity, count)
                    if (PrefUtils.getAddItemCount(activity) == 0) {
                        activity.textAddItemCount.visibility = View.GONE
                    } else {
                        activity.textAddItemCount.text = "$count"
                    }*/
                }

            })
        rvFavorite.adapter = adapter

    }
}