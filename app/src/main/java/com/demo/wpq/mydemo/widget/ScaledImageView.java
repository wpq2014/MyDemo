package com.demo.wpq.mydemo.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 保持原图比例
 * @author wpq
 * @version 1.0
 */
public class ScaledImageView extends AppCompatImageView {

    private int mOriginalWidth, mOriginalHeight;

    public ScaledImageView(Context context) {
        super(context);
    }

    public ScaledImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 原图尺寸（px）
     * @param originalWidth 原图宽（px）
     * @param originalHeight 原图高（px）
     */
    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.mOriginalWidth = originalWidth;
        this.mOriginalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mOriginalWidth > 0 && mOriginalHeight > 0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            Log.e("ScaledImageView", width + "，" + height);
            float scale = (float) mOriginalWidth / (float) mOriginalHeight;
            if (width > 0) {
                height = (int) (width * scale);
                Log.e("ScaledImageView", width + "，" + height);
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
