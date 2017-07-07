package com.androidog.loadmorerecyclerviewlibrary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public abstract class BaseSingleViewTypeAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<T> mList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BaseSingleViewTypeAdapter(List<T> list) {
        mList = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (0 == getLayoutId()) {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        View convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(convertView);
        onCreate(holder);
        setListeners(holder);
        return holder;
    }

    private void setListeners(final RecyclerViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(holder);
                }
                return true;
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        onBind(holder, position, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *               item at the given position in the data set.
     */
    protected abstract void onCreate(RecyclerViewHolder viewHolder);

    /**
     * 设置事件监听和数据
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *               item at the given position in the data set.
     * @param position Position of the item whose data we want within the adapter's data set.
     * @param itemData 数据源
     */
    protected abstract void onBind(RecyclerViewHolder viewHolder, int position, T itemData);

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView.ViewHolder viewHolder);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }
}
