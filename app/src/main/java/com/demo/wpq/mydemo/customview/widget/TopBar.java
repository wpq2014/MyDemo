package com.demo.wpq.mydemo.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;

/**
 * Desc: 自定义标题栏，标题在中间，代码中创建布局
 *
 * Created by wpq on 16/7/11.
 */
public class TopBar extends FrameLayout {

    /** 中间标题文字默认颜色 */
    private static final int DEFAULT_TITLE_TEXT_COLOR = 0xFFFFFFFF;
    /** 中间标题文字默认大小 */
    public float DEFAULT_TITLE_TEXT_SIZE = 18.0f;
    /** 两边文字默认大小 */
    public float DEFAULT_LEFT_OR_RIGHT_TEXT_SIZE = 16.0f;
    /** 左边布局默认padding */
    public float DEFAULT_LEFT_PADDING = 15.0f;
    /** 右边布局默认padding */
    public float DEFAULT_RIGHT_PADDING = 10.0f;
    /** 底部分割线默认高度 */
    public float DEFAULT_BOTTOM_LINE_HEIGHT = 1.0f;

    // 中间标题
    private String mTitle;
    private TextView mTitleTextView;
    private int mTitleTextColor;
    private float mTitleTextSize;

    // 左边
    private String mLeftText;
    private LinearLayout mLeftLinearLayout;
    private ImageView mLeftImageView;
    private TextView mLeftTextView;
    /** 左边布局padding */
    private int mLeftPadding;
    private int mLeftImageSrc;
    private int mLeftTextColor;
    private float mLeftTextSize;
    private Drawable mLeftBackground;

    // 右边
    private String mRightText;
    private LinearLayout mRightLinearLayout;
    private ImageView mRightImageView;
    private TextView mRightTextView;
    /** 右边布局padding */
    private int mRightPadding;
    private int mRightImageSrc;
    private int mRightTextColor;
    private float mRightTextSize;
    private Drawable mRightBackground;

    // 底部分割线
    private View mBottomLine;
    private Drawable mBottomLineDrawable;
    private int mBottomLineHeight;

    public interface OnTopBarClickListener {
        void onTopBarLeftClicked(View v);
        void onTopBarRightClicked(View v);
    }

    private OnTopBarClickListener onTopBarClickListener;

    public void setOnTopBarClickListener(OnTopBarClickListener onTopBarClickListener) {
        this.onTopBarClickListener = onTopBarClickListener;
    }

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(final Context context, AttributeSet attrs) {
        super(context, attrs);

        // 默认值转为px
        mTitleTextSize = sp2px(DEFAULT_TITLE_TEXT_SIZE);
        mLeftTextSize = sp2px(DEFAULT_LEFT_OR_RIGHT_TEXT_SIZE);
        mRightTextSize = sp2px(DEFAULT_LEFT_OR_RIGHT_TEXT_SIZE);
        mLeftPadding = dp2px(DEFAULT_LEFT_PADDING);
        mRightPadding = dp2px(DEFAULT_RIGHT_PADDING);
        mBottomLineHeight = dp2px(DEFAULT_BOTTOM_LINE_HEIGHT);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        try {
            // 标题
            mTitle = a.getString(R.styleable.TopBar_top_title);
            mTitleTextColor = a.getColor(R.styleable.TopBar_top_titleTextColor, DEFAULT_TITLE_TEXT_COLOR);
            mTitleTextSize = a.getDimension(R.styleable.TopBar_top_titleTextSize, mTitleTextSize);
            // 左边
            mLeftPadding = a.getDimensionPixelSize(R.styleable.TopBar_top_leftPadding, mLeftPadding);
            mLeftText = a.getString(R.styleable.TopBar_top_leftText);
            mLeftImageSrc = a.getResourceId(R.styleable.TopBar_top_leftImageSrc, 0);
            mLeftTextColor = a.getColor(R.styleable.TopBar_top_leftTextColor, DEFAULT_TITLE_TEXT_COLOR);
            mLeftTextSize = a.getDimension(R.styleable.TopBar_top_leftTextSize, mLeftTextSize);
            mLeftBackground = a.getDrawable(R.styleable.TopBar_top_leftBackground);
            // 右边
            mRightPadding = a.getDimensionPixelSize(R.styleable.TopBar_top_rightPadding, mRightPadding);
            mRightText = a.getString(R.styleable.TopBar_top_rightText);
            mRightImageSrc = a.getResourceId(R.styleable.TopBar_top_rightImageSrc, 0);
            mRightTextColor = a.getColor(R.styleable.TopBar_top_rightTextColor, DEFAULT_TITLE_TEXT_COLOR);
            mRightTextSize = a.getDimension(R.styleable.TopBar_top_rightTextSize, mRightTextSize);
            mRightBackground = a.getDrawable(R.styleable.TopBar_top_rightBackground);
            // 底部分割线
            mBottomLineDrawable = a.getDrawable(R.styleable.TopBar_top_bottomLineDrawable);
            mBottomLineHeight = a.getDimensionPixelSize(R.styleable.TopBar_top_bottomLineHeight, mBottomLineHeight);
        } finally {
            a.recycle();
        }

        // 标题
        FrameLayout.LayoutParams lpTitle = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lpTitle.gravity = Gravity.CENTER;
        lpTitle.setMargins(dp2px(50), 0, dp2px(50), 0); // 默认左右 margin = 50dp
        mTitleTextView = new TextView(context);
        mTitleTextView.setLayoutParams(lpTitle);
        mTitleTextView.setGravity(Gravity.CENTER);
        mTitleTextView.setPadding(dp2px(5), 0, dp2px(5), 0); // 默认左右padding = 5dp
        mTitleTextView.setText(mTitle);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        addView(mTitleTextView); // 中间标题布局

        // 左边
        FrameLayout.LayoutParams lpLeft = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lpLeft.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        mLeftLinearLayout = new LinearLayout(context);
        mLeftLinearLayout.setLayoutParams(lpLeft);
        mLeftLinearLayout.setBackgroundDrawable(mLeftBackground);
        mLeftLinearLayout.setGravity(Gravity.CENTER);
        mLeftLinearLayout.setPadding(mLeftPadding, 0, mLeftPadding, 0); // 左右padding
        // 左边ImageView
        mLeftImageView = new ImageView(context);
        mLeftImageView.setImageResource(mLeftImageSrc);
        mLeftImageView.setPadding(0, 0, dp2px(1), 0); // 默认图片右边跟文字的距离设为1dp
        mLeftLinearLayout.addView(mLeftImageView);
        // 左边TextView
        mLeftTextView = new TextView(context);
        mLeftTextView.setText(mLeftText);
        mLeftTextView.setTextColor(mLeftTextColor);
        mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        mLeftLinearLayout.addView(mLeftTextView);
        // 添加左边布局
        addView(mLeftLinearLayout);
        mLeftLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTopBarClickListener.onTopBarLeftClicked(v);
            }
        });

        // 右边
        FrameLayout.LayoutParams lpRight = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lpRight.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        mRightLinearLayout = new LinearLayout(context);
        mRightLinearLayout.setLayoutParams(lpRight);
        mRightLinearLayout.setBackgroundDrawable(mRightBackground);
        mRightLinearLayout.setGravity(Gravity.CENTER);
        mRightLinearLayout.setPadding(mRightPadding, 0, mRightPadding, 0); // 左右padding
        // 右边ImageView
        mRightImageView = new ImageView(context);
        mRightImageView.setImageResource(mRightImageSrc);
        mRightImageView.setPadding(0, 0, dp2px(1), 0); // 默认图片右边跟文字的距离设为1dp
        mRightLinearLayout.addView(mRightImageView);
        // 右边TextView
        mRightTextView = new TextView(context);
        mRightTextView.setText(mRightText);
        mRightTextView.setTextColor(mRightTextColor);
        mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
        mRightLinearLayout.addView(mRightTextView);
        // 添加右边布局
        addView(mRightLinearLayout);
        mRightLinearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTopBarClickListener.onTopBarRightClicked(v);
            }
        });

        // 底部
        FrameLayout.LayoutParams lpBottom = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, mBottomLineHeight);
        lpBottom.gravity = Gravity.BOTTOM;
        mBottomLine = new View(context);
        mBottomLine.setLayoutParams(lpBottom);
        mBottomLine.setBackgroundDrawable(mBottomLineDrawable);
        addView(mBottomLine);
    }

    public void setTitle(String title) {
        this.mTitle = title;
        this.mTitleTextView.setText(mTitle);
        invalidate();
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.mTitleTextColor = titleTextColor;
        this.mTitleTextView.setTextColor(mTitleTextColor);
        invalidate();
    }

    /**
     * 设置标题文字大小
     * @param titleTextSize 标题文字大小，单位sp，只需传数值即可
     */
    public void setTitleTextSize(float titleTextSize) {
        this.mTitleTextSize = sp2px(titleTextSize);
        this.mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        invalidate();
    }

    public void setLeftText(String leftText) {
        this.mLeftText = leftText;
        this.mLeftTextView.setText(mLeftText);
        invalidate();
    }

    public View getLeftLayout() {
        return mLeftLinearLayout;
    }

    public ImageView getLeftImageView() {
        return mLeftImageView;
    }

    public void setLeftImageViewResource(int resId) {
        this.mLeftImageSrc = resId;
        this.mLeftImageView.setImageResource(mLeftImageSrc);
        invalidate();
    }

    public void setLeftImageViewDrawable(Drawable leftImageViewDrawable) {
        this.mLeftImageView.setImageDrawable(leftImageViewDrawable);
        invalidate();
    }

    public TextView getLeftTextView() {
        return mLeftTextView;
    }

    /**
     * 左边布局padding（暂时左右padding一样，如果有特殊需求可以为上下左右单独设置padding）
     * @param leftPadding
     */
    public void setLeftPadding(float leftPadding) {
        this.mLeftPadding = dp2px(leftPadding);
        this.mLeftLinearLayout.setPadding(mLeftPadding, 0, mLeftPadding, 0); // 左右padding
        invalidate();
    }

    public void setLeftTextColor(int leftTextColor) {
        this.mLeftTextColor = leftTextColor;
        this.mLeftTextView.setTextColor(mLeftTextColor);
        invalidate();
    }

    /**
     * 设置左边文字大小
     * @param leftTextSize 左边文字大小，单位sp，只需传数值即可
     */
    public void setLeftTextSize(float leftTextSize) {
        this.mLeftTextSize = sp2px(leftTextSize);
        this.mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        invalidate();
    }

    public void setLeftBackground(Drawable leftBackground) {
        this.mLeftBackground = leftBackground;
        this.mLeftLinearLayout.setBackgroundDrawable(mLeftBackground);
        invalidate();
    }

    public void setRightText(String rightText) {
        this.mRightText = rightText;
        this.mRightTextView.setText(mRightText);
        invalidate();
    }

    public View getRightLayout() {
        return mRightLinearLayout;
    }

    public ImageView getRightImageView() {
        return mRightImageView;
    }

    public void setRightImageViewResource(int resId) {
        this.mRightImageSrc = resId;
        this.mRightImageView.setImageResource(mRightImageSrc);
        invalidate();
    }

    public void setRightImageViewDrawable(Drawable leftImageViewDrawable) {
        this.mRightImageView.setImageDrawable(leftImageViewDrawable);
        invalidate();
    }

    public TextView getRightTextView() {
        return mRightTextView;
    }

    /**
     * 左边布局padding（暂时左右padding一样，如果有特殊需求可以为上下左右单独设置padding）
     * @param rightPadding
     */
    public void setRightPadding(int rightPadding) {
        this.mRightPadding = dp2px(rightPadding);
        this.mRightLinearLayout.setPadding(mRightPadding, 0, mRightPadding, 0); // 左右padding
        invalidate();
    }

    public void setRightTextColor(int rightTextColor) {
        this.mRightTextColor = rightTextColor;
        this.mRightTextView.setTextColor(mRightTextColor);
        invalidate();
    }

    /**
     * 设置左边文字大小
     * @param rightTextSize 左边文字大小，单位sp，只需传数值即可
     */
    public void setRightTextSize(float rightTextSize) {
        this.mRightTextSize = sp2px(rightTextSize);
        this.mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
        invalidate();
    }

    public void setRightBackground(Drawable rightBackground) {
        this.mRightBackground = rightBackground;
        this.mRightLinearLayout.setBackgroundDrawable(mRightBackground);
        invalidate();
    }

    public View getBottomLine() {
        return mBottomLine;
    }

    public void setBottomLineColor(int bottomLineColor) {
        this.mBottomLine.setBackgroundColor(bottomLineColor);
        invalidate();
    }

    public void setBottomLineDrawable(Drawable bottomLineDrawable) {
        this.mBottomLineDrawable = bottomLineDrawable;
        this.mBottomLine.setBackgroundDrawable(mBottomLineDrawable);
        invalidate();
    }

    public void setBottomLineHeight(float bottomLineHeight) {
        this.mBottomLineHeight = dp2px(bottomLineHeight);
        FrameLayout.LayoutParams lpBottom = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, mBottomLineHeight);
        lpBottom.gravity = Gravity.BOTTOM;
        mBottomLine.setLayoutParams(lpBottom);
    }

    private int dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }
}
