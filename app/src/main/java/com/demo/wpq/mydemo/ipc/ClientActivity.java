package com.demo.wpq.mydemo.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.demo.wpq.mydemo.IRemoteService;
import com.demo.wpq.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * http://flrito.cc/notes-androidzhong-aidlde-ji-ben-yong-fa/
 */
public class ClientActivity extends AppCompatActivity {

    @BindView(R.id.tv_pid)
    TextView tvPid;
    @BindView(R.id.tv_service_pid)
    TextView tvServicePid;
    @BindView(R.id.tv_say_hello)
    TextView tvSayHello;

    private IRemoteService remoteService = null;
    private boolean bound = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        ButterKnife.bind(this);

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
