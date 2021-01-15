package com.webmyne.modak.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.webmyne.modak.R

class CardTypeSpinAdapter(val context: Context, var list: Array<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            val mInflater: LayoutInflater = LayoutInflater.from(context)
            view = mInflater.inflate(R.layout.spinner_item, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }

        vh.txtAccount_Type.text = list.get(position)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(20, 30, 20, 30)
        vh.txtAccount_Type.layoutParams = params
        return view
    }

    override fun getItem(position: Int): Any? {

        return null

    }

    override fun getItemId(position: Int): Long {

        return 0

    }

    override fun getCount(): Int {
        return list.size
    }

    private class ItemRowHolder(row: View?) {

        val txtAccount_Type: TextView

        init {
            this.txtAccount_Type = row?.findViewById(R.id.txtItem) as TextView
        }
    }
}