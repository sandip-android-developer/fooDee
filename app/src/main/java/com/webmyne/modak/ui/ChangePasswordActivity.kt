package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.webmyne.modak.R
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.toolbar.*

class ChangePasswordActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var bundle = Bundle()

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, ChangePasswordActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        initview()
        actionListner()


    }

    private fun actionListner() {
        txtToolbar.setText(R.string.change_password)
        imgBackActivity.setOnClickListener {
            finish()
        }
    }

    private fun initview() {
        txtChangePass.setOnClickListener {
            if (edtCurrentPass.text.toString().trim().length == 0) {
                edtCurrentPass.error = "Please Enter Current Password"
                //Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!db.isUserAlreadyExist(
                    PrefUtils.getUserEmail(this),
                    edtCurrentPass.text.toString().trim()
                )
            ) {
                edtCurrentPass.error = "Current Password is Not Exist"
                Toast.makeText(this, "Current Password is Not Exist", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (edtNewPass.text.toString().trim().length == 0) {
                edtNewPass.error = "Please Enter New Password"
                //Toast.makeText(this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (edtNewCnfPass.text.toString().trim().length == 0) {
                edtNewCnfPass.error = "Please Enter Confirm Password"
                //Toast.makeText(this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!edtNewPass.text.toString().trim().equals(edtNewCnfPass.text.toString().trim())) {
                edtNewCnfPass.error = "Password And Confirm Password Is Mismatch"
                return@setOnClickListener
            } else {
                Log.e("Email", "Email" + PrefUtils.getUserEmail(this))

                db.updateCurrentPassword(edtNewCnfPass.text.toString().trim(), PrefUtils.getUserEmail(this))
                LoginActivity.launchActivity(this)
            }


        }
    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }
}