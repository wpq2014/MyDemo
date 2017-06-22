package com.demo.wpq.mydemo.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Desc: 布局动画
 * <p/>
 * Created by wpq on 16/7/15.
 */
public class LayoutAnimationActivity extends BaseAppCompatActivity {


    @BindView(R.id.listView)
    ListView listView;

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, ObjectAnimatorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getIntentExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_layout_animation;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 40; i++) {
                    list.add("LayoutAnimationController " + i);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LayoutAnimationActivity.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);

                //两种方式：布局文件设置或代码控制
                LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(LayoutAnimationActivity.this, R.anim.layout_animation1));
                lac.setInterpolator(new AccelerateDecelerateInterpolator());
                lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
                listView.setLayoutAnimation(lac);
            }
        }, 600);
    }
}
