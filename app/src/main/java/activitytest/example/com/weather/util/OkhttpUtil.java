package activitytest.example.com.weather.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpUtil {
    public static final OkHttpClient client=new OkHttpClient ();

    public static void getWeather(String city, Callback callback){
        Request request=new Request .Builder ()
                .url ( "http://apis.juhe.cn/simpleWeather/query?city="+city+"&key=5bc882c40fd42335f47b4055781d0390" )
                .get ()
                .build ();
        client.newCall ( request ).enqueue ( callback );
    }

}
