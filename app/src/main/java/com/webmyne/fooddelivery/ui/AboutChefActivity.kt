package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_about_chef.*
import kotlinx.android.synthetic.main.activity_chef_details.*
import kotlinx.android.synthetic.main.cheft_details.*
import kotlinx.android.synthetic.main.toolbar.*

class AboutChefActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_chef)
             var readMore:Button =findViewById<Button>(R.id.btnReadMore)
        var readLess:Button =findViewById<Button>(R.id.btnReadLess)


        readMore.setOnClickListener {
           btnReadMore.visibility = View.INVISIBLE
           txtReadMore.visibility = View.VISIBLE
         btnReadLess.visibility = View.VISIBLE

     }
        readLess.setOnClickListener {
            btnReadLess.visibility = View.INVISIBLE
            txtReadMore.visibility = View.GONE
            btnReadMore.visibility = View.VISIBLE

        }
        layoutReviewChef.setOnClickListener {
            loadButtonUi(1)
        }
        layoutShareChef.setOnClickListener {
            loadButtonUi(2)
        }
        layoutRate.setOnClickListener {
            loadButtonUi(3)
        }
        layoutMenu.setOnClickListener {
            loadButtonUi(4)
        }
    }


    private fun loadButtonUi(position: Int) {
        when (position) {
            1 -> {
                txtReviewsChefName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                imgReviewsChef.setImageResource(R.drawable.icon_review_selected)
                layoutShareChef.setOnClickListener {
                    imgShare.setImageResource(R.drawable.icon_share_selected)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    txtShareName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
                layoutRate.setOnClickListener {
                    imgRate.setImageResource(R.drawable.icon_rate_selected)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.colorsplacebackground))

                }
                layoutMenu.setOnClickListener {
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail_selected)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
            }
            2 -> {
                imgShare.setImageResource(R.drawable.icon_share_selected)
                txtShareName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                layoutReviewChef.setOnClickListener {
                    imgReviewsChef.setImageResource(R.drawable.icon_review_selected)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
                layoutRate.setOnClickListener {
                    imgRate.setImageResource(R.drawable.icon_rate_selected)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                }
                layoutMenu.setOnClickListener {
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail_selected)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))
                }
            }
            3 -> {
                imgRate.setImageResource(R.drawable.icon_rate_selected)
                txtRateName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                layoutReviewChef.setOnClickListener {
                    imgReviewsChef.setImageResource(R.drawable.icon_review_selected)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
                layoutShareChef.setOnClickListener {
                    imgShare.setImageResource(R.drawable.icon_share_selected)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
                layoutMenu.setOnClickListener {
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail_selected)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
            }
            4 -> {
                imgMenu.setImageResource(R.drawable.icon_menu_chef_detail_selected)
                txtMenuName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                layoutShareChef.setOnClickListener {
                    imgShare.setImageResource(R.drawable.icon_share_selected)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
                layoutRate.setOnClickListener {
                    imgRate.setImageResource(R.drawable.icon_rate_selected)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgReviewsChef.setImageResource(R.drawable.icon_review)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.black))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.colorsplacebackground))

                }
                layoutReviewChef.setOnClickListener {
                    imgReviewsChef.setImageResource(R.drawable.icon_review_selected)
                    imgShare.setImageResource(R.drawable.icon_share)
                    imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                    imgRate.setImageResource(R.drawable.icon_rate)
                    txtShareName.setTextColor(getResources().getColor(R.color.black))
                    txtReviewsChefName.setTextColor(getResources().getColor(R.color.colorsplacebackground))
                    txtMenuName.setTextColor(getResources().getColor(R.color.black))
                    txtRateName.setTextColor(getResources().getColor(R.color.black))

                }
            }
        }

    }
}