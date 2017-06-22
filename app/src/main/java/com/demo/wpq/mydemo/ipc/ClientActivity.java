package com.demo.wpq.mydemo.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.demo.wpq.mydemo.IRemoteService;
import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.eventbus.EventBusFirstActivity;

import butterknife.BindView;

/**
 * http://flrito.cc/notes-androidzhong-aidlde-ji-ben-yong-fa/
 */
public class ClientActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_pid)
    TextView tvPid;
    @BindView(R.id.tv_service_pid)
    TextView tvServicePid;
    @BindView(R.id.tv_say_hello)
    TextView tvSayHello;

    // intent data
    private String title;

    private IRemoteService remoteService = null;
    private boolean bound = false;

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, EventBusFirstActivity.class);
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
        return R.layout.activity_client;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        tvPid.setText("the client pid is " + Process.myPid());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(conn);
        bound = false;
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = IRemoteService.Stub.asInterface(service);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            remoteService = null;
            bound = false;
        }
    };

    public void showServicePid(View view) {
        if (bound) {
            try {
                Log.e("Hello", "the service pid is " + remoteService.sayHello().getPid());
                tvServicePid.setText("the service pid is " + remoteService.sayHello().getPid());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sayHello(View view) {
        if (bound) {
            try {
                Log.e("Hello", remoteService.sayHello().getMsg());
                tvSayHello.setText(remoteService.sayHello().getMsg());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
