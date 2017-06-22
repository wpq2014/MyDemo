package com.demo.wpq.mydemo.listview_and_recyclerview.test_base_recycler_adapter;

import android.view.View;
import android.view.ViewGroup;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseRecyclerAdapter;
import com.demo.wpq.mydemo.retrofit.GanHuo;
import com.demo.wpq.mydemo.utils.MScreenUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class TestMyRecyclerViewAdapter extends BaseRecyclerAdapter<GanHuo.Result>{

    public TestMyRecyclerViewAdapter(List<GanHuo.Result> list) {
        super(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_myrecyclerview;
    }

    @Override
    protected void init(View convertView) {
        ViewGroup.LayoutParams lp = convertView.getLayoutParams();
        lp.height = MScreenUtil.getScreenWidth(convertView.getContext()) / 2;
//        lp.height = (int) (MScreenUtil.getScreenWidth(convertView.getContext()) / 2 * (Math.random() + 0.5));
        convertView.setLayoutParams(lp);
    }

    @Override
    protected void onBind(RecyclerViewHolder viewHolder, int position, GanHuo.Result itemData) {
        SimpleDraweeView ivAvatar = viewHolder.getView(R.id.iv_avatar);
        ivAvatar.setImageURI(itemData.getUrl());
    }
}
