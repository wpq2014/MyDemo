package com.demo.wpq.mydemo.customview.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.demo.wpq.mydemo.utils.KeyboardUtil;

/**
 * Desc: 解决ScrollView嵌套ViewPager上下左右滚动冲突；
 *       触摸ScrollView弹回输入法键盘，针对ScrollView里有EditText的情况
 *
 * Created by wpq on 16/7/12.
 */
public class MScrollView extends ScrollView {

    private float xDistance, yDistance, xLast, yLast;

    public MScrollView(Context context) {
        super(context);
    }

    public MScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float xCurr = ev.getX();
                final float yCurr = ev.getY();

                xDistance += Math.abs(xCurr - xLast);
                yDistance += Math.abs(yCurr - yLast);
                if (xDistance > yDistance) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        KeyboardUtil.hideKeyboard(this);
        return super.onTouchEvent(ev);
    }
}

      /** ScrollView嵌套ViewPager也可以这样解决冲突 */
//    private void init(){
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mViewPagerAdapter=new ViewPagerAdapter(this);
//        mViewPager.setAdapter(mViewPagerAdapter);
//        mViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mViewPager.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//    }