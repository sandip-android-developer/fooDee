package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_basket.*

class BasketActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        txtSelectPayment.setOnClickListener {
            var paymentMethod=Intent(this,CardActivity::class.java)
            startActivity(paymentMethod)
        }
       imgbackBasket.setOnClickListener {
            var paymentMethod=Intent(this,DashboardActivity::class.java)
            startActivity(paymentMethod)
        }

    }
}