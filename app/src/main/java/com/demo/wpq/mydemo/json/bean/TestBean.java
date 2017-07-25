package com.demo.wpq.mydemo.json.bean;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 特殊json，同一个key不同类型value
 * @author wpq
 * @version 1.0
 */
public class TestBean{

    /**
     * name : 服务人数
     * reField : personNumber
     * type : 1
     * repara : repara1
     * value : ["1人","2人","3人","4人","5人","6人","7人","8人"]
     * value : [
                     {
                         "code":"101001001311551098324420",
                         "name":"沙发除螨"
                     },
                     {
                         "code":"101001001311212158719172",
                         "name":"床具除螨"
                     }
                ]
     * value : {
                     "min":"1",
                     "max":"100"
               }
     */

    private String name;
    public String reField;
    public String type;
    public String repara;
    public Object value;

    public List<String> stringList;
    public List<ValueListBean> beanList;
    public ValueObjectBean objectBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.err.println(name);
        this.name = name;
    }

    /** 根据type解析value */
    public void parseValue(Gson gson) {
        String valueString = String.valueOf(value);
        try {
            switch (type) {
                case "1":
                    stringList = gson.fromJson(valueString, new TypeToken<List<String>>() {}.getType());
                    break;
                case "2":
                    objectBean = gson.fromJson(valueString, ValueObjectBean.class);
                    break;
                case "3":
                    beanList = gson.fromJson(valueString, new TypeToken<List<ValueListBean>>() {}.getType());
                    break;
            }
        } catch (JsonSyntaxException e) {}
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", reField='" + reField + '\'' +
                ", type='" + type + '\'' +
                ", repara='" + repara + '\'' +
                ", value=" + value +
                '}';
    }

    public static class ValueListBean{
        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "ValueListBean{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ValueObjectBean{
        private String min;
        private String max;

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        @Override
        public String toString() {
            return "ValueObjectBean{" +
                    "min='" + min + '\'' +
                    ", max='" + max + '\'' +
                    '}';
        }
    }
}
