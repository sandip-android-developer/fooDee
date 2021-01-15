package com.webmyne.modak.helper

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.webmyne.modak.custome.MDToast
import com.webmyne.modak.ui.BaseActivity
import com.webmyne.modak.ui.LoginActivity
import java.util.regex.Pattern
import android.graphics.BitmapFactory
import android.graphics.Bitmap


object Functions {
    val EMAIL_PATTERN: String =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    var dialogOptionsSelectedListener: DialogOptionsSelectedListener? = null

    fun fireIntent(baseActivity: BaseActivity, cls: Class<*>, isNewActivity: Boolean) {
        val i = Intent(baseActivity, cls)
        baseActivity.startActivity(i)
        if (!isNewActivity) {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_left,
                com.webmyne.modak.R.anim.slide_out_right
            )
        } else {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_right,
                com.webmyne.modak.R.anim.slide_out_left
            )
        }
    }

    fun fireIntent(
        baseActivity: BaseActivity,
        intent: Intent,
        isNewActivity: Boolean,
        isFinished: Boolean
    ) {
        if (isFinished)
            baseActivity.finish()
        baseActivity.startActivity(intent)
        if (!isNewActivity) {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_left,
                com.webmyne.modak.R.anim.slide_out_right
            )
        } else {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_right,
                com.webmyne.modak.R.anim.slide_out_left
            )
        }
    }

    /* fun showToast(activity: BaseActivity, message: String, type: Int) {
         val mdToast = MDToast.makeText(activity, message, MDToast.LENGTH_SHORT, type)
         mdToast.show()
     }*/
    fun fireIntent(baseActivity: BaseActivity, intent: Intent, isNewActivity: Boolean) {
        baseActivity.startActivity(intent)
        if (!isNewActivity) {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_left,
                com.webmyne.modak.R.anim.slide_out_right
            )
        } else {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_right,
                com.webmyne.modak.R.anim.slide_out_left
            )
        }
    }

    fun fireIntent(baseActivity: BaseActivity, isNewActivity: Boolean) {
        baseActivity.finish()
        if (!isNewActivity) {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_left,
                com.webmyne.modak.R.anim.slide_out_right
            )
        } else {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_right,
                com.webmyne.modak.R.anim.slide_out_left
            )
        }
    }

    fun fireIntentForResult(
        baseActivity: BaseActivity,
        cls: Class<*>,
        requestCode: Int,
        isNewActivity: Boolean
    ) {
        val intent = Intent(baseActivity, cls)
        baseActivity.startActivityForResult(intent, requestCode)
        if (!isNewActivity) {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_left,
                com.webmyne.modak.R.anim.slide_out_right
            )
        } else {
            baseActivity.overridePendingTransition(
                com.webmyne.modak.R.anim.slide_in_right,
                com.webmyne.modak.R.anim.slide_out_left
            )
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
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
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
        val mdToast = MDToast.makeText(context, message, MDToast.LENGTH_SHORT, type)
        mdToast.show()
    }

    fun getImage(image: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }

    fun setRatingStarColor(drawable: Drawable, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(drawable, color)
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

    fun showAlertDialogWithTwoOption(
        mContext: Context,
        positiveText: String,
        negativeText: String,
        message: String,
        dialogOptionsSelectedListener: DialogOptionsSelectedListener?
    ) {
        val builder = AlertDialog.Builder(mContext)
        builder.setMessage(message)
            .setCancelable(true)

        if (positiveText.trim { it <= ' ' }.length > 0) {
            builder.setPositiveButton(positiveText) { dialog, which ->
                if (dialogOptionsSelectedListener != null)
                    dialogOptionsSelectedListener.onSelect(true)
                dialog.dismiss()
            }
        }
        if (negativeText.trim { it <= ' ' }.length > 0) {
            builder.setNegativeButton(negativeText) { dialog, which ->
                if (dialogOptionsSelectedListener != null)
                    dialogOptionsSelectedListener.onSelect(false)
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
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }


    fun getCircularBitmap(bitmapimage: Bitmap): Bitmap {
        var squareBitmapWidth = Math.min(bitmapimage.width, bitmapimage.height)
        val dstBitmap = Bitmap.createBitmap(
            squareBitmapWidth, // Width
            squareBitmapWidth, // Height
            Bitmap.Config.ARGB_8888 // Config
        )
        val canvas = Canvas(dstBitmap)

        val paint = Paint()
        paint.isAntiAlias = true
        val rect = Rect(0, 0, squareBitmapWidth, squareBitmapWidth)
        var rectF = RectF(rect)
        canvas.drawOval(rectF, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmapimage, rect, rect, paint)
        //bitmapimage.recycle()
        return dstBitmap
    }

    fun logoutDialog(context: BaseActivity) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setMessage("Are you sure want to logout this application?")

        // set dialog message

        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("YES", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    val intent: Intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                    dialog!!.dismiss()
                }

            })
            .setNegativeButton("NO", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.dismiss()
                }

            })


        // create alert dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        // show it
        alertDialog.show()
    }

}

