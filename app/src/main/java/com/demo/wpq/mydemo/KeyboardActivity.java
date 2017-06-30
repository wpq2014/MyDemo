package com.demo.wpq.mydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.utils.KeyboardChangeListener;

import butterknife.BindView;

/**
 * @author wpq
 * @version 1.0
 */
public class KeyboardActivity extends BaseAppCompatActivity implements KeyboardChangeListener.OnKeyboardListener {

    @BindView(R.id.rootView)
    LinearLayout mRootView;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.btn_bottom)
    Button mBtnBottom;

    // intent data
    private String title;

    private KeyboardChangeListener mKeyboardChangeListener;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, KeyboardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getIntentExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_keyboard;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mKeyboardChangeListener = new KeyboardChangeListener(this);
        mKeyboardChangeListener.setOnKeyBoardListener(this);
    }

    @Override
    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
        Log.e(TAG, isShow + ", " + keyboardHeight);
        mBtnBottom.setVisibility(isShow ? View.GONE : View.VISIBLE);

//        Log.e(TAG, mRootView.getRootView().getHeight() + ", " + mRootView.getHeight());
        if (isShow) {
            mScrollView.smoothScrollBy(0, keyboardHeight);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mKeyboardChangeListener != null) {
            mKeyboardChangeListener.removeCallback();
        }
    }

}
