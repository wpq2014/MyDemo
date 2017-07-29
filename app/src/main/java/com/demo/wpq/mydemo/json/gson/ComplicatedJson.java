package com.demo.wpq.mydemo.json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 复杂的json
 * @author wpq
 * @version 1.0
 */
public class ComplicatedJson {

    public static void main(String[] args) {
        Gson gson = new Gson();

        // 特殊json，同一个key不同类型value。查看Json格式：http://www.json.cn
        String jsonTest = "[{\"name\":\"服务人数\",\"reField\":\"personNumber\",\"type\":\"1\",\"value\":[\"1人\",\"2人\",\"3人\",\"4人\",\"5人\",\"6人\",\"7人\",\"8人\"],\"repara\":\"repara1\"},{\"name\":\"服务内容\",\"reField\":\"productCode\",\"type\":\"3\",\"value\":[{\"code\":\"101001001311551098324420\",\"name\":\"沙发除螨\"},{\"code\":\"101001001311212158719172\",\"name\":\"床具除螨\"}],\"repara\":\"repara5\"},{\"name\":\"服务数量\",\"reField\":\"quantity\",\"type\":\"2\",\"value\":{\"min\":\"1\",\"max\":\"100\"},\"repara\":\"repara6\"}]";

        // 第一种方案（普通）
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


        // 第二种方案（正规）http://www.jianshu.com/p/185e1ee9f05b

    }
}
