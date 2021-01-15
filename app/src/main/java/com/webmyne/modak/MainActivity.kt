package com.webmyne.modak

import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import java.security.MessageDigest

class MainActivity : Application() {
    object Analytics {
        val APPLE_SIZE_KEY: String = "APPLE_SIZE_KEY"
    }

    override fun onCreate() {
        super.onCreate()
        initFreshco()
        printHashKey()
    }

    private fun initFreshco() {
        var config: ImagePipelineConfig =
            ImagePipelineConfig.newBuilder(this).setDownsampleEnabled(true).build()
        Fresco.initialize(this, config)
    }

    private fun printHashKey() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                // val hashKey = String(Base64.encode(md.digest(), 0))
                //    Log.i("System out", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: Throwable) {
            Log.e("System out", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("System out", "printHashKey()", e)
        }

    }
}
