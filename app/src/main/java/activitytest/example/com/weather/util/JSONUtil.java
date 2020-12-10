package activitytest.example.com.weather.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import activitytest.example.com.weather.db.bean.Root7D;
import activitytest.example.com.weather.db.bean.RootToday;

public class JSONUtil {

    public static RootToday getRootToDay(String s){
        Gson gson=new Gson ();
        return gson.fromJson ( s, RootToday.class );
    }

    public static Root7D getRoot7D(String s){
        Gson gson = new Gson ();
        return gson.fromJson ( s,Root7D.class);
    }

}

