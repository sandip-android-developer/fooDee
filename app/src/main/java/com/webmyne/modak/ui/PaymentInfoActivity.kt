package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import com.webmyne.modak.R
import com.webmyne.modak.custome.TfTextView
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.User_PaymentInfo
import kotlinx.android.synthetic.main.activity_card.*

class PaymentInfoActivity : BaseActivity() {

    var bundle = Bundle()
    val db: DatabaseHelper = DatabaseHelper(this)
    var list: List<String> = java.util.ArrayList()
    var dbByteArrayImage: ByteArray? = null
    var cardNumber=0

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, PaymentInfoActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        initview()
        actionListner()

    }


    private fun actionListner() {

        val intent = intent
        /* if (db.getAllDetailsUser().get(0).userPayment!=null)
         {
             list=db.getAllDetailsUser().get(0).userPaymentIfo
             var list:List<String> = ArrayList()
             list=intent.getStringArrayListExtra("cardDetails")
         }*/

        imgbackModakMoney.setOnClickListener {
            onBackPressed()
        }
        /*rvCardPayment.layoutManager = LinearLayoutManager(this)
        rvCardPayment.adapter = CardAdapter(this)*/
    }

    private fun initview() {
        imgAddCard.setOnClickListener {
            SaveCardActivity.launchActivity(this)

        }
        txtmakePayment.setOnClickListener {
            OrderConfirmationActivity.launchActivity(this)
        }

    }

    private fun CreateCardList(userPaymentListList: MutableList<User_PaymentInfo>) {

        val parentLayout = findViewById<View>(R.id.llCardList) as LinearLayout
        parentLayout.removeAllViews()
        val layoutInflater = layoutInflater
        var view: View


        for (i in 0 until userPaymentListList.size) {
            view = layoutInflater.inflate(R.layout.item_card_details, parentLayout, false)
            val llItemCard = view.findViewById(R.id.llItemCard) as LinearLayout
            val cardTypeImage = view.findViewById(R.id.imgCardType) as ImageView
            val cNumber = view.findViewById(R.id.txtCardNumber) as TfTextView
            val rbCheck = view.findViewById(R.id.rbCheck) as RadioButton
            cardTypeImage.setImageBitmap(Functions.getImage(userPaymentListList.get(i).cardTypeImage!!))
            cardNumber=userPaymentListList.get(i).cardNumber.length
            println("cardNumber--"+cardNumber)
            var sb= StringBuilder()
            var dummy:Int=4
            for (k in 0 until cardNumber)
            {
                if (k>=cardNumber-4)
                {
                    sb.append(userPaymentListList.get(i).cardNumber.get(k))
                }
                else
                {
                    if (k==dummy && k>0)
                    {
                        dummy=dummy+5
                        sb.append(" ")
                    }
                    else
                    {
                        sb.append("*")
                    }
                }
            }
            cNumber.text = sb.toString().trim()
            if (userPaymentListList.get(i).isDefault)
            {
                rbCheck.isChecked=true
            }
            else
            {
                rbCheck.isChecked=false
            }
            rbCheck.setOnClickListener {
                rbCheck.isChecked=false
                db.updateDefaultPaymentOption(userPaymentListList.get(i).cardId)
                rbCheck.isChecked=true
            }
            parentLayout.addView(view)
        }
    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }

    override fun onResume() {
        super.onResume()
        var paymentlist:MutableList<User_PaymentInfo> = mutableListOf()
        paymentlist.addAll(db.getUserCardByUserId(PrefUtils.getUserID(this).toInt()))
        CreateCardList(paymentlist)
    }
}
