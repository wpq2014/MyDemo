package com.demo.wpq.mydemo.customview;

import android.os.Bundle;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.customview.view.SkewView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 斜切
 * Created by lion on 2017/4/7.
 */

public class SkewActivity extends BaseAppCompatActivity {

    @BindView(R.id.skewView)
    SkewView mSkewView;

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_skew;
    }

    @Override
    public void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    public void getBundleExtras(Bundle bundle) {

    }

    @OnClick(R.id.skewView)
    public void onViewClicked() {
    }
}
