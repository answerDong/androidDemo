package com.appsino.bingluo.databingtest.Login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.appsino.bingluo.databingtest.MainActivity;
import com.appsino.bingluo.databingtest.R;
import com.appsino.bingluo.databingtest.Utils.SPUtils;
import com.appsino.bingluo.databingtest.Utils.Utils;
import com.appsino.bingluo.databingtest.View.DrivingRouteOverlay;
import com.appsino.bingluo.databingtest.app.BaseActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Answer on 2018/7/12.
 */
@Route(path = "/com/mainpageactivity")
public class MainPageActivity extends BaseActivity implements SensorEventListener {
    @BindView(R.id.tv_main_toperson)
    TextView tvMainToperson;
    @BindView(R.id.tv_main_thisplace)
    EditText tvMainThisplace;
    @BindView(R.id.tv_main_toplace)
    EditText tvMainToplace;
    @BindView(R.id.btn_main_submit)
    Button btnMainSubmit;
    @BindView(R.id.tv_main_sbplace)
    TextView tvsbplace;
    private MapView mMapView;
    private String provider;
    private BaiduMap mBaiduMap;
    BitmapDescriptor mCurrentMarker;
    double first [] = new double[2];;
    double second [] = new double[2];
    public MyLocationListenner myListener = new MyLocationListenner();
    // 定位相关
    LocationClient mLocClient;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    private SensorManager mSensorManager;
//    OnCheckedChangeListener radioButtonListener;
    Button requestLocButton;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;
    private RoutePlanSearch mSearch;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    GeoCoder geoCoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        ButterKnife.bind(this);
        mMapView = (MapView) findViewById(R.id.mmap);
        mBaiduMap = mMapView.getMap();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainPageActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},0);
            }else{
                initMap();
            }
        }else{
            initMap();
        }

    }

    private void initMap() {
        // 开启定位图层
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
        mCurrentMarker =  BitmapDescriptorFactory .fromResource(R.mipmap.icon_map);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, false, mCurrentMarker,00000000,00000000));
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setOnMapClickListener(listener);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        mLocClient.setLocOption(option);
        mLocClient.start();
        mSearch = RoutePlanSearch.newInstance();
        geoCoder = GeoCoder.newInstance();
        mSearch.setOnGetRoutePlanResultListener(routelistener);

    }

    @OnClick({R.id.tv_main_toperson, R.id.tv_main_thisplace, R.id.tv_main_toplace, R.id.btn_main_submit,R.id.tv_main_sbplace})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_main_toperson:
//                ARouter.getInstance().build("/com/loginactivity").navigation();
                break;
            case R.id.tv_main_thisplace:
                break;
            case R.id.tv_main_toplace:
                break;
            case R.id.btn_main_submit:
                boolean islo = SPUtils.getBoolean(MainPageActivity.this,"islogin",false);
                if(islo == false){
                    ARouter.getInstance().build("/com/loginactivity").navigation();
                }else{
                    if(TextUtils.isEmpty(tvMainThisplace.getText().toString())&&TextUtils.isEmpty(tvMainToplace.getText().toString())){
                        Utils.ToastSign(MainPageActivity.this,"提交成功，请等待接单");
                        mBaiduMap.clear();
                    }
                }
                break;
            case R.id.tv_main_sbplace:
                Log.i("tag","==================baidulo"+first[0]);
                Log.i("tag","==================baidulo"+first[1]);
                Log.i("tag","==================baidulo"+second[0]);
                Log.i("tag","==================baidulo"+second[1]);
                if(first[0]!=0.0&&second[1]!=0.0){
                    LatLng from = new LatLng(first[0], first[1]);
                    LatLng dest = new LatLng(second[0],second[1]);
                    PlanNode stNode = PlanNode.withLocation(from);
                    PlanNode enNode = PlanNode.withLocation(dest);
//                    发起驾车线路规划检索；
                    mSearch.drivingSearch((new DrivingRoutePlanOption())
                            .from(stNode)
                            .to(enNode));
                }
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double x = event.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.i("tag","==================baidulocation"+location.getAddrStr());
            String address =location.getStreet()+location.getStreetNumber();
            tvMainThisplace.setText(address);
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            first[0] = location.getLatitude();
            first[1] = location.getLongitude();
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }
            mLocClient.stop();
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        mSearch.destroy();
        geoCoder.destroy();
        super.onDestroy();
    }
    BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
        /**
         * 地图单击事件回调函数
         * @param point 点击的地理坐标
         */
        public void onMapClick(LatLng point){
//            LatLng point = new LatLng(39.963175, 116.400244);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_map);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
            second[0] = point.latitude;
            second[1] = point.longitude;
            Log.i("tag","==================baidulocat"+point.latitude);
            Log.i("tag","==================baidulocat"+point.longitude);
            latlngToAddress(point);
        }
        /**
         * 地图内 Poi 单击事件回调函数
         * @param poi 点击的 poi 信息
         */
        public boolean onMapPoiClick(MapPoi poi){
            return true;
        }
    };
    OnGetRoutePlanResultListener routelistener = new OnGetRoutePlanResultListener() {

        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            //获取驾车线路规划结果

            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(MainPageActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                //result.getSuggestAddrInfo()
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                mBaiduMap.clear();
                // mroute = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
//                mrouteOverlay = overlay;
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
            }

        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };
    //定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            return BitmapDescriptorFactory.fromResource(R.mipmap.icon_st);
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            return BitmapDescriptorFactory.fromResource(R.mipmap.icon_en);
        }
    }
    private void latlngToAddress(LatLng latlng) {
        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        //设置地址或经纬度反编译后的监听,这里有两个回调方法,
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                Log.i("tag","==================baidulocation result11111");
                Log.i("tag","==================baidulocation result"+result.getAddress());
                if (result == null ||  result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(MainPageActivity.this, "找不到该地址!",Toast.LENGTH_SHORT).show();
                }
                tvMainToplace.setText(result.getAddress());
            }
        });
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latlng));
    }
}
