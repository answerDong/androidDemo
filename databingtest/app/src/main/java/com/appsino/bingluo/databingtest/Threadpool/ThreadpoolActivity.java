package com.appsino.bingluo.databingtest.Threadpool;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.appsino.bingluo.databingtest.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Answer on 2018/7/4.
 */

public class ThreadpoolActivity extends Activity{
    @BindView(R.id.btn_threadpool_one)Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadpool);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_threadpool_one)void btnone(){
        Log.i("tag","===========futureTask start--"+System.nanoTime());
        try {
            Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int a = 0;
                    for (int i=0;i<100;i++){
                        a = a+i;
                    }
                    return a;
                }
            };
            Log.i("tag","===========futureTask start"+System.nanoTime());
            FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
            Executor threadpool = Executors.newFixedThreadPool(4);
            threadpool.execute(futureTask);
            Log.i("tag","===========futureTask"+futureTask.get());
            Log.i("tag","===========futureTask end"+System.nanoTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
