package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var signup: TextView =findViewById(R.id.txtSignUp)
        var login:TextView=findViewById(R.id.txtLogin)
        login.setOnClickListener {
            var click= Intent(this, DashboardActivity::class.java)
            startActivity(click)
        }
        layoutSignUp.setOnClickListener {
            var click= Intent(this, SignupActivity::class.java)
            startActivity(click)
        }
    }

}

