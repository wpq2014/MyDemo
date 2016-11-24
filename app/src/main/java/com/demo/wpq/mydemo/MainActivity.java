package com.demo.wpq.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.demo.wpq.mydemo.animation.AnimationActivity;
import com.demo.wpq.mydemo.bean.HotBean;
import com.demo.wpq.mydemo.customview.CustomViewActivity;
import com.demo.wpq.mydemo.qrcode.CaptureActivity;
import com.demo.wpq.mydemo.retrofit.RetrofitActivity;
import com.demo.wpq.mydemo.todolist.TodoActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String hotJson = "{\"msg\":\"获取成功！\",\"code\":1,\"data\":{\"list\":[{\"name\":\"三只松鼠\"}]}}";
        HotBean hotBean = JSON.parseObject(hotJson, HotBean.class);
        Log.e("fastjson解析", hotBean.toString());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ((TextView)findViewById(R.id.text)).setText("呵呵呵");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(96);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                ((TextView)findViewById(R.id.text)).setText("哈哈哈");
            }
        }).start();

    }

    /**
     * 动画
     * @param view
     */
    public void clickAnimation(View view){
        startActivity(new Intent(this, AnimationActivity.class));
    }

    /**
     * 扫码
     * @param view
     */
    public void clickQRCode(View view){
        startActivity(new Intent(this, CaptureActivity.class));
    }

    /**
     * 自定义View
     * @param view
     */
    public void clickCustomView(View view){
        startActivity(new Intent(this, CustomViewActivity.class));
    }

    /**
     * 6.0申请权限
     * @param view
     */
    public void clickRequestPermission(View view){
        startActivity(new Intent(this, RequestPermissionActivity.class));
    }

    /**
     * interview
     * @param view
     */
    public void clickInterview(View view){
        startActivity(new Intent(this, TodoActivity.class));
    }

    /**
     * Retrofit
     * @param view
     */
    public void clickRetrofit(View view){
        startActivity(new Intent(this, RetrofitActivity.class));
    }

    /**
     * AIDL
     * @param view
     */
    public void clickAIDL(View view){
        startActivity(new Intent(this, ClientActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
