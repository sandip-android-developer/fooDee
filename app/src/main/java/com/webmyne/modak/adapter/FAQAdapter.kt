package com.webmyne.modak.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.modak.R
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.item_faq.view.*

class FAQAdapter(
    val context: BaseActivity
) : RecyclerView.Adapter<FAQAdapter.AllUserVH>() {
    var flag: Int = 0
    var rowindex = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUserVH {
        return AllUserVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_faq,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: AllUserVH, position: Int) {

        holder.rlFaq.setOnClickListener {

            if (flag == 0) {
                flag = 1
                holder.txtFeqMore.visibility = View.VISIBLE
                holder.imgOpenFeq.setImageResource(R.drawable.ic_icon_colse_faq)
            } else {
                flag = 0
                holder.txtFeqMore.visibility = View.GONE
                holder.imgOpenFeq.setImageResource(R.drawable.ic_icon_open_faq)
            }
            rowindex = position
            notifyDataSetChanged()

        }
        if (rowindex != position) {
            holder.txtFeqMore.visibility = View.GONE
            holder.imgOpenFeq.setImageResource(R.drawable.ic_icon_open_faq)
        } else {
            holder.txtFeqMore.visibility = View.VISIBLE
            holder.imgOpenFeq.setImageResource(R.drawable.ic_icon_colse_faq)
        }


    }

    override fun getItemCount(): Int {
        return 5

    }

    class AllUserVH(view: View) : RecyclerView.ViewHolder(view) {
        val txtFeqMore = view.txtFeqMore
        val imgOpenFeq = view.imgOpenFeq
        val rlFaq = view.rlFaq

    }
}