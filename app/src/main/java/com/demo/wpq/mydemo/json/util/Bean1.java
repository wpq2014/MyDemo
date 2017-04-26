package com.demo.wpq.mydemo.json.util;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class Bean1 implements Serializable{
    private static final long serialVersionUID = 6278211053270659535L;

//    "{\"users\":{\"user107\":\"小明\", \"user834\":\"小亮\", \"user2384\":\"小红\"}, \"ids\":[1, 2, 3, 4, 5], \"objectBean\":{\"msg\":\"hello\", \"objectChild\":{\"name\":\"雷布斯\"}, \"list\":[{\"name\":\"雷布斯\"}, {\"name\":\"马斯克\"}]}, \"error\": false, \"results\":[{\"listChild\":[{\"age\":666}, {\"age\":999}], \"type\":\"福利\", \"who\":\"代码家\", \"resultChild\":{\"child\":\"呵呵哒\"}}]}";

    public JSONObject users;
    public List<Integer> ids;
    public ObjectBean objectBean;
    public List<Result> results;
    public Boolean error;

    public static class ObjectBean{

        public String msg;
        public ObjectChild objectChild;
        public List<ObjectChild> list;

        public static class ObjectChild{
            public String name;
        }
    }

    public static class Result{

        public List<ListChild> listChild;
        public ResultChild resultChild;
        public String what; // 测试服务器没有返回的字段
        public String type;
        public String who;

        public static class ResultChild{
            public String child;
        }

        public static class ListChild{
            public int age;
        }
    }
}
