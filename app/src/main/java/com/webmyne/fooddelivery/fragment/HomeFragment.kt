package com.webmyne.fooddelivery.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_home_recycler.*

@SuppressLint("ValidFragment")
class HomeFragment (baseActivity: BaseActivity) : BaseFragment(){

    init {
        setBaseActivity(baseActivity)
    }

    companion object {
        fun getInstance(baseActivity: BaseActivity): HomeFragment {
            return HomeFragment(baseActivity)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMethod()
        actionListner()
    }

    private fun actionListner() {


    }

    private fun initMethod() {

        rvHome.layoutManager = LinearLayoutManager(getBaseActivity()!!)
    }
}