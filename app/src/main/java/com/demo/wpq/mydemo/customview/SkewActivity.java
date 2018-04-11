package com.demo.wpq.mydemo.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.customview.widget.SkewView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 斜切
 * Created by lion on 2017/4/7.
 */

public class SkewActivity extends BaseAppCompatActivity {

    @BindView(R.id.skewView)
    SkewView mSkewView;
    @BindView(R.id.tv_test)
    TextView mTvTest;

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, SkewActivity.class);
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
        return R.layout.activity_skew;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        TextView textView = new TextView(this);
        textView.setText("大家快乐圣诞节");
        textView.setTextSize(16f);
        textView.setTextColor(Color.BLACK);

        // 先把view从父布局移除
        ViewGroup targetParent = (ViewGroup) textView.getParent();
        if (targetParent != null) {
            targetParent.removeView(textView);
        }

        ViewGroup srcParent = (ViewGroup) mTvTest.getParent();
        int index = -1;
        for (int i = 0; i < srcParent.getChildCount(); i++) {
            if (srcParent.getChildAt(i) == mTvTest) {
                index = i;
            }
        }
        srcParent.removeViewAt(index);
        srcParent.addView(textView, index, mTvTest.getLayoutParams());
    }

    @OnClick(R.id.skewView)
    public void onViewClicked() {
    }
}
