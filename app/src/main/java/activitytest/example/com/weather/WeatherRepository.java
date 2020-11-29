package activitytest.example.com.weather;

import android.content.Context;
import android.icu.lang.UCharacter;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.JsonRootBean;
import activitytest.example.com.weather.db.model.Realtime;
import activitytest.example.com.weather.util.JSONUtil;
import activitytest.example.com.weather.util.OkhttpUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherRepository {

    public static WeatherRepository repository;

    private static MutableLiveData<Realtime> realtimeMutableLiveData;
    private static MutableLiveData<List<Future>> futuresMutableLivaData;
    private static MutableLiveData<String> statusInfoMutableLiveData;

    private static final String TAG="WeatherRepository";

    public static WeatherRepository getRepository(){
        if (repository == null){
            repository = new WeatherRepository ();
            realtimeMutableLiveData = new MutableLiveData<> ();
            futuresMutableLivaData = new MutableLiveData<> ();
            statusInfoMutableLiveData = new MutableLiveData<> ();
        }

        return repository;
    }



    public void getWeatherNetWork(String city){
        OkhttpUtil.getWeather ( city, new Callback () {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d ( TAG,"网络连接失败" );

                statusInfoMutableLiveData.postValue ( "网络连接失败" );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d ( TAG,"网络连接成功" );
                String s = Objects.requireNonNull ( response.body () ).string ();
                JsonRootBean weather = JSONUtil.getWeather ( s );

                statusInfoMutableLiveData.postValue (weather.getReason ());

                if (weather.getError_code () == 0){
                    Realtime realtime = weather.getResult ().getRealtime ();
                    realtimeMutableLiveData.postValue ( realtime );
                    List<Future> futureList = weather.getResult ().getFuture ();
                    futuresMutableLivaData.postValue ( futureList );
                }


            }
        } );
    }

    /**
     * @return 获取今日天气
     */
    public LiveData<Realtime> getRealtime(){

        return realtimeMutableLiveData;
    }

    /**
     * @return 获取未来五天的天气
     */
    public LiveData<List<Future>> getFutures(){

        return futuresMutableLivaData;
    }

    /**
     * @return 获取状态码
     */
    public LiveData<String> getStatus_Code(){
        return statusInfoMutableLiveData;
    }


}
