package activitytest.example.com.weather.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpUtil {
    public static final OkHttpClient client=new OkHttpClient ();

    public static void getToday(int cityID, Callback callback){
        Request request=new Request .Builder ()
                .url ( "https://devapi.qweather.com/v7/weather/now?location="+cityID+"&key=42f589ec3d1f4fdb8c853522b5040b39" )
                .get ()
                .build ();
        client.newCall ( request ).enqueue ( callback );
    }

    public static void get7D(int cityID, Callback callback){
        Request request=new Request .Builder ()
                .url ( "https://devapi.qweather.com/v7/weather/7d?location="+cityID+"&key=42f589ec3d1f4fdb8c853522b5040b39" )
                .get ()
                .build ();
        client.newCall ( request ).enqueue ( callback );
    }

    public static void getTodayComfort(int cityID,Callback callback){
        Request request = new Request.Builder ()
                .url ( "https://devapi.qweather.com/v7/indices/1d?type=0&location="+cityID+"&key=42f589ec3d1f4fdb8c853522b5040b39" )
                .get ()
                .build ();
        client.newCall ( request ).enqueue ( callback );
    }

    public static void getCityCode(String cityName,Callback callback){
        Request request = new Request.Builder ()
                .url ( "https://geoapi.qweather.com/v2/city/lookup?location="+cityName+"&key=42f589ec3d1f4fdb8c853522b5040b39" )
                .get ()
                .build ();
        client.newCall ( request ).enqueue ( callback );
    }
}
