package com.demo.wpq.mydemo.listview_and_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.customview.KeepProportionImageViewActivity;
import com.demo.wpq.mydemo.customview.adapter.CustomAdapter;
import com.demo.wpq.mydemo.customview.bean.CustomBean;
import com.demo.wpq.mydemo.listview_and_recyclerview.listview_header.ListViewHeaderActivity;
import com.demo.wpq.mydemo.listview_and_recyclerview.recyclerview_wrap_recyclerview.ComplexRecyclerViewActivity;
import com.demo.wpq.mydemo.listview_and_recyclerview.test_base_recycler_adapter.TestBaseRecyclerAdapterActivity;
import com.demo.wpq.mydemo.listview_and_recyclerview.todolist.TodoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * @author wpq
 * @version 1.0
 */
public class ListViewAndRecyclerViewActivity extends BaseAppCompatActivity implements CustomAdapter.OnRecyclerListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindArray(R.array.listViewAndRecyclerView_array)
    String[] array;

    // intent data
    private String title;

    private Class<?>[] mClasses = {ListViewHeaderActivity.class, TodoActivity.class, ComplexRecyclerViewActivity.class, TestBaseRecyclerAdapterActivity.class};

    private List<CustomBean> mList = new ArrayList<>();
    private CustomAdapter mCustomAdapter;

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
        return R.layout.activity_listview_and_recyclerview;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));

        for (int i = 0; i < array.length; i++) {
            mList.add(new CustomBean(array[i], mClasses[i]));
        }
        mCustomAdapter = new CustomAdapter(this, mList);
        mCustomAdapter.setOnRecyclerListener(this);
        mRecyclerView.setAdapter(mCustomAdapter);
    }

    @Override
    public void onItemClicked(CustomBean customBean) {
        Intent intent = new Intent(this, customBean.intentClass);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, customBean.itemName);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
