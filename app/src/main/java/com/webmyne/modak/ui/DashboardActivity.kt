package com.webmyne.modak.ui

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.*
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.SeekBar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.webmyne.modak.R
import com.webmyne.modak.custome.LogoutDialog
import com.webmyne.modak.custome.MDToast
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.Functions.getImage
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.*
import kotlinx.android.synthetic.main.activity_dashbord.*
import java.util.*


class DashboardActivity : BaseActivity(), LocationListener {
    override fun onLocationChanged(location: Location?) {
        println("8iuireuiruie" + location)
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(this, Locale.getDefault())
        try {
            addresses = geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
            val address = addresses[0].getAddressLine(0)
            val city = addresses[0].locality
            val state = addresses[0].adminArea
            val country = addresses[0].countryName
            val postalCode = addresses[0].postalCode
            val knownName = addresses[0].featureName
            txtCity.text = city
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

    var LOCATION_PERMISSION_REQUEST_CODE = 1
    var bundle = Bundle()
    var db: DatabaseHelper? = null
    var closeIconTime = 0
    var closeIconPrice = 0
    var closeIconFood = 0
    var closeIconDistance = 0
    var closeIconCuisine = 0
    var closeIconMode = 0
    var cal: Calendar? = null
    var todate: Date? = null
    var selectedDate: Date? = null
    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null
    var userdetails: MutableList<User> = mutableListOf()
    var tabSelected:Int=1

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, DashboardActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    /*override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt("tabSelected", tabSelected)
        println("Home**************oncretae onSaveInstanceState" + outState)
        println("Home**************oncretae onSaveInstanceState tabSelected" + tabSelected)
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        tabSelected = savedInstanceState?.getInt("tabSelected")!!
        println("Home**************oncretae onRestoreInstanceState" + savedInstanceState)
        println("Home**************oncretae tabSelected" + tabSelected)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* try {
            super.onCreate(savedInstanceState)
            if (savedInstanceState != null) {
                tabSelected = savedInstanceState?.getInt("tabSelected")!!
            } else {

            }
        } catch (e: Exception) {
            println("Home**************oncretae crash" + e.toString())
            //  Crashlytics.logException(e)
            tabSelected = savedInstanceState?.getInt("tabSelected")!!
           *//* val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish()*//*
        }*/

        setContentView(com.webmyne.modak.R.layout.activity_dashbord)
        drawer_layout.setScrimColor(Color.TRANSPARENT)
        var drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        var content = findViewById<RelativeLayout>(R.id.llDashbord)
        var contentdashbord =
            findViewById<LinearLayout>(R.id.contentdashbord)
        var contentdashbord1 =
            findViewById<LinearLayout>(R.id.contentdashbord1)
        val actionBarDrawerToggle =
            object : ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.open,
                R.string.close
            ) {

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    if (contentdashbord.visibility == View.VISIBLE) {
                        val slideX = drawerView.width * slideOffset
                        content.translationX = slideX
                    } else {
                        val slideX = -drawerView.width * slideOffset
                        content.translationX = slideX
                    }

                }
            }

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        checklocationEnable()
        initview()
        actionListner()


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

    private fun actionListner() {
        txtCity.text = PrefUtils.getCityname(this)
        layoutBottom.visibility = View.VISIBLE
        imgiconGps.visibility = View.VISIBLE
        imgIconSerach.visibility = View.VISIBLE
        imgIconFilter.visibility = View.VISIBLE
        imgIconCart.visibility = View.VISIBLE
        loadButtonUi(tabSelected, 0)
        txtCity.setOnClickListener {
            AddressActivity.launchActivity(this)
        }
        imgiconGps.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            AddressActivity.launchActivity(this)
        }
        layoutFavorite.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(1, 0)
        }

        layoutDelivery.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(2, 0)
        }

        latoutTakeout.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(3, 0)
        }

        layoutDinein.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            loadButtonUi(4, 0)
        }
        imgIconCart.setOnClickListener {
            //BasketActivity.launchActivity(this)
            if (db?.getTotalItemCount() == 0) {
                Functions.showToast(this, "Cart Is Empty!!", MDToast.TYPE_INFO)
                return@setOnClickListener
            }
            DeliveryTimeOrderActivity.launchActivity(this)
        }
        imgIconFilter.setOnClickListener {
            contentdashbord1.visibility = View.VISIBLE
            drawer_layout.openDrawer(GravityCompat.END)

        }
        rlTime.setOnClickListener {
            if (closeIconTime == 0) {
                imgTime.setImageResource(R.drawable.ic_menu_icon_close)
                llTime.visibility = View.VISIBLE
                closeIconTime = 1
            } else {
                closeIconTime = 0
                imgTime.setImageResource(R.drawable.ic_menu_icon_open)
                llTime.visibility = View.GONE
            }
        }
        checkbox1Time.setOnClickListener {
            imgcheckbox1Time.setImageResource(R.drawable.menu_checked)
            imgcheckbox2Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox3Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox4Time.setImageResource(R.drawable.menu_unchecked)
        }
        checkbox2Time.setOnClickListener {
            imgcheckbox1Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox2Time.setImageResource(R.drawable.menu_checked)
            imgcheckbox3Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox4Time.setImageResource(R.drawable.menu_unchecked)

        }
        checkbox3Time.setOnClickListener {
            imgcheckbox1Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox2Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox3Time.setImageResource(R.drawable.menu_checked)
            imgcheckbox4Time.setImageResource(R.drawable.menu_unchecked)

        }
        checkbox4Time.setOnClickListener {
            imgcheckbox1Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox2Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox3Time.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox4Time.setImageResource(R.drawable.menu_checked)

        }
        llcheckbox1Price.setOnClickListener {
            imgcheckbox2Price.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox1Price.setImageResource(R.drawable.menu_checked)

        }
        llcheckbox2Price.setOnClickListener {
            imgcheckbox1Price.setImageResource(R.drawable.menu_unchecked)
            imgcheckbox2Price.setImageResource(R.drawable.menu_checked)

        }



        rlprice.setOnClickListener {
            if (imgPriceFilter.drawable.constantState.equals
                    (resources.getDrawable(R.drawable.ic_menu_icon_open).constantState)
            ) {
                imgPriceFilter.setImageResource(R.drawable.ic_menu_icon_close)
                llPrice.visibility = View.VISIBLE
            } else {
                imgPriceFilter.setImageResource(R.drawable.ic_menu_icon_open)
                llPrice.visibility = View.GONE
            }
        }

        cal = Calendar.getInstance()
        todate = cal!!.time
        rlDate.setOnClickListener {
            DateDialog()
        }



        rlFoodType.setOnClickListener {
            if (closeIconFood == 0) {
                imgfoodType.setImageResource(R.drawable.ic_menu_icon_close)
                llFoodType.visibility = View.VISIBLE
                closeIconFood = 1
            } else {
                closeIconFood = 0
                imgfoodType.setImageResource(R.drawable.ic_menu_icon_open)
                llFoodType.visibility = View.GONE
            }
        }

        llcbVeg.setOnClickListener {
            imgcbVeg.setImageResource(R.drawable.menu_checked)
            imgcbNonVeg.setImageResource(R.drawable.menu_unchecked)
        }

        llcbNonVeg.setOnClickListener {
            imgcbVeg.setImageResource(R.drawable.menu_unchecked)
            imgcbNonVeg.setImageResource(R.drawable.menu_checked)
        }

        rlDistance.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (closeIconDistance == 0) {
                    imgDistanceFilter.setImageResource(R.drawable.ic_menu_icon_close)
                    llDistance.visibility = View.VISIBLE
                    closeIconDistance = 1
                    Distance()
                } else {
                    closeIconDistance = 0
                    imgDistanceFilter.setImageResource(R.drawable.ic_menu_icon_open)
                    llDistance.visibility = View.GONE
                }
                return false
            }

        }) /*{
            if (closeIconDistance == 0) {
                imgDistanceFilter.setImageResource(R.drawable.ic_menu_icon_close)
                llDistance.visibility = View.VISIBLE
                closeIconDistance = 1
                Distance()
            } else {
                closeIconDistance = 0
                imgDistanceFilter.setImageResource(R.drawable.ic_menu_icon_open)
                llDistance.visibility = View.GONE
            }
        }*/

        rlCusines.setOnClickListener {
            if (closeIconCuisine == 0) {
                imgCusines.setImageResource(R.drawable.ic_menu_icon_close)
                llCuisine.visibility = View.VISIBLE
                closeIconCuisine = 1
            } else {
                closeIconCuisine = 0
                imgCusines.setImageResource(R.drawable.ic_menu_icon_open)
                llCuisine.visibility = View.GONE
            }
        }

        llcbSouthIndianCuisine.setOnClickListener {
            if (imgccbSouthIndianCuisine.drawable.constantState.equals
                    (resources.getDrawable(R.drawable.menu_checked).constantState)
            ) {
                imgccbSouthIndianCuisine.setImageResource(R.drawable.menu_unchecked)
            } else {
                imgccbSouthIndianCuisine.setImageResource(R.drawable.menu_checked)
            }

        }

        llcbNorthIndianCuisine.setOnClickListener {
            if (imgcbNorthIndianCuisine.drawable.constantState.equals
                    (resources.getDrawable(R.drawable.menu_checked).constantState)
            ) {
                imgcbNorthIndianCuisine.setImageResource(R.drawable.menu_unchecked)
            } else {
                imgcbNorthIndianCuisine.setImageResource(R.drawable.menu_checked)
            }

        }

        llcbPanjabiCuisine.setOnClickListener {
            if (imgcbPanjabiCuisine.drawable.constantState.equals
                    (resources.getDrawable(R.drawable.menu_checked).constantState)
            ) {
                imgcbPanjabiCuisine.setImageResource(R.drawable.menu_unchecked)
            } else {
                imgcbPanjabiCuisine.setImageResource(R.drawable.menu_checked)
            }

        }

        llcbGujaratiCuisine.setOnClickListener {
            if (imgcbGujaratiCuisine.drawable.constantState.equals
                    (resources.getDrawable(R.drawable.menu_checked).constantState)
            ) {
                imgcbGujaratiCuisine.setImageResource(R.drawable.menu_unchecked)
            } else {
                imgcbGujaratiCuisine.setImageResource(R.drawable.menu_checked)
            }

        }

        llcbMarathiCuisine.setOnClickListener {
            if (imgcbMarathiCuisine.drawable.constantState.equals
                    (resources.getDrawable(R.drawable.menu_checked).constantState)
            ) {
                imgcbMarathiCuisine.setImageResource(R.drawable.menu_unchecked)
            } else {
                imgcbMarathiCuisine.setImageResource(R.drawable.menu_checked)
            }

        }


        llcbKolkataCuisine.setOnClickListener {
            if (imgcbKolkataCuisine.drawable.constantState.equals
                    (resources.getDrawable(R.drawable.menu_checked).constantState)
            ) {
                imgcbKolkataCuisine.setImageResource(R.drawable.menu_unchecked)
            } else {
                imgcbKolkataCuisine.setImageResource(R.drawable.menu_checked)
            }

        }


        rlMode.setOnClickListener {
            if (closeIconMode == 0) {
                imgModeFilter.setImageResource(R.drawable.ic_menu_icon_close)
                llMode.visibility = View.VISIBLE
                closeIconMode = 1
            } else {
                closeIconMode = 0
                imgModeFilter.setImageResource(R.drawable.ic_menu_icon_open)
                llMode.visibility = View.GONE
            }
        }

        llcbHomeMode.setOnClickListener {

            imgcbHomeMode.setImageResource(R.drawable.menu_checked)
            imgcbDineMode.setImageResource(R.drawable.menu_unchecked)
            imgcbTakeMode.setImageResource(R.drawable.menu_unchecked)
        }
        llcbDineMode.setOnClickListener {

            imgcbHomeMode.setImageResource(R.drawable.menu_unchecked)
            imgcbDineMode.setImageResource(R.drawable.menu_checked)
            imgcbTakeMode.setImageResource(R.drawable.menu_unchecked)
        }
        llcbTakeMode.setOnClickListener {

            imgcbHomeMode.setImageResource(R.drawable.menu_unchecked)
            imgcbDineMode.setImageResource(R.drawable.menu_unchecked)
            imgcbTakeMode.setImageResource(R.drawable.menu_checked)
        }

        llcloseFilter.setOnClickListener {
            drawer_layout.closeDrawers()
        }
        btnFilterApply.setOnClickListener {
            drawer_layout.closeDrawers()
        }

    }

    private fun Distance() {
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var pval = 0
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                pval = progress
                txtProgressSeekbar.text = pval.toString() + " miles"
                txtProgressSeekbar.setTextColor(resources.getColor(R.color.colorsplacebackground))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {


            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun DateDialog() {
        val day = cal!!.get(Calendar.DAY_OF_MONTH)
        val month = cal!!.get(Calendar.MONTH)
        val year = cal!!.get(Calendar.YEAR)

        val listener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            var monthOfYear = monthOfYear
            monthOfYear = monthOfYear + 1
            txtDateSelected.text = " : " + String.format("%02d", dayOfMonth) + "/" + String.format(
                "%02d",
                monthOfYear
            ) + "/" + year
        }

        val dpDialog = DatePickerDialog(this, listener, year, month - 1, day)
        dpDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        println("Date()--" + Date().time + 7)
        println("TodayDate()--" + System.currentTimeMillis())
        dpDialog.datePicker.maxDate = Date().time + 604800000
        dpDialog.show()
    }


    private fun initview() {

        TedPermission.with(this@DashboardActivity)
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    if (ActivityCompat.checkSelfPermission(
                            this@DashboardActivity,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            this@DashboardActivity,
                            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                            this@DashboardActivity.LOCATION_PERMISSION_REQUEST_CODE
                        )
                        return
                    }
                    locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    locationManager!!.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0L,
                        0f,
                        this@DashboardActivity
                    )

                }

                override fun onPermissionDenied(deniedPermissions: List<String>) {
                    Functions.showToast(
                        this@DashboardActivity,
                        "Denied Service",
                        MDToast.TYPE_ERROR
                    )
                }
            }).check()
        db = DatabaseHelper(this)

        var chefDetails: MutableList<Chef_Details> = mutableListOf()
        chefDetails.addAll(db?.getAllChefList()!!)
        for (i in 0 until chefDetails.size) {
            var chefLanguageSpoken: Chef_Language_Spoken = Chef_Language_Spoken()
            var chefMode: Chef_mode = Chef_mode()
            var chefFoodType: Chef_Food_Type = Chef_Food_Type()

            for (j in 0 until 3) {
                println("chefDetails.get(i).chef_id--" + chefDetails.get(i).chef_id)
                chefLanguageSpoken.Chef_language_id = chefDetails.get(i).chef_id
                chefMode.Chef_mode_id = chefDetails.get(i).chef_id
                chefFoodType.Chef_food_type_id = chefDetails.get(i).chef_id
                if (j == 0) {
                    chefLanguageSpoken.Langauge_Name = "Chinease"
                    chefMode.mode_Name = "Dine In"
                    chefFoodType.food_type_Name = "Chinease"
                } else if (j == 1) {
                    chefLanguageSpoken.Langauge_Name = "Panjabi"
                    chefMode.mode_Name = "Home Delivery"
                    chefFoodType.food_type_Name = "Panjabi"
                } else {
                    chefLanguageSpoken.Langauge_Name = "South Indian"
                    chefMode.mode_Name = "Take Out"
                    chefFoodType.food_type_Name = "South Indian"
                }
                db?.InsertChefFoodType(chefFoodType)
                db?.InsertChefLanguageSpoken(chefLanguageSpoken)
                db?.InsertChefMode(chefMode)

            }

        }
        /* var customNavView: View =
             LayoutInflater.from(this).inflate(R.layout.drawer_item_view, null, false)
         var navHome: LinearLayout = customNavView.findViewById(R.id.navHome)
         var imgUserImage: CircleImageView = customNavView.findViewById(R.id.imgUserImage)
         var txtuserName: TfTextView = customNavView.findViewById(R.id.txtuserName)
         var navMyProfile: LinearLayout = customNavView.findViewById(R.id.navMyProfile)
         var navMyAdress: LinearLayout = customNavView.findViewById(R.id.navMyAdress)
         var navPaymentInfo: LinearLayout = customNavView.findViewById(R.id.navPaymentInfo)
         var navNotifations: LinearLayout = customNavView.findViewById(R.id.navNotifations)
         var navFreeMeals: LinearLayout = customNavView.findViewById(R.id.navFreeMeals)
         var navSetting: LinearLayout = customNavView.findViewById(R.id.navSetting)
         var navHelp: LinearLayout = customNavView.findViewById(R.id.navHelp)
         var layoutMyOrder: LinearLayout = customNavView.findViewById(R.id.layoutMyOrder)
         var layoutMoney: LinearLayout = customNavView.findViewById(R.id.layoutMoney)
         var layoutReview: LinearLayout = customNavView.findViewById(R.id.layoutReview)
         var rlProfile: RelativeLayout = customNavView.findViewById(R.id.rlProfile)
         var navLogOut: LinearLayout = customNavView.findViewById(R.id.navLogOut)*/

        var userdetails: MutableList<User> = mutableListOf()
        userdetails.addAll(
            db?.getAllDetailsUserByEmailId(
                PrefUtils.getUserEmail(this),
                PrefUtils.getUserID(this)
            )!!
        )
        if (userdetails.size > 0) {

            println("username----" + userdetails.get(0).username)
            txtuserName.text = userdetails.get(0).username
            try {
                imgUserImage.setImageBitmap(Functions.getCircularBitmap(getImage(userdetails.get(0).userImage!!)))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        imgIconMenu.setOnClickListener {

            drawer_layout.openDrawer(GravityCompat.START)
        }
        // customNavView.setOnClickListener(null)
        //nav_view.addView(customNavView)
        navHome.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            txtCity.text = PrefUtils.getCityname(this)
            /*layoutBottom.visibility = View.VISIBLE
            imgiconGps.visibility = View.VISIBLE
            imgIconSerach.visibility = View.VISIBLE
            imgIconFilter.visibility = View.VISIBLE
            imgIconCart.visibility = View.VISIBLE*/
            loadButtonUi(1, 0)
            drawer_layout.closeDrawers()
        }
        navMyProfile.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            MyProfileActivity.launchActivity(this)
            drawer_layout.closeDrawers()
        }
        navMyAdress.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            MyAddressActivity.launchActivity(this)
            drawer_layout.closeDrawers()
        }
        navPaymentInfo.setOnClickListener {

            PaymentInfoActivity.launchActivity(this)
            drawer_layout.closeDrawers()
        }
        navNotifations.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            NotificationActivity.launchActivity(this)

            drawer_layout.closeDrawers()
        }
        navFreeMeals.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            FreeMealsActivity.launchActivity(this)

            drawer_layout.closeDrawers()
        }
        navSetting.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            SettingActivity.launchActivity(this)

            drawer_layout.closeDrawers()
        }
        navHelp.setOnClickListener {
            //Functions.hideKeyPad(DashboardActivity@ this, it)
            HelpActivity.launchActivity(this)

            drawer_layout.closeDrawers()
        }
        layoutMyOrder.setOnClickListener {
            Functions.hideKeyPad(DashboardActivity@ this, it)
            MyOrderActivity.launchActivity(this)
            drawer_layout.closeDrawers()
        }
        layoutMoney.setOnClickListener {
            ModakMoneyActivity.launchActivity(this)
            drawer_layout.closeDrawers()
        }
        layoutReview.setOnClickListener {
            ReviewsrActivity.launchActivity(this)
            drawer_layout.closeDrawers()
        }
        navLogOut.setOnClickListener {
            LogoutDialog(this, 1, object : LogoutDialog.OnClickYes {
                override fun onClickYes(flag: Int) {
                    LoginActivity.launchActivity(this@DashboardActivity)
                }

            })
            drawer_layout.closeDrawers()
        }
        rlProfile.setOnClickListener {
            EditProfileActivity.launchActivity(this)
            drawer_layout.closeDrawers()
        }


    }


    override fun onResume() {
        super.onResume()
        txtCity.text = PrefUtils.getCityname(this)
        userdetails.clear()
        userdetails.addAll(
            db?.getAllDetailsUserByEmailId(
                PrefUtils.getUserEmail(this),
                PrefUtils.getUserID(this)
            )!!
        )
        if (userdetails.size > 0) {

            println("username----" + userdetails.get(0).username)
            //imgUserImage.setImageDrawable(R.drawable.ic_user_icon)
            txtuserName.text = userdetails.get(0).username
            try {
                imgUserImage.setImageBitmap(Functions.getCircularBitmap(getImage(userdetails.get(0).userImage!!)))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}

