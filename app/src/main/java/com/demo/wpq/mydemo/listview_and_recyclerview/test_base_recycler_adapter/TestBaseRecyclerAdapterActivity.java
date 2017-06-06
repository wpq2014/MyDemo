package com.demo.wpq.mydemo.listview_and_recyclerview.test_base_recycler_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.base.BaseRecyclerAdapter;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.listview_and_recyclerview.util.DividerItemDecoration;
import com.demo.wpq.mydemo.utils.MToastUtil;
import com.demo.wpq.mydemo.widget.recyclerview.RecyclerHeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 测试{@link BaseRecyclerAdapter}
 *
 * @author wpq
 * @version 1.0
 */
public class TestBaseRecyclerAdapterActivity extends BaseAppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    // intent data
    private String title;

    private TestBaseRecyclerAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, TestBaseRecyclerAdapterActivity.class);
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
        return R.layout.activity_test_base_recycler_adapter;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @SuppressLint("InflateParams")
    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL, Color.TRANSPARENT, 5));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i= 0; i < 40; i++) {
            mList.add("我是 " + i + " 号种子选手");
        }

        mAdapter = new TestBaseRecyclerAdapter(mList);

        // header and footer
        final RecyclerHeaderAndFooterWrapper headerAndFooterWrapper = new RecyclerHeaderAndFooterWrapper(mAdapter);
        headerAndFooterWrapper.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_test_recycler0, null));
        headerAndFooterWrapper.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_test_recycler1, null));
        headerAndFooterWrapper.addFooterView(LayoutInflater.from(this).inflate(R.layout.footer_test_recycler0, null));
        mRecyclerView.setAdapter(headerAndFooterWrapper);

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, int position) {
                MToastUtil.show("您点击了：第" + (position - 2) + "项");
            }
        });

        mAdapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, int position) {
                MToastUtil.show("您长按删除了：第" + (position - 2) + "项");
                mList.remove(position - 2);
//                mAdapter.notifyItemRemoved(position);
                headerAndFooterWrapper.notifyItemRemoved(position);
                return true;
            }
        });
    }

}
