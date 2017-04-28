package com.demo.wpq.mydemo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wpq
 * @version 1.0
 */
@BindEventBus
public class FirstActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.btn_intent)
    Button mBtnIntent;

    @Override
    public void getBundleExtras(Bundle bundle) {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_first;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_intent)
    public void onViewClicked() {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SecondEvent event) {
        if (null != event) {
            mTvMsg.setText(event.msg);
        }
    }
}
