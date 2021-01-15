package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Spinner
import com.webmyne.modak.adapter.GetCityAdapter
import com.webmyne.modak.adapter.GetCountryAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.ResponsPojo.GetCountryResponse
import com.webmyne.modak.model.ResponsPojo.GetStateResponse
import com.webmyne.modak.model.ResponsPojo.Result
import com.webmyne.modak.model.ResponsPojo.ResultX
import com.webmyne.modak.model.RetroFitService
import com.webmyne.modak.model.User_Address
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class AddCartActivity : BaseActivity() {
    val db: DatabaseHelper = DatabaseHelper(this)
    var bundle = Bundle()
    var countryAdapter: GetCountryAdapter? = null
    var countryList: List<Result> = ArrayList()
    var cityAdapter: GetCityAdapter? = null
    var stateList: List<ResultX> = ArrayList()
    var countryId: String = ""
    var countryName: String = ""
    var cityName: String = ""

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, AddCartActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.webmyne.modak.R.layout.activity_basket)
        initview()
        actionListner()

    }

    private fun actionListner() {
        var spinner: Spinner = edtCountry
        var cityspinner: Spinner = edtCity
        txtSelectPayment.setOnClickListener {
            CardActivity.launchActivity(this)
        }
        txtToolbar.setText(com.webmyne.modak.R.string.basket)
        imgBackActivity.setOnClickListener {
            finish()
        }
        countryAdapter =
            GetCountryAdapter(
                this,
                com.webmyne.modak.R.layout.spinner_item,
                com.webmyne.modak.R.id.txtItem,
                countryList,
                object : GetCountryAdapter.oncountryselected {
                    override fun oncountryselected(ID: String, Countryname: String) {
                        countryId = ID
                        countryName = Countryname
                        Log.e("Id", "Id" + countryId)
                        stateListApi()
                    }

                })
        spinner.adapter = countryAdapter
        spinner.setSelection(0)

        cityAdapter =
            GetCityAdapter(
                this,
                com.webmyne.modak.R.layout.spinner_item,
                com.webmyne.modak.R.id.txtItem,
                stateList,
                object : GetCityAdapter.oncityselected {
                    override fun oncityselected(name: String) {
                        cityName = name
                        PrefUtils.setCityname(this@AddCartActivity, name)
                    }

                })
        cityspinner.adapter = cityAdapter
        cityspinner.setSelection(0)
        var useraddress = User_Address()
        useraddress.userId = PrefUtils.getUserID(this).toInt()
        useraddress.country = countryName
        useraddress.city = countryName
        useraddress.address1 = edtHomeAddress1.text.toString().trim()
        useraddress.zipcode = edtZipNumber.text.toString().trim()
        useraddress.state = countryName
        db.InsertUserAddress(useraddress)

    }

    private fun stateListApi() {
        if (!countryId.equals("")) {

            if (!Functions.isConnected(this)) {
                // Functions.showToast(this, getString(R.string.internet_connection), MDToast.TYPE_INFO)
            } else {

                RetroFitService.getService().getState("getStates", countryId)
                    .enqueue(object : Callback<GetStateResponse> {
                        override fun onFailure(call: Call<GetStateResponse>, t: Throwable) {
                            hideProgressDialog()
                            Log.e("Error", t.localizedMessage)

                        }

                        override fun onResponse(
                            call: Call<GetStateResponse>,
                            response: Response<GetStateResponse>
                        ) {
                            if (response.body() != null) {
                                Log.e("Response", "Response" + response.body()!!.result)

                                stateList = response.body()!!.result
                                val visitType = ResultX()
                                visitType.name = "Choose State"
                                visitType.id = "0"
                                (stateList as MutableList<ResultX>).add(0, visitType)
                                cityAdapter!!.setStateList(stateList)
                            }
                        }

                    })


            }
        }
    }

    private fun initview() {
        GetCountryApi()
        var list: List<String> = ArrayList()
        // list=db.getUserData(PrefUtils.getUserEmail(this))
        if (list.size > 0) {
            Log.e("Address", "Address" + list)
            val name = list.get(1)
            val separated = name.split(" ")
            edtFirstName.setText(separated[0])
            /* if (separated[1].equals(""))
             {
                 edtFirstName.setText( " ")
             }
             else
             {
                 edtFirstName.setText( separated[1])
             }*/

            edtEmailAddress.setText(PrefUtils.getUserEmail(this))
            edtPhoneNumebr.setText(list.get(2))
            Log.e("Address", "Address0" + list.get(0))
            Log.e("Address", "Address1" + list.get(1))
            Log.e("Address", "Address2" + list.get(2))
            Log.e("Address", "Address3" + list.get(3))
        }


    }

    private fun GetCountryApi() {
        if (!Functions.isConnected(this)) {
            // Functions.showToast(this, getString(R.string.internet_connection), MDToast.TYPE_INFO)
        } else {

            RetroFitService.getService().getCountry("getCountries").enqueue(responseCallback)
        }
    }

    val responseCallback = object : Callback<GetCountryResponse> {

        override fun onResponse(
            call: Call<GetCountryResponse>,
            response: Response<GetCountryResponse>
        ) {
            if (response.body() != null) {
                Log.e("Response", "Response" + response.body()!!.result)
                countryList = response.body()!!.result
                val visitType = Result()
                visitType.name = "Choose Country"
                visitType.id = "0"
                (countryList as MutableList<Result>).add(0, visitType)
                countryAdapter!!.setCountryList(countryList)
            }
        }

        override fun onFailure(call: Call<GetCountryResponse>, t: Throwable) {
            Log.e("Error", t.localizedMessage)
        }
    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

}