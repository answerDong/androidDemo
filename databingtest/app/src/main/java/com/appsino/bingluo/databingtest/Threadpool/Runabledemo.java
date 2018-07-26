package com.appsino.bingluo.databingtest.Threadpool;

import android.util.Log;

/**
 * Created by Answer on 2018/7/3.
 */

public class Runabledemo implements Runnable {
    @Override
    public void run() {
        Log.i("tag","==================线程名称"+Thread.currentThread().getId());
    }
}
