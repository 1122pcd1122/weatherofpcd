package activitytest.example.com.weather;


import activitytest.example.com.weather.db.CityDataBase;
import activitytest.example.com.weather.db.Dao.CityDao;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gyf.immersionbar.ImmersionBar;

public class  MainActivity extends AppCompatActivity  {


    public LocationClient mLocationClient = null;
    private final MyLocationListener myListener = new MyLocationListener ();

    private final String TAG = "MainActivity";

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
        FragmentManager fragmentManager=getSupportFragmentManager ();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        fragmentTransaction.add ( R.id.weatherFragment,weatherFragment,"WeatherFragment");
        fragmentTransaction.commit ();

    }



    private void setLocationClient() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode( LocationClientOption.LocationMode.Hight_Accuracy);

        option.setIsNeedAddress ( true );

        option.setCoorType("bd09ll");

        option.setScanSpan(1000);

        option.setOpenGps(true);

        option.setLocationNotify(true);

        option.SetIgnoreCacheException(false);

        option.setWifiCacheTimeOut(5*60*1000);

        option.setNeedNewVersionRgc(true);

        mLocationClient.setLocOption(option);
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