package activitytest.example.com.weather.db.Dao;

import activitytest.example.com.weather.db.bean.City;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CityDao {
    @Query ( "SELECT * FROM CITY WHERE CityName = :cityName" )
    public City getCity(String cityName);
}
