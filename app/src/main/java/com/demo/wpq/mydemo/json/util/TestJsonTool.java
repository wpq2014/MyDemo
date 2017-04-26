package com.demo.wpq.mydemo.json.util;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wpq
 * @version 1.0
 */
public class TestJsonTool {

    static String json1 = "{\"users\":{\"user107\":\"小明\", \"user834\":\"小亮\", \"user2384\":\"小红\"}, \"ids\":[1, 2, 3, 4, 5], \"objectBean\":{\"msg\":\"hello\", \"objectChild\":{\"name\":\"雷布斯\"}, \"list\":[{\"name\":\"雷布斯\"}, {\"name\":\"马斯克\"}]}, \"error\": false, \"results\":[{\"listChild\":[{\"age\":666}, {\"age\":999}], \"type\":\"福利\", \"who\":\"代码家\", \"resultChild\":{\"child\":\"呵呵哒\"}}]}";
    static String json2 = "{error: false, results:[{type:福利, who:武普泉}]}";

    public static void main(String[] args) {
        Bean1 bean1 = JsonUtil.parseObject(json1, Bean1.class);

        // 动态key
        JSONObject userObj = bean1.users;
        Iterator<String> it = userObj.keys();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        List<Integer> ids = bean1.ids;
        for (Integer id : ids) {
            System.out.println(id);
        }
        System.out.println(bean1.error);
        Bean1.ObjectBean objectBean = bean1.objectBean;
        System.out.println(objectBean.msg + ", " + objectBean.objectChild.name);
        List<Bean1.ObjectBean.ObjectChild> list = objectBean.list;
        for (Bean1.ObjectBean.ObjectChild child : list) {
            System.out.println(child.name);
        }
        List<Bean1.Result> results1 = bean1.results;
        for (Bean1.Result result : results1) {
            List<Bean1.Result.ListChild> listChild = result.listChild;
            for (Bean1.Result.ListChild child : listChild) {
                System.out.println(child.age);
            }
            System.out.println(result.type + ", " + result.who + "，" + result.resultChild.child);
        }

        // 正则 打印所有key
        final String regex = "\"([^\\\" ]+?)\":";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(json1);
        for (; matcher.find(); ) {
            String groupName = matcher.group(1);
            System.out.println(groupName);
        }
    }
}
