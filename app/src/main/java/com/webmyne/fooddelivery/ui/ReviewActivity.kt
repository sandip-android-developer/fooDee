package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        imgBackReview.setOnClickListener {
            var backReview=Intent(this,DashboardActivity::class.java)
            startActivity(backReview)
        }
    }
}
