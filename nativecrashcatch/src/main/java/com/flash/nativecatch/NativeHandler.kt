package com.flash.nativecatch

/**
 * Created by tianxiaolei on 2018/1/3.
 */
class NativeHandler {
    var onNativeCrashed: OnNativeCrashed? = null

    interface OnNativeCrashed {
        fun onNativeCrashed()
    }

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("nativeCrashHandler-lib")
        }
    }

    external fun createNativeException(): Boolean

    external fun initNativeHandler(pid: Int)

    fun onNativeCrash() {
        onNativeCrashed?.onNativeCrashed()
    }
}
