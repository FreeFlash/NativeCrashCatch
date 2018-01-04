package com.flash.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_native_crash.*

class NativeCrashActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_createNativeException -> MApplication.nativeHandler.createNativeException()
            R.id.button_testToast -> Toast.makeText(MApplication.context, "随便弹个消息！", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, NativeCrashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_crash)
        button_createNativeException.setOnClickListener(this)
        button_testToast.setOnClickListener(this)
    }

}
