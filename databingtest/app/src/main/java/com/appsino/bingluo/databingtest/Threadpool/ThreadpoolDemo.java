package com.appsino.bingluo.databingtest.Threadpool;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Answer on 2018/7/3.
 */

public class ThreadpoolDemo implements Runnable{
    ExecutorService executor;
    public ThreadpoolDemo(){
        executor = Executors.newFixedThreadPool(3);
    }
    @Override
    public void run() {
        executor.execute(new Runabledemo());
    }
}
