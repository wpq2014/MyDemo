package com.dankegongyu.basic.libs.utils;

import android.app.Service;
import android.os.Vibrator;
import android.util.Log;

import com.dankegongyu.basic.libs.module.DKGlobalContext;

/**
 * 描述：播放声音帮助类
 * 作者：ChaoZheng on 2020-01-14 22:54
 * 邮箱：chaozheng@dankegongyu.com
 */
public class DkVibrateUtils {
    /**
     * 手机震动一下
     */
    public static void startVibrate() {
        Vibrator vibrator = (Vibrator) DKGlobalContext.getAppContext().getSystemService(Service.VIBRATOR_SERVICE);
        long[] vibrationPattern = new long[]{0, 180, 80, 120};
        // 第一个参数为开关开关的时间，第二个参数是重复次数，振动需要添加权限
        if (null != vibrator) {
            vibrator.vibrate(vibrationPattern, -1);
        }
    }

    /**
     * 手机震动
     */
    public static void startVibrate(long[] vibratePattern) {
        startVibrate(vibratePattern, -1);
    }

    /**
     * 手机震动
     */
    public static void startVibrate(long[] vibratePattern, int repeat) {
        long delay = vibratePattern[0];
        if (delay < 0) {
            Log.i("Vibrate", "Vibrate Time " + delay + " Invalid!");
        }
        if (vibratePattern.length > 1) {
            for (int i = 1; i < vibratePattern.length; i++) {
                if (vibratePattern[i] <= 0) {
                    Log.i("Vibrate", "Vibrate Time " + vibratePattern[i] + " Invalid!");
                    return;
                }
            }
        }
        Vibrator vibrator = (Vibrator) DKGlobalContext.getAppContext().getSystemService(Service.VIBRATOR_SERVICE);
        if (null != vibrator) {
            // 第一个参数为开关开关的时间，第二个参数是重复次数，振动需要添加权限
            vibrator.vibrate(vibratePattern, repeat);
        }
    }
}
