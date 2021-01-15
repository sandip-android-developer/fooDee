package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_profile.*

class MyProfileActivity : BaseActivity() {
    private lateinit var mDrawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutMyProfile.visibility= View.VISIBLE
        mDrawerLayout = findViewById(R.id.drawer_layout)
        imgMyProfileMenuIcon.setOnClickListener {
            if(!mDrawerLayout.isDrawerOpen(GravityCompat.START)) mDrawerLayout.openDrawer(Gravity.START);
            else mDrawerLayout.closeDrawer(Gravity.END);
        }

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true

            mDrawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.navHome -> {
                    Toast.makeText(this, "Wallet", Toast.LENGTH_LONG).show()
                }
                R.id.navMyProfile -> {
                    var home = Intent(this, MyProfileActivity::class.java)
                    startActivity(home)
                }
                R.id.navMyAdress -> {

                }
                R.id.navPaymentInfo -> {
                    var money = Intent(this, CardActivity::class.java)
                    startActivity(money)
                }
                R.id.navNotifations -> {
                    var not = Intent(this, NotificationActivity::class.java)
                    startActivity(not)
                }
                R.id.navFreeMeals -> {
                    var basket = Intent(this, BasketActivity::class.java)
                    startActivity(basket)
                }
                R.id.navSetting -> {
                    var setting = Intent(this, SettingActivity::class.java)
                    startActivity(setting)
                }
                R.id.navHelp -> {
                    var help = Intent(this, HelpActivity::class.java)
                    startActivity(help)
                }
                R.id.navLogOut -> {
                    var logout = Intent(this, LoginActivity::class.java)
                    startActivity(logout)
                }
            }
            true
        }
        txtChangePassword.setOnClickListener {
            var changePass=Intent(this,ChangePasswordActivity::class.java)
            startActivity(changePass)
        }
        txtMyAddress.setOnClickListener {
            var editProfile = Intent(this, EditProfileActivity::class.java)
            startActivity(editProfile)
        }

    }
}
