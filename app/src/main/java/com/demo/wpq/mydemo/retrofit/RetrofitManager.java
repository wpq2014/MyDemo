package com.demo.wpq.mydemo.retrofit;

import android.content.Context;

import com.demo.wpq.mydemo.MApplication;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String API_HOST = "https://api.miaoyiapp.com/";
    private static final String AUTH_HOST = "https://auth.miaoyiapp.com/";

    private static Retrofit apiRetrofit = null;
    private static Retrofit authRetrofit = null;

    private static OkHttpClient mClient = null;

    public static synchronized OkHttpClient init(Context context) {
        if (mClient == null) {
            try {
                mClient = new OkHttpClient.Builder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        })
//                    .socketFactory(javax.net.ssl.SSLSocketFactory.getDefault())
//                    .socketFactory(getSSLSocketFactory(context.getAssets().open("miaoyiapp.cer")))
                        .sslSocketFactory(getSSLSocketFactory(context.getAssets().open("miaoyiapp.cer")))
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mClient;
    }

    public static OkHttpClient getClient() {
        return mClient == null ? init(MApplication.getInstance().getApplicationContext()) : mClient;
    }

    private static SSLSocketFactory getSSLSocketFactory(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param hostUrls e.g. String[] hostUrls = {"https://api.healskare.com"};
     * @return
     */
    private static HostnameVerifier getHostnameVerifier(final String[] hostUrls) {
        HostnameVerifier TRUSTED_VERIFIER = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                boolean ret = false;
                for (String host : hostUrls) {
                    if (host.equalsIgnoreCase(hostname)) {
                        ret = true;
                    }
                }
                return ret;
            }
        };
        return TRUSTED_VERIFIER;
    }

    public static Retrofit getApiRetrofit() {
        if (apiRetrofit == null) {
            try {
                apiRetrofit = new Retrofit.Builder()
                        .baseUrl(API_HOST)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getClient())
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return apiRetrofit;
    }

    public static Retrofit getAuthRetrofit() {
        if (authRetrofit == null) {
            try {
                authRetrofit = new Retrofit.Builder()
                        .baseUrl(AUTH_HOST)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getClient())
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return authRetrofit;
    }

}
