package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.webmyne.modak.R
import com.webmyne.modak.adapter.NotificationAdapter
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.toolbar.*

class ReviewsrActivity: BaseActivity() {
    var bundle = Bundle()

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, ReviewsrActivity::class.java)
                Functions.fireIntent(activity,intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        initview()
        actionListner()


    }

    private fun actionListner() {
        txtToolbar.setText(R.string.reviews)
        imgBackActivity.setOnClickListener {
           onBackPressed()
        }
    }

    private fun initview() {
        rvReviews.layoutManager=LinearLayoutManager(this)
        rvReviews.adapter=NotificationAdapter(this)
    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }

}