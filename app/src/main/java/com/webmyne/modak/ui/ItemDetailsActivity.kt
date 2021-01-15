package com.webmyne.modak.ui

import android.content.Intent
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.webmyne.modak.R
import com.webmyne.modak.adapter.ChefReviewsAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.Item_Details
import kotlinx.android.synthetic.main.activity_item_details.*


class ItemDetailsActivity : BaseActivity() {

    var CountItem = 0
    var bundle = Bundle()
    var basePriceItem = 0.00
    var count = 0
    var itemDetails: Item_Details? = null
    val db: DatabaseHelper = DatabaseHelper(this)
    var itemId: Int = 0

    companion object {
        fun launchActivity(
            activity: BaseActivity?,
            countItem: Int,
            itemImage: Item_Details
        ) {
            if (activity != null) {
                val intent = Intent(activity, ItemDetailsActivity::class.java)
                intent.putExtra("ItemCount", countItem)
                intent.putExtra("itemDetails", itemImage)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.webmyne.modak.R.layout.activity_item_details)
        rvItemReviews.layoutManager = LinearLayoutManager(this)
        rvItemReviews.adapter = ChefReviewsAdapter(this)
        btnReviews.text = "Reviews " + "(" + PrefUtils.getReviewsCount(this).toString() + " )"
        initview()
        actionListner()
    }

    private fun actionListner() {
        count = PrefUtils.getAddItemCount(this)
        btnDetails.setBackgroundResource(com.webmyne.modak.R.color.colorsplacebackground)
        btnDetails.setTextColor(resources.getColor(com.webmyne.modak.R.color.white))
        imgChefImage.setOnClickListener {
            loadButtonUi(13, 0)

        }
        btnDetails.setOnClickListener {
            loadButtonUi1(1)

        }
        btnReviews.setOnClickListener {
            btnDetails.setBackgroundResource(com.webmyne.modak.R.color.bgapp)
            btnDetails.setTextColor(resources.getColor(com.webmyne.modak.R.color.black))
            loadButtonUi1(2)

        }

        imgHomeItemLeftArrow1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if (CountItem >= 0) {
                    CountItem--
                    count--
                    txtCountNumberOfItemDetails.text = "$CountItem"
                    var price = basePriceItem * CountItem.toDouble()
                    txtItemPrice.text = "$" + "$price"
                    var itemDetails: Item_Details = Item_Details()
                    itemDetails.itemId = itemId
                    itemDetails.itemCount = CountItem
                    db.updateItemCountByItemId(itemDetails)
                    if (db.getTotalItemCount() == 0) {
                        textAddItemCountDetails.visibility = View.GONE
                    } else {
                        textAddItemCountDetails.text = db.getTotalItemCount().toString()
                    }
                }
            }

        })
        imgHomeItemRightArrow1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CountItem++
                count++
                txtCountNumberOfItemDetails.text = "$CountItem"
                var price = basePriceItem * CountItem.toDouble()
                txtItemPrice.text = "$" + "$price"
                var itemDetails: Item_Details = Item_Details()
                itemDetails.itemId = itemId
                itemDetails.itemCount = CountItem
                db.updateItemCountByItemId(itemDetails)

                if (db.getTotalItemCount() == 0) {
                    textAddItemCountDetails.visibility = View.GONE
                } else {
                    textAddItemCountDetails.text = db.getTotalItemCount().toString()
                }
            }

        })
        imgHomeItemAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CountItem++

                count++
                txtCountNumberOfItemDetails.text = "$CountItem"
                var price = basePriceItem * CountItem.toDouble()
                txtItemPrice.text = "$" + "$price"
                var itemDetails: Item_Details = Item_Details()
                itemDetails.itemId = itemId
                itemDetails.itemCount = CountItem
                db.updateItemCountByItemId(itemDetails)

                if (db.getTotalItemCount() == 0) {
                    textAddItemCountDetails.visibility = View.GONE
                } else {
                    textAddItemCountDetails.text = db.getTotalItemCount().toString()
                }
            }

        })
        imgIconMenuItrmDetails.setOnClickListener {
            onBackPressed()
        }


    }

    fun loadButtonUi1(position: Int) {
        when (position) {
            1 -> {
                btnDetails.setBackgroundResource(com.webmyne.modak.R.color.colorsplacebackground)
                btnDetails.setTextColor(resources.getColor(com.webmyne.modak.R.color.white))
                btnReviews.setOnClickListener {
                    btnDetails.setBackgroundResource(com.webmyne.modak.R.color.bgapp)
                    btnDetails.setTextColor(resources.getColor(com.webmyne.modak.R.color.black))
                    loadButtonUi1(2)

                }
            }
            2 -> {

                btnReviews.setBackgroundResource(com.webmyne.modak.R.color.colorsplacebackground)
                btnReviews.setTextColor(resources.getColor(com.webmyne.modak.R.color.white))
                txtDetails.visibility = View.INVISIBLE
                rvItemReviews.visibility = View.VISIBLE
                btnDetails.setOnClickListener {
                    btnReviews.setBackgroundResource(com.webmyne.modak.R.color.bgapp)
                    btnReviews.setTextColor(resources.getColor(com.webmyne.modak.R.color.black))
                    rvItemReviews.visibility = View.INVISIBLE
                    txtDetails.visibility = View.VISIBLE
                    loadButtonUi1(1)

                }
            }
        }
    }

    private fun initview() {
        count = PrefUtils.getAddItemCount(this)
        //  CountItem = intent.getIntExtra("ItemCount", 0)
        itemDetails = intent.getSerializableExtra("itemDetails") as Item_Details
        itemId = itemDetails!!.itemId
        rvItemReviews.layoutManager = LinearLayoutManager(this)
        rvItemReviews.adapter = ChefReviewsAdapter(this)
        txtItemPrice.text = "$" + itemDetails!!.ItemPrice.toDouble()
        txtHomeItemDistabace.text = itemDetails!!.itemDistance + " Mile away"
        txtHomeItemFamousPlaceName.text = itemDetails!!.ChefCuisineTye
        rating.rating = itemDetails!!.ChefAverageRating
        //rating.getProgressDrawable().setTint(getColor(R.color.quantum_yellow700))
        val stars = rating.progressDrawable as LayerDrawable
        // Filled stars
        Functions.setRatingStarColor(
            stars.getDrawable(2),
            ContextCompat.getColor(this, R.color.quantum_yellow700)
        )
        // Half filled stars
        Functions.setRatingStarColor(
            stars.getDrawable(1),
            ContextCompat.getColor(this, R.color.colorgray)
        )
        // Empty stars
        Functions.setRatingStarColor(
            stars.getDrawable(0),
            ContextCompat.getColor(this, R.color.colorgray)
        )
        txtHomeChefName.text = itemDetails!!.ChefName
        txtHomeItemName.text = itemDetails!!.ItemName
        btnReviews.text = "Reviews " + "(" + PrefUtils.getReviewsCount(this).toString() + " )"
        txtItemDescription.text = itemDetails!!.ItemDescription
        CountItem = db.getItemCountByItemID(itemDetails!!.itemId)
        basePriceItem = itemDetails!!.ItemPrice.toDouble()
        txtCountNumberOfItemDetails.text = "$CountItem"
        var price = basePriceItem * CountItem.toDouble()
        if (CountItem > 0) {
            txtItemPrice.text = "$" + "$price"
        }
        if (db.getTotalItemCount() == 0) {
            textAddItemCountDetails.visibility = View.GONE
        } else {
            textAddItemCountDetails.text = db.getTotalItemCount().toString()
        }

        Glide.with(this)
            .load(itemDetails!!.ChefImage)
            .apply(RequestOptions.circleCropTransform())
            .into(imgChefImage)
        Glide.with(this)
            .load(itemDetails!!.ItemImage)
            .into(itemImage)
    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

    interface OnAddItem {
        fun OnItemAdd(count: Int)
    }




}


