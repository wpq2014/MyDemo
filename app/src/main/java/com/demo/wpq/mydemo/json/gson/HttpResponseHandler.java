package com.demo.wpq.mydemo.json.gson;

import com.google.gson.Gson;

/**
 * @author wpq
 * @version 1.0
 */
public abstract class HttpResponseHandler<T> implements HttpResponseInterface{

    private Class<T> cls;

    public HttpResponseHandler(Class<T> cls) {
        this.cls = cls;
    }

    public HttpResponseHandler() {

    }

    @Override
    public void onSuccess(String json) {
        T t = new Gson().fromJson(json, cls);
//        ResponseBean<List<T>> responseBean = ResponseBean.fromJSONArray(json, cls);
//        List<T> list = responseBean.data;
        onSuccess(t);
    }

    public abstract void onSuccess(T t);
}
