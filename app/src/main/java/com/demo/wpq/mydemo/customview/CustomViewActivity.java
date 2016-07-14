package com.demo.wpq.mydemo.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.simple)
    public void onClick() {
        startActivity(new Intent(this, TopBarActivity.class));
    }
}
