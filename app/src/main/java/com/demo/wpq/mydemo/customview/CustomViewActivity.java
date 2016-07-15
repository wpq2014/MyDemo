package com.demo.wpq.mydemo.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.demo.wpq.mydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Desc: 自定义View
 * <p/>
 * Created by wpq on 16/7/14.
 */
public class CustomViewActivity extends AppCompatActivity {

    @Bind(R.id.simple)
    Button simple;
    @Bind(R.id.tree)
    Button tree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.simple, R.id.tree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simple: //简单自定义
            {
                startActivity(new Intent(this, TopBarActivity.class));
                break;
            }
            case R.id.tree: //任意层级树
            {

                break;
            }
        }
    }

}
