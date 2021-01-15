package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.webmyne.modak.adapter.CardAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import kotlinx.android.synthetic.main.activity_card.*
import java.util.*


class CardActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var list: List<String> = ArrayList()
    var bundle = Bundle()
    val PAYPAL_REQUEST_CODE = 123
    var PAYPAL_CLIENT_ID =
        "AV3DH-JhA47zWQnUGiwBI3Tb-Dhxn8jVlBGAcUMqEVV75AT0Re8kHe0FQBD9-9QbZdXFu0tQE6SBn1V0"
    //var config: PayPalConfiguration? = null
    var paymentAmount = ""

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, CardActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.webmyne.modak.R.layout.activity_card)
        /* config = PayPalConfiguration()
             .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
             .clientId(PAYPAL_CLIENT_ID)

         val intent = Intent(this, PayPalService::class.java)
         intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)*/
        startService(intent)
        initview()
        actionListner()


    }

    private fun actionListner() {
        val intent = intent
       /* if (db.getUserCardByUserId(PrefUtils.getUserID(this))!=null)
        {
            list=db.getAllDetailsUser().get(0).userPaymentIfo
            var list:List<String> = ArrayList()
            list=intent.getStringArrayListExtra("cardDetails")
        }*/
        imgAddCard.setOnClickListener {
            SaveCardActivity.launchActivity(this)

        }
        imgbackModakMoney.setOnClickListener {
            onBackPressed()

        }
        /*rvCardPayment.layoutManager = LinearLayoutManager(this)
        rvCardPayment.adapter = CardAdapter(this)*/
    }

    private fun initview() {
        // getPayment()
    }

    /* private fun getPayment() {
         paymentAmount = "200"

         //Creating a paypalpayment
         val payment = PayPalPayment(
             BigDecimal(paymentAmount), "USD", "Simplified Coding Fee",
             PayPalPayment.PAYMENT_INTENT_SALE
         )

         //Creating Paypal Payment activity intent
         val intent = Intent(this, PaymentActivity::class.java)

         //putting the paypal configuration to the intent
         intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)

         //Puting paypal payment to the intent
         intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)

         //Starting the intent activity for result
         //the request code will be used on the method onActivityResult
         startActivityForResult(intent, PAYPAL_REQUEST_CODE)
     }
 */
    /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         //If the result is from paypal
         if (requestCode == PAYPAL_REQUEST_CODE) {

             //If the result is OK i.e. user has not canceled the payment
             if (resultCode == Activity.RESULT_OK) {
                 //Getting the payment confirmation
                 val confirm =
                     data!!.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)

                 //if confirmation is not null
                 if (confirm != null) {
                     try {
                         //Getting the payment details
                         val paymentDetails = confirm.toJSONObject().toString(4)
                         Log.i("paymentExample", paymentDetails)

                         //Starting a new activity for the payment details and also putting the payment details with intent
                         startActivity(
                             Intent(this, ConfirmationActivity::class.java)
                                 .putExtra("PaymentDetails", paymentDetails)
                                 .putExtra("PaymentAmount", paymentAmount)
                         )

                     } catch (e: JSONException) {
                         Log.e("paymentExample", "an extremely unlikely failure occurred: ", e)
                     }

                 }
             } else if (resultCode == Activity.RESULT_CANCELED) {
                 Log.i("paymentExample", "The user canceled.")
             } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                 Log.i(
                     "paymentExample",
                     "An invalid Payment or PayPalConfiguration was submitted. Please see the docs."
                 )
             }
         }
     }*/

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

    public override fun onDestroy() {
        // stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }

}