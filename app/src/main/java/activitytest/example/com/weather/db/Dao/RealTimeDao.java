package activitytest.example.com.weather.db.Dao;

import activitytest.example.com.weather.db.model.Realtime;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RealTimeDao {

    //获得今日天气
    @Query ( "SELECT * FROM Realtime" )
    LiveData<Realtime> getRealTime();

    @Insert
    void insertRealTime(Realtime[] realtime);



    @Query ( "DELETE FROM Realtime" )
    void deleteAllRealTime();



}
