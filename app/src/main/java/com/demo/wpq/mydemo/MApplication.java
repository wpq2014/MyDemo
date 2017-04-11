package com.demo.wpq.mydemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.demo.wpq.mydemo.json.bean.ResponseBean;
import com.demo.wpq.mydemo.json.bean.ResultBean;
import com.demo.wpq.mydemo.retrofit.RetrofitManager;
import com.demo.wpq.mydemo.utils.CrashUtils;
import com.demo.wpq.mydemo.utils.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MApplication extends Application {

    public static final String TAG = MApplication.class.getSimpleName();

    private static MApplication mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Fresco.initialize(this);
        RetrofitManager.init(this);

        Utils.init(getApplicationContext());
        CrashUtils.getInstance().init();

        testGson();

    }

    public static MApplication getInstance() {
        return mInstance;
    }

    private void testGson() {
        String jsonArray  = "{\"code\":1, \"msg\":\"请求成功\", \"data\":[{\"type\":\"福利\", \"who\":\"代码家\"}]}";
        String jsonObject = "{\"code\":0, \"msg\":\"请求失败\", \"data\":{\"type\":\"干货\", \"who\":\"武普泉\"}}";
        String jsonString = "{\"code\":0, \"msg\":\"请求失败\", \"data\":\"干货福利\"}";
        // Gson自带泛型解析
        Gson gson = new Gson();
        // 数组
        ResponseBean<List<ResultBean>> arrayBean = gson.fromJson(jsonArray, new TypeToken<ResponseBean<List<ResultBean>>>() {}.getType());
        List<ResultBean> list = arrayBean.data;
        Log.e(TAG, arrayBean.code + ", " + arrayBean.msg + ", " + list.get(0).type + ", " + list.get(0).who);
        // 对象
        ResponseBean<ResultBean> objectBean = gson.fromJson(jsonObject, new TypeToken<ResponseBean<ResultBean>>() {}.getType());
        ResultBean object = objectBean.data;
        Log.e(TAG, objectBean.code + ", " + objectBean.msg + ", " + object.type + ", " + object.who);
        // String
        ResponseBean<String> stringBean = gson.fromJson(jsonString, new TypeToken<ResponseBean<String>>() {}.getType());
        Log.e(TAG, stringBean.code + ", " + stringBean.msg + ", " + stringBean.data);
    }


}
