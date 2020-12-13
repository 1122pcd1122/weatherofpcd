package activitytest.example.com.weather;

import android.app.Application;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import activitytest.example.com.weather.db.bean.comfort.ComfortNow;
import activitytest.example.com.weather.db.bean.comfort.RootComfort;
import activitytest.example.com.weather.db.bean.seven7d.Daily;
import activitytest.example.com.weather.db.bean.today.Now;
import activitytest.example.com.weather.db.bean.seven7d.Root7D;
import activitytest.example.com.weather.db.bean.today.RootToday;
import activitytest.example.com.weather.util.JSONUtil;
import activitytest.example.com.weather.util.OkhttpUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherRepository {

    public static WeatherRepository repository;

    private static MutableLiveData<Now> nowMutableLiveData;
    private static MutableLiveData<List<Daily>> listDailyMutableLiveData;
    private static MutableLiveData<String> statusInfoMutableLiveData;
    private static MutableLiveData<List<ComfortNow>> listComfortNowMutableLiveData;

    private static final String TAG="WeatherRepository";


    public static WeatherRepository getRepository(Application application){
        if (repository == null){
            repository = new WeatherRepository ();
            nowMutableLiveData = new MutableLiveData<> ();
            listDailyMutableLiveData = new MutableLiveData<> ();
            statusInfoMutableLiveData = new MutableLiveData<> ();
            listComfortNowMutableLiveData = new MutableLiveData<> ();

        }

        return repository;
    }

    public void getTodayNetWork(int cityID){
        OkhttpUtil.getToday ( cityID, new Callback () {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d ( TAG,"网络连接失败" );

                statusInfoMutableLiveData.postValue ( "网络连接失败" );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d ( TAG,"网络连接成功" );
                String s = Objects.requireNonNull ( response.body () ).string ();

                Log.d ( TAG,"今日天气"+s );

                RootToday rootToDay = JSONUtil.getRootToDay ( s );
                String ErrorCode = rootToDay.getCode ();

                if (ErrorCode.equals ( "200" )){
                    nowMutableLiveData.postValue ( rootToDay.getNow ());
                }


                Log.d ( TAG,"状态码:"+rootToDay.getCode ()+"" );
                Log.d ( TAG,"该城市的天气预报和实况自适应网页"+rootToDay.getFxLink ()+"" );
                Log.d ( TAG,"更新时间"+rootToDay.getUpdateTime () +"");
                Log.d ( TAG,"即时天气"+rootToDay.getNow ());
                Log.d ( TAG,"天气资源"+rootToDay.getRefer ().getSources ().get ( 0 ) );
                Log.d ( TAG,"许可证"+rootToDay.getRefer ().getLicense ().get ( 0 ) );

            }
        } );
    }

    public void get7DNetWork(int cityID){
        OkhttpUtil.get7D ( cityID, new Callback () {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d ( TAG,"网络连接失败" );

                statusInfoMutableLiveData.postValue ( "网络连接失败" );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d ( TAG,"网络连接成功" );
                String s = Objects.requireNonNull ( response.body () ).string ();

                Log.d ( TAG,"七日天气"+s );

                Root7D root7D = JSONUtil.getRoot7D ( s );
                String ErrorCode = root7D.getCode ();

                if (ErrorCode.equals ( "200" )){
                    listDailyMutableLiveData.postValue ( root7D.getDaily () );
                }

                Log.d ( TAG,"状态码:"+root7D.getCode ()+"" );
                Log.d ( TAG,"该城市的天气预报和实况自适应网页"+root7D.getFxLink ()+"" );
                Log.d ( TAG,"更新时间"+root7D.getUpdateTime () +"");
                Log.d ( TAG,"即时天气"+root7D.getDaily () );
                Log.d ( TAG,"天气资源"+root7D.getRefer ().getSources ().get ( 0 ) );
                Log.d ( TAG,"许可证"+root7D.getRefer ().getLicense ().get ( 0 ) );

            }
        } );
    }

    public void getComfort(int cityID){
        OkhttpUtil.getTodayComfort ( cityID, new Callback () {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d ( TAG,"网络连接失败" );

                statusInfoMutableLiveData.postValue ( "网络连接失败" );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d ( TAG,"网络连接成功" );
                String s = Objects.requireNonNull ( response.body () ).string ();

                Log.d ( TAG,"今日舒适度:"+s );

                RootComfort rootComfort = JSONUtil.getRootComfort ( s );
                String ErrorCode = rootComfort.getCode ();

                if (ErrorCode.equals ( "200" )){
                    listComfortNowMutableLiveData.postValue ( rootComfort.getComfortNow () );
                }

                Log.d ( TAG,"状态码:"+rootComfort.getCode ()+"" );
                Log.d ( TAG,"该城市的天气预报和实况自适应网页"+rootComfort.getFxLink ()+"" );
                Log.d ( TAG,"更新时间"+rootComfort.getUpdateTime () +"");
                Log.d ( TAG,"即时天气"+rootComfort.getComfortNow () );
                Log.d ( TAG,"天气资源"+rootComfort.getRefer ().getSources ().get ( 0 ) );
                Log.d ( TAG,"许可证"+rootComfort.getRefer ().getLicense ().get ( 0 ) );
            }
        } );
    }

    /**
     * @return 获取今日天气
     */
    public LiveData<Now> getToday(){
//        if (realTimeDao.getRealTime ().getValue () != null){
//            return realTimeDao.getRealTime ();
//        }
        return nowMutableLiveData;
    }

    /**
     * @return 获取未来五天的天气
     */
    public LiveData<List<Daily>> get7D(){

//        if (Objects.requireNonNull ( futureDao.getFutures ().getValue () ).size () != 0){
//            return futureDao.getFutures ();
//        }
        return listDailyMutableLiveData;
    }

    public LiveData<List<ComfortNow>> getComfortNow(){
        return listComfortNowMutableLiveData;
    }

    /**
     * @return 获取状态码
     */
    public LiveData<String> getStatus_Code(){

        return statusInfoMutableLiveData;
    }

//    static class InsertAsyncTask extends AsyncTask<Future ,Void ,Void>{
//
//        FutureDao futureDao;
//
//        public InsertAsyncTask(FutureDao futureDao) {
//            this.futureDao = futureDao;
//        }
//
//        @Override
//        protected Void doInBackground(Future... futures) {
//            futureDao.insertFutures ( futures );
//            return null;
//        }
//    }
//
//    static class DeleteAsyncTask extends AsyncTask<Future ,Void ,Void>{
//
//        FutureDao futureDao;
//
//        public DeleteAsyncTask(FutureDao futureDao) {
//            this.futureDao = futureDao;
//        }
//
//        @Override
//        protected Void doInBackground(Future... futures) {
//            futureDao.deleteAllFuture ();
//            return null;
//        }
//    }
//
//
//    static class InsertAsyncTask1 extends AsyncTask<Realtime ,Void ,Void>{
//
//        RealTimeDao realTimeDao;
//
//        public InsertAsyncTask1(RealTimeDao realTimeDao) {
//            this.realTimeDao = realTimeDao;
//        }
//
//
//        @Override
//        protected Void doInBackground(Realtime... realtimes) {
//            realTimeDao.insertRealTime ( realtimes );
//            return null;
//        }
//    }
//
//    static class DeleteAsyncTask1 extends AsyncTask<Realtime ,Void ,Void>{
//
//        RealTimeDao realTimeDao;
//
//        public DeleteAsyncTask1(RealTimeDao realTimeDao) {
//            this.realTimeDao = realTimeDao;
//        }
//
//
//        @Override
//        protected Void doInBackground(Realtime... realtimes) {
//            realTimeDao.deleteAllRealTime ();
//            return null;
//        }
//    }




}
