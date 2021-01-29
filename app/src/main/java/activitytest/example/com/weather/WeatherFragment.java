
package activitytest.example.com.weather;


import activitytest.example.com.weather.adapter.AdviceAdapter;
import activitytest.example.com.weather.adapter.FutureAdapter;
import activitytest.example.com.weather.databinding.AdviceLayoutBinding;
import activitytest.example.com.weather.databinding.TodayCardviewBinding;
import activitytest.example.com.weather.databinding.WeatherFragmentBinding;
import activitytest.example.com.weather.databinding.WeatherToolbarBinding;
import activitytest.example.com.weather.db.CityDataBase;
import activitytest.example.com.weather.db.Dao.CityDao;
import activitytest.example.com.weather.db.bean.city.Location;
import activitytest.example.com.weather.db.bean.comfort.ComfortNow;
import activitytest.example.com.weather.db.bean.seven7d.Daily;
import activitytest.example.com.weather.db.bean.today.Now;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class WeatherFragment extends Fragment {

    private static final String TAG = "MY";
    private TodayViewModel todayViewModel;
    private Seven7DayViewModel seven7DayViewModel;
    private LocationViewModel locationViewModel;
    private WeatherFragmentBinding weatherFragmentBinding;
    private TodayCardviewBinding todayCardviewBinding;

    public String city;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getDataBaseOrDao ();
        getViewModelEntity ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        weatherFragmentBinding = DataBindingUtil.bind ( LayoutInflater.from ( getContext () ).inflate ( R.layout.weather_fragment, container, false ) );
        todayCardviewBinding  = DataBindingUtil.bind ( LayoutInflater.from ( getContext () ).inflate ( R.layout.today_cardview, container, false ) );

        //设置recycleView样式
        setRecycleViewModel ();
        getData ();

        return weatherFragmentBinding.getRoot ();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        ImageButton imageButton = getView ().findViewById ( R.id.search );
        imageButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (),SearchActivity.class);
                startActivity ( intent );

            }
        } );
    }

    public void getData() {


        //获取本地天气
        getLocalWeather ();

//        //获取其它城市的天气
//        getWeather ();
    }


    public void getDataBaseOrDao() {

        CityDataBase cityDataBase = CityDataBase.getCitDataBase ( getContext ());
        CityDao cityDao = cityDataBase.getCityDao ();
    }


    public void getViewModelEntity() {

        todayViewModel = new ViewModelProvider ( this ).get ( TodayViewModel.class );
        seven7DayViewModel= new ViewModelProvider ( this ).get ( Seven7DayViewModel.class );
        locationViewModel = new ViewModelProvider ( this ).get ( LocationViewModel.class );
    }

    private void setRecycleViewModel() {
        //设置recycleView的样式
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager ( getContext (),RecyclerView.HORIZONTAL ,false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager ( getContext (),RecyclerView.HORIZONTAL ,false);
        weatherFragmentBinding.adviceRecycle.setLayoutManager ( layoutManager1 );
        weatherFragmentBinding.sevenDay.setLayoutManager ( layoutManager );
    }


    /**
     * 获取当地天气
     */
    public void getLocalWeather(){

        SharedPreferences sharedPreferences = Objects.requireNonNull ( getActivity () ).getSharedPreferences ( "City",Context.MODE_PRIVATE );
        city = sharedPreferences.getString ( "City", "null" );
        Log.d ( TAG,city );

        if (city.equals ( "null" )){
            Toast.makeText ( getContext (), "未能确定位置", Toast.LENGTH_SHORT ).show ();
        }

        locationViewModel.getWeatherRepository ( city );
        Log.d ( TAG,locationViewModel.getLocation ()+"" );

        locationViewModel.getLocation ().observe ( getViewLifecycleOwner(), new Observer<Location> () {
            @Override
            public void onChanged(Location location) {
                if (location == null){
                    Toast.makeText ( getContext (), "未确定位置", Toast.LENGTH_SHORT ).show ();
                }else {

                    int cityID = Integer.parseInt (locationViewModel.getLocation ().getValue ().getId ());
                    Log.d ( TAG, "本地位置:"+city  );
                    Log.d ( TAG,"此时城市"+city +"此时城市ID"+ location.getId ()+"" );
                    setToday ( cityID );
                    set7D ( cityID );
                }
            }
        } );


    }

//
//    /**
//     * 获取天气信息
//     */
//    public void getWeather(){
//
//        //显示选择天气
//        binding.select.setOnClickListener ( new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//             city =binding.ediText.getText ().toString ();
//             locationViewModel.getWeatherRepository ( city );
//             if (city.equals ( "" )){
//                 Toast.makeText ( getContext (), "请输入城市信息", Toast.LENGTH_SHORT ).show ();
//             }else {
//                 locationViewModel.getLocation ().observe ( getViewLifecycleOwner (), new Observer<Location> () {
//                     @Override
//                     public void onChanged(Location location) {
//                         if (location!=null)
//                         {
//                             int cityID = Integer.parseInt ( location.getId () );
//                             Log.d ( TAG,"此时城市"+city +"此时城市ID"+ location.getId () +"" );
//                             setToday ( cityID );
//                             set7D(cityID);
//
//                         }else {
//                             Toast.makeText ( getContext (), "未确定位置", Toast.LENGTH_SHORT ).show ();
//
//                         }
//
//                     }
//
//                 } );
//             }
//
//            }
//
//        } );
//
//    }

    /**
     * 显示今日天气
     * @param cityID 城市id
     */
    @SuppressLint("FragmentLiveDataObserve")
    public void setToday(int cityID){
        todayViewModel.getWeatherRepository ( cityID );
        //天气数据
        todayViewModel.getToday ().observe ( this, new Observer<Now> () {
            @SuppressLint("CheckResult")
            @Override
            public void onChanged(Now now) {
                weatherFragmentBinding.setCity ( city );
                Calendar calendar = Calendar.getInstance ();
                int i = calendar.get ( Calendar.DAY_OF_WEEK );
                weatherFragmentBinding.setWeek ( "星期"+getWeek ( i ));
                weatherFragmentBinding.setNow ( now );
                Glide.with ( todayCardviewBinding.getRoot () ).load ( "https://raw.githubusercontent.com/qwd/WeatherIcon/master/weather-icon-S2/128/"+now.getIcon ()+".png" ).into ( todayCardviewBinding.weatherIcon);
            }

        } );
        //提示信息
        todayViewModel.getComfortNow ().observe ( this, new Observer<List<ComfortNow>> () {
            @Override
            public void onChanged(List<ComfortNow> comFortNow) {

                AdviceAdapter adviceAdapter = new AdviceAdapter ( getContext (),comFortNow );
                weatherFragmentBinding.adviceRecycle.setAdapter ( adviceAdapter );

            }

        } );

    }

    /**
     * 获取7天天气
     * @param cityID 城市ID
     */
    private void set7D(int cityID) {
        //从仓库中获取数据
        seven7DayViewModel.getWeatherRepository ( cityID );
        seven7DayViewModel.getDaily ().observe ( getViewLifecycleOwner(), new Observer<List<Daily>> () {
            @Override
            public void onChanged(List<Daily> dailies) {
                FutureAdapter futureAdapter = new FutureAdapter ( getContext (),dailies );
                weatherFragmentBinding.sevenDay.setAdapter ( futureAdapter );
            }

        } );

    }
    public String getCity() {
        return city;
    }

    public String getWeek(int i){
        switch (i){
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
        }
        return null;
    }
}