package com.demo.wpq.mydemo.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wpq
 * @version 1.0
 */
@BindEventBus
public class EventBusFirstActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.btn_intent)
    Button mBtnIntent;

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, EventBusFirstActivity.class);
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
        return R.layout.activity_first;
    }

    @Override
    public String getToolBarTitle() {
        return getString(R.string.eventbus_first);
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_intent)
    public void onViewClicked() {
        startActivity(EventBusSecondActivity.newIntent(this, getString(R.string.eventbus_second)));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SecondEvent event) {
        if (null != event) {
            mTvMsg.setText(event.msg);
        }
    }
}
