package com.webmyne.fooddelivery.fragment

import android.annotation.SuppressLint
import android.arch.core.util.Function
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.ui.BaseActivity
import com.webmyne.fooddelivery.ui.ChefDetailsActivity
import com.webmyne.fooddelivery.ui.ItemDetailsActivity
import com.webmyne.fooddelivery.ui.LocationActivity
import kotlinx.android.synthetic.main.activity_home.*

@SuppressLint("ValidFragment")
class DeliveryFragment(baseActivity: BaseActivity) :BaseFragment() {
    var CountItem = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_basket, container, false)
        actionListner()
    }

    private fun actionListner() {
/* countPlus: ImageView = findViewById(R.id.imgHomeItemRightArrow)
        var countMinus: ImageView = findViewById(R.id.imgHomeItemLeftArrow)
        var Count: TextView = findViewById(R.id.txtHomeNumberOfItem)
        var chefname: ImageView = findViewById(R.id.imgHomeItemImage)
        chefname.setOnClickListener {
            DeliveryFragment.
            ContextCompat.startActivity(intent)
        }

        countMinus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (CountItem > 0) {
                    CountItem--

                    Count.text = "$CountItem"
                }
            }

        })
        countPlus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                CountItem++
                Count.text = "$CountItem"
            }

        })
    }
}
*/
    }
        companion object {
            fun getInstance(baseActivity: BaseActivity): DeliveryFragment {
                return DeliveryFragment(baseActivity)

            }
        }

}