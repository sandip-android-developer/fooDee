package com.webmyne.modak.custome

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.webmyne.modak.R


class MDToast
/**
 * Construct an empty Toast object.  You must call [.setView] before you
 * can call [.show].
 *
 * @param activity The activity to use.  Usually your [Application]
 * or [Activity] object.
 */
(private val mContext: Context) : Toast(mContext) {
    private var mView: View? = null
    /**
     * @return the type of MDToast which is actual used.
     */
    /**
     * Set the type of the MDToast.
     *
     * @param type the type to set.
     */
    var type: Int = 0

    override fun setText(@StringRes resId: Int) {
        setText(mContext.getString(resId))
    }

    override fun setText(s: CharSequence) {
        if (mView == null) {
            throw RuntimeException("This Toast was not created with Toast.makeText()")
        }
        val tv = mView!!.findViewById(R.id.textToast) as TextView
        tv.text = s
    }

    /**
     * Set the icon resource id to display in the MD toast.
     *
     * @param iconId the resource id.
     */
    fun setIcon(@DrawableRes iconId: Int) {
        setIcon(ContextCompat.getDrawable(mContext, iconId))
    }

    /**
     * Set the icon to display in the MD toast.
     *
     * @param icon the drawable to set as icon.
     */
    fun setIcon(icon: Drawable?) {
        if (mView == null) {
            throw RuntimeException("This Toast was not created with Toast.makeText()")
        }
        val iv = mView!!.findViewById(R.id.iconToast) as ImageView
        iv.setImageDrawable(icon)
    }

    companion object {

        val TYPE_INFO = 0
        val TYPE_SUCCESS = 1
        val TYPE_WARNING = 2
        val TYPE_ERROR = 3

        var LENGTH_LONG = Toast.LENGTH_LONG
        var LENGTH_SHORT = Toast.LENGTH_SHORT

        fun makeText(context: Context, message: String, duration: Int = LENGTH_SHORT, type: Int = TYPE_INFO): MDToast {
            val mdToast = MDToast(context)

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.custom_toast_container, null)

            val icon = view.findViewById(R.id.iconToast) as ImageView
            val text = view.findViewById(R.id.textToast) as TextView
            when (type) {
                TYPE_SUCCESS -> {
                    icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_checked))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.background = ContextCompat.getDrawable(context, R.drawable.custom_toast_success_background)
                    } else
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSuccess))
                }
                TYPE_WARNING -> {
                    icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_hold))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.background = ContextCompat.getDrawable(context, R.drawable.custom_toast_warn_background)
                    } else
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWarning))
                }
                TYPE_ERROR -> {
                    icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_warning))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.background = ContextCompat.getDrawable(context, R.drawable.custom_toast_error_background)
                    } else
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorError))
                }
                else -> {
                    icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_warning))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        view.background = ContextCompat.getDrawable(context, R.drawable.custom_toast_info_background)
                    } else
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorInfo))
                }
            }

            text.text = message
            mdToast.duration = duration
            mdToast.view = view

            mdToast.mView = view
            mdToast.type = type
            return mdToast
        }
    }
}
