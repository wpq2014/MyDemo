package com.demo.wpq.mydemo.listview_and_recyclerview.timeline;

import android.widget.TextView;

import com.androidog.loadmorerecyclerviewlibrary.BaseSingleViewTypeAdapter;
import com.androidog.loadmorerecyclerviewlibrary.RecyclerViewHolder;
import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.customview.widget.FoldingView;
import com.demo.wpq.mydemo.customview.widget.TimeLineView;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class TimeLineAdapter extends BaseSingleViewTypeAdapter<TimeLineBean> {

    public TimeLineAdapter(List<TimeLineBean> list) {
        super(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_time_line;
    }

    @Override
    protected void onCreate(RecyclerViewHolder viewHolder) {

    }

    @Override
    protected void onBind(RecyclerViewHolder viewHolder, int position, TimeLineBean itemData) {
        TimeLineView timeLineView = viewHolder.getView(R.id.timeLineView);
        if (position == 0) {
            timeLineView.setType(TimeLineView.Type.START);
        } else if (position == getItemCount() - 1) {
            timeLineView.setType(TimeLineView.Type.END);
        } else {
            timeLineView.setType(TimeLineView.Type.CENTER);
        }

        TextView textView = viewHolder.getView(R.id.timeLineText);
        textView.setText("item " + position);

        FoldingView foldingView = viewHolder.getView(R.id.foldingView);
        foldingView.setFolding(position % 2 == 0);
    }
}
