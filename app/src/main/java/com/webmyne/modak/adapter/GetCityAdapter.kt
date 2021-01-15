package com.webmyne.modak.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.webmyne.modak.R
import com.webmyne.modak.model.ResponsPojo.ResultX
import java.util.*

open class GetCityAdapter(
    context: Context, resource: Int, textviewId: Int,
    stateList: List<ResultX>,
    var onCityselected: oncityselected
) : ArrayAdapter<ResultX>(context, resource, textviewId, stateList) {


    internal var flater: LayoutInflater
    private var stateList: List<ResultX>? = null
    private val resource: Int

    init {
        flater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.stateList = stateList
        this.resource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        val txt = view.findViewById(R.id.txtItem) as TextView
        txt.text = stateList!!.get(position).name
        onCityselected.oncityselected(stateList!!.get(position).name)
        return view

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        val txt = view.findViewById(R.id.txtItem) as TextView
        txt.setTextColor(ContextCompat.getColor(context, R.color.black))
        txt.text = stateList!!.get(position).name
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(20, 30, 20, 30)
        txt.layoutParams = params
        return view
    }

    override fun getCount(): Int {
        return stateList!!.size
    }

    fun setStateList(list: List<ResultX>) {
        this.stateList = ArrayList<ResultX>()
        this.stateList = list
        notifyDataSetChanged()
    }

    interface oncityselected {
        fun oncityselected(cityName: String)
    }

}
