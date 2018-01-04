package com.flash.simple

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.flash.nativecatch.NativeHandler

/**
 * Created by tianxiaolei on 2018/1/4.
 */
class MApplication : Application() {
    companion object {
        lateinit var context: Context
        val nativeHandler = NativeHandler()

        init {
            nativeHandler.onNativeCrashed = object : NativeHandler.OnNativeCrashed {
                override fun onNativeCrashed() {
                    val intent = Intent()
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.action = "android.intent.action.VIEW"
                    val uri = Uri.parse("https://www.baidu.com")
                    intent.data = uri
                    MApplication.context.startActivity(intent)
                    Log.i("Java", "onNativeCrashed")
                    Toast.makeText(MApplication.context, "出现Native错误！", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        Log.i("MApplication","onCreate()")
        initWeb()
    }

    private fun initWeb() {
        Log.i("processName", getCurrentProcessName())
        if (".native_crash_test".equals(getCurrentProcessName())) {
            nativeHandler.initNativeHandler(android.os.Process.myPid())
        }
    }

    /**
     * 当前进程名
     *
     * @return
     */
    private fun getCurrentProcessName(): String {
        try {
            val pid = android.os.Process.myPid()
            var processName = ""
            val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (process in manager.runningAppProcesses) {
                if (process.pid == pid) {
                    processName = process.processName
                }
            }
            return processName
        } catch (t: Throwable) {
            return ""
        }
    }
}
