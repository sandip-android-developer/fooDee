package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.webmyne.modak.R
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.activity_freemeals.*
import kotlinx.android.synthetic.main.toolbar.*

class FreeMealsActivity : BaseActivity() {

    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, FreeMealsActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freemeals)
        initview()
        actionListner()

    }


    private fun actionListner() {


    }

    private fun initview() {

        txtToolbar.setText(R.string.free_meals)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }
        Glide.with(this)
            .load(R.drawable.nofood)
            .into(imgNoFood)

    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }
}
