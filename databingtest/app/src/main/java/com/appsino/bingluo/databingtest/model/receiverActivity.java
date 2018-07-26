package com.appsino.bingluo.databingtest.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.appsino.bingluo.databingtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Answer on 2018/6/8.
 */
@Route(path = "/com/receiveractivity")
public class receiverActivity extends Activity {
    @BindView(R.id.btn_receiver_start)
    Button btnReceiverStart;
    public static final String RECEIVERTEST = "receivertest";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_receiver_start)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setAction("com.appsino.myreceiver");
        intent.putExtra("receivertest","df");
        sendBroadcast(intent);
    }
}
