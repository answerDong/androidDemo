package com.appsino.bingluo.databingtest.model;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.appsino.bingluo.databingtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Answer on 2018/6/8.
 */

public class Activity2 extends Activity {
    @BindView(R.id.btnunbindservice)
    Button btnunbindservice;
    @BindView(R.id.btnbindservice)
    Button btnbindservice;
    private boolean isbind = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindservicetwo);
        ButterKnife.bind(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @OnClick({R.id.btnunbindservice, R.id.btnbindservice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnunbindservice:
                if(isbind){
                    Intent intent = new Intent(this,ServiceDemo.class);
                    unbindService(serviceConnection);
                }
                break;
            case R.id.btnbindservice:
                Intent intent = new Intent(this,ServiceDemo.class);
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                break;
        }
    }
    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isbind = true;
            Log.i("tag","===============serviceActivity2 Connected"+name);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isbind = false;
            Log.i("tag","===============serviceActivity2 Disconnected"+name);
        }
    };
}
