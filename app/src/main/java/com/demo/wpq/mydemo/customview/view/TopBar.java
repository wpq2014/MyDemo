package com.demo.wpq.mydemo.customview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;

/**
 * Desc: 自定义TopBar
 *
 * Created by wpq on 16/7/11.
 */
public class TopBar extends LinearLayout{

    private final int topBarBackground = 0xCC000000;
    private final int textColorDefault = 0xFFFFFFFF;
    private final float titleTextSizeDefault = 18f;
    private final float leftTextSizeDefault = 15f;

    private TextView tvTitile;
    private String titleText;
    private int titleTextColor;
    private float titleTextSize;

    private Button leftButton;
    private String leftText;
    private int leftTextColor;
    private float leftTextSize;
    private Drawable leftBackground;

    private Button rightButton;
    private String rightText;
    private int rightTextColor;
    private float rightTextSize;
    private Drawable rightBackground;
    private boolean rightVisible;


    public interface OnTopBarClickListener{
        void onTopBarLeftButtonClick(View v);
        void onTopBarRightButtonClick(View v);
    }

    private OnTopBarClickListener onTopBarClickListener;

    public void setOnTopBarClickListener(OnTopBarClickListener onTopBarClickListener){
        this.onTopBarClickListener = onTopBarClickListener;
    }


    public TopBar(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(final Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        titleText = t.getString(R.styleable.TopBar_tTitle);
        titleTextColor = t.getColor(R.styleable.TopBar_tTitleTextColor, textColorDefault);
        titleTextSize = t.getDimension(R.styleable.TopBar_tTitleTextSize, titleTextSizeDefault);

        leftText = t.getString(R.styleable.TopBar_tLeftText);
        leftTextColor = t.getColor(R.styleable.TopBar_tLeftTextColor, textColorDefault);
        leftTextSize = t.getDimension(R.styleable.TopBar_tLeftTextSize, leftTextSizeDefault);
        leftBackground = t.getDrawable(R.styleable.TopBar_tLeftBackground);

        rightText = t.getString(R.styleable.TopBar_tRightText);
        rightTextColor = t.getColor(R.styleable.TopBar_tRightTextColor, textColorDefault);
        rightTextSize = t.getDimension(R.styleable.TopBar_tRightTextSize, leftTextSizeDefault);
        rightBackground = t.getDrawable(R.styleable.TopBar_tRightBackground);
        rightVisible = t.getBoolean(R.styleable.TopBar_tRightVisible, true);

        t.recycle();

        LinearLayout.LayoutParams lpTitle = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f);
        tvTitile = new TextView(context);
        tvTitile.setLayoutParams(lpTitle);
        tvTitile.setGravity(Gravity.CENTER);
        tvTitile.setText(titleText);
        tvTitile.setTextColor(titleTextColor);
        tvTitile.setTextSize(titleTextSize);

        LinearLayout.LayoutParams lpLeft = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 0f);
        leftButton = new Button(context);
        leftButton.setLayoutParams(lpLeft);
        tvTitile.setGravity(Gravity.CENTER);
        if(!TextUtils.isEmpty(leftText)) {
            leftButton.setText(leftText);
            leftButton.setTextColor(leftTextColor);
            leftButton.setTextSize(leftTextSize);
            leftButton.setBackground(leftBackground);
        }

        rightButton = new Button(context);
        rightButton.setLayoutParams(lpLeft);
        tvTitile.setGravity(Gravity.CENTER);
        if(!TextUtils.isEmpty(rightText)) {
            rightButton.setText(rightText);
            rightButton.setTextColor(rightTextColor);
            rightButton.setTextSize(leftTextSize);
            rightButton.setBackground(rightBackground);
            if(rightVisible){
                rightButton.setVisibility(View.VISIBLE);
            }else{
                rightButton.setVisibility(View.INVISIBLE);
            }
        }

        setBackgroundColor(topBarBackground);
        addView(leftButton);
        addView(tvTitile);
        addView(rightButton);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTopBarClickListener.onTopBarLeftButtonClick(v);
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTopBarClickListener.onTopBarRightButtonClick(v);
            }
        });
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


}
