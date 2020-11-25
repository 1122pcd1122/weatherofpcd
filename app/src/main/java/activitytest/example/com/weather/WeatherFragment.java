package activitytest.example.com.weather;

import activitytest.example.com.weather.adapter.FutureAdapter;
import activitytest.example.com.weather.databinding.WeatherFragmentBinding;
import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.Realtime;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.List;


public class WeatherFragment extends Fragment {

    private WeatherViewModel mViewModel;
    private FutureViewModel futureViewModel;
    private WeatherFragmentBinding binding;


    OnHeadlineSelectedListener callback;



    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }

    public void setOnHeadlineSelectedListener(OnHeadlineSelectedListener callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate ( inflater, R.layout.weather_fragment,container,false  );


        RecyclerView recyclerView= binding.recycleView;
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager ( getContext () );
        recyclerView.setLayoutManager ( layoutManager );

        binding.select.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String city=binding.ediText.getText ().toString ();
                mViewModel.getRealtimeLiveData (city).observe ( WeatherFragment.this, new Observer<Realtime> () {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChanged(Realtime realtime) {
                        if (realtime.getInfo ().equals ( "无法获取连接地址" )){
                            Toast.makeText ( getContext (), "无法获取连接地址", Toast.LENGTH_SHORT ).show ();
                            return;
                        }
                        binding.temperature.setText ( realtime.getTemperature ()+"℃" );
                        binding.info.setText ( realtime.getInfo () );
                        binding.aqi.setText ( realtime.getAqi () );
                        binding.wid.setText ( realtime.getWid () );
                        binding.direct.setText ( realtime.getDirect () );
                        binding.humidity.setText ( realtime.getHumidity () );

                        Log.d ( "ViewModel",realtime.getAqi ()
                                +realtime.getDirect ()
                                +realtime.getHumidity ()
                                +realtime.getPower ()
                                +realtime.getTemperature ()
                                +realtime.getPower ()
                        );
                    }
                } );

                futureViewModel.getListLiveData ( city ).observe ( WeatherFragment.this, new Observer<List<Future>> () {
                    @Override
                    public void onChanged(List<Future> futures) {
                        if (futures.size () == 0){
                            Toast.makeText ( getContext (), "无法获取连接地址", Toast.LENGTH_SHORT ).show ();
                            return;
                        }
                        FutureAdapter futureAdapter=new FutureAdapter (getContext (),futures);
                        recyclerView.setAdapter ( futureAdapter );
                    }
                } );

            }
        } );


        return binding.getRoot ();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
        mViewModel = new ViewModelProvider ( this ).get ( WeatherViewModel.class );
        futureViewModel=new ViewModelProvider ( this ).get ( FutureViewModel.class );
    }

   
}