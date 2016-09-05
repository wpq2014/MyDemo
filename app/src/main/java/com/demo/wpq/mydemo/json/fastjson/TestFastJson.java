package com.demo.wpq.mydemo.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.wpq.mydemo.json.Person;

public class TestFastJson {

    public static void main(String[] args) {
        //
        String json = "{\"name\":\"fastjson\",\"age\":10}";
        Person p1 = JSON.parseObject(json, Person.class);
        System.out.println(p1.toString());

        //
        JSONObject o2 = new JSONObject();
        o2.put("name", "fastjson2");
        o2.put("age", 20);
        System.out.println(o2.toString());
        Person p2 = JSON.parseObject(o2.toString(), Person.class);
        System.out.println(p2.toString());

        //
        Person p3 = new Person();
        p3.setName("fastjson3");
        p3.setAge(30);
        String o3 = JSON.toJSONString(p3);
        System.out.println(o3);
    }
}
