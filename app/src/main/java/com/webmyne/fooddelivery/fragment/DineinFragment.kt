package com.webmyne.fooddelivery.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_city.*

@SuppressLint("ValidFragment")
class DineinFragment  constructor(baseActivity: BaseActivity) :BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_city, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner:Spinner=spinerCountry
        val cityspinner:Spinner=spinerCity
        val country = arrayOf("Country", "Australia", "Brazil", "China", "France", "Germany", "India", "Ireland", "Italy", "Mexico", "Poland")
        var city = arrayOf("City","Vadodara", "Mumbai", "Delhi", "Banglore", "Ranchi", "Surat", "Ahemdabad", "Chennai", "kolkata", "Pune", "Bubhneswar")
        spinner.adapter= ArrayAdapter(activity,android.R.layout.simple_spinner_item, country)
        cityspinner.adapter=ArrayAdapter(activity,android.R.layout.simple_spinner_item,city)
    }
    companion object{
        fun getInstance(baseActivity: BaseActivity): DineinFragment {
            return DineinFragment(baseActivity)
        }
    }
}