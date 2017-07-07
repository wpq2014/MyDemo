package com.demo.wpq.mydemo.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.demo.wpq.mydemo.utils.MScreenUtil;

/**
 * 时间轴
 * @author wpq
 * @version 1.0
 */
public class TimeLineView extends View {

    public enum Type{
        START, END, CENTER
    }

    // 起始状态颜色
    private static final int START_COLOR  = 0xFFFF0000;
    // 结束状态颜色
    private static final int END_COLOR    = 0xFF00FF00;
    // 其他状态颜色
    private static final int CENTER_COLOR = 0xFF888888;

    private Paint mStartPaint;
    private Paint mEndPaint;
    private Paint mCenterPaint;
    private Type mType;
    private Bitmap mBitmapCache;

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mStartPaint = new Paint();
        mStartPaint.setAntiAlias(true);
        mStartPaint.setColor(START_COLOR);
        mStartPaint.setStrokeWidth(MScreenUtil.dp2px(2f, getResources()));

        mEndPaint = new Paint();
        mEndPaint.setAntiAlias(true);
        mEndPaint.setColor(END_COLOR);
        mEndPaint.setStrokeWidth(MScreenUtil.dp2px(2f, getResources()));

        mCenterPaint = new Paint();
        mCenterPaint.setAntiAlias(true);
        mCenterPaint.setColor(CENTER_COLOR);
        mCenterPaint.setStrokeWidth(MScreenUtil.dp2px(2f, getResources()));

        mType = Type.START;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        if (mBitmapCache != null && (mBitmapCache.getWidth() != width || mBitmapCache.getHeight() != height)) {
            mBitmapCache.recycle();
            mBitmapCache = null;
        }

        if (mBitmapCache == null) {
            mBitmapCache = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvasCache = new Canvas(mBitmapCache);

            float halfWidth = width / 2f;
            float halfHeight = height / 2f;
            float centerCircleRadius = width / 4f;

            switch (mType) {
                case START:
                    canvasCache.drawLine(halfWidth, halfHeight, halfWidth, height, mCenterPaint);
                    canvasCache.drawCircle(halfWidth, halfHeight, halfWidth, mStartPaint);
                    break;
                case END:
                    canvasCache.drawLine(halfWidth, 0, halfWidth, halfHeight, mCenterPaint);
                    canvasCache.drawCircle(halfWidth, halfHeight, halfWidth, mEndPaint);
                    break;
                case CENTER:
                    canvasCache.drawLine(halfWidth, 0, halfWidth, height, mCenterPaint);
                    canvasCache.drawCircle(halfWidth, halfHeight, centerCircleRadius, mCenterPaint);
                    break;
            }
        }
        canvas.drawBitmap(mBitmapCache, 0, 0, null);
    }

    public void setType(Type type) {
        if (type != this.mType) {
            this.mType = type;
            if (mBitmapCache != null) {
                mBitmapCache.recycle();
                mBitmapCache = null;
            }
            invalidate();
        }
    }
}
