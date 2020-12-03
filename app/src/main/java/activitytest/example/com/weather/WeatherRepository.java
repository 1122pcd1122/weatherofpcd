package activitytest.example.com.weather;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.icu.lang.UCharacter;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import activitytest.example.com.weather.db.Dao.FutureDao;
import activitytest.example.com.weather.db.Dao.RealTimeDao;
import activitytest.example.com.weather.db.WeatherDB;
import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.JsonRootBean;
import activitytest.example.com.weather.db.model.Realtime;
import activitytest.example.com.weather.util.JSONUtil;
import activitytest.example.com.weather.util.OkhttpUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherRepository {

    public static WeatherRepository repository;

    private static MutableLiveData<Realtime> realtimeMutableLiveData;
    private static MutableLiveData<List<Future>> futuresMutableLivaData;
    private static MutableLiveData<String> statusInfoMutableLiveData;



    private static final String TAG="WeatherRepository";

    public static FutureDao futureDao;
    public static RealTimeDao realTimeDao;


    public static WeatherRepository getRepository(Application application){
        if (repository == null){
            repository = new WeatherRepository ();
            realtimeMutableLiveData = new MutableLiveData<> ();
            futuresMutableLivaData = new MutableLiveData<> ();
            statusInfoMutableLiveData = new MutableLiveData<> ();
            WeatherDB weatherDB = WeatherDB.getDatabase ( application.getApplicationContext () );
            futureDao = weatherDB.getFutureDao ();
            realTimeDao = weatherDB.getRealTimeDao ();
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
                    new DeleteAsyncTask1 ( realTimeDao ).execute ();
                    new InsertAsyncTask1 ( realTimeDao ).execute ( realtime );
                    List<Future> futureList = weather.getResult ().getFuture ();
                    futuresMutableLivaData.postValue ( futureList );
                    new DeleteAsyncTask ( futureDao ).execute (  );
                    new InsertAsyncTask ( futureDao ).execute (  futureList.toArray ( new Future[0] ) );
                }


            }
        } );
    }

    /**
     * @return 获取今日天气
     */
    public LiveData<Realtime> getRealtime(){
//        if (realTimeDao.getRealTime ().getValue () != null){
//            return realTimeDao.getRealTime ();
//        }
        return realtimeMutableLiveData;
    }

    /**
     * @return 获取未来五天的天气
     */
    public LiveData<List<Future>> getFutures(){

//        if (Objects.requireNonNull ( futureDao.getFutures ().getValue () ).size () != 0){
//            return futureDao.getFutures ();
//        }
        return futuresMutableLivaData;
    }

    /**
     * @return 获取状态码
     */
    public LiveData<String> getStatus_Code(){

        return statusInfoMutableLiveData;
    }

    static class InsertAsyncTask extends AsyncTask<Future ,Void ,Void>{

        FutureDao futureDao;

        public InsertAsyncTask(FutureDao futureDao) {
            this.futureDao = futureDao;
        }

        @Override
        protected Void doInBackground(Future... futures) {
            futureDao.insertFutures ( futures );
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Future ,Void ,Void>{

        FutureDao futureDao;

        public DeleteAsyncTask(FutureDao futureDao) {
            this.futureDao = futureDao;
        }

        @Override
        protected Void doInBackground(Future... futures) {
            futureDao.deleteAllFuture ();
            return null;
        }
    }


    static class InsertAsyncTask1 extends AsyncTask<Realtime ,Void ,Void>{

        RealTimeDao realTimeDao;

        public InsertAsyncTask1(RealTimeDao realTimeDao) {
            this.realTimeDao = realTimeDao;
        }


        @Override
        protected Void doInBackground(Realtime... realtimes) {
            realTimeDao.insertRealTime ( realtimes );
            return null;
        }
    }

    static class DeleteAsyncTask1 extends AsyncTask<Realtime ,Void ,Void>{

        RealTimeDao realTimeDao;

        public DeleteAsyncTask1(RealTimeDao realTimeDao) {
            this.realTimeDao = realTimeDao;
        }


        @Override
        protected Void doInBackground(Realtime... realtimes) {
            realTimeDao.deleteAllRealTime ();
            return null;
        }
    }




}
