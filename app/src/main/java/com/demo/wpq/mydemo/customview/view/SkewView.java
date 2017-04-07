package com.demo.wpq.mydemo.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * http://blog.csdn.net/cquwentao/article/details/51363719
 * Created by lion on 2017/4/7.
 */

public class SkewView extends View {

    private Paint mRedPaint;
    private Paint mGreenPaint;

    public SkewView(Context context) {
        super(context);

        init();
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mRedPaint = generatePaint(Color.RED, Paint.Style.FILL, 5);
        mGreenPaint = generatePaint(Color.GREEN, Paint.Style.FILL, 5);
    }

    private Paint generatePaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        paint.setTextSize(50);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("画布在x轴方向上倾斜45°:", 30, 80, mRedPaint);
        canvas.save();
        Rect rect = new Rect(30, 110, 320, 310);
        canvas.drawRect(rect, mRedPaint);
        canvas.skew(1, 0); // x轴倾斜45°
        canvas.drawRect(rect, mGreenPaint);
        canvas.restore();

        canvas.drawText("画布在y轴方向上倾斜45°:", 30, 400, mRedPaint);
        canvas.save();
        Rect rect2 = new Rect(30, 430, 320, 630);
        canvas.drawRect(rect2, mRedPaint);
        canvas.skew(0, 1); // y轴倾斜45°
        canvas.drawRect(rect2, mGreenPaint);
        canvas.restore();

    }

}
