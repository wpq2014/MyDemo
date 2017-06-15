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
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.demo.wpq.mydemo.animation.AnimationActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.customview.CustomViewActivity;
import com.demo.wpq.mydemo.customview.adapter.CustomAdapter;
import com.demo.wpq.mydemo.customview.bean.CustomBean;
import com.demo.wpq.mydemo.eventbus.EventBusFirstActivity;
import com.demo.wpq.mydemo.ipc.ClientActivity;
import com.demo.wpq.mydemo.listview_and_recyclerview.ListViewAndRecyclerViewActivity;
import com.demo.wpq.mydemo.qrcode.CaptureActivity;
import com.demo.wpq.mydemo.retrofit.RetrofitActivity;
import com.demo.wpq.mydemo.utils.MToastUtil;
import com.demo.wpq.mydemo.widget.MaxLengthEditText;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CustomAdapter.OnRecyclerListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private MaxLengthEditText mEditText;
    private RecyclerView mRecyclerView;

    @BindArray(R.array.mainArray)
    String[] array;

    private Class<?>[] mClasses = {AnimationActivity.class, CaptureActivity.class, CustomViewActivity.class, ListViewAndRecyclerViewActivity.class,
                                   RequestPermissionActivity.class, RetrofitActivity.class, ClientActivity.class, WebActivity.class, EventBusFirstActivity.class};
    private List<CustomBean> mList = new ArrayList<>();
    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                MToastUtil.show(MainActivity.this, "字数不能超过" + 20);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        for (int i = 0; i < array.length; i++) {
            mList.add(new CustomBean(array[i], mClasses[i]));
        }
        mCustomAdapter = new CustomAdapter(this, mList);
        mCustomAdapter.setOnRecyclerListener(this);
        mRecyclerView.setAdapter(mCustomAdapter);

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

    @Override
    public void onItemClicked(CustomBean customBean) {
        Intent intent = new Intent(this, customBean.intentClass);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, customBean.itemName);
        intent.putExtras(bundle);
        startActivity(intent);
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
