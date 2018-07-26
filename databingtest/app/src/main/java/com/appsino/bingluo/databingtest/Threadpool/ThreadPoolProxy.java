package com.appsino.bingluo.databingtest.Threadpool;

import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池封装一
 * Created by Answer on 2018/6/28.
 */

public class ThreadPoolProxy {
    //创建一个线程池对象
    ThreadPoolExecutor mExecutor;
    //最大核心数
    private  int mCorePoolSize;
    //最大的线程数
    private int mMaximumPoolSize;

    /**
     *
     * @param corePoolSize 核心池的线程数
     * @param maximumPoolSize   最大线程数
     */
    public ThreadPoolProxy(int corePoolSize,int maximumPoolSize){
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
    }

    /**
     * 初始化ThreadPoolExecutor
     * 双重检查加锁，只有第一次实例化才启动同步机制，提高性能。
     */
    private void initThreadPoolExecutor() {
        //双重检查
        if (mExecutor == null ||mExecutor.isTerminated()||mExecutor.isShutdown()) {
            synchronized (ThreadPoolExecutor.class){
                if (mExecutor == null ||mExecutor.isTerminated()||mExecutor.isShutdown()) {
                    long keepAliveTime = 3000;
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
                    mExecutor = new ThreadPoolExecutor(mCorePoolSize,mMaximumPoolSize,keepAliveTime,unit, (BlockingQueue<Runnable>) threadFactory,handler);
                }
            }

        }
    }

    /**
     * 执行任务
     * @param task
     */
    public void execute(Runnable task){
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 提交任务
     */
    public Future<?> submit(Runnable task){
        initThreadPoolExecutor();
        return mExecutor.submit(task);

    }

    /**
     * 移除任务
     */
    public void remove(Runnable task){
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }
}
