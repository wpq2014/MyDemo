package com.demo.wpq.mydemo.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.demo.wpq.mydemo.HelloMsg;
import com.demo.wpq.mydemo.IRemoteService;

public class RemoteService extends Service {

    IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public HelloMsg sayHello() throws RemoteException {
            return new HelloMsg("msg from service at Thread " + Thread.currentThread().toString() + "\n" +
                    "tid is " + Thread.currentThread().getId() + "\n" +
                    "main thread id is " + getMainLooper().getThread().getId(), Process.myPid());
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
