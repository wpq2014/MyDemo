package com.demo.wpq.mydemo.retrofit;

import java.io.Serializable;

public class VersionBean implements Serializable {

//    {
//        "min": "2.1.0",
//            "current": "2.2.1",
//            "currentVersionInfo": "",
//            "server": 30,
//            "dbUpdateTime": {
//                "department": 1459473583753,
//                "hospital": 1459364405214
//            }
//    }

    private String min;
    public String current;
    public String currentVersionInfo;
    public int server;
    public DbUpdateTime dbUpdateTime = new DbUpdateTime();

    /**
     * 是否需要更新所有医院和所有科室缓存
     */
    public static class DbUpdateTime implements Serializable {
        public long department; // 医院下面的科室更新
        public long hospital; // 医院

        @Override
        public String toString() {
            return "DbUpdateTime{" +
                    "department=" + department +
                    ", hospital=" + hospital +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "min='" + min + '\'' +
                ", current='" + current + '\'' +
                ", currentVersionInfo='" + currentVersionInfo + '\'' +
                ", server=" + server +
                ", dbUpdateTime=" + dbUpdateTime +
                '}';
    }
}
