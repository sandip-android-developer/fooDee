package com.webmyne.modak.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.webmyne.modak.R
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.model.User_Address
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.address_list.view.*

class MyAddressAdapter(
    val context: BaseActivity,
    var userAddressList: MutableList<User_Address>
): RecyclerView.Adapter<MyAddressAdapter.AllUserVH>() {
    var rowIndex:Int = -1
    var db = DatabaseHelper(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUserVH {
        return AllUserVH(
            LayoutInflater.from(context).inflate(
                R.layout.address_list,
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder:AllUserVH, position: Int) {
        if (userAddressList.get(position).adressType==0)
        {
            holder.itemView.txtaddresstype.text = "Home"
        }
        else
        {
            holder.itemView.txtaddresstype.text = "Office"
        }
        if (userAddressList.get(position).defaultAddress)
        {
            holder.itemView.imgAddressSelected.visibility = View.VISIBLE
        }
        else
        {
            holder.itemView.imgAddressSelected.visibility = View.INVISIBLE
        }
        holder.itemView.txtAddressCity.text=userAddressList.get(position).address1+","+userAddressList.get(position).city
        holder.itemView.txtAddressCountry.text=userAddressList.get(position).state+","+userAddressList.get(position).zipcode+","+userAddressList.get(position).country


        holder.itemView.setOnClickListener {
            var useraddress = User_Address()
            useraddress.addressId = userAddressList.get(position).addressId
            useraddress.defaultAddress = true
            db.updateAddressTableByAddressId(useraddress)
            rowIndex = position
            notifyDataSetChanged()

        }

        if(rowIndex == -1 && position == 0){
            holder.itemView.imgAddressSelected.visibility = View.VISIBLE
        }else{
            if(rowIndex == position) {
                Log.e("Click","Click")
                holder.itemView.imgAddressSelected.visibility = View.VISIBLE
            }else {
                Log.e("Click1","Click1")
                holder.itemView.imgAddressSelected.visibility = View.INVISIBLE
            }
        }
        if (userAddressList.size-1==position)
        {
            holder.viewMyAddress.visibility=View.GONE
        }
        else
        {
            holder.viewMyAddress.visibility=View.VISIBLE
        }


    }
    override fun getItemCount(): Int {
        return userAddressList.size

    }
    class AllUserVH(view: View): RecyclerView.ViewHolder(view) {
        val imgAddressSelected=view.imgAddressSelected
        val viewMyAddress=view.viewMyAddress

    }
}