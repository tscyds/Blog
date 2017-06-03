package com.dson.blog.util;


import android.util.Log;

import com.dson.blog.BuildConfig;

public class Logger {

    public static void debug(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void error(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
}
