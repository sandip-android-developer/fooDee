package com.webmyne.modak.custome

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.StrictMode
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.webmyne.modak.R
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.dialog_logout.*


class LogoutDialog(
    val context: BaseActivity,
    var flag: Int,
    var onClickYes: OnClickYes
) :
    Dialog(context) {

    init {
        init()
    }

    private fun init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_logout)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.attributes = lp
        window!!.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )
        window!!.setFlags(
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        )
        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        show()
        initView()
        actionListner()

    }

    private fun actionListner() {
        if (flag==2)
        {
            txtLogout.text="Are You Sure Want To Exit ??"
        }
        txtCancel_logout.setOnClickListener { v ->
            Functions.hideKeyPad(context, v)
            dismiss()
        }
        txtExit_logout.setOnClickListener { v ->

            onClickYes.onClickYes(flag)
            dismiss()
            /* LoginActivity.launchActivity(context)
               dismiss()*/
        }

    }

    private fun initView() {

    }

    interface OnClickYes {
        fun onClickYes(flag: Int)
    }

}

