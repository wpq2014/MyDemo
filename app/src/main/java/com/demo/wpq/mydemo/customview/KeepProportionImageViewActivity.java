package com.demo.wpq.mydemo.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.widget.KeepProportionImageView;

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

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, KeepProportionImageViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getBundleExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_keep_proportion_imageview;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }
}
