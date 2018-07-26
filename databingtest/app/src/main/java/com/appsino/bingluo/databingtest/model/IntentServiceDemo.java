package com.appsino.bingluo.databingtest.model;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Answer on 2018/6/8.
 */

public class IntentServiceDemo extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceDemo() {
        super("intentservice");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(true){
            Log.i("tag","============serviceIntnet start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("tag","============serviceIntnet end");
        }

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
