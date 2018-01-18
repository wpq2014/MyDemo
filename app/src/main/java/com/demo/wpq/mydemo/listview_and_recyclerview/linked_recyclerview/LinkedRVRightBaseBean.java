package com.demo.wpq.mydemo.listview_and_recyclerview.linked_recyclerview;

import java.io.Serializable;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/1/18 14:25
 */
public class LinkedRVRightBaseBean implements Serializable{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CONTENT = 1;

    public int type;
}
