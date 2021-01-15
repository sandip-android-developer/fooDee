package com.webmyne.modak.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Spinner
import com.facebook.FacebookSdk
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.PlacesClient
import com.webmyne.modak.adapter.GetCityAdapter
import com.webmyne.modak.adapter.GetCountryAdapter
import com.webmyne.modak.adapter.PlacesAdapter
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.ResponsPojo.GetCountryResponse
import com.webmyne.modak.model.ResponsPojo.GetStateResponse
import com.webmyne.modak.model.ResponsPojo.Result
import com.webmyne.modak.model.ResponsPojo.ResultX
import com.webmyne.modak.model.RetroFitService
import com.webmyne.modak.model.User_Address
import kotlinx.android.synthetic.main.activity_address.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*


class AddressActivity : BaseActivity(), LocationListener {

    var geocoder: GoogleMap? = null
    var countryAdapter: GetCountryAdapter? = null
    var countryList: List<Result> = ArrayList()
    var cityAdapter: GetCityAdapter? = null
    var stateList: List<ResultX> = ArrayList()
    var countryId: String = ""
    var bundle = Bundle()
    var placesAdapter: PlacesAdapter? = null
    var placesClient: PlacesClient? = null
    var bounds = RectangularBounds.newInstance(
        LatLng(-33.880490, 151.184363),
        LatLng(-33.858754, 151.229596)
    )
    var lat: Double = 0.00
    var longitute: Double = 0.00
    var countryName: String = ""
    var cityName: String = ""
    val db: DatabaseHelper = DatabaseHelper(this)
    var ZipCode: String = ""
    var fulladdress: String = ""
    var LOCATION_PERMISSION_REQUEST_CODE = 1
    var locationManager: LocationManager? = null

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, AddressActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.webmyne.modak.R.layout.activity_address)
        initview()
        actionListner()
        checklocationEnable()
    }

    private fun checklocationEnable() {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                this.LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, this)
    }

    override fun onLocationChanged(location: Location?) {
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(this, Locale.getDefault())
       println("Location----"+location)
        addresses = geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
        val address = addresses[0].getAddressLine(0)
        val city = addresses[0].locality
        val state = addresses[0].adminArea
        val country = addresses[0].countryName
        val postalCode = addresses[0].postalCode
        val knownName = addresses[0].featureName
        edtZipCode.setText(postalCode)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

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

    private fun actionListner() {

        autoCompleteaddress.setOnItemClickListener(
            { parent, view, position, id ->
                Functions.hideKeyPad(this, view)
                val geocoder = Geocoder(this, Locale.getDefault())
                var address: List<Address>
                lateinit var location: Address
                try {
                    address = geocoder.getFromLocationName("", 1)

                    if (address != null) {
                        location = address.get(0)
                        println("Adress---" + location)
                        lat = location.latitude
                        longitute = location.longitude
                        countryName = location.countryName
                        cityName = location.adminArea
                        ZipCode = location.postalCode
                        fulladdress = location.subAdminArea
                        var useraddress = User_Address()
                        useraddress.userId = PrefUtils.getUserID(this).toInt()
                        useraddress.country = countryName
                        useraddress.state = cityName
                        useraddress.city = location.getAddressLine(0)
                        useraddress.zipcode = ZipCode
                        useraddress.address1 = location.subAdminArea
                        useraddress.adressType = 0
                        db.InsertUserAddress(useraddress)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            })
        txtGetStarted.setOnClickListener {
            var useraddress = User_Address()
            useraddress.userId = PrefUtils.getUserID(this).toInt()
            useraddress.country = countryName
            useraddress.state = cityName
            useraddress.city = ""
            useraddress.zipcode = edtZipCode.text.toString().trim()
            useraddress.address1 = ""
            useraddress.adressType = 0
            db.InsertUserAddress(useraddress)
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        Log.e("Click", "Click")

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


    private fun initview() {
        var spinner: Spinner = spinerCountry
        var cityspinner: Spinner = spinerCity
        Places.initialize(applicationContext!!, "AIzaSyBuv21TYl3GzsoFb2lmhsM_Dn4cD36t8OI")
        placesClient = Places.createClient(FacebookSdk.getApplicationContext()!!)
        println("Addapter Comming")
        placesAdapter = PlacesAdapter(
            FacebookSdk.getApplicationContext()!!,
            android.R.layout.simple_list_item_1,
            bounds
        )
        autoCompleteaddress.setAdapter(placesAdapter)
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
                        PrefUtils.setCityname(this@AddressActivity, name)
                    }

                })

        cityspinner.adapter = cityAdapter
        cityspinner.setSelection(0)
        println("CityName1--" + cityName)


    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }
}