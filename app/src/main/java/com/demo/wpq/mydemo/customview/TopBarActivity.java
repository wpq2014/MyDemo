package com.demo.wpq.mydemo.customview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.customview.view.CommonTitleBar;
import com.demo.wpq.mydemo.customview.view.DirectionView;
import com.demo.wpq.mydemo.customview.view.MarqueeTextView;
import com.demo.wpq.mydemo.customview.view.TopBar;
import com.demo.wpq.mydemo.utils.MToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * Created by wpq on 16/7/11.
 */
public class TopBarActivity extends Activity {

    @BindView(R.id.topbar0)
    TopBar topbar0;
    @BindView(R.id.commonTitleBar0)
    CommonTitleBar commonTitleBar0;
    @BindView(R.id.commonTitleBar1)
    CommonTitleBar commonTitleBar1;
    @BindView(R.id.commonTitleBar2)
    CommonTitleBar commonTitleBar2;
    @BindView(R.id.directionView)
    DirectionView directionView;
    @BindView(R.id.marquee_textView)
    MarqueeTextView marqueeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar);
        ButterKnife.bind(this);

        topbar0.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarLeftClicked(View v) {
                MToastUtil.show("leftButton0 clicked");
            }

            @Override
            public void onTopBarRightClicked(View v) {
                MToastUtil.show("rightButton0 clicked");
            }
        });

        topbar0.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 3s后改变topbar0
                topbar0.setLeftImageViewResource(R.mipmap.ic_back);
                topbar0.setLeftImageViewDrawable(getResources().getDrawable(R.mipmap.ic_share));
                topbar0.setLeftBackground(new ColorDrawable(Color.GREEN));
                topbar0.getLeftTextView().setVisibility(View.GONE);
                topbar0.setLeftPadding(15);
                topbar0.setTitle("我是标题");
                topbar0.setTitleTextColor(Color.RED);
                topbar0.getRightImageView().setVisibility(View.GONE);
                topbar0.setRightText("呵呵哒");
                topbar0.setRightTextColor(getResources().getColor(R.color.remind_bgcolor));
            }
        }, 3000);

        commonTitleBar0.setCenterTextColor(Color.RED)
                .setCenterTextSize(20)
                .setLeftImageLayoutVisibility(View.GONE);
        commonTitleBar1.setCenterText("搜索")
                .setSplitLineBackground(new ColorDrawable(Color.RED))
                .setSplitLineHeight(5)
                .setRightTextEnabled(true)
                .setOnLeftImageClickListener(new CommonTitleBar.OnTitleBarClickListener() {
                    @Override
                    public void onClick(View view) {
                        MToastUtil.show("返回");
                    }
                })
                .setOnRightTextClickListener(new CommonTitleBar.OnTitleBarClickListener() {
                    @Override
                    public void onClick(View view) {
                        MToastUtil.show(((TextView) view).getText().toString());
                    }
                });
        commonTitleBar2.setRightTextVisibility(View.GONE);

        directionView.setOnDirectionChangedListener(new DirectionView.OnDirectionChangedListener() {
            @Override
            public void onDirectionChanged(int currentDirection) {
                switch (currentDirection) {
                    case DirectionView.Direction.LEFT:
                        showToast("左");
                        break;
                    case DirectionView.Direction.RIGHT:
                        showToast("右");
                        break;
                    case DirectionView.Direction.UP:
                        showToast("前");
                        break;
                    case DirectionView.Direction.DOWN:
                        showToast("后");
                        break;
                }
            }
        });
    }

    private void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
