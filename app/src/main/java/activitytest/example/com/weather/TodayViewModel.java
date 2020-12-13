package activitytest.example.com.weather;

import android.app.Application;

import java.util.List;

import activitytest.example.com.weather.db.bean.comfort.ComfortNow;
import activitytest.example.com.weather.db.bean.today.Now;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TodayViewModel extends AndroidViewModel {

    public WeatherRepository weatherRepository;
    public Application application;

    public TodayViewModel(@NonNull Application application) {
        super ( application );
        this.application = application;
    }


    public void getWeatherRepository(int cityID) {
        weatherRepository = WeatherRepository.getRepository (application);
        weatherRepository.getTodayNetWork ( cityID );
        weatherRepository.getComfort ( cityID );
    }

    public LiveData<Now> getToday(){

        return weatherRepository.getToday ();
    }

    public LiveData<List<ComfortNow>> getComfortNow(){
        return weatherRepository.getComfortNow ();
    }

    public LiveData<String> getStatusInfo(){
        return weatherRepository.getStatus_Code ();
    }


}