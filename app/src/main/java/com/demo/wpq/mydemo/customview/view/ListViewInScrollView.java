package com.demo.wpq.mydemo.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Desc: ScrollView嵌套ListView，解决高度问题，嵌套GridView也是这样
 *
 * Created by wpq on 16/7/11.
 */
public class ListViewInScrollView extends ListView{
    public ListViewInScrollView(Context context) {
        super(context);
    }

    public ListViewInScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewInScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public ListViewInScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
