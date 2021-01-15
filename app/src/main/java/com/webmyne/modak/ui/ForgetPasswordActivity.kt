package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.webmyne.modak.custome.MDToast
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.activity_forget_password.*


class ForgetPasswordActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, ForgetPasswordActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.webmyne.modak.R.layout.activity_forget_password)
        initView()
        actionListner()
    }

    private fun actionListner() {
        /*imgBackForgetPassword.setOnClickListener {
            onBackPressed()
        }*/
        txtRegister.setOnClickListener {
            SignupActivity.launchActivity(this)
        }
    }

    private fun initView() {
        txtGetPass.setOnClickListener {
            if (!Functions.emailValidation(edtForgetEmail.text.toString().trim())) {
                Toast.makeText(this, "Email Id  not Valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!db.isEmailIdAlreadyExist(edtForgetEmail.text.toString().trim())) {
                Functions.showToast(
                    this,
                    getString(com.webmyne.modak.R.string.please_enter_registred_email),
                    MDToast.TYPE_INFO
                )
                return@setOnClickListener
            } else {
                Functions.showToast(
                    this,
                    getString(com.webmyne.modak.R.string.password_send_to_registered_login_id),
                    MDToast.TYPE_INFO
                )
                var list: List<String> = ArrayList()
                // list=db.getUserData(edtForgetEmail.text.toString().trim())
                Log.e("Address", "Address" + list)
                //txtFPassword.text=list.get(0)
            }
        }


    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }
}