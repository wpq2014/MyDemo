package com.demo.wpq.mydemo.listview_and_recyclerview.test_base_recycler_adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.listview_and_recyclerview.util.GridSpacingItemDecoration;
import com.demo.wpq.mydemo.retrofit.GanHuo;
import com.demo.wpq.mydemo.retrofit.RetrofitService;
import com.demo.wpq.mydemo.utils.MScreenUtil;
import com.demo.wpq.mydemo.utils.MToastUtil;
import com.demo.wpq.mydemo.widget.recyclerview.MyRecyclerView;
import com.demo.wpq.mydemo.widget.recyclerview.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wpq
 * @version 1.0
 */
public class TestMyRecyclerViewActivity extends BaseAppCompatActivity {

    public static final String TAG = TestMyRecyclerViewActivity.class.getSimpleName();
    /** 分页设置每页10条 */
    public static final int PAGE_ITEM = 9;

    @BindView(R.id.recyclerView)
    MyRecyclerView mRecyclerView;

    // intent data
    private String title;

    private List<GanHuo.Result> mList = new ArrayList<>();
    private TestMyRecyclerViewAdapter mAdapter;
    private int page = 54; // 当前用的接口最多512条数据

    View header1;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, TestMyRecyclerViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getIntentExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_test_my_recyclerview;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, MScreenUtil.dp2px(this, 2), false));
//        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL, Color.TRANSPARENT, MScreenUtil.dp2px(this, 1)));
//        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.HORIZONTAL, Color.TRANSPARENT, MScreenUtil.dp2px(this, 1)));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setOnLoadListener(new MyRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                gankGirl();
            }
        });

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View header0 = LayoutInflater.from(this).inflate(R.layout.header_test_recycler0, null);
        header0.setLayoutParams(lp);
        mRecyclerView.addHeaderView(header0);
         header1 = LayoutInflater.from(this).inflate(R.layout.header_test_recycler1, null);
        header1.setLayoutParams(lp);
        mRecyclerView.addHeaderView(header1);
        // 因为上边设置了setLoadMoreEnabled(true)，所以这里addFooterView不生效
        final View footer0 = LayoutInflater.from(this).inflate(R.layout.footer_test_recycler0, null);
        footer0.setLayoutParams(lp);
        mRecyclerView.addFooterView(footer0);

        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                MToastUtil.show(TestMyRecyclerViewActivity.this, "点击了第 " + viewHolder.getAdapterPosition() + " 项");
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {
                MToastUtil.show(TestMyRecyclerViewActivity.this, "长按了第 " + viewHolder.getAdapterPosition() + " 项");
            }
        });

        mAdapter = new TestMyRecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

//        mRecyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mRecyclerView.removeHeaderView(header1);
//                mRecyclerView.removeFooterView(footer0);
//            }
//        }, 2000);

        gankGirl();
    }

    private void gankGirl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<GanHuo> call = retrofitService.getGanHuo("福利", PAGE_ITEM, page);
        call.enqueue(new Callback<GanHuo>() {
            @Override
            public void onResponse(Call<GanHuo> call, retrofit2.Response<GanHuo> response) {
                Log.e(TAG, response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    Log.e(TAG, response.body()+"");
                    GanHuo ganhuo = response.body();

                    mList.addAll(ganhuo.getResults());
                    mAdapter.notifyDataSetChanged();
                    if (mList.size() < PAGE_ITEM) {
                        mRecyclerView.noNeedToLoadMore();
                    } else if (ganhuo.getResults().size() < PAGE_ITEM) {
                        mRecyclerView.noMore();
                    } else {
                        mRecyclerView.loadMoreComplete();
                        page++;
                    }

                }
            }

            @Override
            public void onFailure(Call<GanHuo> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                mRecyclerView.loadMoreError();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_recyclerview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_more_than_one_page: // 一页以上
                mList.clear();
                mAdapter.notifyDataSetChanged();
                page = 54;
                gankGirl();
                break;
            case R.id.action_not_enough_one_page: // 不足一页
                mList.clear();
                mAdapter.notifyDataSetChanged();
                page = 57;
                gankGirl();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
