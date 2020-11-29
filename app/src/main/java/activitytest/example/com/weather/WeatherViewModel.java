package activitytest.example.com.weather;

import android.app.Application;

import java.util.List;

import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.Realtime;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {



    public WeatherRepository weatherRepository;


    public void getWeatherRepository(String city) {
        weatherRepository = WeatherRepository.getRepository ();
        weatherRepository.getWeatherNetWork ( city );
    }

    public LiveData<Realtime> getRealTime(){

        return weatherRepository.getRealtime ();
    }

    public LiveData<String> getStatusInfo(){
        return weatherRepository.getStatus_Code ();
    }

    public LiveData<List<Future>> getFutures(){
        return weatherRepository.getFutures ();
    }
}