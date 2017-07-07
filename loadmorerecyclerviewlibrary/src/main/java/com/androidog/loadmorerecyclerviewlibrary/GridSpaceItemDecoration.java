package com.androidog.loadmorerecyclerviewlibrary;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private LoadMoreRecyclerView mRecyclerView;
    private int spanCount;
    private int space;
    private boolean includeEdge;

    /**
     * @param mRecyclerView {@link LoadMoreRecyclerView}
     * @param space 分割线宽度
     * @param includeEdge 屏幕所有两边是否需要画分割线
     */
    public GridSpaceItemDecoration(LoadMoreRecyclerView mRecyclerView, int space, boolean includeEdge) {
        this.mRecyclerView = mRecyclerView;
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            this.spanCount = gridLayoutManager.getSpanCount();
        }
        this.space = space;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (mRecyclerView.isHeader(position) || mRecyclerView.isFooter(position) || mRecyclerView.isLoadMore(position)) {
            return;
        }
        int column = (position - mRecyclerView.getHeadersCount()) % spanCount;
        if (includeEdge) {
            outRect.left = space - column * space / spanCount;
            outRect.right = (column + 1) * space / spanCount;
            outRect.bottom = space;
        } else {
            outRect.left = column * space / spanCount;
            outRect.right = space - (column + 1) * space / spanCount;
            outRect.bottom = space;
        }
    }
}