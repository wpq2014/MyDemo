package com.demo.wpq.mydemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * EditText如果限制了最大输入，超出的话，给出相应的提示
 * @author WuPuquan
 * @version 1.0
 */
@SuppressLint("AppCompatCustomView")
public class MaxLengthEditText extends EditText {

    public static final String TAG = MaxLengthEditText.class.getSimpleName();

    private OnTextLengthWatcher mOnTextLengthWatcher;

    public MaxLengthEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLength(attrs, context);
    }

    public MaxLengthEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLength(attrs, context);
    }

    private void initLength(AttributeSet a, Context context) {
        //命名空间
        String namespace = "http://schemas.android.com/apk/res/android";
        //获取属性中设置的最大长度
        int maxLength = a.getAttributeIntValue(namespace, "maxLength", -1);
        //如果设置了最大长度，给出相应的处理
        if (maxLength > -1) {
            setFilters(new InputFilter[]{new MyLengthFilter(maxLength, context)});
        }
    }

    /**
     * 从源码中复制出来的
     * 来源：InputFilter.LengthFilter
     * <p>
     * <p>
     * 这里只是添加了一句话：
     * Toast.makeText(context, "字数不能超过" + mMax, Toast.LENGTH_SHORT).show();
     * <p>
     * This filter will constrain edits not to make the length of the text
     * greater than the specified length.
     */
    private class MyLengthFilter implements InputFilter {

        private final int mMax;
        private Context context;

        public MyLengthFilter(int max, Context context) {
            mMax = max;
            this.context = context;
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            //这里的操作可能不太懂，但是这种格式可以简化一下，成为：
            //int keep = mMax - x;
            //可以猜想：keep表示当前字符超出maxLength的大小
            int keep = mMax - (dest.length() - (dend - dstart));

            //这里对keep的值分别作了判断:
            //keep<=0:也就是当前输入的字符数量大于或者等于maxLength
            //返回""，当字符数量达到maxLength时，我们不能再输入内容，""符合我们的认知
            if (keep <= 0) {
                //这里，用来给用户提示
//                Toast.makeText(context, "字数不能超过" + mMax, Toast.LENGTH_SHORT).show();
                if (null != mOnTextLengthWatcher) {
                    mOnTextLengthWatcher.onOverMaxLength();
                }
                return "";
            } else if (keep >= end - start) {
                return null; // keep original
            } else {
                keep += start;
                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                    --keep;
                    if (keep == start) {
                        return "";
                    }
                }
                return source.subSequence(start, keep);
            }
        }

        /**
         * @return the maximum length enforced by this input filter
         */
        public int getMax() {
            return mMax;
        }
    }

    public interface OnTextLengthWatcher {

        /**
         * 超过指定字数时触发
         */
        void onOverMaxLength();
    }

    public void addOnTextLengthWatcher(OnTextLengthWatcher onTextLengthWatcher) {
        this.mOnTextLengthWatcher = onTextLengthWatcher;
    }
}