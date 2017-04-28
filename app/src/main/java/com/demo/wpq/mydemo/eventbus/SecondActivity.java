package com.demo.wpq.mydemo.eventbus;

import android.os.Bundle;
import android.widget.Button;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wpq
 * @version 1.0
 */
public class SecondActivity extends BaseAppCompatActivity {

    @BindView(R.id.btn_publish)
    Button mBtnPublish;

    @Override
    public void getBundleExtras(Bundle bundle) {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_second;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_publish)
    public void onViewClicked() {
        EventBus.getDefault().post(new SecondEvent("收到一条新消息 from [发布者SecondActivity]"));
        finish();
    }
}
