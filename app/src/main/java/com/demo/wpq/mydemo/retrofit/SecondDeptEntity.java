package com.demo.wpq.mydemo.retrofit;

import java.io.Serializable;

public class SecondDeptEntity implements Serializable {
    public int id;
    public String lv1;
    public String lv2;

    @Override
    public String toString() {
        return "SecondDeptEntity{" +
                "id=" + id +
                ", lv1='" + lv1 + '\'' +
                ", lv2='" + lv2 + '\'' +
                '}';
    }
}

