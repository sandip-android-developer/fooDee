package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        txtSaveEditProfile.setOnClickListener {
            var save=Intent(this,MyProfileActivity::class.java)
            startActivity(save)
        }
        txtXCancelEditProfile.setOnClickListener {
            var save=Intent(this,MyProfileActivity::class.java)
            startActivity(save)
        }
        imgbackEdit.setOnClickListener {
            var save=Intent(this,MyProfileActivity::class.java)
            startActivity(save)
        }

    }
}