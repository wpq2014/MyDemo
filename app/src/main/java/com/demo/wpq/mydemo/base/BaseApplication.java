package com.demo.wpq.mydemo.base;

import android.app.Application;

/**
 * @author wpq
 * @version 1.0
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityListener();
    }

    private void registerActivityListener() {

    }
}
