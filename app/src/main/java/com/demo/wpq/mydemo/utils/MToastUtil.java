package com.demo.wpq.mydemo.utils;

import android.app.Application;
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

    private static Context sApplicationContext;
    private static Toast sToast;

    private MToastUtil() { /* cannot be instantiated */ }

    /**
     * 在Application onCreate中初始化
     *
     * @param applicationContext {@link android.app.Application}
     */
    public static void init(@NonNull Context applicationContext) {
        if (!(applicationContext instanceof Application)) {
            throw new RuntimeException("applicationContext must be an Application Context");
        }

        if (sApplicationContext == null) {
            sApplicationContext = applicationContext;
        }
    }

    public static void show(@StringRes int resId) {
        show(resId, Gravity.BOTTOM);
    }

    public static void show(@StringRes int resId, int gravity) {
        show(resId, gravity, Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int resId, int gravity, int duration) {
        checkArgument();
        show(sApplicationContext.getResources().getString(resId), duration, gravity);
    }

    public static void show(@NonNull String text) {
        show(text, Gravity.BOTTOM);
    }

    public static void show(@NonNull String text, int gravity) {
        show(text, Toast.LENGTH_SHORT, gravity);
    }

    /**
     * make standard toast
     *
     * @param text     text for toast
     * @param gravity  {@link android.view.Gravity}
     * @param duration Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    public static void show(@NonNull String text, int gravity, int duration) {
        checkArgument();
        if (null == sToast) {
            sToast = Toast.makeText(sApplicationContext, text, duration);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        // Default Gravity: Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
        // Default yOffset = 64dip
        int yOffset = 0;
        switch (gravity) {
            case Gravity.BOTTOM:
                yOffset = dp2px(64);
                break;
            case Gravity.CENTER:
                yOffset = 0;
                break;
            case Gravity.TOP:
                yOffset = dp2px(64);
                break;
        }
        sToast.setGravity(gravity, 0, yOffset);
        sToast.show();
    }

    private static void checkArgument() {
        if (sApplicationContext == null) {
            throw new IllegalArgumentException("sApplicationContext can not be null");
        }
    }

    private static int dp2px(float dpValue) {
        float scale = sApplicationContext.getResources().getDisplayMetrics().density;
        return (int) (scale * dpValue + 0.5f);
    }

}
