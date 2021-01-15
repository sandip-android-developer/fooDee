package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.webmyne.modak.R
import com.webmyne.modak.adapter.MyAddressAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.Item_Details
import com.webmyne.modak.model.User_Address
import kotlinx.android.synthetic.main.activity_delivery_time.*
import kotlinx.android.synthetic.main.toolbar.*

class DeliveryTimeOrderActivity: BaseActivity() {
    var bundle = Bundle()
    var db = DatabaseHelper(this)
    var userAddressList:MutableList<User_Address> = mutableListOf()
    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, DeliveryTimeOrderActivity::class.java)
                Functions.fireIntent(activity,intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_time)
        initview()
        actionListner()


    }

    private fun actionListner() {
        txtToolbar.setText(R.string.basket)
        imgBackActivity.setOnClickListener {
            finish()
        }
        txtProceed.setOnClickListener {
            PrefUtils.setAddItemCount(this,0)
            ReviewOrderActivity.launchActivity(this)
        }
        llasap_deliveryTime.setOnClickListener {
            loadTimeUi(1)
        }
        ll30min_deliveryTime.setOnClickListener {
            loadTimeUi(2)
        }
        ll1Hours_deliveryTime.setOnClickListener {
            loadTimeUi(3)
        }
        ll130Hours_deliveryTime.setOnClickListener {
            loadTimeUi(4)
        }
        llDineIn_deliveryTime.setOnClickListener {
            loadChooseTimeUi(1)
        }
        llTakeOut_deliveryTime.setOnClickListener {
            loadChooseTimeUi(2)
        }
        llDelivery_deliveryTime.setOnClickListener {
            loadChooseTimeUi(3)
        }
        txtViewOrder_deliveryTime.setOnClickListener {

        }
    }
    fun loadTimeUi(position: Int) {
        when (position) {
            1 -> {
                imgasap_deliveryTime.setImageResource(R.drawable.checked)
                imgll30min_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                img1Hours_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                img130Hours_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
            }
            2 -> {
                imgasap_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                imgll30min_deliveryTime.setImageResource(R.drawable.checked)
                img1Hours_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                img130Hours_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)

            }
            3 -> {
                img1Hours_deliveryTime.setImageResource(R.drawable.checked)
                imgasap_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                imgll30min_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                img130Hours_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
            }
            4 -> {
                img130Hours_deliveryTime.setImageResource(R.drawable.checked)
                imgasap_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                imgll30min_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                img1Hours_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
            }

        }

    }
    fun loadChooseTimeUi(position: Int) {
        when (position) {
            1 -> {
                llPickUpTime.visibility=View.GONE
                rvDeliveryTime.visibility=View.GONE
                txtDeliveryTime.visibility=View.GONE
                txtOnDemand.visibility=View.GONE
                imgDineIn_deliveryTime.setImageResource(R.drawable.checked)
                imgTakeOut_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                imgDelivery_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                txtDineIn_deliveryTime.setTextColor(resources.getColor(R.color.black))
                txtTakeOut_deliveryTime.setTextColor(resources.getColor(R.color.lightGray1))
                txtDelivery_deliveryTime.setTextColor(resources.getColor(R.color.lightGray1))
            }
            2 -> {
                llPickUpTime.visibility=View.VISIBLE
                rvDeliveryTime.visibility=View.VISIBLE
                txtDeliveryTime.visibility=View.VISIBLE
                txtOnDemand.visibility=View.VISIBLE
                rvDeliveryTime.layoutManager=LinearLayoutManager(this)
                rvDeliveryTime.adapter=MyAddressAdapter(this, userAddressList)
                imgDineIn_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                imgTakeOut_deliveryTime.setImageResource(R.drawable.checked)
                imgDelivery_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                txtDineIn_deliveryTime.setTextColor(resources.getColor(R.color.lightGray1))
                txtTakeOut_deliveryTime.setTextColor(resources.getColor(R.color.black))
                txtDelivery_deliveryTime.setTextColor(resources.getColor(R.color.lightGray1))

            }
            3 -> {
                llPickUpTime.visibility=View.GONE
                rvDeliveryTime.visibility=View.GONE
                txtDeliveryTime.visibility=View.GONE
                txtOnDemand.visibility=View.GONE
                imgDineIn_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                imgTakeOut_deliveryTime.setImageResource(R.drawable.unchacked_breakfast_list)
                imgDelivery_deliveryTime.setImageResource(R.drawable.checked)
                txtDineIn_deliveryTime.setTextColor(resources.getColor(R.color.lightGray1))
                txtTakeOut_deliveryTime.setTextColor(resources.getColor(R.color.lightGray1))
                txtDelivery_deliveryTime.setTextColor(resources.getColor(R.color.black))
            }
        }
    }
    private fun initview() {
        var ItemDetailsDetails = ArrayList<Item_Details>()
        var totalCount=0
        var price:Double=0.00
        if (db.getAllItemDetails().size>0)
        {
            ItemDetailsDetails.addAll(db.getAllItemDetailsWIthChef(0))
        }
        for (i in 0 until ItemDetailsDetails.size)
        {
            if (ItemDetailsDetails.get(i).itemCount>0)
            {
                price=price+ItemDetailsDetails.get(i).itemCount.toInt() * ItemDetailsDetails.get(i).ItemPrice.toDouble()
                totalCount=totalCount+ItemDetailsDetails.get(i).itemCount
            }

        }
        txtItemPrice.text="$"+"$price"
        txtNumberOfItem.text="("+totalCount+" Meals)"
        userAddressList.addAll(db.getUserAddressByUserId(PrefUtils.getUserID(this).toInt()))
        edtCommentTime.isFocusable=false
        edtCommentTime.setOnTouchListener(View.OnTouchListener { v, event ->
            edtCommentTime.isFocusable=true
            edtCommentTime.isCursorVisible=true
            edtCommentTime.isFocusableInTouchMode = true
            false
        })
    }

    override fun onBackPressed() {
       // Functions.fireIntent(this,false)
        super.onBackPressed()
        finish()
    }

}