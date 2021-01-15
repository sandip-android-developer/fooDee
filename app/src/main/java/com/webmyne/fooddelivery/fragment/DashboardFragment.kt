package com.webmyne.fooddelivery.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.ui.BaseActivity

@SuppressLint("ValidFragment")
class DashboardFragment(baseActivity: BaseActivity) :BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_item_list, container, false)

    }
    companion object{
        fun getInstance(baseActivity: BaseActivity): DashboardFragment {
            return DashboardFragment(baseActivity)
        }
    }
}