package com.webmyne.modak.ui

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.facebook.FacebookSdk
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.PlacesClient
import com.webmyne.modak.R
import com.webmyne.modak.adapter.MyAddressAdapter
import com.webmyne.modak.adapter.PlacesAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.User_Address
import kotlinx.android.synthetic.main.activity_myaddress.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.IOException
import java.util.*


class MyAddressActivity : BaseActivity() {
    var placesAdapter: PlacesAdapter? = null
    var placesClient: PlacesClient? = null
    var db = DatabaseHelper(this)
    var bounds = RectangularBounds.newInstance(
        LatLng(-33.880490, 151.184363),
        LatLng(-33.858754, 151.229596)
    )
    var lat: Double = 0.00
    var longitute: Double = 0.00

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, MyAddressActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myaddress)
        initview()
        actionListner()

    }

    private fun actionListner() {

        autoCompleteTextView.setOnItemClickListener(
            { parent, view, position, id ->
                Functions.hideKeyPad(this, view)
                val geocoder = Geocoder(this, Locale.getDefault())
                var address: List<Address>
                lateinit var location: Address
                try {
                    address = geocoder.getFromLocationName("", 1)
                    if (address != null) {
                        location = address.get(0)
                        lat = location.latitude
                        longitute = location.longitude
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            })
    }

    private fun initview() {
        var userAddressList:MutableList<User_Address> = mutableListOf()
        userAddressList.addAll(db.getUserAddressByUserId(PrefUtils.getUserID(this).toInt()))

        rvMyAddress.layoutManager = LinearLayoutManager(this)
        rvMyAddress.adapter = MyAddressAdapter(this, userAddressList)
        // Places.initialize(getApplicationContext()!!,getApplicationContext()!!.resources.getString(R.string.google_api_key))
        Places.initialize(
            FacebookSdk.getApplicationContext()!!,
            "AIzaSyBuv21TYl3GzsoFb2lmhsM_Dn4cD36t8OI"
        )
        placesClient = Places.createClient(FacebookSdk.getApplicationContext()!!)
        println("Addapter Comming")
        placesAdapter = PlacesAdapter(
            FacebookSdk.getApplicationContext()!!,
            android.R.layout.simple_list_item_1,
            bounds
        )
        autoCompleteTextView.setAdapter(placesAdapter)
        /*  autoCompleteTextView.addTextChangedListener(object : TextWatcher {
              private val TRIGGER_SERACH = 1
              private val SEARCH_TRIGGER_DELAY_IN_MS: Long = 300

              private val handler = object : Handler() {
                  override fun handleMessage(msg: Message) {
                      if (msg.what == TRIGGER_SERACH) {
                          try {
                              //if (name.text.toString().trim().length > 0) {
                                  autoCompleteTextView.setAdapter(placesAdapter)
                              //}
                          } catch (e: Exception) {
                              e.printStackTrace()
                          }

                      }
                  }
              }*/

        /*   override fun afterTextChanged(p0: Editable?) {
               var length = p0?.length
               if (length!!.toInt() > 0) {
                  // name.text = autoCompleteTextView.text.toString()
               } else {
                  // name.text = ""
               }
               handler.removeMessages(TRIGGER_SERACH)
               handler.sendEmptyMessageDelayed(TRIGGER_SERACH, SEARCH_TRIGGER_DELAY_IN_MS)
           }

           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

           }
       })*/
        txtToolbar.setText(R.string.my_address)
        imgBackActivity.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        Functions.fireIntent(this,false)
    }
}