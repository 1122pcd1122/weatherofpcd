package activitytest.example.com.weather;

import android.app.Application;

import java.util.List;

import activitytest.example.com.weather.db.bean.Daily;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class Seven7DayViewModel extends AndroidViewModel {


    public WeatherRepository weatherRepository;
    public Application application;

    public Seven7DayViewModel(@NonNull Application application) {
        super ( application );
    }



    public void getWeatherRepository(int cityID) {
        weatherRepository = WeatherRepository.getRepository (application);
        weatherRepository.get7DNetWork ( cityID );
    }


    public LiveData<List<Daily>> getDaily(){
        return weatherRepository.get7D ();
    }

    public LiveData<String> getStatusInfo(){
        return weatherRepository.getStatus_Code ();
    }
}
