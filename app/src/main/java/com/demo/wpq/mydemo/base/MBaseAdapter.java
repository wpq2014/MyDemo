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
public abstract class MBaseAdapter<T> extends BaseAdapter {

    private List<T> mDatas;

    public MBaseAdapter(List<T> datas) {
        mDatas = datas;
    }

    /**
     * 刷新
     * @param datas
     */
    public void update(List<T> datas) {
        if (datas == null) return;
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            if (0 == getLayoutId()) {
                throw new IllegalArgumentException("You must return a right contentView layout resource Id");
            }
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
            init(convertView);
//            QLKLog.e(this.getClass().getSimpleName() + " init()：" + position);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
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
     * @param viewHolder A ViewHolder describes an item view and metadata about its place within the ListView|GridView
     * @param position Position of the item whose data we want within the adapter's data set.
     * @param itemData 数据源
     */
    protected abstract void convert(ViewHolder viewHolder, int position, T itemData);

    protected static class ViewHolder {

        private final View mConvertView;
        private SparseArray<View> mViews;

        ViewHolder(View convertView) {
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
