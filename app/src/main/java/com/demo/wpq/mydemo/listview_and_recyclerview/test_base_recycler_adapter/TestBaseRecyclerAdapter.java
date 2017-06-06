package com.demo.wpq.mydemo.listview_and_recyclerview.test_base_recycler_adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseRecyclerAdapter;
import com.demo.wpq.mydemo.utils.MScreenUtil;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class TestBaseRecyclerAdapter extends BaseRecyclerAdapter<String>{

    public TestBaseRecyclerAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_test_base_recycler_adapter;
    }

    @Override
    protected void init(View convertView) {
        ViewGroup.LayoutParams lp = convertView.getLayoutParams();
        lp.height = MScreenUtil.getScreenHeight(convertView.getContext().getApplicationContext()) / 5;
        convertView.setLayoutParams(lp);
    }

    @Override
    protected void onBind(RecyclerViewHolder viewHolder, int position, String itemData) {
        TextView textView = viewHolder.getView(R.id.btn_item);
        textView.setText(itemData);
    }
}
