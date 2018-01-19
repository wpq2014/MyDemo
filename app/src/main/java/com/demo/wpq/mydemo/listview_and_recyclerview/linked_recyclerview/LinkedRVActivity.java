package com.demo.wpq.mydemo.listview_and_recyclerview.linked_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/1/18 11:24
 */
public class LinkedRVActivity extends BaseAppCompatActivity {

    @BindView(R.id.rv_left)
    RecyclerView mRvLeft;
    @BindView(R.id.rv_right)
    RecyclerView mRvRight;

    // intent data
    private String title;

    private GridLayoutManager mRightGridLayoutManager;
    private LinkedRVLeftAdapter mRVLeftAdapter;
    private LinkedRVRightAdapter mRVRightAdapter;
    private boolean needMove = false;
    private int movePosition;
    private boolean isChangeByLeftClick = false;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, LinkedRVActivity.class);
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
        return R.layout.activity_linked_recyclerview;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mRvLeft.setLayoutManager(new LinearLayoutManager(this));
        List<String> leftData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            leftData.add("第 " + i + " 类");
        }
        mRvLeft.setAdapter(mRVLeftAdapter = new LinkedRVLeftAdapter(leftData));
        mRVLeftAdapter.setOnLeftItemClickListener(mOnLeftItemClickListener);

        mRightGridLayoutManager = new GridLayoutManager(this, 3);
        mRvRight.setLayoutManager(mRightGridLayoutManager);
        List<LinkedRVRightBaseBean> rightData = new ArrayList<>();
        for (int i = 0; i < leftData.size(); i++) {
            LinkedRVRightHeaderBean header = new LinkedRVRightHeaderBean();
            header.type = LinkedRVRightBaseBean.TYPE_HEADER;
            header.header = leftData.get(i);
            rightData.add(header);

            for (int j = 0; j < 1 + new Random().nextInt(9); j++) {
                LinkedRVRightContentBean content = new LinkedRVRightContentBean();
                content.type = LinkedRVRightBaseBean.TYPE_CONTENT;
                content.headerForChild = leftData.get(i);
                content.content = leftData.get(i) + " " + j;
                rightData.add(content);
            }
        }
        mRvRight.setAdapter(mRVRightAdapter = new LinkedRVRightAdapter(rightData));
        mRvRight.addOnScrollListener(mOnRightScrollListener);
    }

    private RecyclerView.OnScrollListener mOnRightScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (needMove) {
                needMove = false;
                //获取要置顶的项在当前屏幕的位置，n是记录的要置顶项在RecyclerView中的位置
                int n = movePosition - mRightGridLayoutManager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRvRight.getChildCount()) {
                    //获取要置顶的项顶部离RecyclerView顶部的距离
                    int top = mRvRight.getChildAt(n).getTop();
                    //最后的移动
                    mRvRight.scrollBy(0, top);
                }
            }
            //当前屏幕内第一个item和最后一个item。
            if (!isChangeByLeftClick) {
//                    int firstVisibleItem = mRightGridLayoutManager.findFirstCompletelyVisibleItemPosition();
                int firstVisibleItem = mRightGridLayoutManager.findFirstVisibleItemPosition();
                String header = mRVRightAdapter.getHeaderForPosition(firstVisibleItem);
                mRVLeftAdapter.notifySelectedPositionByHeader(header);
                mRvLeft.scrollToPosition(mRVLeftAdapter.getSelectedPosition());
            } else {
                isChangeByLeftClick = false;
            }
        }
    };

    private LinkedRVLeftAdapter.OnLeftItemClickListener mOnLeftItemClickListener = new LinkedRVLeftAdapter.OnLeftItemClickListener() {
        @Override
        public void onLeftItemClick(int position) {
            String leftSelected = mRVLeftAdapter.getSelectedData();
            moveToPosition(movePosition = mRVRightAdapter.getPositionForHeader(leftSelected));
        }
    };

    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mRightGridLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mRightGridLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            mRvRight.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            View view = mRvRight.getChildAt(n - firstItem);
            mRvRight.scrollBy(0, view.getTop());
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            mRvRight.scrollToPosition(n);
            movePosition = n;
            needMove = true;
        }
    }

}
