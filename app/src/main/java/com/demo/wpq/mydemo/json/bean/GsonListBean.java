package com.demo.wpq.mydemo.json.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Gson泛型解析-数组
 * Created by lion on 2017/4/10.
 */

public class GsonListBean<T> implements Serializable {
    private static final long serialVersionUID = -3212832294172578804L;

//    {"code":1, "msg":"请求成功", "data":[{"type":"福利", "who":"代码家"}]}

    public Integer code;
    public String msg;
    public List<T> data;

    @Override
    public String toString() {
        return "GsonListBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /*********** Gson自带泛型解析 **********/
//    GsonListBean<ResponseResultBean> arrayBean = new Gson().fromJson(json, new TypeToken<GsonListBean<ResponseResultBean>>() {}.getType());

}
