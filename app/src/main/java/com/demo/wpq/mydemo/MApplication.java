package com.demo.wpq.mydemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
