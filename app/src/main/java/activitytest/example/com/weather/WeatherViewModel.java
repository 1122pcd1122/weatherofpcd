package activitytest.example.com.weather;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import activitytest.example.com.weather.db.model.Realtime;
import activitytest.example.com.weather.util.JSONUtil;
import activitytest.example.com.weather.util.OkhttpUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public static MutableLiveData<Realtime> realtimeLiveData;


    public MutableLiveData<Realtime> getRealtimeLiveData(String city) {
        if (realtimeLiveData == null){
            realtimeLiveData =new MutableLiveData<> ();

        }
        setRealtimeLiveData ( city );
        return realtimeLiveData;
    }

    public void setRealtimeLiveData(String city) {
        OkhttpUtil.getWeather ( city, new Callback () {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d ( "ViewModel","获取失败" );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d ( "ViewModel","获取成功" );
                String jsonBeanEnity = Objects.requireNonNull ( response.body () ).string ();
                Log.d ( "ViewModel",  jsonBeanEnity);

                Realtime realtime = JSONUtil.getRealTime ( jsonBeanEnity );
                realtimeLiveData.postValue ( realtime );
            }
        } );
    }
}