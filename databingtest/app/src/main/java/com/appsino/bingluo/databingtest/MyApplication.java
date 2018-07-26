package com.appsino.bingluo.databingtest;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Answer on 2018/5/29.
 */

public class MyApplication extends Application{
    public static ImageLoader imageLoader = ImageLoader.getInstance();
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();
        ImageLoader.getInstance().init(config);
        //初始化数据库
        FlowManager.init(new FlowConfig.Builder(this).build());
        //百度地图
        SDKInitializer.initialize(getApplicationContext());
    }
}
