package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.webmyne.modak.R
import com.webmyne.modak.adapter.CardTypeSpinAdapter
import com.webmyne.modak.adapter.FAQAdapter
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.activity_help.*
import kotlinx.android.synthetic.main.toolbar.*

class HelpActivity : BaseActivity() {

    var bundle = Bundle()
    internal lateinit var sp: Spinner
    var faqAdapter: FAQAdapter? = null

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, HelpActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        initview()
        actionListner()

    }


    private fun actionListner() {
        val selectIssueType = arrayOf("Select Type", "General", "Suggestions", "Complaints")
        if (spinerReason != null) {

            var accountTypeAdapter = CardTypeSpinAdapter(this, selectIssueType)
            spinerReason.adapter = accountTypeAdapter
            spinerReason.setSelection(0)

            spinerReason.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

    }

    private fun initview() {
        rvFaq.layoutManager = LinearLayoutManager(this)
        faqAdapter = FAQAdapter(this)
        rvFaq.adapter = faqAdapter

        txtToolbar.setText(R.string.help)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }
}
