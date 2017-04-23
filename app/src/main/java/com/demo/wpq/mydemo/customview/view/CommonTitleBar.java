package com.demo.wpq.mydemo.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;

/**
 * Desc: 自定义标题栏-引用xml布局
 * Author: wpq
 * Date: 2017/4/21 09:45
 */
public class CommonTitleBar extends FrameLayout implements View.OnClickListener {

    /** 整个TitleBar布局 */
    private FrameLayout mFlTitleBar;
    /** 左边图片布局 */
    private LinearLayout mLlLeftImage;
    /** 左边图片 */
    private ImageView mIvLeft;
    /** 中间布局 */
    private LinearLayout mLlCenter;
    /** 中间文字 */
    private TextView mTvCenter;
    /** 中间图片 */
    private ImageView mIvCenter;
    /** 右边布局 */
    private LinearLayout mLlRight;
    /** 右边图片布局 */
    private LinearLayout mLlRightImage;
    /** 右边图片 */
    private ImageView mIvRight;
    /** 右边文字 */
    private TextView mTvRight;
    /** 底部分割线 */
    private View mViewSplitLine;

    public CommonTitleBar(@NonNull Context context) {
        this(context, null);
    }

    public CommonTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_common_title_bar, this, true);

        mFlTitleBar = (FrameLayout) findViewById(R.id.fl_titleBar);
        mLlLeftImage = (LinearLayout) findViewById(R.id.ll_left_image);
        mIvLeft = (ImageView) findViewById(R.id.iv_left);
        mLlCenter = (LinearLayout) findViewById(R.id.ll_center);
        mTvCenter = (TextView) findViewById(R.id.tv_center);
        mIvCenter = (ImageView) findViewById(R.id.iv_center);
        mLlRight = (LinearLayout) findViewById(R.id.ll_right);
        mLlRightImage = (LinearLayout) findViewById(R.id.ll_right_image);
        mIvRight = (ImageView) findViewById(R.id.iv_right);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mViewSplitLine = findViewById(R.id.v_splitLine);
        // init
        mIvCenter.setVisibility(View.GONE);
        mLlRightImage.setVisibility(View.GONE);
        mTvRight.setVisibility(View.GONE);
        // listener
        mLlLeftImage.setOnClickListener(this);
        mLlCenter.setOnClickListener(this);
        mLlRightImage.setOnClickListener(this);
        mTvRight.setOnClickListener(this);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar);
        String centerText = ta.getString(R.styleable.CommonTitleBar_tb_centerText);
        int centerImageRes = ta.getResourceId(R.styleable.CommonTitleBar_tb_centerImageSrc, 0);
        String rightText = ta.getString(R.styleable.CommonTitleBar_tb_rightText);
        int rightImageRes = ta.getResourceId(R.styleable.CommonTitleBar_tb_rightImageSrc, 0);
        ta.recycle();
        // 中间文字
        mTvCenter.setText(centerText);
        // 中间图片
        if (0 != centerImageRes) {
            mIvCenter.setVisibility(View.VISIBLE);
            mIvCenter.setImageResource(centerImageRes);
        }
        // 右边图片
        if (0 != rightImageRes) {
            mLlRightImage.setVisibility(View.VISIBLE);
            mIvRight.setImageResource(rightImageRes);
        }
        // 右边文字
        if (!TextUtils.isEmpty(rightText)) {
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setText(rightText);
        }
    }

    public CommonTitleBar setTitleBarBackground(int color) {
        mFlTitleBar.setBackgroundColor(color);
        return this;
    }

    public CommonTitleBar setTitleBarBackground(Drawable drawable) {
        mFlTitleBar.setBackgroundDrawable(drawable);
        return this;
    }

    public CommonTitleBar setCenterLayoutBackground(int color) {
        mLlCenter.setBackgroundColor(color);
        return this;
    }

    public CommonTitleBar setCenterLayoutBackground(Drawable drawable) {
        mLlCenter.setBackgroundDrawable(drawable);
        return this;
    }

    public CommonTitleBar setCenterText(String title) {
        mTvCenter.setText(title);
        return this;
    }

    public CommonTitleBar setCenterTextColor(int color) {
        mTvCenter.setTextColor(color);
        return this;
    }

    public CommonTitleBar setCenterTextSize(float textSize) {
        mTvCenter.setTextSize(TypedValue.COMPLEX_UNIT_PX, sp2px(textSize));
        return this;
    }

    public CommonTitleBar setCenterImageResource(int resId) {
        mIvCenter.setImageResource(resId);
        return this;
    }

    public CommonTitleBar setCenterImageDrawable(Drawable drawable) {
        mIvCenter.setImageDrawable(drawable);
        return this;
    }

    public CommonTitleBar setLeftImageLayoutBackground(int resId) {
        mLlLeftImage.setBackgroundResource(resId);
        return this;
    }

    public CommonTitleBar setLeftImageLayoutBackground(Drawable drawable) {
        mLlLeftImage.setBackgroundDrawable(drawable);
        return this;
    }

    public CommonTitleBar setLeftImageResource(int resId) {
        mIvLeft.setImageResource(resId);
        return this;
    }

    public CommonTitleBar setLeftImageDrawable(Drawable drawable) {
        mIvLeft.setImageDrawable(drawable);
        return this;
    }

    /**
     * 右边图片布局
     * @param resId
     * @return
     */
    public CommonTitleBar setRightImageLayoutBackground(int resId) {
        mLlRightImage.setBackgroundResource(resId);
        return this;
    }

    /**
     * 右边图片布局
     * @param drawable
     * @return
     */
    public CommonTitleBar setRightImageLayoutBackground(Drawable drawable) {
        mLlRightImage.setBackgroundDrawable(drawable);
        return this;
    }

    public CommonTitleBar setRightImageResource(int resId) {
        mIvRight.setImageResource(resId);
        return this;
    }

    public CommonTitleBar setRightImageDrawable(Drawable drawable) {
        mIvRight.setImageDrawable(drawable);
        return this;
    }

    public CommonTitleBar setRightText(String text) {
        mTvRight.setText(text);
        return this;
    }

    public CommonTitleBar setRightTextColor(int color) {
        mTvRight.setTextColor(color);
        return this;
    }

    public CommonTitleBar setRightTextSize(float textSize) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, sp2px(textSize));
        return this;
    }

    public CommonTitleBar setRightTextBackground(int resId) {
        mTvRight.setBackgroundResource(resId);
        return this;
    }

    public CommonTitleBar setRightTextBackground(Drawable drawable) {
        mTvRight.setBackgroundDrawable(drawable);
        return this;
    }

    /**
     * 底部分割线背景设置
     * @param resId
     * @return
     */
    public CommonTitleBar setSplitLineBackground(int resId) {
        mViewSplitLine.setBackgroundResource(resId);
        return this;
    }

    /**
     * 底部分割线背景设置
     * @param drawable
     * @return
     */
    public CommonTitleBar setSplitLineBackground(Drawable drawable) {
        mViewSplitLine.setBackgroundDrawable(drawable);
        return this;
    }

    public CommonTitleBar setSplitLineHeight(float height) {
        ViewGroup.LayoutParams lp = mViewSplitLine.getLayoutParams();
        lp.height = dp2px(height);
        mViewSplitLine.setLayoutParams(lp);
        return this;
    }

    public CommonTitleBar setCenterTextVisibility(int visibility) {
        mTvCenter.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setCenterImageVisibility(int visibility) {
        mIvCenter.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setLeftImageLayoutVisibility(int visibility) {
        mLlLeftImage.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setRightImageLayoutVisibility(int visibility) {
        mLlRightImage.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setRightTextVisibility(int visibility) {
        mTvRight.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setLeftImageLayoutEnabled(boolean enabled) {
        mLlLeftImage.setEnabled(enabled);
        mIvLeft.setEnabled(enabled);
        return this;
    }

    public CommonTitleBar setCenterLayoutEnabled(boolean enabled) {
        mLlCenter.setEnabled(enabled);
        mTvCenter.setEnabled(enabled);
        mIvCenter.setEnabled(enabled);
        return this;
    }

    public CommonTitleBar setRightImageLayoutEnabled(boolean enabled) {
        mLlRightImage.setEnabled(enabled);
        mIvRight.setEnabled(enabled);
        return this;
    }

    public CommonTitleBar setRightTextEnabled(boolean enabled) {
        mTvRight.setEnabled(enabled);
        return this;
    }

    private int dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 左边
            case R.id.ll_left_image: {
                if (null != mOnLeftImageClickListener) {
                    mOnLeftImageClickListener.onClick(mLlLeftImage);
                }
                break;
            }
            // 中间
            case R.id.ll_center: {
                if (null != mOnCenterClickListener) {
                    mOnCenterClickListener.onClick(mLlCenter);
                }
                break;
            }
            // 右边图片
            case R.id.ll_right_image: {
                if (null != mOnRightImageClickListener) {
                    mOnRightImageClickListener.onClick(mLlRightImage);
                }
                break;
            }
            // 右边文字
            case R.id.tv_right: {
                if (null != mOnRightTextClickListener) {
                    mOnRightTextClickListener.onClick(mTvRight);
                }
                break;
            }
        }
    }

    public interface OnTitleBarClickListener{
        void onClick(View view);
    }

    private OnTitleBarClickListener mOnLeftImageClickListener;
    private OnTitleBarClickListener mOnCenterClickListener;
    private OnTitleBarClickListener mOnRightImageClickListener;
    private OnTitleBarClickListener mOnRightTextClickListener;

    public CommonTitleBar setOnLeftImageClickListener(OnTitleBarClickListener onLeftImageClickListener) {
        this.mOnLeftImageClickListener = onLeftImageClickListener;
        return this;
    }

    public CommonTitleBar setOnCenterClickListener(OnTitleBarClickListener onCenterClickListener) {
        this.mOnCenterClickListener = onCenterClickListener;
        return this;
    }

    public CommonTitleBar setOnRightImageClickListener(OnTitleBarClickListener onRightImageClickListener) {
        this.mOnRightImageClickListener = onRightImageClickListener;
        return this;
    }

    public CommonTitleBar setOnRightTextClickListener(OnTitleBarClickListener onRightTextClickListener) {
        this.mOnRightTextClickListener = onRightTextClickListener;
        return this;
    }
}
