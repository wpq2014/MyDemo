package com.demo.wpq.mydemo.listview_and_recyclerview.linked_recyclerview;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;

import java.util.List;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/1/18 11:26
 */
public class LinkedRVLeftAdapter extends RecyclerView.Adapter<LinkedRVLeftAdapter.ViewHolder>{

    private List<String> data;
    private int selectedPosition = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_item;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    public LinkedRVLeftAdapter(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linked_recyclerview_left, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tv_item.setText(data.get(position));
        holder.tv_item.setTextColor(selectedPosition == position ? 0xff3dbcc6 : 0xff333333);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLeftItemClickListener != null) {
                    setSelectedPosition(position);
                    notifyDataSetChanged();
                    mOnLeftItemClickListener.onLeftItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public void notifySelectedPositionByHeader(String header) {
        if (data == null) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(header)) {
                this.selectedPosition = i;
                notifyDataSetChanged();
                break;
            }
        }
    }

    public String getSelectedData() {
        return data == null ? "" : data.get(this.selectedPosition);
    }

    public interface OnLeftItemClickListener{
        void onLeftItemClick(int position);
    }

    private OnLeftItemClickListener mOnLeftItemClickListener;

    public void setOnLeftItemClickListener(OnLeftItemClickListener onLeftItemClickListener) {
        this.mOnLeftItemClickListener = onLeftItemClickListener;
    }

}
