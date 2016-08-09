package com.demo.wpq.mydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.bean.DishType;
import com.demo.wpq.mydemo.model.DishModel;

/**
 * Desc:
 * Created by wpq on 16/8/9.
 */
public class DishLeftAdapter extends BaseAdapter{

    private Context context;
    private DishModel dishModel;

    public DishLeftAdapter(Context context, DishModel dishModel){
        this.context = context;
        this.dishModel = dishModel;
    }

    @Override
    public int getCount() {
        return dishModel.getLeftList().size();
    }

    @Override
    public Object getItem(int position) {
        return dishModel.getLeftList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.dish_left_list_item, null);
            holder = new ViewHolder();
            holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        DishType dishType = dishModel.getLeftList().get(position);
        holder.tvLeft.setText(dishType.getTypeName());

        if(dishModel.getLeftPositionSelected() == position){
            holder.tvLeft.setBackgroundColor(Color.WHITE);
        }else{
            holder.tvLeft.setBackgroundColor(0xffeeeeee);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView tvLeft;
    }
}
