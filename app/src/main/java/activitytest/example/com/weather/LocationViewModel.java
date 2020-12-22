package activitytest.example.com.weather;

import android.app.Application;

import java.util.List;

import activitytest.example.com.weather.db.bean.city.Location;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LocationViewModel extends AndroidViewModel {

    public Application application;
    public WeatherRepository weatherRepository;

    public LocationViewModel(@NonNull Application application) {
        super ( application );
    }

    public void getWeatherRepository(String cityName) {
        weatherRepository = WeatherRepository.getRepository (application);
        weatherRepository.getCityID ( cityName );
    }

    public LiveData<Location> getLocation(){
         return weatherRepository.getLocation ();
    }
}
