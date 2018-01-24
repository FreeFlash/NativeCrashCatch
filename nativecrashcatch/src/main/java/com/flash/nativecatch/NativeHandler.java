package com.flash.nativecatch;

public class NativeHandler {

    public OnNativeCrashed onNativeCrashed;

    public void setOnNativeCrashed(OnNativeCrashed onNativeCrashed) {
        this.onNativeCrashed = onNativeCrashed;
    }

    public interface OnNativeCrashed {
        void onNativeCrashed();
    }

    static {
        System.loadLibrary("nativeCrashHandler-lib");
    }

    public native void createNativeException();

    public native void initNativeHandler(int pid);

    public void onNativeCrash() {
        if (onNativeCrashed != null) {
            onNativeCrashed.onNativeCrashed();
        }
    }
}
