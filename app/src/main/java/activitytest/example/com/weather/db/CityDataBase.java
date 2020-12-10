package activitytest.example.com.weather.db;

import android.content.Context;

import activitytest.example.com.weather.db.Dao.CityDao;
import activitytest.example.com.weather.db.bean.City;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database ( entities = {City.class} ,version = 2 ,exportSchema = false)
public abstract class CityDataBase extends RoomDatabase {

    public static CityDataBase cityDataBase;
    public static CityDataBase getCitDataBase(Context context){
        if (cityDataBase == null){
            cityDataBase = Room.databaseBuilder ( context ,CityDataBase.class,"CityDataBase").addMigrations ( MIGRATION_1_2 ).allowMainThreadQueries ().build ();
        }
        return cityDataBase;
    }

    public abstract CityDao getCityDao();

    static final Migration MIGRATION_1_2 = new Migration (1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL ( "DROP TABLE City" );
            database.execSQL ( "CREATE TABLE \"City\" (\n" +
                    "\t\"CityID\"\tINTEGER NOT NULL,\n" +
                    "\t\"cityName\"\tTEXT,\n" +
                    "\t\"Adm1\"\tTEXT,\n" +
                    "\t\"Adm2\"\tTEXT,\n" +
                    "\tPRIMARY KEY(\"CityID\")\n" +
                    ")" );
        }
    };

}
