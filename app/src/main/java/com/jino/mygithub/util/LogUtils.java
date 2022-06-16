package com.jino.mygithub.util;

import android.util.Log;


public class LogUtils {
    private static final boolean DEBUG = true;


    public static void v(String tag, String content) {
        if (DEBUG) {
            Log.v(tag, content);
        }
    }

    public static void d(String tag, String content) {
        if (DEBUG) {
            Log.d(tag, content);
        }
    }

    public static void i(String tag, String content) {
        if (DEBUG) {
            Log.i(tag, content);
        }
    }


    public static void w(String tag, String content) {
        if (DEBUG) {
            Log.w(tag, content);
        }
    }

    public static void e(String tag, String content) {
        if (DEBUG) {
            Log.e(tag, content);
        }
    }
}
