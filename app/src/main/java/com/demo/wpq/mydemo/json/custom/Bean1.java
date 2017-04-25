package com.demo.wpq.mydemo.json.custom;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public class Bean1 {

    //    {"error": false, "results":[{"type":"福利", "who":"代码家", "resultChild":{"child":"呵呵哒"}}]}

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
