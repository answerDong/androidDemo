package com.appsino.bingluo.databingtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Param;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.debug.hv.ViewServer;
import com.appsino.bingluo.databingtest.databinding.ActivityMainBinding;
import com.appsino.bingluo.databingtest.model.Activity2;
import com.appsino.bingluo.databingtest.model.ServiceDemo;
import com.appsino.bingluo.databingtest.model.User;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@Route(path = "/com/mainactivity")
public class MainActivity extends Activity implements View.OnClickListener {
    @BindView(R.id.btnintnet)
    Button btnintnet;
    private TextView textView;
    private User user;
    private Button button;
    private Button btnstop;
    private Button btnintent;
    //先定义 常量
    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    //绑定服务
    private ServiceDemo service = null;
    private boolean isBind = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.i("tag", "=============tag onCreate");
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("leavesC", "123456");
        activityMainBinding.name.setText("sdf");
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        btnstop = (Button) findViewById(R.id.btnstop);
        btnstop.setOnClickListener(this);
        btnintent = (Button) findViewById(R.id.btnintnet);
        btnintent.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tvtext);
        ViewServer.get(this).addWindow(this);
    }


    @IntDef({SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WeekDays {
    }

    @WeekDays
    int currentDay = SUNDAY;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                  UpdateTextTask updateTextTask = new UpdateTextTask(MainActivity.this);
                  updateTextTask.execute();
//                SoftReference<String> str = new SoftString str1 = new String("abc");Reference<String>(str1);
//                WeakReference<String> str2 = new WeakReference<String>(str1);
//                HashSet<String> a = new HashSet<String>();
//                for(int i )

//                String a="a";
//                ARouter.getInstance().build("/com/Activity1").withString("value","haode").navigation();
//                findViewById(R.id.button).setText
//                try {
//                    String a = DeviceInfoUtils.getDeviceId(MainActivity.this);
//                    Log.i("tag","=============tag"+a);
//                } catch (Exception e) {
//                    Log.i("tag","=============tag"+e);
//                    e.printStackTrace();
//                }
//                String b = "abc";
//                Log.i("tag","=============tag equals"+a.equals(b));
//                Log.i("tag","=============tag equals"+a.hashCode()+"   "+b.hashCode());
//                Intent intent = new Intent(this, ServiceDemo.class);
//                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
//                button.setText(a);
                break;
            case R.id.btnstop:
//                Intent intent1 = new Intent(this, ServiceDemo.class);
//                stopService(intent1);
                if (isBind) {
                    unbindService(serviceConnection);
                }
                break;
            case R.id.btnintnet:
                Intent  intent2 = new Intent(this,Activity2.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("tag", "=============tag onResume");
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("tag", "=============tag onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("tag", "=============tag onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("tag", "=============tag onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("tag", "=============tag onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag", "=============tag onDestroy");
        ViewServer.get(this).removeWindow(this);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder bingder) {
            isBind = true;
            ServiceDemo.MyBinder binder = (ServiceDemo.MyBinder) bingder;
            service = binder.getService();
            Log.i("tag", "===============service Connected" + name);
//                int number = service.getRand
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            Log.i("tag", "===============service Disconnected" + name);
        }
    };
    class UpdateTextTask extends AsyncTask<String,Integer,Integer>{
        private Context context;
        private  UpdateTextTask(Context mcontext){
            this.context = mcontext;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context,"开始执行", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Integer doInBackground(String... voids) {
            int i=0;
            while(i<10){
                i++;
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i("tag","=============afffff"+values[0]);
            textView.setText(values[0]+"");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Toast.makeText(context,"执行完毕", Toast.LENGTH_SHORT).show();
        }
    }
}
