package com.webmyne.modak.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.webmyne.modak.adapter.CardTypeSpinAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.User_PaymentInfo
import kotlinx.android.synthetic.main.activity_savecard.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.ByteArrayOutputStream


class SaveCardActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var cardtype: String = ""
    var cardmonth: String = ""
    var cardyear: String = ""
    var cardType =
        arrayOf("Master", "Visa", "Rupay", "Phone Pe", "Google Pay", "Google Wallet", "Paytm")
    var month = arrayOf(
        " 1 jan",
        "2 fab",
        "3 march",
        "4 april",
        "5 may",
        "6 jun",
        "7 july",
        "8 aug",
        "9 sept",
        "10 oct",
        "11 Nov",
        "12 Dec"
    )
    var Year = arrayOf(

        "2020",
        "2021",
        "2022",
        "2023",
        "2024",
        "2025",
        "2026",
        "2027",
        "2028",
        "2029",
        "2030",
        "2031",
        "2032",
        "2033",
        "2034",
        "2035"
    )
    private val space = ' '
    var bundle = Bundle()

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, SaveCardActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.webmyne.modak.R.layout.activity_savecard)
        initview()
        actionView()


    }

    private fun actionView() {
        txtToolbar.setText(com.webmyne.modak.R.string.payment_method)
        imgBackActivity.setOnClickListener {
            finish()
        }


        if (spinerCardType != null) {

            var accountTypeAdapter = CardTypeSpinAdapter(this, cardType)
            spinerCardType.adapter = accountTypeAdapter
            spinerCardType.setSelection(0)

            spinerCardType.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    cardtype = cardType.get(position)
                    println("CardSelected----" + cardtype)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        if (spinerMonth != null) {

            var accountTypeAdapter = CardTypeSpinAdapter(this, month)
            spinerMonth.adapter = accountTypeAdapter
            spinerMonth.setSelection(0)

            spinerMonth.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    cardmonth = month.get(position)

                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        if (spinnerYear != null) {

            var accountTypeAdapter = CardTypeSpinAdapter(this, Year)
            spinnerYear.adapter = accountTypeAdapter
            spinnerYear.setSelection(0)

            spinnerYear.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    cardyear = Year.get(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }


    }


    private fun initview() {

        val googleWallet = BitmapFactory.decodeResource(
            resources,
            com.webmyne.modak.R.drawable.google_wallet
        )
        val visaCard = BitmapFactory.decodeResource(
            resources,
            com.webmyne.modak.R.drawable.visa_card
        )
        val paytm =
            BitmapFactory.decodeResource(resources, com.webmyne.modak.R.drawable.paytm)
        val ruapayCard = BitmapFactory.decodeResource(
            resources,
            com.webmyne.modak.R.drawable.ic_rupay_card
        )
        val masterCard = BitmapFactory.decodeResource(
            resources,
            com.webmyne.modak.R.drawable.ic_mastercard
        )
        val googlePay = BitmapFactory.decodeResource(
            resources,
            com.webmyne.modak.R.drawable.ic_googlepay
        )
        val phonePe = BitmapFactory.decodeResource(
            resources,
            com.webmyne.modak.R.drawable.ic_phonepe
        )

        txtSaveCard.setOnClickListener {

            if (!Functions.isConnected(this)) {
                Toast.makeText(
                    this,
                    getString(com.webmyne.modak.R.string.internet_connection),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                var userPaymentinfo = User_PaymentInfo()
                userPaymentinfo.userId = PrefUtils.getUserID(this).toInt()
                userPaymentinfo.cardTypeName = cardtype
                if (cardtype.toString().trim().equals("Master")) {
                    userPaymentinfo.cardTypeImage = getBitmapAsByteArray(masterCard)
                } else if (cardtype.toString().trim().equals("Visa")) {
                    userPaymentinfo.cardTypeImage = getBitmapAsByteArray(visaCard)
                } else if (cardtype.toString().trim().equals("Rupay")) {
                    userPaymentinfo.cardTypeImage = getBitmapAsByteArray(ruapayCard)
                } else if (cardtype.toString().trim().equals("Phone Pe")) {
                    userPaymentinfo.cardTypeImage = getBitmapAsByteArray(phonePe)
                } else if (cardtype.toString().trim().equals("Google Pay")) {
                    userPaymentinfo.cardTypeImage = getBitmapAsByteArray(googlePay)
                } else if (cardtype.toString().trim().equals("Google Wallet")) {
                    userPaymentinfo.cardTypeImage = getBitmapAsByteArray(googleWallet)
                } else if (cardtype.toString().trim().equals("Paytm")) {
                    userPaymentinfo.cardTypeImage = getBitmapAsByteArray(paytm)
                }
                userPaymentinfo.holderName = edtHolderName.text.toString().trim()
                userPaymentinfo.cardexpiryMonth = cardmonth
                userPaymentinfo.cardExpiryYear = cardyear
                if (db.getUserCardByUserId(PrefUtils.getUserID(this).toInt()).size>0)
                {
                    userPaymentinfo.isDefault = false
                }
                else
                {
                    userPaymentinfo.isDefault = true
                }
                userPaymentinfo.cardNumber = edtCardnumber.text.toString().trim()
                db.InsertUserCardInfo(userPaymentinfo)
                onBackPressed()

            }

        }
        edtCardnumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                /*// Remove spacing char
                if (s!!.length > 0 && s!!.length % 5 == 0) {
                    val c = s.get(s.length - 1)
                    if (space == c) {
                        s.delete(s.length - 1, s.length)
                    }
                }*/
                // Insert char where needed.
                if (s!!.length > 0 && s.length % 5 == 0) {
                    val c = s.get(s.length - 1)
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(s.toString(), space.toString()).size <= 3
                    ) {
                        s.insert(s.length - 1, space.toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

}


