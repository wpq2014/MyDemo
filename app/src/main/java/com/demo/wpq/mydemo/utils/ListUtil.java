package com.demo.wpq.mydemo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * List工具类
 * @author wpq
 * @version 1.0
 */
public class ListUtil {

    /**
     * 过滤为null的元素
     *
     * @param list
     * @param <E>
     * @return
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    public static <E> List<E> filterNullElements(List<E> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        List<?> nullList = new ArrayList<>();
        nullList.add(null);
        list.removeAll(nullList);
        return list;
    }
}
