package com.demo.wpq.mydemo.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("version/current/android")
    Call<VersionBean> getVersionInfo();

    @GET("api/v1/hot/department-types?withName=1")
    Call<List<SecondDeptEntity>> getAllDeptsType2();

    @GET("api/data/{type}/{count}/{page}")
    Call<GanHuo> getGanHuo(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
