package com.appsino.bingluo.databingtest.Utils;

import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.logging.Handler;

/**
 * handler弱引用工具类
 * Created by Answer on 2018/6/21.
 */

public class HandlerUtils {
    private HandlerUtils() {

    }
    public static class HandlerHolder extends android.os.Handler{
        WeakReference<onReceiverMessageListener> weakReference;

        /**
         * activity实现接口
         * @param lister
         */
        public HandlerHolder(onReceiverMessageListener lister){
            weakReference = new WeakReference<onReceiverMessageListener>(lister);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(weakReference!=null&&weakReference.get()!=null){
                weakReference.get().HandleMesage(msg);
            }
        }
    }
    public void HandlerMessage(Message msg){

    }
    public interface onReceiverMessageListener{
        void HandleMesage(Message msg);
    }
}
