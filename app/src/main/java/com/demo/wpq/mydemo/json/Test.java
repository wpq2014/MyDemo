package com.demo.wpq.mydemo.json;

import com.alibaba.fastjson.JSON;
import com.demo.wpq.mydemo.retrofit.GanHuo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 武普泉
 * @version 1.0.0
 */

public class Test {

    static String json = "{\"error\":false,\"results\":[{\"type\":\"福利\",\"who\":\"代码家\"}]}";
    static String json2 = "{error: false, results:[{type:福利, who:武普泉}]}";

    public static void main(String[] args) {
        /*************************/
        String jsonData = "{\"code\":0,\"msg\":\"成功\",\"data\":[{\"key\":\"A\"}]}";
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            Object object = jsonObject.opt("data");
            System.out.println(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GanHuo ganHuo = JSON.parseObject(json, GanHuo.class);
        System.err.println(ganHuo.getResults().get(0).getType());

//        GanHuo ganHuo2 = JSONUtil.toBean(json, GanHuo.class);
//        System.err.println(ganHuo.getResults().get(0).getWho());

        GanHuo ganHuo3 = new GanHuo();
        ganHuo3.setError(false);
        GanHuo.Result result = new GanHuo.Result();
        result.setType("干货");
        result.setWho("武普泉");
        List<GanHuo.Result> list = new ArrayList<>();
        list.add(result);
        ganHuo3.setResults(list);
        String json3 = JSON.toJSONString(ganHuo3);
        System.err.println(json3);
//        GanHuo ganHuo4 = JSONUtil.toBean(json3, GanHuo.class);
//        System.err.println(ganHuo4.getResults().get(0).getWho());

        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(json);
            System.out.println(jsonObject);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
    }
}
