package com.demo.wpq.mydemo.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.utils.MScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Explain:
 * Author: WuPuquan
 * Time: 2017/3/31 11:21
 */

public class TeethActivity extends BaseAppCompatActivity {

    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.view_centerLine)
    View mViewCenterLine;
    @BindView(R.id.iv_up09)
    ImageView mIvUp09;
    @BindView(R.id.iv_up08)
    ImageView mIvUp08;
    @BindView(R.id.iv_up07)
    ImageView mIvUp07;
    @BindView(R.id.iv_up06)
    ImageView mIvUp06;
    @BindView(R.id.iv_up05)
    ImageView mIvUp05;
    @BindView(R.id.iv_up04)
    ImageView mIvUp04;
    @BindView(R.id.iv_up03)
    ImageView mIvUp03;
    @BindView(R.id.iv_up02)
    ImageView mIvUp02;
    @BindView(R.id.iv_up01)
    ImageView mIvUp01;
    @BindView(R.id.iv_up00)
    ImageView mIvUp00;
    @BindView(R.id.iv_down09)
    ImageView mIvDown09;
    @BindView(R.id.iv_down08)
    ImageView mIvDown08;
    @BindView(R.id.iv_down07)
    ImageView mIvDown07;
    @BindView(R.id.iv_down06)
    ImageView mIvDown06;
    @BindView(R.id.iv_down05)
    ImageView mIvDown05;
    @BindView(R.id.iv_down04)
    ImageView mIvDown04;
    @BindView(R.id.iv_down03)
    ImageView mIvDown03;
    @BindView(R.id.iv_down02)
    ImageView mIvDown02;
    @BindView(R.id.iv_down01)
    ImageView mIvDown01;
    @BindView(R.id.iv_down00)
    ImageView mIvDown00;
    @BindView(R.id.btn_startAnimation)
    Button mBtnStartAnimation;

    // intent data
    private String title;

    private List<ImageView> teethUpList = new ArrayList<>();
    private List<ImageView> teethDownList = new ArrayList<>();

    // 上边10颗牙，两牙间的角度：180/9
    private final double degree = 180 / 9;
    private int radius; // 圆半径

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, TeethActivity.class);
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
        return R.layout.activity_teeth;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
// 根据屏幕分辨率适配：原图宽度720
        int sw = MScreenUtil.getScreenWidth(this);
        int sh = MScreenUtil.getScreenHeight(this);

        // 背景图，原图325x562，左右space的宽度，原图46
        // 因为原图上下牙不是标准的半圆，所以这个背景不好适配
        // 上下牙容易跟背景图重叠
        int ivBgTargetWidth = sw * 325 / 720;
        ViewGroup.LayoutParams lpBg = mIvBg.getLayoutParams();
        lpBg.width = ivBgTargetWidth;
        lpBg.height = ivBgTargetWidth * 562 / 325;
        mIvBg.setLayoutParams(lpBg);

        // 上下牙间距，720/sw = height/sh 同理 720/sw = 70/目标高度
        ViewGroup.LayoutParams lpCenter = mViewCenterLine.getLayoutParams();
        lpCenter.width = sw;
        lpCenter.height = 70 * sw / 720;
        mViewCenterLine.setLayoutParams(lpCenter);

        // 上边左下角牙，原图宽度90
        int toothWidth = sw * 90 / 720;
        // 计算画牙的圆的半径：（屏幕宽度-（一边space-牙的半宽）* 2）/ 2
        // 原图上圆的半径
        int radiusOrigin = (720 - 46 * 2 - 90) / 2;
        radius = sw * radiusOrigin / 720;

        // 上牙集合
        teethUpList.add(mIvUp00);
        teethUpList.add(mIvUp01);
        teethUpList.add(mIvUp02);
        teethUpList.add(mIvUp03);
        teethUpList.add(mIvUp04);
        teethUpList.add(mIvUp05);
        teethUpList.add(mIvUp06);
        teethUpList.add(mIvUp07);
        teethUpList.add(mIvUp08);
        teethUpList.add(mIvUp09);

        // 下牙集合
        teethDownList.add(mIvDown00);
        teethDownList.add(mIvDown01);
        teethDownList.add(mIvDown02);
        teethDownList.add(mIvDown03);
        teethDownList.add(mIvDown04);
        teethDownList.add(mIvDown05);
        teethDownList.add(mIvDown06);
        teethDownList.add(mIvDown07);
        teethDownList.add(mIvDown08);
        teethDownList.add(mIvDown09);

        // 调整牙的大小
        for (ImageView tooth : teethUpList) {
            ViewGroup.LayoutParams lpTooth = tooth.getLayoutParams();
            lpTooth.width = sw * 90 / 720;
            lpTooth.height = sw * 90 / 720;
            tooth.setLayoutParams(lpTooth);
        }

        // 调整牙的大小
        for (ImageView tooth : teethDownList) {
            ViewGroup.LayoutParams lpTooth = tooth.getLayoutParams();
            lpTooth.width = sw * 90 / 720;
            lpTooth.height = sw * 90 / 720;
            tooth.setLayoutParams(lpTooth);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startUpTeethAnimator();
                startDownTeethAnimator();
            }
        }, 800);
    }

    /**
     * 上牙动画：从左下角第一颗开始
     */
    private void startUpTeethAnimator() {
        for (int i = 0; i < teethUpList.size(); i++) {
            double rotateDegree = i * degree;
            PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
            float x = (float) (radius * Math.cos(rotateDegree * Math.PI / 180));
            PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationX", 0f, -x);
            float y = (float) (radius * Math.sin(rotateDegree * Math.PI / 180));
            PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("translationY", 0f, -y);
            ObjectAnimator animatorUp = ObjectAnimator.ofPropertyValuesHolder(teethUpList.get(i), p1, p2, p3);
            animatorUp.setDuration(1500);
            animatorUp.setInterpolator(new OvershootInterpolator()); //超过预期
            animatorUp.start();
        }
    }

    /**
     * 上牙动画：从左下角第一颗开始
     */
    private void startDownTeethAnimator() {
        for (int i = 0; i < teethDownList.size(); i++) {
            double rotateDegree = i * degree;
            PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
            float x = (float) (radius * Math.cos(rotateDegree * Math.PI / 180));
            PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationX", 0f, x);
            float y = (float) (radius * Math.sin(rotateDegree * Math.PI / 180));
            PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("translationY", 0f, y);
            ObjectAnimator animatorUp = ObjectAnimator.ofPropertyValuesHolder(teethDownList.get(i), p1, p2, p3);
            animatorUp.setDuration(1500);
            animatorUp.setInterpolator(new OvershootInterpolator()); //超过预期
            animatorUp.start();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @OnClick({R.id.btn_startAnimation, R.id.iv_up09, R.id.iv_up08, R.id.iv_up07, R.id.iv_up06, R.id.iv_up05, R.id.iv_up04, R.id.iv_up03, R.id.iv_up02, R.id.iv_up01, R.id.iv_up00, R.id.iv_down09, R.id.iv_down08, R.id.iv_down07, R.id.iv_down06, R.id.iv_down05, R.id.iv_down04, R.id.iv_down03, R.id.iv_down02, R.id.iv_down01, R.id.iv_down00})
    public void onClick(View view) {
        String click = "";
        switch (view.getId()) {
            case R.id.iv_up09:
                click = "我是上边第9颗";
                break;
            case R.id.iv_up08:
                click = "我是上边第8颗";
                break;
            case R.id.iv_up07:
                click = "我是上边第7颗";
                break;
            case R.id.iv_up06:
                click = "我是上边第6颗";
                break;
            case R.id.iv_up05:
                click = "我是上边第5颗";
                break;
            case R.id.iv_up04:
                click = "我是上边第4颗";
                break;
            case R.id.iv_up03:
                click = "我是上边第3颗";
                break;
            case R.id.iv_up02:
                click = "我是上边第2颗";
                break;
            case R.id.iv_up01:
                click = "我是上边第1颗";
                break;
            case R.id.iv_up00:
                click = "我是上边第0颗";
                break;
            case R.id.iv_down09:
                click = "我是下边第9颗";
                break;
            case R.id.iv_down08:
                click = "我是下边第8颗";
                break;
            case R.id.iv_down07:
                click = "我是下边第7颗";
                break;
            case R.id.iv_down06:
                click = "我是下边第6颗";
                break;
            case R.id.iv_down05:
                click = "我是下边第5颗";
                break;
            case R.id.iv_down04:
                click = "我是下边第4颗";
                break;
            case R.id.iv_down03:
                click = "我是下边第3颗";
                break;
            case R.id.iv_down02:
                click = "我是下边第2颗";
                break;
            case R.id.iv_down01:
                click = "我是下边第1颗";
                break;
            case R.id.iv_down00:
                click = "我是下边第0颗";
                break;
            case R.id.btn_startAnimation:
                startUpTeethAnimator();
                startDownTeethAnimator();
                break;
        }
        if (!TextUtils.isEmpty(click)) Toast.makeText(this, click, Toast.LENGTH_SHORT).show();
    }

}
