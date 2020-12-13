package activitytest.example.com.weather.util;

import com.google.gson.Gson;

import activitytest.example.com.weather.db.bean.comfort.RootComfort;
import activitytest.example.com.weather.db.bean.seven7d.Root7D;
import activitytest.example.com.weather.db.bean.today.RootToday;

public class JSONUtil {

    public static RootToday getRootToDay(String s){
        Gson gson=new Gson ();
        return gson.fromJson ( s, RootToday.class );
    }

    public static Root7D getRoot7D(String s){
        Gson gson = new Gson ();
        return gson.fromJson ( s,Root7D.class);
    }

    public static RootComfort getRootComfort(String s){
        Gson gson = new Gson ();
        return gson.fromJson ( s,RootComfort.class );
    }
}

