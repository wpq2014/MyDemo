package com.demo.wpq.mydemo.json.gson;

import com.demo.wpq.mydemo.json.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class TestGson {

    private static Gson gson = new Gson();

    public static void main(String[] args) throws InterruptedException {

        /*************************/
        String jsonData = "{\"code\":0,\"msg\":\"成功\",\"data\":[{\"key\":\"A\"}]}";

        Object object = gson.fromJson(jsonData, Object.class);
        System.out.println(object);

        // 特殊json，同一个key不同类型value
        String jsonTest = "[{\"name\":\"服务人数\",\"reField\":\"personNumber\",\"type\":\"1\",\"value\":[\"1人\",\"2人\",\"3人\",\"4人\",\"5人\",\"6人\",\"7人\",\"8人\"],\"repara\":\"repara1\"},{\"name\":\"服务内容\",\"reField\":\"productCode\",\"type\":\"3\",\"value\":[{\"code\":\"101001001311551098324420\",\"name\":\"沙发除螨\"},{\"code\":\"101001001311212158719172\",\"name\":\"床具除螨\"}],\"repara\":\"repara5\"},{\"name\":\"服务数量\",\"reField\":\"quantity\",\"type\":\"2\",\"value\":{\"min\":\"1\",\"max\":\"100\"},\"repara\":\"repara6\"}]";

        // 第一种方案
        List<ComplicatedBean> testList = gson.fromJson(jsonTest, new TypeToken<List<ComplicatedBean>>() {}.getType());
        System.out.println(testList);
        for (ComplicatedBean bean : testList) {
            bean.parseValue(gson);
        }
        // 测试解析结果
        for (ComplicatedBean bean : testList) {
            List<String> stringList = bean.stringList;
            if (stringList != null) {
                System.out.println(stringList);
            }
            List<ComplicatedBean.ValueListBean> beanList = bean.beanList;
            if (beanList != null) {
                System.out.println(beanList);
            }
            ComplicatedBean.ValueObjectBean objectBean = bean.objectBean;
            if (objectBean != null) {
                System.out.println(objectBean);
            }
        }

        Thread.sleep(200);
        System.err.println("----------------我是分割线---------------");

        //
        String json = "{name:Gson,age:25}";
        Gson g1 = new GsonBuilder().enableComplexMapKeySerialization().create();
        Person p1 = g1.fromJson(json, Person.class);
        System.out.println(p1.toString());

        //
        try {
            JsonObject o2 = new JsonObject();
            o2.addProperty("name", "Gson2");
            o2.addProperty("age", 35);
            System.out.println(o2.toString());
            Person p2 = g1.fromJson(o2.toString(), Person.class);
            System.out.println(p2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //
        Person p3 = new Person();
        p3.setName("Gson3");
        p3.setAge(45);
        Gson g3 = new Gson();
        String s3 = g3.toJson(p3);
        System.out.println(s3);

    }


}
