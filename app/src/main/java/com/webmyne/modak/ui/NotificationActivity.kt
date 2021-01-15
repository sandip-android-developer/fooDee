package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.webmyne.modak.R
import com.webmyne.modak.adapter.NotificationAdapter
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.model.HomeDetails
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class NotificationActivity : BaseActivity() {
    var item:List<HomeDetails> = ArrayList()
    var itemCount:Int=0
    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, NotificationActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        initview()
        actionListner()

    }


    private fun actionListner() {
        rvNotifications.layoutManager=LinearLayoutManager(this)
        rvNotifications.adapter= NotificationAdapter(this)

    }

    private fun initview() {

        txtToolbar.setText(R.string.notifications)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }
}
