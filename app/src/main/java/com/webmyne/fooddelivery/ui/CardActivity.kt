package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.model.SaveCardDetails
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.home_recycler.*

class CardActivity: AppCompatActivity() {
  /*  private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<CardAdapter.cardVH>? = null*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        imgAddCard.setOnClickListener {
            var addACard=Intent(this,SaveCardActivity::class.java)
            startActivity(addACard)
        }
        imgbackCard.setOnClickListener {
            var backCard=Intent(this,DashboardActivity::class.java)
            startActivity(backCard)
        }
      /*  layoutManager = LinearLayoutManager(this)
        rv_homeItemDetails.layoutManager = layoutManager

        adapter = CardAdapter()
        rv_homeItemDetails.adapter = adapter*/
    }

}