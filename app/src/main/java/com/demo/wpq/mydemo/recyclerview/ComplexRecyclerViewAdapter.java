package com.demo.wpq.mydemo.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wpq.mydemo.R;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<ComplexRecyclerViewAdapter.ComplexViewHolder> {

    private Context mContext;
    private List<List<String>> mList;

    public ComplexRecyclerViewAdapter(Context context, List<List<String>> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ComplexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_complex_recyclerview, null);
        return new ComplexViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ComplexViewHolder holder, int position) {
        holder.childAdapter = new ChildRecyclerViewAdapter(mContext, mList.get(position));
        holder.horizontalRecyclerView.setAdapter(holder.childAdapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ComplexViewHolder extends RecyclerView.ViewHolder {

        HorizontalRecyclerView horizontalRecyclerView;
        ChildRecyclerViewAdapter childAdapter;

        public ComplexViewHolder(View itemView) {
            super(itemView);

            horizontalRecyclerView = (HorizontalRecyclerView) itemView.findViewById(R.id.horizontalRecyclerView);

        }

    }
}
