package activitytest.example.com.weather.db.Dao;

import java.util.List;

import activitytest.example.com.weather.db.model.Future;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FutureDao {

    @Query ( "SELECT * FROM FUTURE" )
    LiveData<List<Future>> getFutures();


    @Query ( "DELETE FROM Future" )
    void deleteAllFuture();

    @Insert
    void insertFutures(Future...futures);
}
