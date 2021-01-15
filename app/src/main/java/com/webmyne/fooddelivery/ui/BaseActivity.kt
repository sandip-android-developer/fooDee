package com.webmyne.fooddelivery.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.widget.Toast
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.fragment.*
import kotlinx.android.synthetic.main.activity_dashbord.*
import java.util.*

open class BaseActivity: AppCompatActivity(){
    var screenWidth: Int = 0
    var screenHeight: Int = 0
    var selectedScreen = 0
    var fragmentBackStack: Stack<Fragment>? = null
    var showBackMessage: Boolean? = true
    var doubleBackToExitPressedOnce: Boolean = false
    var dialog: ProgressDialog? = null

    fun setShowBackMessage(showBackMessage:Boolean){
        this.showBackMessage=showBackMessage
    }
    fun getFragments():Stack<Fragment>?{
        return fragmentBackStack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentBackStack= Stack()
        getWidthAndHeight()
    }

    private fun getWidthAndHeight() {
        val displaymerices=DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymerices)
        screenHeight=displaymerices.heightPixels
        screenWidth=displaymerices.widthPixels

    }
    @Synchronized
    fun pushAddFragments(fragment: Fragment?, shouldAnimate: Boolean, shouldAdd: Boolean) {
        try {
            if (fragment != null) {
                fragmentBackStack?.push(fragment)
                val manager = supportFragmentManager
                val ft = manager.beginTransaction()
                ft.replace(R.id.contentFrame, fragment, fragmentBackStack?.size.toString())
                ft.commit()
                manager.executePendingTransactions()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    @Synchronized
    fun popFragments(shouldAnimate: Boolean) {
        var fragment: Fragment? = null
        if (fragmentBackStack!!.size > 1)
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 2)
        else if (!fragmentBackStack!!.isEmpty())
            fragment = fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 1)
        var manager: FragmentManager = supportFragmentManager

        var ft: FragmentTransaction = manager.beginTransaction()

        if (fragment!!.isAdded())
            ft.remove(fragmentBackStack!!.elementAt(fragmentBackStack!!.size - 1))
        if (fragmentBackStack!!.size > 1) {
            ft.show(fragment).commit()
        } else
            ft.replace(R.id.contentFrame, fragment).commit()
        fragmentBackStack!!.pop()
        manager.executePendingTransactions()
    }

    fun doubleTapOnBackPress() {
        if (doubleBackToExitPressedOnce){
            finish()
            //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this@BaseActivity, "press back again", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 1000)

    }
    override fun onBackPressed() {
        if (fragmentBackStack!!.size <= 1) {
            finish()
            //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        } else {
            if (!(fragmentBackStack!!.get(fragmentBackStack!!.size - 1) as BaseFragment).onFragmentBackPress()) {
                val currentFragment = fragmentBackStack!!.get(fragmentBackStack!!.size - 1)
                if (currentFragment is DashboardFragment) {
                    finish()
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                } else {
                    if (fragmentBackStack!!.get(fragmentBackStack!!.size - 1) is DeliveryFragment) {
                        loadButtonUi(1)
                    } else if (fragmentBackStack!!.get(fragmentBackStack!!.size - 1) is TakeoutFragment) {
                        loadButtonUi(1)
                    } else if (fragmentBackStack!!.get(fragmentBackStack!!.size - 1) is DineinFragment) {
                        loadButtonUi(1)
                    }else {
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
    fun loadButtonUi(position: Int) {
        when (position) {
            1 -> {
                if (selectedScreen != 1) {
                    selectedScreen = 1
                    txtFavorite.setTextColor(getResources().getColor(R.color.black))

                    txtDelivery.setTextColor(getResources().getColor(R.color.colorgray))
                    txtTakeout.setTextColor(getResources().getColor(R.color.colorgray))
                    txtDineIn.setTextColor(getResources().getColor(R.color.colorgray))
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
                    txtFavorite.setTextColor(getResources().getColor(R.color.colorgray))
                    txtTakeout.setTextColor(getResources().getColor(R.color.colorgray))
                    txtDineIn.setTextColor(getResources().getColor(R.color.colorgray))
                    txtDelivery.setTextColor(getResources().getColor(R.color.black))
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
                    txtDelivery.setTextColor(getResources().getColor(R.color.colorgray))
                    txtFavorite.setTextColor(getResources().getColor(R.color.colorgray))
                    txtDineIn.setTextColor(getResources().getColor(R.color.colorgray))
                    txtTakeout.setTextColor(getResources().getColor(R.color.black))
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
                    txtDineIn.setTextColor(getResources().getColor(R.color.black))
                    txtDelivery.setTextColor(getResources().getColor(R.color.colorgray))
                    txtTakeout.setTextColor(getResources().getColor(R.color.colorgray))
                    txtFavorite.setTextColor(getResources().getColor(R.color.colorgray))
                    imgDelivery.setImageResource(R.drawable.footer_icon3_unselected)
                    imgTakeout.setImageResource(R.drawable.footer_icon1_unselected)
                    imgDineIn.setImageResource(R.drawable.footer_icon2_selected)
                    pushAddFragments(DineinFragment.getInstance(BaseActivity@ this), true, true)

                }
            }
        }
    }


}