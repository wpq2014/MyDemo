package com.demo.wpq.mydemo.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wpq
 * @version 1.0
 */
public class EventBusSecondActivity extends BaseAppCompatActivity {

    @BindView(R.id.btn_publish)
    Button mBtnPublish;

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, EventBusSecondActivity.class);
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
        return R.layout.activity_second;
    }

    @Override
    public String getToolBarTitle() {
        return title;
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
