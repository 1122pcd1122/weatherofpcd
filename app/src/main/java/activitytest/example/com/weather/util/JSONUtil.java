package activitytest.example.com.weather.util;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.JsonRootBean;
import activitytest.example.com.weather.db.model.Realtime;
import androidx.lifecycle.LiveData;

public class JSONUtil {

    public static JsonRootBean getWeather(String s){
        Gson gson=new Gson ();
        return gson.fromJson ( s,JsonRootBean.class );
    }


}

