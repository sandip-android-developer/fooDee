package com.webmyne.fooddelivery.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.ui.BaseActivity

@SuppressLint("ValidFragment")
class TakeoutFragment(baseActivity: BaseActivity) :BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_takeout, container, false)
    }
    companion object{
        fun getInstance(baseActivity: BaseActivity): TakeoutFragment {
            return TakeoutFragment(baseActivity)
        }
    }
}