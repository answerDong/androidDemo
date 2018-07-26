package com.appsino.bingluo.databingtest.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Answer on 2018/6/8.
 */

public class broadcastReceiverDemo extends BroadcastReceiver{
    @Override
        public void onReceive(Context context, Intent intent) {
        Log.i("tag","============receiver"+intent.getAction());
        Log.i("tag","============receiver"+intent.getStringExtra("receivertest"));
        }
}
