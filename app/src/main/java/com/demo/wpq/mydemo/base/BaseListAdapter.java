package com.demo.wpq.mydemo.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 简单的ListView适配器（不含ItemViewType）
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    private List<T> mList;

    public BaseListAdapter(List<T> list) {
        mList = list;
    }

    /**
     * 刷新
     * @param list
     */
    public void update(List<T> list) {
        if (list == null) return;
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null? 0 : mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder viewHolder;
        if (convertView == null) {
            if (0 == getLayoutId()) {
                throw new IllegalArgumentException("You must return a right contentView layout resource Id");
            }
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
            init(convertView);
//            QLKLog.e(this.getClass().getSimpleName() + " init()：" + position);
            viewHolder = new ListViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListViewHolder) convertView.getTag();
        }

        try {
            convert(viewHolder, position, getItem(position));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    /**
     * convertView layoutId
     *
     * @return resource ID for an XML layout resource to load (e.g.,
     *         <code>R.layout.main_page</code>)
     */
    protected abstract int getLayoutId();

    /**
     * 初始化操作，比如设置 LayoutParams
     *
     * @param convertView 当前itemView
     */
    protected abstract void init(View convertView);

    /**
     * 设置事件监听和数据
     *
     * @param viewHolder A ListViewHolder describes an item view and metadata about its place within the ListView|GridView
     * @param position Position of the item whose data we want within the adapter's data set.
     * @param itemData 数据源
     */
    protected abstract void convert(ListViewHolder viewHolder, int position, T itemData);

    protected static class ListViewHolder {

        private final View mConvertView;
        private SparseArray<View> mViews;

        ListViewHolder(View convertView) {
            mConvertView = convertView;
            mViews = new SparseArray<>();
        }

        public View getConvertView() {
            return mConvertView;
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (null == view) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

    }
}
