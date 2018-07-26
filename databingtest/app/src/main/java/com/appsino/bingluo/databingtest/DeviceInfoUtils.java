package com.appsino.bingluo.databingtest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by Answer on 2018/5/30.
 */

public class DeviceInfoUtils {
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= 23) {

//            if (ActivityCompat.checkSelfPermission(context,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//
//                return "";
//            }else{
//                if (!TextUtils.isEmpty(tm.getDeviceId(0))) {
//                    return tm.getDeviceId(0);
//                } else if (!TextUtils.isEmpty(tm.getDeviceId(1))) {
//                    return tm.getDeviceId(1);
//                } else {
                    return tm.getDeviceId();
//                }
//            }
        } else {
//            androSystemPropertiesid.os.
//            android.os.SystemProperties.get(android.telephony.cdma.CdmaCellLocation.);
//            if(TextUtils.isEmpty(tm.getDeviceId())){
                return tm.getDeviceId();
//            }else{
//                return "";
//            }

        }
    }
}