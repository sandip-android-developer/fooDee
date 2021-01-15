package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import com.webmyne.modak.R
import com.webmyne.modak.helper.Functions

class OrderConfirmationActivity : BaseActivity() {

    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, OrderConfirmationActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_order_confirmation)
        initview()
        actionListner()

    }


    private fun actionListner() {


    }

    private fun initview() {


    }


    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

    interface OnClickApply {
        fun OnClickApply(flag: Int)
    }
}
