package com.demo.wpq.mydemo.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.demo.wpq.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * Created by wpq on 16/7/15.
 */
public class TreeViewActivity extends AppCompatActivity {


    @BindView(R.id.treeView)
    ListView treeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treeview);
        ButterKnife.bind(this);
    }
}
