package com.demo.wpq.mydemo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Desc:
 * Author: wpq
 * Date: 2017/4/1 16:05
 */

public final class MToastUtil {

    private static Toast sToast;

    private MToastUtil() { /* cannot be instantiated */ }

    public static void showToast(@NonNull Context context, @StringRes int resId) {
        showToast(context, resId, Gravity.BOTTOM);
    }

    public static void showToast(@NonNull Context context, @StringRes int resId, int gravity) {
        showToast(context, resId, Toast.LENGTH_SHORT, gravity);
    }

    public static void showToast(@NonNull Context context, @StringRes int resId, int duration, int gravity) {
        showToast(context, context.getApplicationContext().getResources().getString(resId), duration, gravity);
    }

    public static void showToast(@NonNull Context context, @NonNull String text) {
        showToast(context, text, Gravity.BOTTOM);
    }

    public static void showToast(@NonNull Context context, @NonNull String text, int gravity) {
        showToast(context, text, Toast.LENGTH_SHORT, gravity);
    }

    /**
     * make standard toast
     *
     * @param context
     * @param text
     * @param duration
     * @param gravity  {@link android.view.Gravity}
     */
    public static void showToast(@NonNull Context context, @NonNull String text, int duration, int gravity) {
        if (null == sToast) {
            sToast = Toast.makeText(context.getApplicationContext(), text, duration);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.setGravity(gravity, 0, 0);
        sToast.show();
    }

}
