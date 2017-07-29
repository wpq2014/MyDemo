package com.demo.wpq.mydemo.json.bean;

import java.io.Serializable;

/**
 * Created by lion on 2017/4/11.
 */

public class ResultBean implements Serializable{
//    private static final long serialVersionUID = 6278211053270659535L;

//    {"type":"福利", "who":"代码家"}

    public String type;
    public String who;

    @Override
    public String toString() {
        return "ResultBean{" +
                "type='" + type + '\'' +
                ", who='" + who + '\'' +
                '}';
    }
}
