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
    /** 左边布局 */
    private LinearLayout mLlLeft;
    /** 左边图片 */
    private ImageView mIvLeft;
    /** 中间布局 */
    private LinearLayout mLlCenter;
    /** 中间文字 */
    private TextView mTvTitle;
    /** 中间图片 */
    private ImageView mIvTitle;
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
        mLlLeft = (LinearLayout) findViewById(R.id.ll_left);
        mIvLeft = (ImageView) findViewById(R.id.iv_left);
        mLlCenter = (LinearLayout) findViewById(R.id.ll_center);
        mTvTitle = (TextView) findViewById(R.id.tv_center);
        mIvTitle = (ImageView) findViewById(R.id.iv_center);
        mLlRight = (LinearLayout) findViewById(R.id.ll_right);
        mLlRightImage = (LinearLayout) findViewById(R.id.ll_right_image);
        mIvRight = (ImageView) findViewById(R.id.iv_right);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mViewSplitLine = findViewById(R.id.v_splitLine);
        // init
        mIvTitle.setVisibility(View.GONE);
        mLlRightImage.setVisibility(View.GONE);
        mTvRight.setVisibility(View.GONE);
        // listener
        mLlLeft.setOnClickListener(this);
        mLlCenter.setOnClickListener(this);
        mLlRightImage.setOnClickListener(this);
        mTvRight.setOnClickListener(this);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar);
        String title = ta.getString(R.styleable.CommonTitleBar_tb_title);
        String rightText = ta.getString(R.styleable.CommonTitleBar_tb_rightText);
        int rightImageRes = ta.getResourceId(R.styleable.CommonTitleBar_tb_rightImageSrc, 0);
        ta.recycle();
        // 标题
        mTvTitle.setText(title);
        // 右边图片
        if (rightImageRes != 0) {
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
        invalidate();
        return this;
    }

    public CommonTitleBar setTitleBarBackground(Drawable drawable) {
        mFlTitleBar.setBackgroundDrawable(drawable);
        invalidate();
        return this;
    }

    public CommonTitleBar setTitle(String title) {
        mTvTitle.setText(title);
        invalidate();
        return this;
    }

    public CommonTitleBar setTitleTextColor(int color) {
        mTvTitle.setTextColor(color);
        invalidate();
        return this;
    }

    public CommonTitleBar setTitleTextSize(float textSize) {
        mTvTitle.setTextSize(sp2px(textSize));
        invalidate();
        return this;
    }

    public CommonTitleBar setTitleImageResource(int resId) {
        mIvTitle.setImageResource(resId);
        invalidate();
        return this;
    }

    public CommonTitleBar setTitleImageDrawable(Drawable drawable) {
        mIvTitle.setImageDrawable(drawable);
        invalidate();
        return this;
    }

    public CommonTitleBar setLeftBackground(int resId) {
        mLlLeft.setBackgroundResource(resId);
        invalidate();
        return this;
    }

    public CommonTitleBar setLeftBackground(Drawable drawable) {
        mLlLeft.setBackgroundDrawable(drawable);
        invalidate();
        return this;
    }

    public CommonTitleBar setLeftImageResource(int resId) {
        mIvLeft.setImageResource(resId);
        invalidate();
        return this;
    }

    public CommonTitleBar setLeftImageDrawable(Drawable drawable) {
        mIvLeft.setImageDrawable(drawable);
        invalidate();
        return this;
    }

    /**
     * 右边图片布局
     * @param resId
     * @return
     */
    public CommonTitleBar setRightImageLayoutBackground(int resId) {
        mLlRightImage.setBackgroundResource(resId);
        invalidate();
        return this;
    }

    /**
     * 右边图片布局
     * @param drawable
     * @return
     */
    public CommonTitleBar setRightImageLayoutBackground(Drawable drawable) {
        mLlRightImage.setBackgroundDrawable(drawable);
        invalidate();
        return this;
    }

    public CommonTitleBar setRightImageResource(int resId) {
        mIvRight.setImageResource(resId);
        invalidate();
        return this;
    }

    public CommonTitleBar setRightImageDrawable(Drawable drawable) {
        mIvRight.setImageDrawable(drawable);
        invalidate();
        return this;
    }

    public CommonTitleBar setRightText(String text) {
        mTvRight.setText(text);
        invalidate();
        return this;
    }

    public CommonTitleBar setRightTextColor(int color) {
        mTvRight.setTextColor(color);
        invalidate();
        return this;
    }

    public CommonTitleBar setRightTextSize(float textSize) {
        mTvRight.setTextSize(sp2px(textSize));
        invalidate();
        return this;
    }

    public CommonTitleBar setRightTextBackground(int resId) {
        mTvRight.setBackgroundResource(resId);
        invalidate();
        return this;
    }

    public CommonTitleBar setRightTextBackground(Drawable drawable) {
        mTvRight.setBackgroundDrawable(drawable);
        invalidate();
        return this;
    }

    /**
     * 底部分割线背景设置
     * @param resId
     * @return
     */
    public CommonTitleBar setSplitLineBackground(int resId) {
        mViewSplitLine.setBackgroundResource(resId);
        invalidate();
        return this;
    }

    /**
     * 底部分割线背景设置
     * @param drawable
     * @return
     */
    public CommonTitleBar setSplitLineBackground(Drawable drawable) {
        mViewSplitLine.setBackgroundDrawable(drawable);
        invalidate();
        return this;
    }

    public CommonTitleBar setSplitLineHeight(float height) {
        ViewGroup.LayoutParams lp = mViewSplitLine.getLayoutParams();
        lp.height = dp2px(height);
        mViewSplitLine.setLayoutParams(lp);
        invalidate();
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
            case R.id.ll_left: {
                if (null != mOnTitleBarLeftClickListener) {
                    mOnTitleBarLeftClickListener.onClick(mLlLeft);
                }
                break;
            }
            // 中间
            case R.id.ll_center: {
                if (null != mOnTitleBarTitleClickListener) {
                    mOnTitleBarTitleClickListener.onClick(mLlCenter);
                }
                break;
            }
            // 右边图片
            case R.id.ll_right_image: {
                if (null != mOnTitleBarRightImageClickListener) {
                    mOnTitleBarRightImageClickListener.onClick(mLlRightImage);
                }
                break;
            }
            // 右边文字
            case R.id.tv_right: {
                if (null != mOnTitleBarRightTextClickListener) {
                    mOnTitleBarRightTextClickListener.onClick(mTvRight);
                }
                break;
            }
        }
    }

    public interface OnTitleBarClickListener{
        void onClick(View view);
    }

    private OnTitleBarClickListener mOnTitleBarLeftClickListener;
    private OnTitleBarClickListener mOnTitleBarTitleClickListener;
    private OnTitleBarClickListener mOnTitleBarRightImageClickListener;
    private OnTitleBarClickListener mOnTitleBarRightTextClickListener;

    public CommonTitleBar setOnTitleBarLeftClickListener(OnTitleBarClickListener onTitleBarLeftClickListener) {
        this.mOnTitleBarLeftClickListener = onTitleBarLeftClickListener;
        return this;
    }

    public CommonTitleBar setOnTitleBarTitleClickListener(OnTitleBarClickListener onTitleBarTitleClickListener) {
        this.mOnTitleBarTitleClickListener = onTitleBarTitleClickListener;
        return this;
    }

    public CommonTitleBar setOnTitleBarRightImageClickListener(OnTitleBarClickListener onTitleBarRightImageClickListener) {
        this.mOnTitleBarRightImageClickListener = onTitleBarRightImageClickListener;
        return this;
    }

    public CommonTitleBar setOnTitleBarRightTextClickListener(OnTitleBarClickListener onTitleBarRightTextClickListener) {
        this.mOnTitleBarRightTextClickListener = onTitleBarRightTextClickListener;
        return this;
    }
}
