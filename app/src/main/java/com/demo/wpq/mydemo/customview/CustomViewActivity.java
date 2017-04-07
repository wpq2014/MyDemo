package com.demo.wpq.mydemo.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.customview.adapter.CustomAdapter;
import com.demo.wpq.mydemo.customview.bean.CustomBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.demo.wpq.mydemo.R.id.recyclerView;

/**
 * Desc: 自定义View
 * <p>
 * Created by wpq on 16/7/14.
 */
public class CustomViewActivity extends AppCompatActivity implements CustomAdapter.OnRecyclerListener {

    @BindView(recyclerView)
    RecyclerView mRecyclerView;

    @BindArray(R.array.customView_array)
    String[] array;

    private Class<?>[] mClasses = {TopBarActivity.class, LinkedListViewActivity.class, TreeViewActivity.class, SkewActivity.class};

    private List<CustomBean> mList = new ArrayList<>();
    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));

        for (int i = 0; i < array.length; i++) {
            mList.add(new CustomBean(array[i], mClasses[i]));
        }
        mCustomAdapter = new CustomAdapter(this, mList);
        mCustomAdapter.setOnRecyclerListener(this);
        mRecyclerView.setAdapter(mCustomAdapter);
    }

    @Override
    public void onItemClicked(CustomBean customBean) {
        startActivity(new Intent(this, customBean.intentClass));
    }
}
