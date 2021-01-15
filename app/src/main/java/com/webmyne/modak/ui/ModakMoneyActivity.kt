package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import com.webmyne.modak.R
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class ModakMoneyActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var list: List<String> = ArrayList()
    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, ModakMoneyActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modak_money)

        initview()
        actionListner()


    }

    private fun actionListner() {


    }

    private fun initview() {
        txtToolbar.setText(R.string.modak_money)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

}