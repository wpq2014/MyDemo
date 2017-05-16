package com.demo.wpq.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.bean.Dish;
import com.demo.wpq.mydemo.bean.DishItem;
import com.demo.wpq.mydemo.customview.widget.SectionedBaseAdapter;
import com.demo.wpq.mydemo.model.DishModel;

/**
 * Desc:
 * Created by wpq on 16/8/9.
 */
public class DishRightAdapter extends SectionedBaseAdapter {

    private Context context;
    private DishModel dishModel;

    public DishRightAdapter(Context context, DishModel dishModel) {
        this.context = context;
        this.dishModel = dishModel;
    }

    @Override
    public DishItem getItem(int section, int position) {
        return dishModel.getRightList().get(section).getDishes().get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return dishModel.getRightList().size();
    }

    @Override
    public int getCountForSection(int section) {
        return dishModel.getRightList().get(section).getDishes().size();
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        SectionItem sectionItem;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dish_right_list_item, parent, false);

            sectionItem = new SectionItem();
            sectionItem.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(sectionItem);
        } else {
            sectionItem = (SectionItem) convertView.getTag();
        }

        sectionItem.tv_item.setText(getItem(section, position).getDishName());

        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        SectionHeaderItem sectionHeaderItem;
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.dish_right_list_header, parent, false);

            sectionHeaderItem = new SectionHeaderItem();
            sectionHeaderItem.tv_header = (TextView) convertView.findViewById(R.id.tv_header);
            convertView.setTag(sectionHeaderItem);
        }else{
            sectionHeaderItem = (SectionHeaderItem) convertView.getTag();
        }

        Dish dish = dishModel.getRightList().get(section);
        sectionHeaderItem.tv_header.setText(dish.getType());

        return convertView;
    }

    static class SectionHeaderItem {
        TextView tv_header;
    }

    static class SectionItem {
        TextView tv_item;
    }
}
