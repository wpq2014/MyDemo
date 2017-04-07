package com.demo.wpq.mydemo.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.demo.wpq.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 动画
 *
 * Created by wpq on 16/7/15.
 */
public class AnimationActivity extends AppCompatActivity {


    @BindView(R.id.objectAnimator)
    Button objectAnimator;
    @BindView(R.id.layoutAnimator)
    Button layoutAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
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
