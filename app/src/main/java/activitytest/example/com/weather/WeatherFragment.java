package activitytest.example.com.weather;

import activitytest.example.com.weather.adapter.FutureAdapter;
import activitytest.example.com.weather.databinding.WeatherFragmentBinding;
import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.Realtime;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class WeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private WeatherFragmentBinding binding;
    private RecyclerView recyclerView;
    private Toast toast ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate ( inflater, R.layout.weather_fragment,container,false  );


        //设置recycleView的view
        recyclerView= binding.recycleView;
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager ( getContext () );
        recyclerView.setLayoutManager ( layoutManager );

        //获取天气信息
        getWeather ();



        return binding.getRoot ();
    }


    /**
     * @param savedInstanceState 保存状
     * 获取两个ViewModel实例
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        weatherViewModel = new ViewModelProvider ( this ).get ( WeatherViewModel.class );
    }

    /**
     * 获取天气信息
     */
    public void getWeather(){
        binding.select.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String city=binding.ediText.getText ().toString ();

                if (city.equals ( "" )){
                    Toast.makeText ( getContext (), "请输入城市信息", Toast.LENGTH_SHORT ).show ();
                }else {
                    setRealTime (city);
                }


            }
        } );
    }


    /**
     * 显示今日天气
     * @param city 城市
     */
    public void setRealTime(String city){
        weatherViewModel.getWeatherRepository ( city );

        weatherViewModel.getRealTime ().observe ( WeatherFragment.this, new Observer<Realtime> () {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Realtime realtime) {

                binding.temperature.setText ( realtime.getTemperature ()+"℃" );
                binding.info.setText ( realtime.getInfo () );
                binding.aqi.setText ( realtime.getAqi () );
                binding.wid.setText ( realtime.getWid () );
                binding.direct.setText ( realtime.getDirect () );
                binding.humidity.setText ( realtime.getHumidity () );


            }
        } );

        weatherViewModel.getFutures ().observe ( WeatherFragment.this, new Observer<List<Future>> () {
            @Override
            public void onChanged(List<Future> futures) {

                FutureAdapter futureAdapter = new FutureAdapter ( getContext (), futures );
                recyclerView.setAdapter ( futureAdapter );


            }
        } );

       weatherViewModel.getStatusInfo ().observe ( WeatherFragment.this, new Observer<String> () {
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