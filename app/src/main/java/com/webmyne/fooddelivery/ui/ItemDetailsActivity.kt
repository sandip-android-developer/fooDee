package com.webmyne.fooddelivery.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.webmyne.fooddelivery.R

import com.webmyne.fooddelivery.fragment.DashboardFragment
import kotlinx.android.synthetic.main.activity_dashbord.*
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

@Suppress("DEPRECATION")
class ItemDetailsActivity : AppCompatActivity(){
    private lateinit var mDrawerLayout: DrawerLayout
    var selectedScreen = 0
    var CountItem=0;

@SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         layoutItemDetailsActivity.visibility=View.VISIBLE
        setSupportActionBar(toolbar)
    mDrawerLayout = findViewById(R.id.drawer_layout)


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
                var not = Intent(this, NotificationActivity::class.java)
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
        var chefname:ImageView=findViewById(R.id.imgChefImage)

        var buttonDetails: TextView =findViewById(R.id.btnDetails)
        var buttonReviews: TextView =findViewById(R.id.btnReviews)
    var Count: TextView =findViewById(R.id.txtCountNumberOfItem)
         chefname.setOnClickListener {
                 var click= Intent(this,AboutChefActivity::class.java)
                 startActivity(click)


             }
    buttonDetails.setBackgroundResource(R.color.colorsplacebackground)
    btnDetails.setBackgroundResource(R.drawable.tab_review_select)
        buttonDetails.setOnClickListener {

            loadButtonUi(1)

        }
            buttonReviews.setOnClickListener {
                buttonDetails.setBackgroundResource(R.color.bgapp)
              loadButtonUi(2)

        }
    var countPlus:ImageView=findViewById(R.id.imgHomeItemRightArrow)
    var countMinus:ImageView=findViewById(R.id.imgHomeItemLeftArrow)
        countMinus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if(CountItem>0) {
                    CountItem--
                    Count.text = "$CountItem"
                }
            }

        })
    countPlus.setOnClickListener(object: View.OnClickListener{
        override fun onClick(v: View?) {
            CountItem++
            Count.text="$CountItem"
        }

    })

    }

    fun loadButtonUi(position: Int) {
        when (position) {
            1 -> {
                var buttonReviews: TextView =findViewById(R.id.btnReviews)
                var buttonDetails: TextView =findViewById(R.id.btnDetails)
                btnDetails.setBackgroundResource(R.drawable.tab_review_select)
                buttonReviews.setTextColor(getResources().getColor(R.color.black))
               buttonDetails.setTextColor(getResources().getColor(R.color.white))
                buttonReviews.setOnClickListener {
                    btnReviews.setBackgroundResource(R.drawable.tab_review_select)
                    btnDetails.resources.getColor(R.color.bgapp)
                    buttonDetails.setBackgroundResource(R.color.bgapp)
                    loadButtonUi(2)

                }
            }
            2 -> {
                var buttonReviews: TextView =findViewById(R.id.btnReviews)
                var buttonDetails: TextView =findViewById(R.id.btnDetails)
              //  buttonDetails.resources.getColor(R.color.colorAccent)
                btnReviews.setBackgroundResource(R.drawable.tab_review_select)
                buttonDetails.setTextColor(getResources().getColor(R.color.black))
                buttonReviews.setTextColor(getResources().getColor(R.color.white))
               // btnReviews.setBackgroundResource(R.color.colorsplacebackground)
                txtDetails.visibility = View.INVISIBLE
                txtReviews.visibility = View.VISIBLE
                buttonDetails.setOnClickListener {
                   // buttonDetails.setBackgroundResource(R.color.colorsplacebackground)
                    buttonDetails.setTextColor(getResources().getColor(R.color.black))
                    btnReviews.setBackgroundResource(R.color.bgapp)
                    btnDetails.setBackgroundResource(R.drawable.tab_review_select)
                    txtReviews.visibility = View.INVISIBLE
                    txtDetails.visibility = View.VISIBLE
                    loadButtonUi(1)

                }
              }
            }
        }
}


