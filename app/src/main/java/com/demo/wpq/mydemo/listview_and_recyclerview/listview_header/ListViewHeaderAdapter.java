package com.demo.wpq.mydemo.listview_and_recyclerview.listview_header;

import android.view.View;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.MBaseAdapter;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class ListViewHeaderAdapter extends MBaseAdapter<String> {

    public ListViewHeaderAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_child_recyclerview;
    }

    @Override
    protected void init(View convertView) {

    }

    @Override
    protected void convert(ViewHolder viewHolder, int position, String itemData) {

    }
}
