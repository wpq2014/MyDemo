package com.demo.wpq.mydemo.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.view.KeepProportionImageView;

import butterknife.BindView;

/**
 * Desc: 测试ImageView保持原图宽高比
 * Author: wpq
 * Date: 2017/4/17 17:30
 */
public class KeepProportionImageViewActivity extends BaseAppCompatActivity {

    @BindView(R.id.imageview0)
    ImageView mImageview0;
    @BindView(R.id.imageview1)
    KeepProportionImageView mImageview1;

    @Override
    public void getBundleExtras(Bundle bundle) {

    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_keep_proportion_imageview;
    }

    @Override
    public String getToolBarTitle() {
        return getString(R.string.KeepProportionImageViewActivity);
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }
}
