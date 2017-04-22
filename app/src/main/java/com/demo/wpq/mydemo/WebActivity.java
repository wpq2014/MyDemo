package com.demo.wpq.mydemo;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author wpq
 * @version 1.0
 */
public class WebActivity extends AppCompatActivity {

    public static final String TAG = WebActivity.class.getSimpleName();

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebViewClient(new MWebViewClient());
        mWebView.setWebChromeClient(new MWebChromeClient());
        mWebView.loadUrl("file:///android_asset/test.html");
    }

    private class MWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e(TAG + "-WebViewClient", view.getTitle());
        }
    }

    private class MWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.e(TAG + "-WebChromeClient", title);
        }
    }
}
