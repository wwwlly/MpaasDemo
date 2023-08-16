package com.mpaas.demo.utils;

import android.util.Log;

public class Logger {

    public static final String TAG = "mpaas";

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void e(String msg) {
        Log.d(TAG, msg);
    }
}
