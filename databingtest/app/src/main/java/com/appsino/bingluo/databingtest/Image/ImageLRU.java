package com.appsino.bingluo.databingtest.Image;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;

/**
 * 图片缓存
 * Created by Answer on 2018/7/3.
 */

public class ImageLRU {
    LruCache<String,Drawable> mMemoryCache;
    public void initLRU(){
        //获取系统分配给应用的最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置缓存大小
        int mCacheSize = maxMemory/8;
        mMemoryCache = new LruCache<String,Drawable>(mCacheSize){
            @Override
            protected int sizeOf(String key, Drawable value) {
                if(value instanceof BitmapDrawable){
                    Bitmap bitmap = ((BitmapDrawable) value).getBitmap();
                    return bitmap ==null ? 0 :bitmap.getByteCount();
                }
                return super.sizeOf(key, value);
            }
        };
    }
    private void addDrawableToMemoryCache(String key,Drawable drawable){
        if(getDrawableFromMemCache(key)==null&&drawable!=null){
            mMemoryCache.put(key,drawable);
        }
    }

    /**
     * 从内存中获取一个drawable
     * @param key
     * @return
     */
    public Drawable getDrawableFromMemCache(String key){
        return mMemoryCache.get(key);
    }
    public void removeCacheFromMemory(String key){
        mMemoryCache.remove(key);
    }
    /**
     * 清理内存缓存
     */
    public void cleanMemoryCCache() {
        mMemoryCache.evictAll();
    }
}
