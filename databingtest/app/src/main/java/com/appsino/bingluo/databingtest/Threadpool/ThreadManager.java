package com.appsino.bingluo.databingtest.Threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Answer on 2018/7/2.
 */

public class ThreadManager {
    private static ThreadPool threadPool; // 单列的线程池对象。
    /**
     * 单列，线程安全
     * 获取一个线程池对象
     * @return
     */
    public static ThreadPool getThreadPool()
    {
        if (threadPool == null)
        {
            //枷锁
            synchronized (ThreadManager.class)
            {
                if (threadPool == null)
                {
                    //核心线程数，等于处理器个数乘2
                    int corePoolSize = Runtime.getRuntime().availableProcessors()*2;
                    int maximumPoolSize = 10;
                    long keepAliveTime = 0L;
                    threadPool = new ThreadPool(corePoolSize, maximumPoolSize, keepAliveTime);
                }
            }
        }

        return threadPool;
    }
    public static class ThreadPool {
        public static ThreadPoolExecutor executor = null;

        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime = 0; // 限制线程的的最大存活时间

        public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            super();
            this.corePoolSize = corePoolSize;  //核心线程数
            this.maximumPoolSize = maximumPoolSize; //最大线程 ，当核心线程用完时。决定是否开启最大线程
            this.keepAliveTime = keepAliveTime;  //线程排队时间，
        }
        public void execute(Runnable runnable)
        {
            if (runnable == null)
            {
                return;
            }

            if (executor == null)
            {

                executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                        TimeUnit.MILLISECONDS,// 时间单位
                        new LinkedBlockingQueue<Runnable>(),// 线程队列
                        Executors.defaultThreadFactory(),//线程工厂
                        new ThreadPoolExecutor.AbortPolicy());
            }
            // 给线程池里面添加一个线程
            executor.execute(runnable);
        }
    }
//ThreadManager.getThreadPool().execute(new WorkerThread("歌曲" + i)); 线程池使用
}
