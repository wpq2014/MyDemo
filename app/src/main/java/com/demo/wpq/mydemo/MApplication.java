package com.demo.wpq.mydemo;

import android.app.Application;

import com.demo.wpq.mydemo.retrofit.OkHttpClientManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MApplication extends Application{

    String[] hostUrls = {"https://api.healskare.com"};

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        try {
            OkHttpClient client = new OkHttpClient.Builder()
    //            .socketFactory(javax.net.ssl.SSLSocketFactory.getDefault())
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
//                    .sslSocketFactory(OkHttpClientManager.getSSLSocketFactory(getAssets().open("miaoyiapp.cer")))
                    .socketFactory(OkHttpClientManager.getSSLSocketFactory(getAssets().open("_.miaoyiapp.com.cer")))
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("x-app-platform", "Android")
                                    .addHeader("x-app-id", "miaoyi")
                                    .addHeader("Content-Type", "application/json")
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
            OkHttpUtils.initClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
