package com.demo.wpq.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.demo.wpq.mydemo.eventbus.FirstActivity;
import com.demo.wpq.mydemo.ipc.ClientActivity;
import com.demo.wpq.mydemo.qrcode.CaptureActivity;
import com.demo.wpq.mydemo.recyclerview.ComplexRecyclerViewActivity;
import com.demo.wpq.mydemo.retrofit.RetrofitActivity;
import com.demo.wpq.mydemo.todolist.TodoActivity;
import com.demo.wpq.mydemo.utils.MToastUtil;
import com.demo.wpq.mydemo.view.MaxLengthEditText;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private MaxLengthEditText mEditText;

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

        mEditText = (MaxLengthEditText) findViewById(R.id.et_maxLength);
        mEditText.addOnTextLengthWatcher(new MaxLengthEditText.OnTextLengthWatcher() {
            @Override
            public void onOverMaxLength() {
                MToastUtil.show("字数不能超过" + 20);
            }
        });

        final Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "呵呵哒");
                    }
                }, 80);
            }
        }, 70);

        MHandler mHandler2 = new MHandler(this);
        mHandler2.sendMessage(mHandler2.obtainMessage(0));

        mHandler3.sendMessage(mHandler3.obtainMessage(0));

    }

    private static class MHandler extends Handler{
        WeakReference<MainActivity> mWeakReference;

        public MHandler(MainActivity mainActivity) {
            mWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null != mWeakReference) {
//                Log.e(TAG, "hello");
                sendMessageDelayed(obtainMessage(0), 1000);
            }
        }
    }

    private Handler mHandler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            Log.e(TAG, "mHandler3");
            sendMessageDelayed(obtainMessage(0), 1000);
        }
    };

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
     * 牙
     * @param view
     */
    public void clickTeeth(View view){
        startActivity(new Intent(this, TeethActivity.class));
    }

    /**
     * AIDL
     * @param view
     */
    public void clickAIDL(View view){
        startActivity(new Intent(this, ClientActivity.class));
    }

    public void clickWebView(View view) {
        startActivity(new Intent(this, WebActivity.class));
    }

    /**
     * EventBus
     * @param view
     */
    public void clickEventBus(View view) {
        startActivity(new Intent(this, FirstActivity.class));
    }

    /**
     * RecyclerView
     * @param view
     */
    public void clickRecyclerView(View view) {
        startActivity(new Intent(this, ComplexRecyclerViewActivity.class));
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
