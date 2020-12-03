package activitytest.example.com.weather.db;

import android.content.Context;

import activitytest.example.com.weather.db.Dao.FutureDao;
import activitytest.example.com.weather.db.Dao.RealTimeDao;
import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.Realtime;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database ( entities = {Realtime.class, Future.class},version = 1)
public abstract class WeatherDB extends RoomDatabase{

    private static WeatherDB INSTANCE;
    public static synchronized WeatherDB getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WeatherDB.class,"WeatherDB")
                    .build();
        }
        return INSTANCE;
    }

    //获取对Future的操作类
    public abstract FutureDao getFutureDao();
    //获取对RealTime的操作类
    public abstract RealTimeDao getRealTimeDao();
}
