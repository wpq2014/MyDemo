package com.demo.wpq.mydemo.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Mzg0NDMsInR5cGUiOiJ1c2VyIiwiaWF0IjoxNDc0MzM2MTM5LCJleHAiOjE1MDU4OTM3Mzl9.UP8rCjkOpiYArvyAK_LFAaTJJ58Op6y3-v8TCJFIQRI";

    @GET("version/current/android")
    Call<VersionBean> getVersionInfo();

    @GET("api/v1/hot/department-types?withName=1")
    Call<List<SecondDeptEntity>> getAllDeptsType2();

    @GET("api/v1/who-am-i")
    Call<Object> getUserInfo(@Header("x-user-token") String token);

    @FormUrlEncoded
    @POST("api/v1/user/login")
    Call<TokenEntity> login(@Field("phone") String phone, @Field("pvc") String pvc);

    @GET("api/data/{type}/{count}/{page}")
    Call<GanHuo> getGanHuo(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
