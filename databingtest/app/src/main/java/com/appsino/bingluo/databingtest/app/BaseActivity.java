package com.appsino.bingluo.databingtest.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by Answer on 2018/7/12.
 */

public class BaseActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注解事件绑定
    }
}
