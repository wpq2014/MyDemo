package com.demo.wpq.mydemo.json.bean;

import java.io.Serializable;

/**
 * Gson泛型解析-对象
 * Created by lion on 2017/4/10.
 */

public class ResponseBean<T> implements Serializable {
    private static final long serialVersionUID = -3212832294172578804L;

//    {"code":1, "msg":"请求成功", "data":[{"type":"a", "who":"b"}]}
//    {"code":1, "msg":"请求成功", "data":{"type":"a", "who":"b"}}
//    {"code":1, "msg":"请求成功", "data":""}

    public Integer code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

//    private void testGson() {
//        String jsonArray  = "{\"code\":1, \"msg\":\"请求成功\", \"data\":[{\"type\":\"a\", \"who\":\"b\"}]}";
//        String jsonObject = "{\"code\":0, \"msg\":\"请求失败\", \"data\":{\"type\":\"a\", \"who\":\"b\"}}";
//        String jsonString = "{\"code\":0, \"msg\":\"请求失败\", \"data\":\"abc\"}";
//        // Gson自带泛型解析
//        Gson gson = new Gson();
//        // 数组
//        ResponseBean<List<ResultBean>> arrayBean = gson.fromJson(jsonArray, new TypeToken<ResponseBean<List<ResultBean>>>() {}.getType());
//        List<ResultBean> list = arrayBean.data;
//        Log.e(TAG, arrayBean.code + ", " + arrayBean.msg + ", " + list.get(0).type + ", " + list.get(0).who);
//        // 对象
//        ResponseBean<ResultBean> objectBean = gson.fromJson(jsonObject, new TypeToken<ResponseBean<ResultBean>>() {}.getType());
//        ResultBean object = objectBean.data;
//        Log.e(TAG, objectBean.code + ", " + objectBean.msg + ", " + object.type + ", " + object.who);
//        // String
//        ResponseBean<String> stringBean = gson.fromJson(jsonString, new TypeToken<ResponseBean<String>>() {}.getType());
//        Log.e(TAG, stringBean.code + ", " + stringBean.msg + ", " + stringBean.data);
//    }

}
