package com.demo.wpq.mydemo.customview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context context) {
		super(context);
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeTextView(Context context, AttributeSet attrs,
						   int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean isFocused() {
		return true;
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
	}

}

// <com.healskare.miaoyi.ui.widget.MyMarqueeTextView
// android:id="@+id/tv_remind"
// android:layout_width="match_parent"
// android:layout_height="wrap_content"
// android:textSize="14sp"
// android:background="@color/remind_bgcolor"
// android:textColor="@color/remind_textcolor"
// android:text="滚滚长江东逝水，浪花淘尽英雄，是非成败转头空，青山依旧在，几度夕阳红；白发渔樵江渚上，惯看秋月春风，一壶浊酒喜相逢，古今多少事，都付笑谈中.."
// android:paddingLeft="10dp"
// android:paddingTop="8dp"
// android:paddingBottom="8dp"
// android:marqueeRepeatLimit="marquee_forever"
// android:ellipsize="marquee"
// android:scrollHorizontally="true"
// android:focusableInTouchMode="true"
// android:singleLine="true"
// android:focusable="true"
// />