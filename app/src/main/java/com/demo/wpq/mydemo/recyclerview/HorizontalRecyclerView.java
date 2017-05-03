package com.demo.wpq.mydemo.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author wpq
 * @version 1.0
 */
public class HorizontalRecyclerView extends RecyclerView {

    private float xDistance;
    private float yDistance;
    private float xLast;
    private float yLast;

    public HorizontalRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public HorizontalRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {

        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xDistance = yDistance = 0f;
//                xLast = e.getX();
//                yLast = e.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final float xCurr = e.getX();
//                final float yCurr = e.getY();
//
//                xDistance += Math.abs(xCurr - xLast);
//                yDistance += Math.abs(yCurr - yLast);
//
//                if (xDistance > yDistance) {
//                    return true;
//                }
//                break;
//        }
        return super.onTouchEvent(e);
    }
}
