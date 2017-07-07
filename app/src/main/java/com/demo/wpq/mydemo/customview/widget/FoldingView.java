package com.demo.wpq.mydemo.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.demo.wpq.mydemo.utils.MScreenUtil;

/**
 * 手动画加减号：+ and -  作为展开折叠图标
 * @author wpq
 * @version 1.0
 */
public class FoldingView extends View {

    private static final int PAINT_COLOR = 0xFF84a6c5;

    private Paint mIconPaint;
    private boolean mFolding = true;

    public FoldingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mIconPaint = new Paint();
        mIconPaint.setAntiAlias(true);
        mIconPaint.setStrokeWidth(MScreenUtil.dp2px(2f, getResources()));
        mIconPaint.setColor(PAINT_COLOR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        canvas.drawLine(0, halfHeight, width, halfHeight, mIconPaint);
        if (mFolding) {
            canvas.drawLine(halfWidth, 0, halfWidth, height, mIconPaint);
        }
    }

    public void setFolding(boolean folding) {
        if (folding != this.mFolding) {
            this.mFolding = folding;
            invalidate();
        }
    }
}
