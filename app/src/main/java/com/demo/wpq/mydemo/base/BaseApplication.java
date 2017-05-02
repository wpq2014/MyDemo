package com.demo.wpq.mydemo.base;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public abstract class BaseApplication extends Application {

    private static final List<Activity> mActivities = Collections.synchronizedList(new LinkedList<Activity>());

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityListener();
    }

    private boolean pushActivity(Activity activity) {
        synchronized (mActivities) {
            return mActivities.add(activity);
        }
    }

    private boolean popActivity(Activity activity) {
        synchronized (mActivities) {
            return activity != null && mActivities.remove(activity);
        }
    }

    public static Activity getTopActivity() {
        synchronized (mActivities) {
            if (mActivities.isEmpty()) return null;
            return mActivities.get(mActivities.size() - 1);
        }
    }

    public static boolean finishActivity(Activity activity) {
        synchronized (mActivities) {
            if (activity != null && mActivities.contains(activity)) {
                activity.finish();
                return mActivities.remove(activity);
            }
            return false;
        }
    }

    public static void finishAllActivities() {
        synchronized (mActivities) {
            for (Activity activity : mActivities) {
                activity.finish();
            }
            mActivities.clear();
        }
    }

    /**
     * 保留栈底Activity，其他都finish
     */
    public static void finishAllActivitiesRemainBottom() {
        synchronized (mActivities) {
            for (int i = mActivities.size() - 1; i > 0; ) {
                Activity activity = mActivities.get(i);
                activity.finish();
                mActivities.remove(activity);
                i = mActivities.size() - 1;
            }
        }
    }

    private void registerActivityListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    pushActivity(activity);
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    popActivity(activity);
                }
            });
        }
    }
}
