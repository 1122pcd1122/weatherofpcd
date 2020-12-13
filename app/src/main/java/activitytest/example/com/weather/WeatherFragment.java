
package activitytest.example.com.weather;


import activitytest.example.com.weather.adapter.FutureAdapter;
import activitytest.example.com.weather.databinding.WeatherFragmentBinding;
import activitytest.example.com.weather.db.CityDataBase;
import activitytest.example.com.weather.db.Dao.CityDao;
import activitytest.example.com.weather.db.bean.comfort.ComfortNow;
import activitytest.example.com.weather.db.bean.seven7d.Daily;
import activitytest.example.com.weather.db.bean.today.Now;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class WeatherFragment extends Fragment {

    private static final String TAG = "MY";
    private TodayViewModel todayViewModel;
    private Seven7DayViewModel seven7DayViewModel;
    private WeatherFragmentBinding binding;
    private Toast toast ;
    private String city;

    private CityDao cityDao;



    /**
     * @param savedInstanceState
     * 获取两个ViewModel实例
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        todayViewModel = new ViewModelProvider ( this ).get ( TodayViewModel.class );
        seven7DayViewModel= new ViewModelProvider ( this ).get ( Seven7DayViewModel.class );
        CityDataBase cityDataBase = CityDataBase.getCitDataBase ( getContext ());
        cityDao = cityDataBase.getCityDao ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.bind ( LayoutInflater.from ( getContext () ).inflate ( R.layout.weather_fragment, container, false ) );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager ( getContext () );
        binding.sevenDay.setLayoutManager ( layoutManager );



        //获取当日天气
        getLocalWeather ();

        //获取天气
        getWeather ();

        return binding.getRoot ();
    }


    public void getLocalWeather(){
        SharedPreferences sharedPreferences = Objects.requireNonNull ( getActivity () ).getSharedPreferences ( "City",Context.MODE_PRIVATE );
        city = sharedPreferences.getString ( "City", null );

        if (cityDao.getCity ( city ) != null){
            int cityID = cityDao.getCity ( city ).cityID;
            Log.d ( TAG, "本地位置:"+city  );
            Log.d ( TAG,"此时城市"+city +"此时城市ID"+ cityDao.getCity ( city ).cityID +"" );
            setToday ( cityID );
            set7D ( cityID );
        }else {
            Toast.makeText ( getContext (), "未能确定位置", Toast.LENGTH_SHORT ).show ();
        }

    }


    /**
     * 获取天气信息
     */
    public void getWeather(){


        //显示选择天气
        binding.select.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
             city =binding.ediText.getText ().toString ();
                if (city.equals ( "" )){
                    Toast.makeText ( getContext (), "请输入城市信息", Toast.LENGTH_SHORT ).show ();
                }else {
                    if (cityDao.getCity ( city ) == null){
                        Toast.makeText ( getContext (), "请输入正确信息", Toast.LENGTH_SHORT ).show ();
                    }else {
                        int cityID = cityDao.getCity ( city ).cityID;
                        Log.d ( TAG,"此时城市"+city +"此时城市ID"+ cityDao.getCity ( city ).cityID +"" );
                        setToday ( cityID );
                        set7D(cityID);
                    }
                }

            }
        } );
    }





    /**
     * 显示今日天气
     * @param cityID 城市id
     */
    @SuppressLint("FragmentLiveDataObserve")
    public void setToday(int cityID){


        todayViewModel.getWeatherRepository ( cityID );


        //天气信息
        todayViewModel.getToday ().observe ( this, new Observer<Now> () {
            @SuppressLint("CheckResult")
            @Override
            public void onChanged(Now now) {

                binding.city.setText ( city );
                Date obsTime = now.getObsTime ();

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd kk:mm" );
                String format = simpleDateFormat.format ( obsTime );
                binding.obsTime.setText ( format );
                binding.temp.setText ( now.getTemp ());
                binding.text.setText ( now.getText () );
                binding.c1.setText ( "℃" );


            }
        } );


        //状态信息
        todayViewModel.getStatusInfo ().observe ( this, new Observer<String> () {
            @SuppressLint("ShowToast")
            @Override
            public void onChanged(String s) {
                if (toast == null){
                    toast = Toast.makeText ( getContext (),s,Toast.LENGTH_SHORT );
                }else {
                    toast.setText ( s );
                }
                toast.show ();
            }
        } );


        todayViewModel.getComfortNow ().observe ( this, new Observer<List<ComfortNow>> () {
            @Override
            public void onChanged(List<ComfortNow> comFortNow) {
                String text = comFortNow.get ( 0 ).getText ();
                binding.comfort.setText ( text );
            }
        } );


    }

    /**
     * 获取7天天气
     * @param cityID 城市ID
     */
    private void set7D(int cityID) {
        seven7DayViewModel.getWeatherRepository ( cityID );
        seven7DayViewModel.getDaily ().observe ( getViewLifecycleOwner(), new Observer<List<Daily>> () {
            @Override
            public void onChanged(List<Daily> dailies) {
                FutureAdapter futureAdapter = new FutureAdapter ( getContext (),dailies );
                binding.sevenDay.setAdapter ( futureAdapter );
            }
        } );

        seven7DayViewModel.getStatusInfo ().observe ( getViewLifecycleOwner(), new Observer<String> () {
            @SuppressLint("ShowToast")
            @Override
            public void onChanged(String s) {
                if (toast == null){
                    toast = Toast.makeText ( getContext (),s,Toast.LENGTH_SHORT );
                }else {
                    toast.setText ( s );
                }
                toast.show ();
            }
        } );

    }



}