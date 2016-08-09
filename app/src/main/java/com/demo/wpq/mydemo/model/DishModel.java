package com.demo.wpq.mydemo.model;

import com.demo.wpq.mydemo.bean.Dish;
import com.demo.wpq.mydemo.bean.DishItem;
import com.demo.wpq.mydemo.bean.DishType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Desc:
 * Created by wpq on 16/8/9.
 */
public class DishModel {

    private List<DishType> leftList = new ArrayList<>();
    /**
     * 当前选中项
     */
    private int leftPositionSelected = 0;

    private List<Dish> rightList = new ArrayList<>();

    public void addLeft(String typeName){
        leftList.add(new DishType(typeName));
    }

    public List<DishType> getLeftList() {
        return Collections.unmodifiableList(leftList);
    }

    public void setLeftList(List<DishType> leftList) {
        this.leftList = leftList;
    }

    public int getLeftPositionSelected() {
        return leftPositionSelected;
    }

    public void setLeftPositionSelected(int leftPositionSelected) {
        this.leftPositionSelected = leftPositionSelected;
    }

    public void addRight(String dishType, List<DishItem> dishList){
        rightList.add(new Dish(dishType, dishList));
    }

    public List<Dish> getRightList() {
        return rightList;
    }

    public void setRightList(List<Dish> rightList) {
        this.rightList = rightList;
    }
}
