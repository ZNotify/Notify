package top.learningman.push

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Process
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.xiaomi.mipush.sdk.MiPushClient
import top.learningman.push.utils.Utils

class MainApplication : Application() {
    companion object {
        lateinit var handler: Handler

        fun isHandlerInit(): Boolean {
            return this::handler.isInitialized
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (shouldInit()) {
            Log.d("Manufacturer", Build.MANUFACTURER)
            if (Utils.isXiaoMi()) {
                MiPushClient.registerPush(this, "2882303761520145940", "5542014546940")
            } else if (Utils.isGMS(this)) {
                Firebase.messaging.isAutoInitEnabled = true
                FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)
            } else {
                Toast.makeText(this, "Use fallback", Toast.LENGTH_SHORT).show()
                MiPushClient.registerPush(this, "2882303761520145940", "5542014546940")
            }
        }
    }

    private fun shouldInit(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfo = am.runningAppProcesses
        val mainProcessName = applicationInfo.processName
        val myPid = Process.myPid()
        for (info in processInfo) {
            if (info.pid == myPid && mainProcessName == info.processName)
                return true
        }
        return false
    }
}