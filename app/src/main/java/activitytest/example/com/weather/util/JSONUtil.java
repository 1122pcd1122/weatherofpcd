package activitytest.example.com.weather.util;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activitytest.example.com.weather.db.model.Future;
import activitytest.example.com.weather.db.model.JsonRootBean;
import activitytest.example.com.weather.db.model.Realtime;
import androidx.lifecycle.LiveData;

public class JSONUtil {
    public static Realtime getRealTime(String s){
        Gson gson=new Gson ();
        JsonRootBean jsonRootBean= gson.fromJson ( s,JsonRootBean.class );
        if (jsonRootBean.getError_code () == 10012){
                Realtime realtime=new Realtime ();
                realtime.setInfo ( "无法获取连接地址" );
            return realtime;
        }
        return jsonRootBean.getResult ().getRealtime ();
    }

    public static List<Future> getFuture(String s){
        Gson gson=new Gson ();
        JsonRootBean jsonRootBean= gson.fromJson ( s,JsonRootBean.class );
        if (jsonRootBean.getError_code () == 10012){
            return new ArrayList<Future> ();
        }
        return jsonRootBean.getResult ().getFuture ();
    }
}

