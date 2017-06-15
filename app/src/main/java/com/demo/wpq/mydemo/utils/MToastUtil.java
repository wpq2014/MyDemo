package com.demo.wpq.mydemo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.demo.wpq.mydemo.R;

/**
 * Desc:
 * Author: wpq
 * Date: 2017/4/1 16:05
 */

public final class MToastUtil {

    private static Toast sToast;

    private MToastUtil() { throw new AssertionError("cannot be instantiated"); }

    public static void show(@NonNull Context context, @StringRes int resId) {
        show(context, resId, Gravity.BOTTOM);
    }

    public static void show(@NonNull Context context, @StringRes int resId, int gravity) {
        show(context, resId, gravity, Toast.LENGTH_SHORT);
    }

    public static void show(@NonNull Context context, @StringRes int resId, int gravity, int duration) {
        show(context, context.getApplicationContext().getResources().getString(resId), gravity, duration);
    }

    public static void show(@NonNull Context context, @NonNull CharSequence text) {
        show(context, text, Gravity.BOTTOM);
    }

    public static void show(@NonNull Context context, @NonNull CharSequence text, int gravity) {
        show(context, text, gravity, Toast.LENGTH_SHORT);
    }

    /**
     * make standard toast
     *
     * @param text     text for toast
     * @param gravity  {@link Gravity}
     * @param duration Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    public static void show(@NonNull Context context, @NonNull CharSequence text, int gravity, int duration) {
        if (null == sToast) {
            sToast = Toast.makeText(context.getApplicationContext(), text, duration);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        // Default Gravity: Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
        // Default yOffset = 64dip
        int yOffset = 0;
        switch (gravity) {
            case Gravity.BOTTOM:
                yOffset = dp2px(context, 64);
                break;
            case Gravity.CENTER:
                yOffset = 0;
                break;
            case Gravity.TOP:
                yOffset = dp2px(context, 64);
                break;
        }
        sToast.setGravity(gravity, 0, yOffset);
        sToast.show();
    }

    private static int dp2px(@NonNull Context context, float dpValue) {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (scale * dpValue + 0.5f);
    }

    public static void showNetworkErrorToast(@NonNull Context context) {
        show(context, R.string.networkErrorToast);
    }

}
