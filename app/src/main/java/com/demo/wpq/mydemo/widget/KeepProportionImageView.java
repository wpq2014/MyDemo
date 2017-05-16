package com.demo.wpq.mydemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.demo.wpq.mydemo.R;

/**
 * Desc: ImageView按原图宽高比适配
 * Author: wpq
 * Date: 2017/4/17 16:59
 */
public class KeepProportionImageView extends AppCompatImageView{

    /** 原图宽高比 */
    private float ratio;

    public KeepProportionImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.KeepProportionImageView);
        ratio = ta.getFloat(R.styleable.KeepProportionImageView_width_to_height_radio, 0F);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (0 != ratio) {
            // 按照原图宽高比计算新的高度
            int height = (int) (MeasureSpec.getSize(widthMeasureSpec) / ratio);
            // 重新适配
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
