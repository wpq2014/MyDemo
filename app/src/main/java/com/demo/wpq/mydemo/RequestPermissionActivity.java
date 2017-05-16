package com.demo.wpq.mydemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc:
 * Created by wpq on 16/7/28.
 */
public class RequestPermissionActivity extends BaseAppCompatActivity {

    private static final int REQUEST_CODE_ONE = 23;
    private static final int REQUEST_CODE_SEVERAL = 24;

    @BindView(R.id.btn_one_permission)
    Button btnOnePermission;
    @BindView(R.id.btn_several_permission)
    Button btnSeveralPermission;

    // intent data
    private String title;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, RequestPermissionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getBundleExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_request_permission;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_one_permission, R.id.btn_several_permission})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one_permission: //一个权限
                MPermissions.requestPermissions(RequestPermissionActivity.this, REQUEST_CODE_ONE, Manifest.permission.READ_EXTERNAL_STORAGE);
                break;
            case R.id.btn_several_permission: //多个权限
                MPermissions.requestPermissions(RequestPermissionActivity.this, REQUEST_CODE_SEVERAL, Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUEST_CODE_ONE)
    public void requestSDCardSuccess(){
        Toast.makeText(this, "授权一个成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUEST_CODE_ONE)
    public void requestSDCardFailed(){
        Toast.makeText(this, "授权一个失败", Toast.LENGTH_SHORT).show();
    }

    @PermissionGrant(REQUEST_CODE_SEVERAL)
    public void requestSeveralSuccess(){
        Toast.makeText(this, "授权多个成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUEST_CODE_SEVERAL)
    public void requestSeveralFailed(){
        Toast.makeText(this, "授权多个失败", Toast.LENGTH_SHORT).show();
    }

}
