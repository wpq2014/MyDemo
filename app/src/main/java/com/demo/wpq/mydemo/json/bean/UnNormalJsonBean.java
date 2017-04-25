package com.demo.wpq.mydemo.json.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * Desc: 下划线、中文、数字key
 * Author: wpq
 * Date: 2017/4/20 14:25
 */
public class UnNormalJsonBean implements Serializable{

//    {"_code":0, "消息":"请求失败", "0":"干货福利", "users":{"user107":"小明", "user834":"小亮", "user2384":"小红"}}

    @SerializedName("_code") // 下划线
    private Integer code;
    @SerializedName("消息") // 中文
    public String msg;
    @SerializedName("0") // 数字
    public String data;
    public Map<String, String> users; // 动态key

    @Override
    public String toString() {
        return "UnNormalJsonBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", users=" + users +
                '}';
    }
}
