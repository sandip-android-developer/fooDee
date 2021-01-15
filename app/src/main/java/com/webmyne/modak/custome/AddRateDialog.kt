package com.webmyne.modak.custome

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.StrictMode
import android.support.constraint.Constraints.TAG
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import com.hsalf.smilerating.SmileRating
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.Chef_Review
import com.webmyne.modak.model.User
import com.webmyne.modak.ui.BaseActivity
import kotlinx.android.synthetic.main.dialog_add_reviews.*


class AddRateDialog(val context: BaseActivity) : Dialog(context),
    SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {

    var userRating: Int = 0
    var view: View = View(context)
    val db: DatabaseHelper = DatabaseHelper(context)

    init {
        init()
    }

    private fun init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        view = LayoutInflater.from(context)
            .inflate(com.webmyne.modak.R.layout.dialog_add_reviews, null)
        setContentView(view)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        val displayMetrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.attributes = lp
        window!!.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )
        window!!.setFlags(
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        )

        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        show()
        initView()

    }

    private fun initView() {
        addRating.setOnSmileySelectionListener(this)
        addRating.setOnRatingSelectedListener(this)
        /* val typeface = Typeface.createFromAsset(context.getAssets(), "MetalMacabre.ttf")
         addRating.setTypeface(typeface)*/
        var level = addRating.rating
        println("rating---" + level)
        var userdetails: MutableList<User> = mutableListOf()
        userdetails.addAll(
            db.getAllDetailsUserByEmailId(
                PrefUtils.getUserEmail(context),
                PrefUtils.getUserID(context)
            )
        )
        btnAddRating.setOnClickListener {
            if (userRating <= 0) {
                Functions.showToast(context, "Please Rate Us", MDToast.TYPE_INFO)
                return@setOnClickListener
            } else if (edtReviewDesc.text.toString().trim().length == 0) {
                Functions.showToast(context, "Please Give Feedback", MDToast.TYPE_INFO)
                return@setOnClickListener
            }
            var chefReview: Chef_Review = Chef_Review()
            chefReview.ChefReviewUserName = userdetails.get(0).username
            chefReview.ChefReviewUserImage = userdetails.get(0).userImage
            chefReview.ChefReviewUserRating = userRating.toString()
            chefReview.ChefReviewUserDesc = edtReviewDesc.text.toString()
            db.InsertChefReviewDetails(chefReview)
            Functions.hideKeyPad(context, edtReviewDesc)
            dismiss()
        }


    }

    override fun onSmileySelected(smiley: Int, reselected: Boolean) {
        when (smiley) {
            SmileRating.BAD -> Log.i(TAG, "Bad")
            SmileRating.GOOD -> Log.i(TAG, "Good")
            SmileRating.GREAT -> Log.i(TAG, "Great")
            SmileRating.OKAY -> Log.i(TAG, "Okay")
            SmileRating.TERRIBLE -> Log.i(TAG, "Terrible")
            SmileRating.NONE -> Log.i(TAG, "None")
        }
    }

    override fun onRatingSelected(level: Int, reselected: Boolean) {
        userRating = level
    }

    override fun onBackPressed() {
        super.onBackPressed()
        dismiss()
    }
}