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

    public static void show(@NonNull Context context, @StringRes int resId) {
        show(context, resId, Gravity.BOTTOM);
    }

    public static void show(@NonNull Context context, @StringRes int resId, int gravity) {
        show(context, resId, Toast.LENGTH_SHORT, gravity);
    }

    public static void show(@NonNull Context context, @StringRes int resId, int duration, int gravity) {
        show(context, context.getApplicationContext().getResources().getString(resId), duration, gravity);
    }

    public static void show(@NonNull Context context, @NonNull String text) {
        show(context, text, Gravity.BOTTOM);
    }

    public static void show(@NonNull Context context, @NonNull String text, int gravity) {
        show(context, text, Toast.LENGTH_SHORT, gravity);
    }

    /**
     * make standard toast
     *
     * @param context
     * @param text
     * @param duration
     * @param gravity {@link android.view.Gravity}
     */
    public static void show(@NonNull Context context, @NonNull String text, int duration, int gravity) {
        if (null == sToast) {
            sToast = Toast.makeText(context, text, duration);
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

    private static int dp2px(Context context, float dipValue) {
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (scale * dipValue + 0.5f);
    }

}
