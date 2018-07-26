package com.appsino.bingluo.databingtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.xml.transform.Source;

/**
 * Created by Answer on 2018/5/29.
 */
@Route(path = "/com/Activity1")
public class Activity1 extends Activity{
    private static final int a = 0;
    private static final int b = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        @demo int abc = a;
        aaa(abc);
    }
    @IntDef({a,b})
    @Retention(RetentionPolicy.SOURCE)
    public @interface demo{
    }
    @demo int currady = a;
    public void aaa(@demo int demo){
        switch (demo){
            case 1:
                break;
            case 3:
                break;
        }
    }
}
