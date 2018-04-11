package com.example.x5webdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.x5webdemo.utils.X5WebView;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/1/24 21:06
 */
public class DankeWebviewActivity extends AppCompatActivity{

    private X5WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danke);

        mWebView = (X5WebView) findViewById(R.id.webView);
        mWebView.loadUrl("https://www.dankegongyu.com/api-page/daily-problems");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
