package com.webmyne.fooddelivery.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.webmyne.fooddelivery.R
import kotlinx.android.synthetic.main.activity_city.*

class CityActivity: AppCompatActivity() {

internal lateinit var sp:Spinner
    internal lateinit var csp:Spinner

    var spinner:Spinner?=null
var txt_view:TextView?=null
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    sp=findViewById(R.id.spinerCountry) as Spinner
    csp=findViewById(R.id.spinerCity)as Spinner
    val country = arrayOf("Country", "Australia", "Brazil", "China", "France", "Germany", "India", "Ireland", "Italy", "Mexico", "Poland")
    var city = arrayOf("City","Vadodara", "Mumbai", "Delhi", "Banglore", "Ranchi", "Surat", "Ahemdabad", "Chennai", "kolkata", "Pune", "Bubhneswar")
     val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item, country)
    val cityadapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,city)
    sp.adapter=adapter
    csp.adapter=cityadapter

    }
}