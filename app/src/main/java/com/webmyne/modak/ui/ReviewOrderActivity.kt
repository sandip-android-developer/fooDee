package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.webmyne.modak.R
import com.webmyne.modak.adapter.SelectItemAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.Item_Details
import kotlinx.android.synthetic.main.activity_review_order.*
import kotlinx.android.synthetic.main.toolbar.*

class ReviewOrderActivity : BaseActivity() {
    var bundle = Bundle()
    var db = DatabaseHelper(this)

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, ReviewOrderActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_order)
        initview()
        actionListner()


    }

    private fun actionListner() {
        txtToolbar.setText(R.string.review_order)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }
        txtCnfOrder.setOnClickListener {
            PrefUtils.setCoupanApply(this, "")
            PaymentInfoActivity.launchActivity(this)

        }
        txtChangeCard.setOnClickListener {
            PaymentInfoActivity.launchActivity(this)
        }
        btnContinueBrowsing.setOnClickListener {
            DashboardActivity.launchActivity(this)
        }

    }

    private fun initview() {
        var totalPrice: Double = 0.00
        var ItemDetailsDetails = ArrayList<Item_Details>()
        var ItemDetailsList = ArrayList<Item_Details>()
        if (db.getAllItemDetails().size > 0) {

            ItemDetailsDetails.addAll(db.getAllItemDetailsWIthChef(0))
        }
        ItemDetailsList.addAll(db?.getAllItemDetailGreaterThanZero())
        for (i in 0 until ItemDetailsList.size) {
            totalPrice =
                totalPrice + (ItemDetailsList.get(i).itemCount.toDouble() * ItemDetailsList.get(i).ItemPrice.toDouble())
        }
        if (PrefUtils.getCoupanApply(this).equals("0")) {
            txtSubTotal.text = "$" + totalPrice.toString()
            txtDiscountAmmiount.text = "- $" + ((totalPrice + 5) * 0.3).toString()
            txtTotalPrice.text = "$" + ((totalPrice + 5) * 0.7).toString()
            txtApplyPromoCode.text = "Remove Code X"
            txtApplyPromoCode.setTextColor(resources.getColor(R.color.colorsplacebackground))

            // PrefUtils.setCoupanApply(this,"")
        } else if (PrefUtils.getCoupanApply(this).equals("1")) {
            txtSubTotal.text = "$" + totalPrice.toString()
            txtDiscountAmmiount.text = "- $" + ((totalPrice + 5) * 0.5).toString()
            txtTotalPrice.text = "$" + ((totalPrice + 5) * 0.5).toString()
            txtApplyPromoCode.text = "Remove Code X"
            txtApplyPromoCode.setTextColor(resources.getColor(R.color.colorsplacebackground))
            //PrefUtils.setCoupanApply(this,"")
        } else {
            txtApplyPromoCode.setTextColor(resources.getColor(R.color.black))
            txtSubTotal.text = "$" + totalPrice.toString()
            txtTotalPrice.text = "$" + (totalPrice + 5).toString()
        }
        txtDeliveryfees.text = "$5"
        println("ItemDetailsList--" + ItemDetailsList.size)
        rvReviewOrder.layoutManager = LinearLayoutManager(this)
        rvReviewOrder.adapter = SelectItemAdapter(this, ItemDetailsList)

        llPromoCode.setOnClickListener {
            if (txtApplyPromoCode.text.toString().trim().equals("Remove Code X")) {
                rlPromoCode.visibility = View.GONE
                txtApplyPromoCode.setTextColor(resources.getColor(R.color.black))
                txtApplyPromoCode.text = "Apply Promo Code"
                txtSubTotal.text = "$" + totalPrice.toString()
                txtTotalPrice.text = "$" + (totalPrice + 5).toString()
                txtDiscountAmmiount.text = ""
                PrefUtils.setCoupanApply(this, "")
            } else {
                PromoCodeActivity.launchActivity(this)
            }

        }
    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

    override fun onResume() {
        super.onResume()
        if (!PrefUtils.getCoupanApply(this).equals("")) {
            rlPromoCode.visibility = View.VISIBLE
            initview()
        }

    }

}