package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_savecard.*

class SaveCardActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savecard)
        imgPaymentBackIcon.setOnClickListener {
            var back=Intent(this,CardActivity::class.java)
            startActivity(back)
        }
        txtSaveCard.setOnClickListener {
            var back=Intent(this,CardActivity::class.java)
            startActivity(back)
        }
        val spinnercard: Spinner =spinerCardType
        val spinnerYear: Spinner =spinnerYear
        val spinnerMonth: Spinner =spinerMonth
        val cardType = arrayOf("Master", "Visa", "Rupay","Phone Pay", "Cash", "Apple Pay","Google Pay")
        var month = arrayOf(" 1 jan","2 fab", "3 march", "4 april", "5 may", "6 jun", "7 july", "8 aug", "9 sept", "10 oct", "11 Nov", "12 Dec")
        var Year = arrayOf("2018","2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029")
        spinnercard.adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item, cardType)
        spinnerMonth.adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,month)
        spinnerYear.adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,Year)
    }
}