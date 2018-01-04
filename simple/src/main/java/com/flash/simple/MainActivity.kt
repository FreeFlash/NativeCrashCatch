package com.flash.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_toNativeCrashActivity -> NativeCrashActivity.start(this)
            R.id.button_createNativeException -> MApplication.nativeHandler.createNativeException()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_toNativeCrashActivity.setOnClickListener(this)
        button_createNativeException.setOnClickListener(this)
    }
}
