package com.webmyne.modak.ui

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.util.Log
import com.webmyne.modak.R
import com.webmyne.modak.custome.MDToast
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.User
import com.webmyne.modak.model.User_Address
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class EditProfileActivity : BaseActivity() {
    var PERMISSION_CODE = 1001
    private val GALLERY = 1
    private val CAMERA = 2
    var flag: Boolean = false
    var path: String = ""
    var bundle = Bundle()
    var db: DatabaseHelper? = null
    var dbImage: Bitmap? = null
    var dbByteArrayImage: ByteArray? = null
    var IsHometype: Int = 0

    companion object {
        fun launchActivity(
            activity: BaseActivity?
        ) {
            if (activity != null) {
                val intent = Intent(activity, EditProfileActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        initView()
        actionListner()


    }

    private fun actionListner() {
        var userdetails: MutableList<User> = mutableListOf()
        txtSaveEditProfile.setOnClickListener {


            if (userdetails.get(0).addressId != 0) {
                var useraddress = User_Address()
                useraddress.userId = PrefUtils.getUserID(this).toInt()
                useraddress.addressId = userdetails.get(0).addressId
                useraddress.state = edtUserState.text.toString().trim()
                useraddress.city = edtUserCity.text.toString().trim()
                useraddress.zipcode = edtUserZipCode.text.toString().trim()
                useraddress.country = edtUserCountry.text.toString().trim()
                useraddress.address1 =
                    edtUserAddress1.text.toString().trim()
                useraddress.adressType = IsHometype
                db?.updateAddressTableByAddressId(useraddress)

            } else {
                var useraddress = User_Address()
                useraddress.userId = PrefUtils.getUserID(this).toInt()
                useraddress.state = edtUserState.text.toString().trim()
                useraddress.city = edtUserCity.text.toString().trim()
                useraddress.zipcode = edtUserZipCode.text.toString().trim()
                useraddress.country = edtUserCountry.text.toString().trim()
                useraddress.address1 =
                    edtUserAddress1.text.toString().trim()
                useraddress.adressType = IsHometype
                db?.InsertUserAddress(useraddress)
            }


            var userdetails = User()
            userdetails.userId = PrefUtils.getUserID(this).toInt()
            userdetails.username =
                edtUserFirstName.text.toString().trim()
            userdetails.userImage = dbByteArrayImage
            println("dbByteArrayImage" + dbByteArrayImage)
            db?.updateUserDetailsTable(userdetails)
            onBackPressed()
        }
        cbOffice.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                cbOffice.isChecked = true
                cbHome.isChecked = false
                IsHometype = 1
            }

        }
        cbHome.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                cbHome.isChecked = true
                cbOffice.isChecked = false
                IsHometype = 0

            }

        }


        txtXCancelEditProfile.setOnClickListener {
            finish()
        }
        imgbackEdit.setOnClickListener {
            onBackPressed()
        }

        userdetails.addAll(
            db?.getAllDetailsUserByEmailId(
                PrefUtils.getUserEmail(this),
                PrefUtils.getUserID(this)
            )!!
        )
        if (userdetails.size > 0) {
            edtUserFirstName.setText(userdetails.get(0).username)
            /*if (userdetails.get(0).cardId!=null
                ||!userdetails.get(0).cardId.equals(""))
            {

            }*/
            if (userdetails.get(0).addressId != null
                || !userdetails.get(0).addressId.equals("")
            ) {
                edtUserAddress1.setText(userdetails.get(0).address1.toString())
                edtUserCity.setText(userdetails.get(0).city.toString())
                edtUserState.setText(userdetails.get(0).state.toString())
                edtUserZipCode.setText(userdetails.get(0).zipcode.toString())
                edtUserCountry.setText(userdetails.get(0).country.toString())
                if (userdetails.get(0).adressType == 0) {
                    cbHome.isChecked = true
                    cbOffice.isChecked = false
                    IsHometype = 0
                } else {
                    cbOffice.isChecked = true
                    cbHome.isChecked = false
                    IsHometype = 1
                }
            }
            try {
                dbByteArrayImage = userdetails.get(0).userImage!!
                imguserEdit.setImageBitmap(Functions.getCircularBitmap(getImage(userdetails.get(0).userImage!!)))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        txtToolbar.setText(R.string.edit_profile)
        imgBackActivity.setOnClickListener {
            finish()
        }
    }

    private fun initView() {
        db = DatabaseHelper(this)
        imguserEdit.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    showPictureDialog()
                }
            } else {
                showPictureDialog()
            }

        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALLERY)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Meassage", "Message")
                    showPictureDialog()
                } else {
                    Log.e("Meassage", "Message")
                    Functions.showToast(this, "Permission Denied...", MDToast.TYPE_INFO)
                }
                return
            }
            else -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    /* path = saveImage(bitmap)
                     Log.e("Path", "Path" + path)*/
                    dbByteArrayImage = getBitmapAsByteArray(bitmap)
                    var bitmapCircle = Functions.getCircularBitmap(bitmap)
                    flag = true
                    imguserEdit!!.setImageBitmap(bitmapCircle)


                    println("dbByteArrayImage--1-----" + dbByteArrayImage)

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

        } else if (requestCode == CAMERA) {
            if (data != null) {
                try {
                    var thumbnail = data.extras!!.get("data") as Bitmap
                    var thumbnailCircle = Functions.getCircularBitmap(thumbnail)
                    flag = true
                    dbByteArrayImage = getBitmapAsByteArray(thumbnail)
                    imguserEdit!!.setImageBitmap(thumbnailCircle)
                    //path = saveImage(thumbnail)
                    //  dbByteArrayImage = getBytes(thumbnail)


                    println("dbByteArrayImage--1-----" + dbByteArrayImage)
                    //  Log.e("Path", "Path" + path)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else {

        }
    }


    fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }

    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    // convert from byte array to bitmap
    fun getImage(image: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }



    private fun saveImage(myBitmap: Bitmap?): String {
        val bytes = ByteArrayOutputStream()
        myBitmap!!.compress(Bitmap.CompressFormat.JPEG, 30, bytes)
        val b = bytes.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)

    }

    override fun onBackPressed() {
        Functions.fireIntent(this, false)
    }

}


