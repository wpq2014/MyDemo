package com.demo.wpq.mydemo.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.customview.bean.CustomBean;

import java.util.List;

/**
 * RecyclerView item
 * Created by lion on 2017/4/7.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private Context mContext;
    private List<CustomBean> mList;

    private OnRecyclerListener mOnRecyclerListener;

    public CustomAdapter(Context context, List<CustomBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_customview_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CustomBean customBean = mList.get(position);
        holder.btnItem.setText(customBean.itemName);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Button btnItem;

        public ViewHolder(View itemView) {
            super(itemView);

            btnItem = (Button) itemView.findViewById(R.id.btn_item);

            btnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecyclerListener.onItemClicked(mList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnRecyclerListener{
        void onItemClicked(CustomBean customBean);
    }

    public void setOnRecyclerListener(OnRecyclerListener onRecyclerListener) {
        this.mOnRecyclerListener = onRecyclerListener;
    }
}
