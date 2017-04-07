package com.demo.wpq.mydemo.customview.bean;

/**
 * Created by lion on 2017/4/7.
 */

public class CustomBean {

    public String itemName;
    public Class<?> intentClass;

    public CustomBean(String itemName, Class<?> intentClass) {
        this.itemName = itemName;
        this.intentClass = intentClass;
    }
}
