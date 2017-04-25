package com.demo.wpq.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
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

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这行代码，
            // 需要先onDetachedFromWindow()，再destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
//            mWebView.clearView();
            mWebView.loadUrl("about:blank");
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
