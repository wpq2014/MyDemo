package com.demo.wpq.mydemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;

/**
 * @author wpq
 * @version 1.0
 */
public class WebActivity extends BaseAppCompatActivity {

    public static final String TAG = WebActivity.class.getSimpleName();

    private WebView mWebView;

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getBundleExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_webview;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mWebView = (WebView) findViewById(R.id.webView);
        if (mWebView != null) {
            WebSettings settings = mWebView.getSettings();
            //自适应屏幕
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setJavaScriptEnabled(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            mWebView.setWebViewClient(new MWebViewClient());
            mWebView.setWebChromeClient(new MWebChromeClient());
            mWebView.loadUrl("file:///android_asset/test.html");
        }
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
            mWebView.clearView();
//            mWebView.loadUrl("about:blank");
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
                mWebView = null;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
