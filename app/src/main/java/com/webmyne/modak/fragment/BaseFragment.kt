@file:Suppress("DEPRECATION")

package com.webmyne.modak.fragment

import android.app.ProgressDialog
import android.support.v4.app.Fragment
import android.view.View
import com.webmyne.modak.R
import com.webmyne.modak.ui.BaseActivity


open class BaseFragment : Fragment() {
    private var dialog: ProgressDialog? = null
    /*protected UserDetails userDetails;*/
    protected val viewbase: View? = null
    /**
     * The Activity.
     */
    /**
     * Gets base activity.
     *
     * @return the base activity
     */
    /**
     * Sets base activity.
     *
     * @param baseActivity the base activity
     */
    private var baseActivity: BaseActivity? = null

    /**
     * On fragment back press boolean.
     *
     * @return the boolean
     */
    fun onFragmentBackPress(): Boolean {
        return false
    }

    fun getBaseActivity(): BaseActivity? {
        return baseActivity
    }

    fun setBaseActivity(baseActivity: BaseActivity) {
        this.baseActivity = baseActivity
    }

    fun showProgressDialog(isCancelable: Boolean) {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
        dialog = ProgressDialog(baseActivity)
        dialog!!.setMessage(baseActivity!!.getString(R.string.msg_please_wait))
        dialog!!.setCancelable(isCancelable)
        dialog!!.show()
    }

    fun hideProgressDialog() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}