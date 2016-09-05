package com.demo.wpq.mydemo.json.gson;

import com.demo.wpq.mydemo.json.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class TestGson {

    public static void main(String[] args) {
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
