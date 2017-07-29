package com.demo.wpq.mydemo.json.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * @author wpq
 * @version 1.0
 */
public abstract class FastHandler<T> {

    private Class<?> cls;

    public FastHandler(Class<?> cls) {
        this.cls = cls;
    }

    public void parse(String json) {
        @SuppressWarnings("unchecked")
        T t = (T) JSON.parseArray(json, cls);
        onSuccess(t);
    }

    public abstract void onSuccess(T t);
}
