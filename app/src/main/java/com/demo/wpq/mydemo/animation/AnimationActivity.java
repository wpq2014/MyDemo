package com.demo.wpq.mydemo.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc: 动画
 *
 * Created by wpq on 16/7/15.
 */
public class AnimationActivity extends BaseAppCompatActivity {

    @BindView(R.id.objectAnimator)
    Button objectAnimator;
    @BindView(R.id.layoutAnimator)
    Button layoutAnimator;

    @Override
    public void getBundleExtras(Bundle bundle) {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_animation;
    }

    @Override
    public String getToolBarTitle() {
        return getString(R.string.animation);
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.objectAnimator, R.id.layoutAnimator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.objectAnimator: //传统动画和属性动画
            {
                startActivity(new Intent(this, ObjectAnimatorActivity.class));
                break;
            }
            case R.id.layoutAnimator: //布局动画
            {
                startActivity(new Intent(this, LayoutAnimationActivity.class));
                break;
            }
        }
    }
}
