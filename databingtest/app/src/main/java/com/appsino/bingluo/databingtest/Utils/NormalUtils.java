package com.appsino.bingluo.databingtest.Utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Answer on 2018/6/21.
 */

public class NormalUtils {
    /**
     * 封装吐司
     * @param activity
     * @param msg
     */
    public static void Toast(Activity activity,String msg){
        Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show();
    }
}
