package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.webmyne.modak.R
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.User
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, SignupActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initview()
        actionListner()

    }

    private fun actionListner() {
        txtAlreayAMemeber.setOnClickListener {
           onBackPressed()
        }

    }

    private fun initview() {
        Functions.hideKeyPad(this,textRegister)
        chkIos.setOnClickListener {V->
            Functions.hideKeyPad(this,V)
        }
        textRegister.setOnClickListener {
            if (edtUserName.text.toString().trim().length == 0) {
                edtUserName.error = "Please Enter UserName"
                return@setOnClickListener
            }
            if (edtEmail.text.toString().trim().length == 0) {
                edtEmail.error = "Please Enter Email"
                return@setOnClickListener
            }
            if (!Functions.emailValidation(edtEmail.text.toString().trim())) {
                edtEmail.error = "Please Enter Valid Email"
                return@setOnClickListener
            }
            if (db.isEmailIdAlreadyExist(edtEmail.text.toString().trim())) {
                edtEmail.error = "This Email is already Exist"
                return@setOnClickListener
            }
            if (edtMobile.text.toString().trim().length == 0) {
                edtMobile.error = "Please Enter Mobile Number"
                return@setOnClickListener
            }
            if (edtMobile.text.toString().trim().length != 10) {
                edtMobile.error = "Please Enter 10 digit Mobile Number"
                return@setOnClickListener
            }
            if (db.isPhoneAlreadyExist(edtMobile.text.toString().trim())) {
                edtMobile.error = "This Mobile No is already Exist"
                return@setOnClickListener
            }
            if (edtPass.text.toString().trim().length == 0) {
                edtPass.error = "Please Enter Password"
                return@setOnClickListener
            }
            if (edtCnfPass.text.toString().trim().length == 0) {
                edtCnfPass.error = "Please Enter Confirm Password"
                return@setOnClickListener
            }
            if (!edtPass.text.toString().trim().equals(edtCnfPass.text.toString().trim())) {
                edtCnfPass.error = "Password And Confirm Password Is Mismatch"
                return@setOnClickListener
            }
            if (!chkIos.isChecked) {
                Toast.makeText(
                    this,
                    getString(R.string.please_accpet_terms_and_condition),
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            } else {

                var userdetails = User()
                userdetails.username = edtUserName.text.toString().trim()
                userdetails.useremail = edtEmail.text.toString().trim()
                userdetails.usermobile = edtMobile.text.toString().trim()
                userdetails.userpassword = edtCnfPass.text.toString().trim()
                var userId = db.insertUserFoodData(userdetails)
                PrefUtils.setUserID(this, userId)
                Toast.makeText(
                    applicationContext,
                    "Account Successfully Created ",
                    Toast.LENGTH_LONG
                ).show()
                //OTPActivity.launchActivity(this,edtMobile.text.toString())
                PrefUtils.setUserLoggedIn(this, true)
                PrefUtils.setUserEmail(this,edtEmail.text.toString().trim())
                DashboardActivity.launchActivity(this)

             //  OTPActivity.launchActivity(this,edtMobile.text.toString().trim())
                //fbRefrence=FirebaseDatabase.getInstance().reference.child("User")
            }
        }

    }


    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }


}