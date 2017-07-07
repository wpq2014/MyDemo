package com.demo.wpq.mydemo.listview_and_recyclerview.timeline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wpq
 * @version 1.0
 */
public class TimeLineActivity extends BaseAppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    // intent data
    private String title;

    private List<TimeLineBean> mList = new ArrayList<>();
    private TimeLineAdapter mAdapter;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, TimeLineActivity.class);
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
        return R.layout.activity_timeline;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 20; i++) {
            mList.add(new TimeLineBean());
        }
        mRecyclerView.setAdapter(mAdapter = new TimeLineAdapter(mList));
    }

}
