package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HelpActivity : BaseActivity() {
    private lateinit var mDrawerLayout: DrawerLayout
    internal lateinit var sp: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layouthelp.visibility=View.VISIBLE
        val feqMore=imgOpenFeq
        val feqLess=imgCloseFeq
        mDrawerLayout = findViewById(R.id.drawer_layout)

   imgHelpMenu.setOnClickListener {
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
                    var home= Intent(this,MyProfileActivity::class.java)
                    startActivity(home)
                }
                R.id.navMyAdress -> {

                }
                R.id.navPaymentInfo -> {
                    var money= Intent(this,CardActivity::class.java)
                    startActivity(money)
                }
                R.id.navNotifations -> {
                    var not= Intent(this, NotificationActivity::class.java)
                    startActivity(not)
                }
                R.id.navFreeMeals -> {
                    var basket= Intent(this,BasketActivity::class.java)
                    startActivity(basket)
                }
                R.id.navSetting -> {
                    var setting= Intent(this,SettingActivity::class.java)
                    startActivity(setting)
                }
                R.id.navHelp -> {
                    var help= Intent(this,HelpActivity::class.java)
                    startActivity(help)
                }
                R.id.navLogOut -> {
                    var logout= Intent(this,LoginActivity::class.java)
                    startActivity(logout)
                }
            }
            true
        }
        feqMore.setOnClickListener {
            imgOpenFeq.visibility=View.INVISIBLE
            imgCloseFeq.visibility=View.VISIBLE
            txtFeqMore.visibility=View.VISIBLE
        }
        feqLess.setOnClickListener {
            imgOpenFeq.visibility=View.VISIBLE
            imgCloseFeq.visibility=View.INVISIBLE
            txtFeqMore.visibility=View.INVISIBLE
        }
        sp=findViewById(R.id.spinerReason) as Spinner
        val country = arrayOf("Select Region","Country", "Australia", "Brazil", "China", "France", "Germany", "India", "Ireland", "Italy", "Mexico", "Poland")
        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item, country)
        sp.adapter=adapter

    }
}
