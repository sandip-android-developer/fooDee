package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.webmyne.modak.R
import com.webmyne.modak.helper.Functions
import org.json.JSONException
import org.json.JSONObject


class ConfirmationActivity : BaseActivity() {
    var bundle = Bundle()

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, ConfirmationActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmationpayment)
        val intent = intent


        try {
            val jsonDetails = JSONObject(intent.getStringExtra("PaymentDetails"))

            //Displaying payment details
            showDetails(
                jsonDetails.getJSONObject("response"),
                intent.getStringExtra("PaymentAmount")
            )
        } catch (e: JSONException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

        initview()
        actionListner()


    }

    @Throws(JSONException::class)
    private fun showDetails(jsonDetails: JSONObject, paymentAmount: String) {
        //Views
        val textViewId = findViewById<TextView>(R.id.paymentId)
        val textViewStatus = findViewById<TextView>(R.id.paymentStatus)
        val textViewAmount = findViewById<TextView>(R.id.paymentAmount)

        //Showing the details from json object
        textViewId.text = jsonDetails.getString("id")
        textViewStatus.text = jsonDetails.getString("state")
        textViewAmount.text = "$paymentAmount USD"
    }

    private fun actionListner() {

    }

    private fun initview() {

    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }

}