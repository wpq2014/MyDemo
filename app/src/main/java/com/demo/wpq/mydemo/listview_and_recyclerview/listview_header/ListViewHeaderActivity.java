package com.demo.wpq.mydemo.listview_and_recyclerview.listview_header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
public class ListViewHeaderActivity extends BaseAppCompatActivity {

    @BindView(R.id.listView)
    ListView mListView;

    // intent data
    private String title;

    private ListViewHeaderAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, ListViewHeaderActivity.class);
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
        return R.layout.activity_listview_header;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.nav_header_main, null));
        mAdapter = new ListViewHeaderAdapter(mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}
