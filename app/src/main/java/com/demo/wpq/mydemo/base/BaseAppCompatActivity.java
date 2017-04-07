package com.demo.wpq.mydemo.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by lion on 2017/4/7.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements IBaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            getBundleExtras(bundle);
        }

        int contentViewLayoutId = getContentViewLayoutID();
        if (0 != contentViewLayoutId) {
            setContentView(contentViewLayoutId);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout Id.");
        }

        initViewsAndEvents(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
}
