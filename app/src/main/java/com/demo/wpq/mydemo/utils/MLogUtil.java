package com.demo.wpq.mydemo.utils;

import android.text.TextUtils;
import android.util.Log;

import com.demo.wpq.mydemo.BuildConfig;

/**
 * Desc:
 * Author: wpq
 * Date: 2017/4/1 16:05
 */
public final class MLogUtil {

    public static final String TAG = "MLogUtil";

    private MLogUtil() { /* cannot be instantiated */ }

    public static void v(String msg) {
        v(null, msg, null);
    }

    public static void v(String tag, String msg) {
        v(tag, msg, null);
    }

    public static void v(String tag, String msg, Throwable t) {
        logWithThrowable(Log.VERBOSE, tag, msg, t);
    }

    public static void d(String msg) {
        d(null, msg, null);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable t) {
        logWithThrowable(Log.DEBUG, tag, msg, t);
    }

    public static void i(String msg) {
        i(null, msg, null);
    }

    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable t) {
        logWithThrowable(Log.INFO, tag, msg, t);
    }

    public static void w(String msg) {
        w(null, msg, null);
    }

    public static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, String msg, Throwable t) {
        logWithThrowable(Log.WARN, tag, msg, t);
    }

    public static void e(String msg) {
        e(null, msg, null);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable t) {
        logWithThrowable(Log.ERROR, tag, msg, t);
    }

    private static void logWithThrowable(int logLevel, String tag, String msg, Throwable t) {
        if (BuildConfig.DEBUG) {
            msg = TextUtils.isEmpty(tag) ? msg : tag + " -> " + msg;
            switch (logLevel) {
                case Log.VERBOSE:
                    Log.v(TAG, msg, t);
                    break;
                case Log.DEBUG:
                    Log.d(TAG, msg, t);
                    break;
                case Log.INFO:
                    Log.i(TAG, msg, t);
                    break;
                case Log.WARN:
                    Log.w(TAG, msg, t);
                    break;
                case Log.ERROR:
                    Log.e(TAG, msg, t);
                    break;

                default:
                    Log.v(TAG, msg, t);
                    break;
            }
        }
    }
}
