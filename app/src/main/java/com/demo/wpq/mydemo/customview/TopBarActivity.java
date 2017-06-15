package com.demo.wpq.mydemo.customview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.customview.widget.CommonTitleBar;
import com.demo.wpq.mydemo.customview.widget.DirectionView;
import com.demo.wpq.mydemo.customview.widget.MarqueeTextView;
import com.demo.wpq.mydemo.customview.widget.TopBar;
import com.demo.wpq.mydemo.utils.MToastUtil;

import butterknife.BindView;

/**
 * Desc:
 * Created by wpq on 16/7/11.
 */
public class TopBarActivity extends BaseAppCompatActivity {

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

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, TopBarActivity.class);
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
        return R.layout.activity_topbar;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mToolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                mToolbar.setNavigationIcon(R.drawable.ic_share);
            }
        }, 1500);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MToastUtil.show(TopBarActivity.this, "ToolBar back clicked~");
//                finish();
            }
        });

        topbar0.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onTopBarLeftClicked(View v) {
                MToastUtil.show(TopBarActivity.this, "leftButton0 clicked");
            }

            @Override
            public void onTopBarRightClicked(View v) {
                MToastUtil.show(TopBarActivity.this, "rightButton0 clicked");
            }
        });

        topbar0.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 3s后改变topbar0
                topbar0.setLeftImageViewResource(R.drawable.ic_back);
                topbar0.setLeftImageViewDrawable(getResources().getDrawable(R.drawable.ic_share));
                topbar0.setLeftBackground(new ColorDrawable(Color.GREEN));
                topbar0.getLeftTextView().setVisibility(View.GONE);
                topbar0.setLeftPadding(15);
                topbar0.setTitle("我是标题");
                topbar0.setTitleTextColor(Color.RED);
                topbar0.getRightImageView().setVisibility(View.GONE);
                topbar0.setRightText("呵呵哒");
                topbar0.setRightTextColor(getResources().getColor(R.color.remind_bgcolor));
            }
        }, 2000);

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
                        MToastUtil.show(TopBarActivity.this, "返回");
                    }
                })
                .setOnRightTextClickListener(new CommonTitleBar.OnTitleBarClickListener() {
                    @Override
                    public void onClick(View view) {
                        MToastUtil.show(TopBarActivity.this, ((TextView) view).getText().toString());
                    }
                });
        commonTitleBar2.setRightTextVisibility(View.GONE);

        directionView.setOnDirectionChangedListener(new DirectionView.OnDirectionChangedListener() {
            @Override
            public void onDirectionChanged(int currentDirection) {
                switch (currentDirection) {
                    case DirectionView.Direction.LEFT:
                        MToastUtil.show(TopBarActivity.this, "左");
                        break;
                    case DirectionView.Direction.RIGHT:
                        MToastUtil.show(TopBarActivity.this, "右");
                        break;
                    case DirectionView.Direction.UP:
                        MToastUtil.show(TopBarActivity.this, "前");
                        break;
                    case DirectionView.Direction.DOWN:
                        MToastUtil.show(TopBarActivity.this, "后");
                        break;
                }
            }
        });
    }

}
