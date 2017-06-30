package com.demo.wpq.mydemo.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.eventbus.BindEventBus;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by lion on 2017/4/7.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements IBaseActivity{

    protected Toolbar mToolbar;
    protected TextView mTvTitle; // 中间的标题

    protected String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getSimpleName();

        // init bundle data
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            getIntentExtras(bundle);
        }

        // init EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }

        // init contentView
        int contentViewLayoutId = getContentViewLayoutID();
        if (0 != contentViewLayoutId) {
            setContentView(contentViewLayoutId);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout Id.");
        }

        // init views and listeners etc.
        init(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(""); // 去掉默认title
            setSupportActionBar(mToolbar);
            // 显示默认返回箭头
            //noinspection ConstantConditions
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        if (mTvTitle != null) {
            mTvTitle.setText(getToolBarTitle()); // 中间的标题
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregister EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
