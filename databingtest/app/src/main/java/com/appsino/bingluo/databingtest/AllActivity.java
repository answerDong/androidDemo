package com.appsino.bingluo.databingtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.appsino.bingluo.databingtest.Threadpool.ThreadpoolDemo;
import com.appsino.bingluo.databingtest.Utils.HandlerUtils;
import com.appsino.bingluo.databingtest.Utils.NormalUtils;
import com.appsino.bingluo.databingtest.View.AutoSplitTextView;
import com.appsino.bingluo.databingtest.db.UserData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.codeboy.android.aligntextview.AlignTextView;

/**
 * Created by Answer on 2018/6/8.
 */

public class AllActivity extends Activity implements HandlerUtils.onReceiverMessageListener{
    @BindView(R.id.btn_to_service)
    Button btnToService;
    @BindView(R.id.btn_to_receiver)
    Button btnToReceiver;
    @BindView(R.id.imageView)
    ImageView imageView;
    private HandlerUtils.HandlerHolder handlerHolder;
//    me.codeboy.android.aligntextview.AlignTextView
    AlignTextView te;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allactivity);
        te = (AlignTextView) findViewById(R.id.tvtextttt);
//        te.setText("将拨通移动公证录音专线号码进行通话录音取证,运营商将收取正常市话费。");
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_to_service, R.id.btn_to_receiver,R.id.btn_to_addlist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_to_addlist:
                UserData userData1 = new UserData();
                ModelAdapter<UserData> adapter1 = FlowManager.getModelAdapter(UserData.class);
                userData1.name = "李四";
                userData1.sex = "男";
                adapter1.insert(userData1);
                break;
            case R.id.btn_to_service:
                //数据库操作
                UserData userData = new UserData();
                ModelAdapter<UserData> adapter = FlowManager.getModelAdapter(UserData.class);
                userData.id = 0;
                userData.name = "张三";
                adapter.insert(userData);
//                handlerHolder = new HandlerUtils.HandlerHolder(this);
//                handlerHolder.sendEmptyMessage(1);
//                ARouter.getInstance().build("/com/mainactivity").navigation();
//                MyAsyncTask myAsyncTask = new MyAsyncTask();
//                myAsyncTask.execute();
//                Log.i("tag","=============rcpu核心数"+Runtime.getRuntime().availableProcessors());
//                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//                for (int i = 0;i<10;i++){
//                    final int index = i;
//                    fixedThreadPool.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.i("tag","=============runnable"+Thread.currentThread().getId()+"    "+index);
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                }
                //多线程
//                ThreadpoolDemo threadpoolDemo = new ThreadpoolDemo();
//                for (int i = 0;i<6;i++){
//                    new Thread(threadpoolDemo).start();
//                }
//                DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(100)).build();
//                ImageLoader.getInstance().displayImage("https://b-ssl.duitang.com/uploads/item/201601/27/20160127121505_dPmN5.jpeg",imageView,displayImageOptions);
                break;
            case R.id.btn_to_receiver:
                List<UserData> list = SQLite.select().from(UserData.class).queryList();
                for(int i = 0;i<list.size();i++){
                    Log.i("tag","===================查询结果"+list.get(i).toString());
                }

//                ARouter.getInstance().build("/com/receiveractivity").navigation();
                break;
        }
    }


    @Override
    public void HandleMesage(Message msg) {

    }

    /**
     * asynctask任务
     */
    private class MyAsyncTask extends AsyncTask<String,Integer,Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            NormalUtils.Toast(AllActivity.this,"线程开始了");
        }

        @Override
        protected Integer doInBackground(String... strings) {
            for(int i = 0 ;i<10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i = i++;
                publishProgress(10);
            }
            return 1;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            NormalUtils.Toast(AllActivity.this,"线程结束了"+values);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            NormalUtils.Toast(AllActivity.this,"线程结束了"+integer);
        }
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
