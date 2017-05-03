package com.demo.wpq.mydemo.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.demo.wpq.mydemo.R;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ItemViewHolder> {

    private Context mContext;
    private List<String> mList;

    public ChildRecyclerViewAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }

    public void update(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_child_recyclerview, null));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mButton.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        Button mButton;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mButton = (Button) itemView.findViewById(R.id.btn_item);
        }
    }
}
