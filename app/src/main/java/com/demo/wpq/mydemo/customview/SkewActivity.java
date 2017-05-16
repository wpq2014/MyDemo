package com.demo.wpq.mydemo.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
    public void getBundleExtras(Bundle bundle) {
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

    }

    @OnClick(R.id.skewView)
    public void onViewClicked() {
    }
}
