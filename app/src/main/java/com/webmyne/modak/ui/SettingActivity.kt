package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import com.webmyne.modak.R
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.toolbar.*

class SettingActivity : BaseActivity() {

    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, SettingActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initview()
        actionListner()

    }


    private fun actionListner() {


    }

    private fun initview() {

        txtToolbar.setText(R.string.setting)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }
}
