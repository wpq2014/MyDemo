package com.demo.wpq.mydemo.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.adapter.DishLeftAdapter;
import com.demo.wpq.mydemo.adapter.DishRightAdapter;
import com.demo.wpq.mydemo.bean.DishItem;
import com.demo.wpq.mydemo.customview.view.PinnedHeaderListView;
import com.demo.wpq.mydemo.model.DishModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * Created by wpq on 16/8/9.
 */
public class LinkedListViewActivity extends AppCompatActivity {

    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.pinnedheader_listview)
    PinnedHeaderListView pinnedheaderListview;

    private DishModel dishModel = new DishModel();
    private DishLeftAdapter leftAdapter;
    private DishRightAdapter rightAdapter;

    private boolean isScroll = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_listview);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            String leftType = "第" + i + "类";
            dishModel.addLeft(leftType);
            List<DishItem> listItem = new ArrayList<>();
            for(int j=0; j<3; j++){
                listItem.add(new DishItem(leftType+" "+"第"+j+"项"));
            }
            dishModel.addRight(leftType, listItem);
        }

        leftAdapter = new DishLeftAdapter(this, dishModel);
        leftListview.setAdapter(leftAdapter);

        rightAdapter = new DishRightAdapter(this, dishModel);
        pinnedheaderListview.setAdapter(rightAdapter);

        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isScroll = false;
                setLeft(position);

                int rightSection = 0;
                for(int i=0; i<position; i++){
                    rightSection += rightAdapter.getCountForSection(i) + 1;
                }
                pinnedheaderListview.setSelection(rightSection);
            }
        });

        leftListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isScroll = false;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isScroll = false;
            }
        });

        pinnedheaderListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(isScroll){
                    int leftSection = rightAdapter.getSectionForPosition(firstVisibleItem);
                    if(dishModel.getLeftPositionSelected() != leftSection){
                        setLeft(leftSection);
                    }
                    isScroll = false;
                }else{
                    isScroll = true;
                }
            }
        });

    }

    private void setLeft(int position){
        dishModel.setLeftPositionSelected(position);
        leftAdapter.notifyDataSetChanged();
        if(position <= leftListview.getFirstVisiblePosition() || position >= leftListview.getLastVisiblePosition()){
//            leftListview.setSelection(position);
            leftListview.smoothScrollToPosition(position);
        }
    }

}
