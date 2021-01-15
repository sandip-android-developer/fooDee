package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.webmyne.modak.R
import com.webmyne.modak.adapter.CurrentOrderAdapter
import com.webmyne.modak.adapter.PastOrderAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.activity_my_order.*
import kotlinx.android.synthetic.main.toolbar.*

class MyOrderActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var bundle = Bundle()

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, MyOrderActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        initview()
        actionListner()

    }

    private fun actionListner() {
        btnCurrentOrder.setOnClickListener {
            Log.e("Button1", "Button 1")
            loadButtonUiMyOrder(1)
        }
        btnPastOrder.setOnClickListener {
            Log.e("Button2", "Button 2")
            btnCurrentOrder.setBackgroundResource(R.color.bgapp)
            loadButtonUiMyOrder(2)

        }
        txtToolbar.setText(R.string.my_order)
        imgBackActivity.setOnClickListener {
            finish()
        }


    }

    private fun loadButtonUiMyOrder(position: Int) {
        when (position) {
            1 -> {
                btnCurrentOrder.setBackgroundResource(R.drawable.left_corner_background)
                btnPastOrder.setTextColor(resources.getColor(R.color.lightGray1))
                btnCurrentOrder.setTextColor(resources.getColor(R.color.white))
                btnPastOrder.setBackgroundResource(R.drawable.right_corner_white_background)
                rvCurrentOrder.layoutManager = LinearLayoutManager(this)
                rvCurrentOrder.adapter = CurrentOrderAdapter(this)
                btnPastOrder.setOnClickListener {
                    loadButtonUiMyOrder(2)

                }
            }
            2 -> {
                btnPastOrder.setBackgroundResource(R.drawable.right_corner_background)
                btnCurrentOrder.setTextColor(resources.getColor(R.color.lightGray1))
                btnPastOrder.setTextColor(resources.getColor(R.color.white))
                btnCurrentOrder.setBackgroundResource(R.drawable.left_corner_white_background)
                rvCurrentOrder.layoutManager = LinearLayoutManager(this)
                rvCurrentOrder.adapter = PastOrderAdapter(this)
                btnCurrentOrder.setOnClickListener {
                    loadButtonUiMyOrder(1)

                }
            }
        }
    }

    private fun initview() {
        rvCurrentOrder.layoutManager = LinearLayoutManager(this)
        rvCurrentOrder.adapter = CurrentOrderAdapter(this)

    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

}