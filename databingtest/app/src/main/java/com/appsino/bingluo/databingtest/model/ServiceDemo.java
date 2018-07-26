package com.appsino.bingluo.databingtest.model;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Answer on 2018/6/8.
 */

public class ServiceDemo extends Service{


    public class MyBinder extends Binder{
       public ServiceDemo getService(){
           return ServiceDemo.this;
       }
    }
    private MyBinder binder = new MyBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("tag","============service onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("tag","============service create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("tag","============service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("tag","============service onConfigurationChanged");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("tag","============service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag","============service onDestroy");
    }
}
