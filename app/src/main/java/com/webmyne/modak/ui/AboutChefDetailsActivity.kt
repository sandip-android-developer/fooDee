package com.webmyne.modak.ui

import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.drawable.LayerDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.webmyne.modak.R
import com.webmyne.modak.custome.AddRateDialog
import com.webmyne.modak.custome.TfTextView
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.model.*
import kotlinx.android.synthetic.main.activity_about_chef.*
import java.io.IOException
import java.sql.DriverManager.println

class AboutChefDetailsActivity : BaseActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    val LOCATION_PERMISSION_REQUEST_CODE = 1
    val REQUEST_CHECK_SETTINGS = 2
    var db: DatabaseHelper = DatabaseHelper(this)
    var itemDetails: Item_Details? = null
    var chefFoodType: MutableList<Chef_Food_Type> = mutableListOf()
    var chefMode: MutableList<Chef_mode> = mutableListOf()
    var chefLanguageSpoken: MutableList<Chef_Language_Spoken> = mutableListOf()
    override fun onMarkerClick(p0: Marker?): Boolean {
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        return false
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        setUpMap()
    }

    private fun setUpMap() {
        setMyLocationEnabled()
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    private fun placeMarkerOnMap(currentLatLng: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLng)
        val titleStr = getAddress(currentLatLng)  // add these two lines
        markerOptions.title(titleStr)
        mMap.addMarker(markerOptions)

    }

    private fun getAddress(currentLatLng: LatLng): String? {
        val geocoder = Geocoder(this)
        val addresses: List<Address>?
        var address: Address? = null
        var addressText = ""

        try {
            addresses = geocoder.getFromLocation(currentLatLng.latitude, currentLatLng.longitude, 1)
            if (!addresses.isEmpty()) {
                address = addresses[0]
                addressText =
                    address.featureName + "," + address.subLocality + "," + address.adminArea + "" + address.countryName + "," + "," + address.postalCode
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }
        return addressText

    }

    private fun setMyLocationEnabled() {
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false


    companion object {
        fun launchActivity(
            activity: BaseActivity?,
            itemdetails: Item_Details
        ) {
            if (activity != null) {
                val intent = Intent(activity, AboutChefDetailsActivity::class.java)
                intent.putExtra("itemDetails", itemdetails)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_chef)
        initview()
        actionListner()
    }

    private fun actionListner() {
        println("***Cooming")

        btnReadMore.setOnClickListener {
            if (btnReadMore.text.toString().trim().equals("Read More")) {
                btnReadMore.text = "Read Less"
                txtReadMore.visibility = View.VISIBLE
            } else if (btnReadMore.text.toString().trim().equals("Read Less")) {
                btnReadMore.text = "Read More"
                txtReadMore.visibility = View.GONE
                btnReadMore.visibility = View.VISIBLE
            }


        }
        layoutReviewChef.setOnClickListener {
            println("***1")
            loadButtonUiAbout(1)
        }
        layoutShareChef.setOnClickListener {
            println("***2")
            loadButtonUiAbout(2)
        }
        layoutRate.setOnClickListener {
            println("***3")
            loadButtonUiAbout(3)
            AddRateDialog(this)
        }
        layoutMenu.setOnClickListener {
            println("***4")
            loadButtonUiAbout(4)
        }
        imgBackChefDetail.setOnClickListener {
            onBackPressed()
        }

    }

    private fun loadButtonUiAbout(position: Int) {
        when (position) {
            1 -> {
                llReviews.visibility = View.VISIBLE
                llAboutChef.visibility = View.GONE
                llMenuChef.visibility = View.GONE
                /*  rvReviews.layoutManager = LinearLayoutManager(this)
                  rvReviews.adapter = ChefReviewsAdapter(this)*/
                var chefReviewDetails: MutableList<Chef_Review> = mutableListOf()
                chefReviewDetails.addAll(db.getAllChefReviewDetails())
                CreateReviewItemView(chefReviewDetails)
                txtReviewCount.text =
                    "Reviews " + "(" + chefReviewDetails.size + " )"
                txtReviewsChefName.setTextColor(resources.getColor(R.color.colorsplacebackground))
                imgReviewsChef.setImageResource(R.drawable.icon_review_selected)
                imgShare.setImageResource(R.drawable.icon_share)
                imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                imgRate.setImageResource(R.drawable.icon_rate)
                txtShareName.setTextColor(resources.getColor(R.color.black))
                txtMenuName.setTextColor(resources.getColor(R.color.black))
                txtRateName.setTextColor(resources.getColor(R.color.black))
                layoutShareChef.setOnClickListener {
                    loadButtonUiAbout(2)

                }
                layoutRate.setOnClickListener {
                    loadButtonUiAbout(3)
                    AddRateDialog(this)

                }
                layoutMenu.setOnClickListener {
                    loadButtonUiAbout(4)

                }
            }
            2 -> {
                llAboutChef.visibility = View.VISIBLE
                llMenuChef.visibility = View.GONE
                llReviews.visibility = View.GONE
                imgShare.setImageResource(R.drawable.icon_share_selected)
                txtShareName.setTextColor(resources.getColor(R.color.colorsplacebackground))
                imgReviewsChef.setImageResource(R.drawable.icon_review)
                imgRate.setImageResource(R.drawable.icon_rate)
                imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                txtReviewsChefName.setTextColor(resources.getColor(R.color.black))
                txtMenuName.setTextColor(resources.getColor(R.color.black))
                txtRateName.setTextColor(resources.getColor(R.color.black))
                layoutReviewChef.setOnClickListener {
                    loadButtonUiAbout(1)

                }
                layoutRate.setOnClickListener {
                    loadButtonUiAbout(3)
                    AddRateDialog(this)
                }
                layoutMenu.setOnClickListener {
                    loadButtonUiAbout(4)
                }
            }
            3 -> {
                llAboutChef.visibility = View.VISIBLE
                llMenuChef.visibility = View.GONE
                llReviews.visibility = View.GONE
                //  AddRateDialog(this)
                imgRate.setImageResource(R.drawable.icon_rate_selected)
                txtRateName.setTextColor(resources.getColor(R.color.colorsplacebackground))
                imgShare.setImageResource(R.drawable.icon_share)
                imgMenu.setImageResource(R.drawable.icon_menu_chef_detail)
                imgReviewsChef.setImageResource(R.drawable.icon_review)
                txtShareName.setTextColor(resources.getColor(R.color.black))
                txtReviewsChefName.setTextColor(resources.getColor(R.color.black))
                txtMenuName.setTextColor(resources.getColor(R.color.black))
                layoutReviewChef.setOnClickListener {
                    loadButtonUiAbout(1)

                }
                layoutShareChef.setOnClickListener {
                    loadButtonUiAbout(2)

                }
                layoutMenu.setOnClickListener {
                    loadButtonUiAbout(4)
                }
            }
            4 -> {
                llAboutChef.visibility = View.GONE
                llMenuChef.visibility = View.VISIBLE
                llReviews.visibility = View.GONE
                imgMenu.setImageResource(R.drawable.icon_menu_chef_detail_selected)
                txtMenuName.setTextColor(resources.getColor(R.color.colorsplacebackground))
                imgRate.setImageResource(R.drawable.icon_rate)
                imgShare.setImageResource(R.drawable.icon_share)
                imgReviewsChef.setImageResource(R.drawable.icon_review)
                txtShareName.setTextColor(resources.getColor(R.color.black))
                txtReviewsChefName.setTextColor(resources.getColor(R.color.black))
                txtRateName.setTextColor(resources.getColor(R.color.black))
                layoutShareChef.setOnClickListener {
                    loadButtonUiAbout(2)

                }
                layoutRate.setOnClickListener {
                    loadButtonUiAbout(3)
                    AddRateDialog(this)

                }
                layoutReviewChef.setOnClickListener {
                    loadButtonUiAbout(1)

                }
            }
        }

    }

    private fun initview() {
        println("***initview")
        itemDetails = intent.getSerializableExtra("itemDetails") as Item_Details?
        if (itemDetails != null) {
            txtChefName.text = itemDetails!!.ChefName
            Glide.with(this)
                .load(itemDetails!!.ChefImage)
                .apply(RequestOptions.circleCropTransform())
                .into(imgChef)

            ratingChef.rating = itemDetails!!.ChefAverageRating
            val stars = ratingChef.progressDrawable as LayerDrawable
            // Filled stars
            Functions.setRatingStarColor(
                stars.getDrawable(2),
                ContextCompat.getColor(this, R.color.quantum_yellow700)
            )
            // Half filled stars
            Functions.setRatingStarColor(
                stars.getDrawable(1),
                ContextCompat.getColor(this, R.color.colorgray)
            )
            // Empty stars
            Functions.setRatingStarColor(
                stars.getDrawable(0),
                ContextCompat.getColor(this, R.color.colorgray)
            )
            txtChefCuisineType.text = itemDetails!!.ChefCuisineTye
            txtDummyMoreDetailts.text = itemDetails!!.ChefDescription
            txtReadMore.text = itemDetails!!.ChefDescription
            chefLanguageSpoken.addAll(db.getChefLanguageSpoken(itemDetails!!.chef_id))
            println("chef_id123---" + itemDetails!!.chef_id)
            chefFoodType.addAll(db.getChefFoodType(itemDetails!!.chef_id))
            chefMode.addAll(db.getChefMode(itemDetails!!.chef_id))
            println("chefMode.size---" + chefMode.size)
            println("chefFoodType.size---" + chefFoodType.size)
            println("chefLanguageSpoken.size---" + chefLanguageSpoken.size)
            var sbSpoken = StringBuilder()
            var sbFoodType = StringBuilder()
            var sbchefMode = StringBuilder()
            for (i in 0 until chefLanguageSpoken.size) {
                sbSpoken.append(chefLanguageSpoken.get(i).Langauge_Name)
                if (i != chefLanguageSpoken.size - 1) {
                    sbSpoken.append(",")
                }
            }
            txtKnownLanguage.text = sbSpoken.toString().trim()

            for (j in 0 until chefFoodType.size) {
                sbFoodType.append(chefFoodType.get(j).food_type_Name)
                if (j != chefFoodType.size - 1) {
                    sbFoodType.append(",")
                }
            }
            txtFoodTypeDises.text = sbFoodType.toString().trim()

            for (k in 0 until chefMode.size) {
                sbchefMode.append(chefMode.get(k).mode_Name)
                if (k != chefMode.size - 1) {
                    sbchefMode.append(",")
                }
            }
            txtDineIn.text = sbchefMode.toString().trim()
        }


    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        // 2
        locationRequest.interval = 10000
        // 3
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        // 4
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        // 5
        task.addOnSuccessListener {
            locationUpdateState = true
            //startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            // 6
            if (e is ResolvableApiException) {
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    private fun CreateReviewItemView(chefReviewDetails: MutableList<Chef_Review>) {
        if (chefReviewDetails.size > 0) {
            println("securitySymbolList--" + chefReviewDetails.size)
            val parentLayout = findViewById<View>(R.id.llReviewsView) as LinearLayout
            parentLayout.removeAllViews()
            val layoutInflater = layoutInflater
            var view: View

            //  for (i in 0 until selectedRemId.size - 1) {
            for (i in 0 until chefReviewDetails.size) {
                view = layoutInflater.inflate(R.layout.item_chef_reviews, parentLayout, false)
                val llView = view.findViewById(R.id.llReviewlatout) as LinearLayout
                /*  val imgUserImage =
                      view.findViewById(R.id.imgUserImage_ChefReview) as CircleImageView*/
                val imgUserImage =
                    view.findViewById(R.id.imgUserImage_ChefReview) as ImageView
                val txtUserName = view.findViewById(R.id.txtUserName_ChefReview) as TfTextView
                val Userrating = view.findViewById(R.id.Userrating) as RatingBar
                val txtReviewDesc = view.findViewById(R.id.txtReviewDesc) as TfTextView

                txtUserName.text = chefReviewDetails.get(i).ChefReviewUserName
                Userrating.rating = chefReviewDetails.get(i).ChefReviewUserRating.toFloat()
                txtReviewDesc.text = chefReviewDetails.get(i).ChefReviewUserDesc
                val stars = Userrating.progressDrawable as LayerDrawable
                Functions.setRatingStarColor(
                    stars.getDrawable(2),
                    ContextCompat.getColor(this, R.color.quantum_yellow700)
                )
                Functions.setRatingStarColor(
                    stars.getDrawable(1),
                    ContextCompat.getColor(this, R.color.colorgray)
                )

                Functions.setRatingStarColor(
                    stars.getDrawable(0),
                    ContextCompat.getColor(this, R.color.colorgray)
                )
                try {
                    imgUserImage.setImageBitmap(
                        Functions.getCircularBitmap(
                            Functions.getImage(
                                chefReviewDetails.get(i).ChefReviewUserImage!!
                            )
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                parentLayout.addView(view)
            }
        }

    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

    interface OnAddItem {
        fun OnItemAdd(count: Int)
    }

}


