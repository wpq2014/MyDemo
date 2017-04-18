package com.demo.wpq.mydemo.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.view.KeepProportionImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc: 测试ImageView保持原图宽高比
 * Author: wpq
 * Date: 2017/4/17 17:30
 */
public class KeepProportionImageViewActivity extends AppCompatActivity {

    @BindView(R.id.imageview0)
    ImageView mImageview0;
    @BindView(R.id.imageview1)
    KeepProportionImageView mImageview1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_proportion_imageview);
        ButterKnife.bind(this);
    }
}
