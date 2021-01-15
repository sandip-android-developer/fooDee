package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.fragment.TakeoutFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {
    var CountItem=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_item_list)
        var countPlus:ImageView=findViewById(R.id.imgHomeItemRightArrow)
        var location:ImageView=findViewById(R.id.imgiconGps)
        var countMinus:ImageView=findViewById(R.id.imgHomeItemLeftArrow)
        var city: TextView =findViewById(R.id.txtCity)
        var Count: TextView =findViewById(R.id.txtHomeNumberOfItem)
        city.setOnClickListener {
            var click= Intent(this, ItemDetailsActivity::class.java)
            setSupportActionBar(toolbar)
            startActivity(click)
        }
        location.setOnClickListener {
            var click= Intent(this, LocationActivity::class.java)
            startActivity(click)
        }
        var chefname: ImageView =findViewById(R.id.imgHomeItemImage)
        chefname.setOnClickListener {
            var click= Intent(this,ChefDetailsActivity::class.java)
            startActivity(click)
        }

        countMinus.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                if (CountItem > 0) {
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
}