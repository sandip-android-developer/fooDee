package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.webmyne.modak.R
import com.webmyne.modak.adapter.MyAddressAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.ResponsPojo.User_Favorite
import com.webmyne.modak.model.User
import com.webmyne.modak.model.User_Address
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.toolbar.*

class MyProfileActivity : BaseActivity() {

    var bundle = Bundle()
    var db = DatabaseHelper(this)
    var passCount:Int=0

    companion object {
        fun launchActivity(
            activity: BaseActivity?
        ) {
            if (activity != null) {
                val intent = Intent(activity, MyProfileActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        initview()
        actionListner()

    }


    private fun actionListner() {
        var userAddressList:MutableList<User_Address> = mutableListOf()
        userAddressList.addAll(db.getUserAddressByUserId(PrefUtils.getUserID(this).toInt()))

        rvMyProfileAddress.layoutManager = LinearLayoutManager(this)
        rvMyProfileAddress.adapter = MyAddressAdapter(this,userAddressList)

    }

    private fun initview() {
        var userdetails: MutableList<User> = mutableListOf()
        userdetails.addAll(
            db.getAllDetailsUserByEmailId(
                PrefUtils.getUserEmail(this),
                PrefUtils.getUserID(this)
            )
        )
        if (userdetails.size > 0) {
            txtUserName_MyProffile.text = userdetails.get(0).username
            txtAddress_MyProfile.text = userdetails.get(0).address1 + "," + userdetails.get(0).city
            passCount=userdetails.get(0).userpassword.length
            var sb=StringBuilder()
            for (k in 0 until passCount)
            {
                sb.append("*")
            }
            txtHidePassword.text = sb.toString().trim()
            try {

                imgUserImage_MyProfile.setImageBitmap(Functions.getImage(userdetails.get(0).userImage!!))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        var userFavoriteList: MutableList<User_Favorite> = mutableListOf()
        userFavoriteList.addAll(db.getAllItemUserFavorite(PrefUtils.getUserID(this).toInt()))
        txtFavoriteitemCount.text = userFavoriteList.size.toString()
        CreateFavoriteView(userFavoriteList)

        /*rvFavorite_MyProfile.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFavorite_MyProfile.adapter = MyFavoriteAdapter(this)*/

        cvChangePassword.setOnClickListener {
            ChangePasswordActivity.launchActivity(this)

        }
        txtMyAddress.setOnClickListener {
            EditProfileActivity.launchActivity(this)
        }
        llProfileLoction.setOnClickListener {
            AddressActivity.launchActivity(this)
        }
        txtToolbar.setText(R.string.my_profile)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }

    }

    private fun CreateFavoriteView(userFavoriteList: MutableList<User_Favorite>) {

        val parentLayout = findViewById<View>(R.id.llFavorite) as LinearLayout
        parentLayout.removeAllViews()
        val layoutInflater = layoutInflater
        var view: View


        for (i in 0 until userFavoriteList.size) {
            view = layoutInflater.inflate(R.layout.item_favorite, parentLayout, false)
            val llFavoriteView = view.findViewById(R.id.llItemFavorite) as LinearLayout
            val favoriteImage = view.findViewById(R.id.imgAddFavorite) as ImageView

            Glide.with(this)
                .load(userFavoriteList.get(i).FavoriteItemImage)
                .into(favoriteImage)

            /* llFavoriteView.setOnClickListener {
             }*/

            parentLayout.addView(view)
        }
    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }
}
