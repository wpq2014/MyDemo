package com.demo.wpq.mydemo.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;

import java.util.List;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseAppCompatActivity {

    public static final String TAG = "RetrofitActivity";

    String[] hostUrls = {"https://dev.healskare.com"};

    @BindView(R.id.result)
    TextView result;

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getIntentExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_retrofit;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        gankGirl();
        getVersionInfo();
        getAllDeptsType2();
        login();
        getUserInfo();
    }

    private void gankGirl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<GanHuo> call = service.getGanHuo("福利", 10, 1);
        call.enqueue(new Callback<GanHuo>() {
            @Override
            public void onResponse(Call<GanHuo> call, retrofit2.Response<GanHuo> response) {
                Log.e(TAG, response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    Log.e(TAG, response.body()+"");
                    GanHuo ganhuo = response.body();
                    Log.e(TAG, ganhuo.getResults().get(0).getUrl());
                }
            }

            @Override
            public void onFailure(Call<GanHuo> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getVersionInfo() {
        RetrofitService service = RetrofitManager.getApiRetrofit().create(RetrofitService.class);
        Call<VersionBean> call = service.getVersionInfo();
        call.enqueue(new Callback<VersionBean>() {
            @Override
            public void onResponse(Call<VersionBean> call, retrofit2.Response<VersionBean> response) {
                Log.e(TAG, "version -- " + response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    VersionBean version = response.body();
                    result.setText(result.getText().toString() + "\n\n" + response.body());
                }
            }

            @Override
            public void onFailure(Call<VersionBean> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getAllDeptsType2() {
        RetrofitService service = RetrofitManager.getApiRetrofit().create(RetrofitService.class);
        Call<List<SecondDeptEntity>> call = service.getAllDeptsType2();
        call.enqueue(new Callback<List<SecondDeptEntity>>() {
            @Override
            public void onResponse(Call<List<SecondDeptEntity>> call, Response<List<SecondDeptEntity>> response) {
                Log.e(TAG, "depts -- " + response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    result.setText(result.getText().toString() + "\n\n" + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SecondDeptEntity>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void login() {
        RetrofitService service = RetrofitManager.getAuthRetrofit().create(RetrofitService.class);
        Call<TokenEntity> call = service.login("18234096324", "3096");
        call.enqueue(new Callback<TokenEntity>() {
            @Override
            public void onResponse(Call<TokenEntity> call, Response<TokenEntity> response) {
                Log.e(TAG, "login -- " + response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    Log.e(TAG, "login -- " + response.body() + "");
                    result.setText(result.getText().toString() + "\n\n" + response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenEntity> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getUserInfo() {
        RetrofitService service = RetrofitManager.getApiRetrofit().create(RetrofitService.class);
        Call<Object> call = service.getUserInfo(RetrofitService.TOKEN);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.e(TAG, "userInfo -- " + response.code() + ", " + response.isSuccessful() + ", " + response.message());
                if (response.isSuccessful()) {
                    Log.e(TAG, "userInfo -- " + response.body() + "");
                    result.setText(result.getText().toString() + "\n\n" + response.body());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
