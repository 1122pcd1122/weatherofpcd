package activitytest.example.com.weather;


import activitytest.example.com.weather.db.CityDataBase;
import activitytest.example.com.weather.db.Dao.CityDao;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class  MainActivity extends AppCompatActivity  {


    public LocationClient mLocationClient = null;
    private final MyLocationListener myListener = new MyLocationListener ();

    private final String TAG = "MainActivity";


    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mLocationClient = new LocationClient ( getApplicationContext () );
        mLocationClient.registerLocationListener ( myListener );
        setLocationClient();
        mLocationClient.start ();




        //初始化WeatherFragment
        WeatherFragment weatherFragment = new WeatherFragment ();
        weatherFragment.setArguments ( bundle );
        FragmentManager fragmentManager=getSupportFragmentManager ();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        fragmentTransaction.add ( R.id.weatherFragment,weatherFragment,"WeatherFragment");
        fragmentTransaction.commit ();
    }

    private void setLocationClient() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode( LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setIsNeedAddress ( true );

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认GCJ02
//GCJ02：国测局坐标；
//BD09ll：百度经纬度坐标；
//BD09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false



        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5*60*1000);
//可选，V7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位



        option.setNeedNewVersionRgc(true);
//可选，设置是否需要最新版本的地址信息。默认需要，即参数为true

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

    }



    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String addr = bdLocation.getAddrStr();    //获取详细地址信息
            String country = bdLocation.getCountry();    //获取国家
            String province = bdLocation.getProvince();    //获取省份
            String city = bdLocation.getCity();    //获取城市
            String district = bdLocation.getDistrict();    //获取区县
            String street = bdLocation.getStreet();    //获取街道信息
            String adcode = bdLocation.getAdCode();    //获取adcode
            String town = bdLocation.getTown();    //获取乡镇信息
            String cityCode = bdLocation.getCityCode (); //获取城市编码

            if (district != null){
                SharedPreferences preferences = getSharedPreferences ( "City",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit ();
                editor.putString ( "City",district );
                editor.apply ();
            }



            Log.d ( TAG,"状态码"+bdLocation.getLocType () +"");
            Log.d ( TAG,"时间"+bdLocation.getTime ()+"" );
            Log.d ( TAG,"经纬度"+bdLocation.getLatitude ()+bdLocation.getLongitude ()+"" );

            Log.d ( TAG,"详细地址"+addr );
            Log.d ( TAG,"国家"+country );
            Log.d ( TAG,"省份"+province );
            Log.d ( TAG,"城市"+city );
            Log.d ( TAG,"区县"+district );
            Log.d ( TAG,"街道"+street );
            Log.d ( TAG,"行政区划妈"+adcode );
            Log.d ( TAG,"乡镇信息"+town );
            Log.d ( TAG,"城市编码"+cityCode );



        }
    }



}