package com.demo.wpq.mydemo.base;

import android.content.Context;
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

    protected Context mContext;
    private List<T> mDatas;

    public MBaseAdapter(Context context, List<T> datas) {
        mContext = context;
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
        QLKViewHolder viewHolder;
        if (convertView == null) {
            if (0 == getLayoutId()) {
                throw new IllegalArgumentException("You must return a right contentView layout resource Id");
            }
            convertView = LayoutInflater.from(mContext).inflate(getLayoutId(), parent, false);
            init(convertView);
//            QLKLog.e(this.getClass().getSimpleName() + " init()：" + position);
            viewHolder = new QLKViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (QLKViewHolder) convertView.getTag();
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
     * @param viewHolder
     * @param position
     * @param itemData   每项数据源
     */
    protected abstract void convert(QLKViewHolder viewHolder, int position, T itemData);

    public static class QLKViewHolder {

        private final View mConvertView;
        private SparseArray<View> mViews;

        QLKViewHolder(View convertView) {
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
