package com.webmyne.fooddelivery.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.webmyne.fooddelivery.R
import com.webmyne.fooddelivery.helper.Functions
import com.webmyne.fooddelivery.helper.PrefUtils

class SplashActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToLogin()
        var photo: ImageView =findViewById<ImageView>(R.id.imgSplace)
        photo.setOnClickListener {
            var click= Intent(this, LoginActivity::class.java)
            startActivity(click)
        }
    }

    private fun navigateToLogin() {
        val longestDelay:Long=1200
        val longestDuration:Long=3000
        var oneTouchTimer = object : CountDownTimer(longestDelay+longestDuration+500,1000){
            override fun onFinish() {
                if(PrefUtils.isUserLoggedIn(this@SplashActivity)){
                    Functions.fireIntent(this@SplashActivity,DashboardActivity::class.java,true,true)
                }else {
                    Functions.fireIntent(this@SplashActivity, LoginActivity::class.java, true, true)
                }
            }
            override fun onTick(millisUntilFinished: Long) {
            }

        }
        oneTouchTimer.start()

    }

}