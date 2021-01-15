package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.helper.Functions
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_dashbord.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*






class DashboardActivity: BaseActivity() {
    private lateinit var mDrawerLayout: DrawerLayout
    private var slidingRootNav: SlidingRootNav? = null
    private var toolbar: Toolbar? = null
    var CountItem = 0;
    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                Functions.fireIntent(activity, DashboardActivity::class.java, true, true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        layoutdash.visibility=View.VISIBLE
        mDrawerLayout = findViewById(R.id.drawer_layout)

        imgIconMenu.setOnClickListener {

            if(!mDrawerLayout.isDrawerOpen(GravityCompat.START)) mDrawerLayout.openDrawer(Gravity.START);
            else mDrawerLayout.closeDrawer(Gravity.END);
        }
        val navigationView: NavigationView = findViewById(R.id.nav_view)
    /*    var header = layoutReview
        header.setOnClickListener(View.OnClickListener {
            var home=Intent(this,ReviewActivity::class.java)
            startActivity(home)
        })*/
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.navHome -> {
                    Toast.makeText(this, "Wallet", Toast.LENGTH_LONG).show()
                }
                R.id.navMyProfile -> {
                    var home=Intent(this,MyProfileActivity::class.java)
                    startActivity(home)
                }
                R.id.navMyAdress -> {

                }
                R.id.navPaymentInfo -> {
                    var money=Intent(this,CardActivity::class.java)
                    startActivity(money)
                }
                R.id.navNotifations -> {
                    var not = Intent(this, NotificationActivity::class.java)
                    startActivity(not)
                }
                R.id.navFreeMeals -> {
                    var basket=Intent(this,BasketActivity::class.java)
                    startActivity(basket)
                }
                R.id.navSetting -> {
                    var setting=Intent(this,SettingActivity::class.java)
                    startActivity(setting)
                }
                R.id.navHelp -> {
                    var help=Intent(this,HelpActivity::class.java)
                    startActivity(help)
                }
                R.id.navLogOut -> {
                   var logout=Intent(this,LoginActivity::class.java)
                    startActivity(logout)
                }
            }
            true
        }
        var location: ImageView = findViewById(R.id.imgiconGps)
        //var countMinus: ImageView = findViewById(R.id.imgHomeItemLeftArrow)
        var city: TextView = findViewById(R.id.txtCity)
        //var Count: TextView = findViewById(R.id.txtHomeNumberOfItem)
        city.setOnClickListener {
            var click = Intent(this, ItemDetailsActivity::class.java)
            setSupportActionBar(toolbar)
            startActivity(click)
        }
        location.setOnClickListener {
            var click = Intent(this, LocationActivity::class.java)
            startActivity(click)
        }
        initviews()
        //initDrawer(savedInstanceState)
        layoutFavorite.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(1)
        }

        layoutDelivery.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(2)
        }

        latoutTakeout.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(3)
        }

        layoutDinein.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(4)
        }

    }


    private fun initDrawer(savedInstanceState: Bundle?) {
        slidingRootNav = SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(toolbar)
            .withRootViewScale(0.7f)
            //  .withMenuOpened(false)
             .withContentClickableWhenMenuOpened(true)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.activity_home)
            .inject()

    }


    private fun initviews() {
        loadButtonUi(1)
    }
}
