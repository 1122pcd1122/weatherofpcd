package activitytest.example.com.weather;

import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.util.JSONUtil;
import activitytest.example.com.weather.util.OkhttpUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FutureViewModel extends ViewModel {

    public  MutableLiveData<List<Future>> listLiveData;

    public  MutableLiveData<List<Future>> getListLiveData(String city){
        if (listLiveData == null){
            listLiveData = new MutableLiveData<> ();
        }
        getFuture ( city );
        return listLiveData;
    }

    public void getFuture(String city){

        OkhttpUtil.getWeather ( city, new Callback () {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d ( "ViewModel","获取失败" );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d ( "ViewModel","获取成功" );
                List<Future> futureList = JSONUtil.getFuture ( Objects.requireNonNull ( response.body () ).string () );

                listLiveData.postValue ( futureList );
            }
        } );


    }
}
