package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
       imgChangePass.setOnClickListener {
            var backMyProfile=Intent(this,MyProfileActivity::class.java)
            startActivity(backMyProfile)
        }
    }
}