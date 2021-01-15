package com.webmyne.fooddelivery.helper

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.ui.BaseActivity
import java.util.regex.Pattern

object Functions {
        val EMAIL_PATTERN: String = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    var dialogOptionsSelectedListener: DialogOptionsSelectedListener? = null

        fun fireIntent(baseActivity: BaseActivity, cls: Class<*>, isNewActivity: Boolean) {
            val i = Intent(baseActivity, cls)
            baseActivity.startActivity(i)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        fun fireIntent(baseActivity: BaseActivity, cls: Class<*>, isNewActivity: Boolean, isFinished: Boolean) {
            if (isFinished)
                baseActivity.finish()
            val i = Intent(baseActivity, cls)
            baseActivity.startActivity(i)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        open fun fireIntent(baseActivity: BaseActivity, intent: Intent, isNewActivity: Boolean) {
            baseActivity.startActivity(intent)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        open fun fireIntent(baseActivity: BaseActivity, isNewActivity: Boolean) {
            baseActivity.finish()
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        open fun fireIntentForResult(baseActivity: BaseActivity, cls: Class<*>, requestCode: Int, isNewActivity: Boolean) {
            val intent = Intent(baseActivity, cls)
            baseActivity.startActivityForResult(intent, requestCode)
            if (!isNewActivity) {
                baseActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            } else {
                baseActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

    fun getRegularFont(_context: Context): Typeface {
        return Typeface.createFromAsset(_context.assets, "fonts/TheSans Plain.ttf")
    }

    fun getBoldFont(_context: Context): Typeface {
        return Typeface.createFromAsset(_context.assets, "fonts/bold.ttf")
    }

    fun emailValidation(email: String): Boolean {
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun hideKeyPad(context: Context, view: View) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun toStr(editText: EditText): String {
        return editText.text.toString().trim { it <= ' ' }
    }

    fun toLength(editText: EditText): Int {
        return editText.text.toString().trim { it <= ' ' }.length
    }

    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun showToast(context: Context, message: String, type: Int) {
       // val mdToast = Tost.makeText(context, message, MDToast.LENGTH_SHORT, type)
       // mdToast.show()
    }

    fun showAlertDialogWithTwoOption(mContext: Context, positiveText: String, negativeText: String, message: String, dialogOptionsSelectedListener: DialogOptionsSelectedListener?) {
        val builder = AlertDialog.Builder(mContext)
        builder.setMessage(message)
                .setCancelable(true)

        if (positiveText.trim { it <= ' ' }.length > 0) {
            builder.setPositiveButton(positiveText) { dialog, which ->
                if (dialogOptionsSelectedListener != null)
                    dialogOptionsSelectedListener!!.onSelect(true)
                dialog.dismiss()
            }
        }
        if (negativeText.trim { it <= ' ' }.length > 0) {
            builder.setNegativeButton(negativeText) { dialog, which ->
                if (dialogOptionsSelectedListener != null)
                    dialogOptionsSelectedListener!!.onSelect(false)
                dialog.dismiss()
            }
        }
        val alert = builder.create()
        alert.setCanceledOnTouchOutside(false)
        alert.setCancelable(false)
        alert.show()
    }

    interface DialogOptionsSelectedListener {
        fun onSelect(isYes: Boolean)
    }

    fun hideStatusBar(activity: BaseActivity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

}

