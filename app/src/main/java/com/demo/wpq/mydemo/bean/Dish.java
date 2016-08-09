package com.demo.wpq.mydemo.bean;

import java.util.List;

public class Dish {

    private String type;
    private List<DishItem> dishes;

    public Dish(String type, List<DishItem> dishes) {
        this.type = type;
        this.dishes = dishes;
    }

    public String getType() {
        return type;
    }

    public List<DishItem> getDishes() {
        return dishes;
    }

}
