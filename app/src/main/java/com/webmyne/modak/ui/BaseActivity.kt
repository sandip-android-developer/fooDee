package com.webmyne.modak.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import com.webmyne.modak.R
import com.webmyne.modak.custome.LogoutDialog
import com.webmyne.modak.fragment.*
import kotlinx.android.synthetic.main.activity_dashbord.*
import java.util.*

open class BaseActivity : AppCompatActivity() {
    var screenWidth: Int = 0
    var screenHeight: Int = 0
    var selectedScreen = 0
    var fragmentBackStack: Stack<Fragment>? = null
    var showBackMessage: Boolean? = true
    var doubleBackToExitPressedOnce: Boolean = false
    var dialog: ProgressDialog? = null
    var fragmentSimple: FavoritesFragments? = null
    var SIMPLE_FRAGMENT_TAG = "myfragmenttag";

    fun setShowBackMessage(showBackMessage: Boolean) {
        this.showBackMessage = showBackMessage
    }

    fun getFragments(): Stack<Fragment>? {
        return fragmentBackStack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*try {
            if (savedInstanceState == null) {
                println("Home**************base savedInstanceState" + savedInstanceState)
                super.onCreate(savedInstanceState)
                fragmentBackStack = Stack()
            } else {
                println("Home**************base savedInstanceState" + savedInstanceState)
                super.onCreate(savedInstanceState)
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            println("Home**************base crash" + e.toString())
            //Crashlytics.logException(e)
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }*/

        /* if (savedInstanceState != null) {
             println("Home**************base 1--" + savedInstanceState)
             // saved instance state, fragment may exist
             // look up the instance that already exists by tag
             super.onCreate(savedInstanceState)
             fragmentSimple = FavoritesFragments(this)
             getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_TAG);
             fragmentBackStack = Stack()

         } else if (fragmentSimple == null) {
             super.onCreate(savedInstanceState)
             println("Home**************base 2--" + savedInstanceState)
             fragmentBackStack = Stack()
             // only create fragment if they haven't been instantiated already
         }*/

        getWidthAndHeight()
    }

    private fun getWidthAndHeight() {
        val displaymerices = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymerices)
        screenHeight = displaymerices.heightPixels
        screenWidth = displaymerices.widthPixels

    }

    fun pushAddFragments(fragment: Fragment?, shouldAnimate: Boolean, shouldAdd: Boolean) {
        try {
            if (fragment != null) {
                fragmentBackStack?.push(fragment)
                val manager = supportFragmentManager
                val ft = manager.beginTransaction()
                ft.replace(R.id.contentFrame, fragment)
                ft.commit()
                manager.executePendingTransactions()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun popFragments(shouldAnimate: Boolean) {
        var fragment: Fragment? = null
        if (fragmentBackStack!!.size > 1)
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 2)
        else if (!fragmentBackStack!!.isEmpty())
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 1)
        var manager: FragmentManager = supportFragmentManager

        var ft: FragmentTransaction = manager.beginTransaction()

        if (fragment!!.isAdded)
            ft.remove(fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 1))
        if (fragmentBackStack!!.size > 1) {
            ft.show(fragment).commit()
        } else
            ft.replace(R.id.contentFrame, fragment).commit()
        fragmentBackStack!!.pop()
        manager.executePendingTransactions()
    }

    fun doubleTapOnBackPress() {
        LogoutDialog(this, 2, object : LogoutDialog.OnClickYes {
            override fun onClickYes(flag: Int) {
                finishAffinity()
            }

        })

    }

    override fun onBackPressed() {
        if (fragmentBackStack!!.size < 1) {
            println("hbehbhb" + fragmentBackStack!!.size)
            if (showBackMessage!!) {
                //doubleTapOnBackPress()
            } else {
                finish()
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
            //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        } else {
            if (!(fragmentBackStack!!.get(fragmentBackStack!!.size - 1) as BaseFragment).onFragmentBackPress()) {
                val currentFragment = fragmentBackStack!!.get(fragmentBackStack!!.size - 1)
                if (currentFragment is FavoritesFragments) {
                    doubleTapOnBackPress()
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                } else {
                    if (fragmentBackStack!!.get(fragmentBackStack!!.size - 1) is DeliveryFragment) {
                        loadButtonUi(1, 0)
                    } else if (fragmentBackStack!!.get(fragmentBackStack!!.size - 1) is TakeoutFragment) {
                        loadButtonUi(1, 0)
                    } else if (fragmentBackStack!!.get(fragmentBackStack!!.size - 1) is DineinFragment) {
                        loadButtonUi(1, 0)
                    } else {
                        popFragments(false)
                    }
                }
            }
        }
    }

    fun showProgressDialog(isCancelable: Boolean) {
        if (dialog != null && dialog?.isShowing!!) {
            dialog!!.dismiss()
        }
        dialog = ProgressDialog(this)
        dialog!!.setMessage(getString(R.string.msg_please_wait))
        dialog!!.setCancelable(isCancelable)
        dialog!!.show()
    }

    fun hideProgressDialog() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

    fun loadButtonUi(position: Int, countItem: Int) {
        when (position) {
            1 -> {
                if (selectedScreen != 1) {
                    selectedScreen = 1
                    txtFavorite.setTextColor(resources.getColor(R.color.black))
                    txtDelivery.setTextColor(resources.getColor(R.color.colorgray))
                    txtTakeout.setTextColor(resources.getColor(R.color.colorgray))
                    txtDineIn.setTextColor(resources.getColor(R.color.colorgray))
                    imgFavorite.setImageResource(R.drawable.footer_icon4_selected)
                    imgDelivery.setImageResource(R.drawable.footer_icon3_unselected)
                    imgTakeout.setImageResource(R.drawable.footer_icon1_unselected)
                    imgDineIn.setImageResource(R.drawable.footer_icon2_unselected)
                    pushAddFragments(FavoritesFragments.getInstance(BaseActivity@ this), true, true)
                }
            }
            2 -> {
                if (selectedScreen != 2) {
                    selectedScreen = 2
                    imgFavorite.setImageResource(R.drawable.footer_icon4_unselected)
                    txtFavorite.setTextColor(resources.getColor(R.color.colorgray))
                    txtTakeout.setTextColor(resources.getColor(R.color.colorgray))
                    txtDineIn.setTextColor(resources.getColor(R.color.colorgray))
                    txtDelivery.setTextColor(resources.getColor(R.color.black))
                    imgDelivery.setImageResource(R.drawable.footer_icon3_selected)
                    imgTakeout.setImageResource(R.drawable.footer_icon1_unselected)
                    imgDineIn.setImageResource(R.drawable.footer_icon2_unselected)
                    pushAddFragments(DeliveryFragment.getInstance(BaseActivity@ this), true, true)
                }
            }
            3 -> {
                if (selectedScreen != 3) {
                    selectedScreen = 3
                    imgFavorite.setImageResource(R.drawable.footer_icon4_unselected)
                    txtDelivery.setTextColor(resources.getColor(R.color.colorgray))
                    txtFavorite.setTextColor(resources.getColor(R.color.colorgray))
                    txtDineIn.setTextColor(resources.getColor(R.color.colorgray))
                    txtTakeout.setTextColor(resources.getColor(R.color.black))
                    imgDelivery.setImageResource(R.drawable.footer_icon3_unselected)
                    imgTakeout.setImageResource(R.drawable.footer_icon1_selected)
                    imgDineIn.setImageResource(R.drawable.footer_icon2_unselected)
                    pushAddFragments(TakeoutFragment.getInstance(BaseActivity@ this), true, true)
                }
            }
            4 -> {
                if (selectedScreen != 4) {
                    selectedScreen = 4
                    imgFavorite.setImageResource(R.drawable.footer_icon4_unselected)
                    txtDineIn.setTextColor(resources.getColor(R.color.black))
                    txtDelivery.setTextColor(resources.getColor(R.color.colorgray))
                    txtTakeout.setTextColor(resources.getColor(R.color.colorgray))
                    txtFavorite.setTextColor(resources.getColor(R.color.colorgray))
                    imgDelivery.setImageResource(R.drawable.footer_icon3_unselected)
                    imgTakeout.setImageResource(R.drawable.footer_icon1_unselected)
                    imgDineIn.setImageResource(R.drawable.footer_icon2_selected)
                    pushAddFragments(DineinFragment.getInstance(BaseActivity@ this), true, true)

                }
            }


        }
    }
}