package com.demo.wpq.mydemo;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.demo.wpq.mydemo.base.BaseApplication;
import com.demo.wpq.mydemo.json.bean.ResponseBean;
import com.demo.wpq.mydemo.json.bean.ResultBean;
import com.demo.wpq.mydemo.json.bean.UnNormalJsonBean;
import com.demo.wpq.mydemo.json.fastjson.FastHandler;
import com.demo.wpq.mydemo.retrofit.RetrofitManager;
import com.demo.wpq.mydemo.utils.CrashUtils;
import com.demo.wpq.mydemo.utils.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.moduth.blockcanary.BlockCanary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.leakcanary.LeakCanary;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.cwkj.bluetooth.connect.BluetoothReceiver;

public class MApplication extends BaseApplication {

    public static final String TAG = MApplication.class.getSimpleName();

    private static Context sContext;

    private BluetoothReceiver bluetoothReceiver;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // https://github.com/markzhai/AndroidPerformanceMonitor
        sContext = this;
        BlockCanary.install(this, new AppContext()).start();

        Fresco.initialize(this);
        RetrofitManager.init(this);

        Utils.init(getApplicationContext());
        CrashUtils.getInstance().init();

        testGson();

    }

    public static Context getAppContext() {
        return sContext;
    }

    private void testGson() {
        String jsonArray  = "{\"code\":1, \"msg\":\"请求成功\", \"data\":[{\"type\":\"福利\", \"who\":\"代码家\"}]}";
        String jsonObject = "{\"code\":0, \"msg\":\"请求失败\", \"data\":{\"type\":\"干货\", \"who\":\"武普泉\"}}";
        String jsonString = "{\"code\":0, \"msg\":\"请求失败\", \"data\":\"干货福利\"}";

        try {
            org.json.JSONObject orgJSONObject = new org.json.JSONObject(jsonArray);
            Log.e(TAG, orgJSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // fastjson
        String fastArray = "[{\"type\":\"福利\", \"who\":\"代码家\"}]";
        new FastHandler<List<ResultBean>>(ResultBean.class) {
            @Override
            public void onSuccess(List<ResultBean> list) {
                Log.e(TAG, list + "");
            }
        }.parse(fastArray);

        // Gson自带泛型解析
        Gson gson = new Gson();

        String resultBeanTest = "{\"type\":\"干货\", \"who\":\"武普泉\"}";
//        new HttpResponseHandler<List<ResultBean>>(List<ResultBean>.class) {
//            @Override
//            public void onSuccess(List<ResultBean> t) {
//                Log.e(TAG, t.type + ", " + t.who);
//            }
//        }.onSuccess(resultBeanTest);

        String jsonTest  = "{\"code\":0, \"msg\":\"请求失败\", \"data\":[null]}";
//        JsonObject jsonObject1 = new JsonParser().parse(jsonTest).getAsJsonObject();
//        JsonArray data = jsonObject1.get("data").getAsJsonArray();
//        JsonElement jsonElement = jsonObject1.get("data");
//        Log.e(TAG, "data: " + (data == null) + jsonObject1.get("data"));

        try {
            JSONObject jsonObject2 = new JSONObject(jsonTest);
//            JSONArray jsonArray1 = jsonObject2.optJSONArray("data");
            Object object = jsonObject2.opt("data");
            Log.e(TAG, "d: " + (object == null) + jsonObject2.opt("data").toString());
            if (object == null || "null".equals(object.toString())) {
                Log.e(TAG, "d: " + "data is null.");
                return;
            }
//            List<ResultBean> listTest = gson.fromJson(object.toString(), new TypeToken<List<ResultBean>>() {}.getType());
            ResultBean beanTest = gson.fromJson(object.toString(), new TypeToken<ResultBean>() {}.getType());

//            JSONObject jsonObject3 = new JSONObject(jsonObject);
//            JSONObject jsonObject4 = jsonObject3.optJSONObject("data");
//            Log.e(TAG, "d2: " + jsonObject4.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        // 解析特殊key
        String unnormalKey = "{\"_code\":0, \"消息\":\"请求失败\", \"0\":\"干货福利\", \"users\":{\"user107\":\"小明\", \"user834\":\"小亮\", \"user2384\":\"小红\"}}";
        UnNormalJsonBean unNormalJsonBean = gson.fromJson(unnormalKey, UnNormalJsonBean.class);
        Log.e(TAG, unNormalJsonBean.toString());
        for (String key : unNormalJsonBean.users.keySet()) {
            Log.e(TAG, key + unNormalJsonBean.users.get(key));
        }

    }


}
