package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.webmyne.modak.R
import com.webmyne.modak.custome.TfTextView
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import kotlinx.android.synthetic.main.activity_promocode.*
import kotlinx.android.synthetic.main.toolbar.*

class PromoCodeActivity : BaseActivity() {

    var bundle = Bundle()


    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, PromoCodeActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promocode)
        initview()
        actionListner()

    }


    private fun actionListner() {


    }

    private fun initview() {
        PromoCodeView()
        txtToolbar.setText("Promo Code")
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }
        txtBtnEdtApply.setOnClickListener {
            txtNotAppicable.visibility=View.VISIBLE
        }

    }

    private fun PromoCodeView() {
        val parentLayout = findViewById<View>(R.id.llPromoCode) as LinearLayout
        parentLayout.removeAllViews()
        val layoutInflater = layoutInflater
        var view: View

        //  for (i in 0 until selectedRemId.size - 1) {
        for (i in 0 until 2) {
            view = layoutInflater.inflate(R.layout.item_promo_code, parentLayout, false)
            val txtApplyProme = view.findViewById(R.id.txtApplyProme) as TfTextView
            val txtOffers = view.findViewById(R.id.txtOffers) as TfTextView
            val txtOffers_Promo = view.findViewById(R.id.txtOffers_Promo) as TfTextView
            val txtPromoDetails = view.findViewById(R.id.txtPromoDetails) as TfTextView

            if (i == 1) {

                txtOffers.text = "Get 50% Off"
                txtOffers_Promo.text = "Grab 50% Off"
                txtPromoDetails.text = "New User Offers:Get 50% Off Up To Rs.100 on First 5 Order "
            } else {

                txtOffers.text = "Get 30% Off"
                txtOffers_Promo.text = "Flat 30% Off"
                txtPromoDetails.text = "Get 30% Off On Min Order Rs.99 "
            }


            txtApplyProme.setOnClickListener {
                PrefUtils.setCoupanApply(this,i.toString())
                finish()

            }

            parentLayout.addView(view)
        }
    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

    interface OnClickApply {
        fun OnClickApply(flag: Int)
    }
}
