package com.demo.wpq.mydemo.widget.recyclerview;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author wpq
 * @version 1.0
 */
public class RecyclerHeaderAndFooterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    protected static class HeaderAndFooterViewHolder extends RecyclerView.ViewHolder{

        public HeaderAndFooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public RecyclerHeaderAndFooterWrapper(RecyclerView.Adapter innerAdapter) {
        this.mInnerAdapter = innerAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return new HeaderAndFooterViewHolder(mHeaderViews.get(viewType));
        }
        if (mFooterViews.get(viewType) != null) {
            return new HeaderAndFooterViewHolder(mFooterViews.get(viewType));
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position) | isFooter(position)) {
            return;
        }
        //noinspection unchecked
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getInnerItemCount() + getFootersCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return mHeaderViews.keyAt(position);
        }
        if (isFooter(position)) {
            return mFooterViews.keyAt(position - getHeadersCount() - getInnerItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isHeader(position) || isFooter(position) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        int position = holder.getLayoutPosition();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams && (isHeader(position) || isFooter(position))) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
        //noinspection unchecked
        mInnerAdapter.onViewAttachedToWindow(holder);
    }

    private int getInnerItemCount() {
        return mInnerAdapter.getItemCount();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(BASE_ITEM_TYPE_HEADER + mHeaderViews.size(), view);
    }

    public void addFooterView(View view) {
        mFooterViews.put(BASE_ITEM_TYPE_FOOTER + mFooterViews.size(), view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    private boolean isHeader(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooter(int position) {
        return position >= getHeadersCount() + getInnerItemCount();
    }
}
