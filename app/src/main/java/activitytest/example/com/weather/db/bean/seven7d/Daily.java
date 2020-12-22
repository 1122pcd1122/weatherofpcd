package activitytest.example.com.weather.db.bean.seven7d;


import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Auto-generated: 2020-12-09 17:10:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Daily {
    private String fxDate;

    private String sunrise;

    private String sunset;

    private String moonrise;

    private String moonset;

    private String moonPhase;

    private String tempMax;

    private String tempMin;

    private String iconDay;

    private String textDay;

    private String iconNight;

    private String textNight;

    private String wind360Day;

    private String windDirDay;

    private String windScaleDay;

    private String windSpeedDay;

    private String wind360Night;

    private String windDirNight;

    private String windScaleNight;

    private String windSpeedNight;

    private String humidity;

    private String precip;

    private String pressure;

    private String vis;

    private String cloud;

    private String uvIndex;

    public void setFxDate(String fxDate){
        this.fxDate = fxDate;
    }
    public String getFxDate()  {
        Calendar calendar = Calendar.getInstance ();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        try {
            calendar.setTime ( Objects.requireNonNull ( format.parse ( fxDate ) ) );
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        return weekDays[calendar.get ( Calendar.DAY_OF_WEEK  ) - 1];
    }
    public void setSunrise(String sunrise){
        this.sunrise = sunrise;
    }
    public String getSunrise(){
        return this.sunrise;
    }
    public void setSunset(String sunset){
        this.sunset = sunset;
    }
    public String getSunset(){
        return this.sunset;
    }
    public void setMoonrise(String moonrise){
        this.moonrise = moonrise;
    }
    public String getMoonrise(){
        return this.moonrise;
    }
    public void setMoonset(String moonset){
        this.moonset = moonset;
    }
    public String getMoonset(){
        return this.moonset;
    }
    public void setMoonPhase(String moonPhase){
        this.moonPhase = moonPhase;
    }
    public String getMoonPhase(){
        return this.moonPhase;
    }
    public void setTempMax(String tempMax){
        this.tempMax = tempMax;
    }
    public String getTempMax(){
        return this.tempMax;
    }
    public void setTempMin(String tempMin){
        this.tempMin = tempMin;
    }
    public String getTempMin(){
        return this.tempMin;
    }
    public void setIconDay(String iconDay){
        this.iconDay = iconDay;
    }
    public String getIconDay(){
        return this.iconDay;
    }
    public void setTextDay(String textDay){
        this.textDay = textDay;
    }
    public String getTextDay(){
        return this.textDay;
    }
    public void setIconNight(String iconNight){
        this.iconNight = iconNight;
    }
    public String getIconNight(){
        return this.iconNight;
    }
    public void setTextNight(String textNight){
        this.textNight = textNight;
    }
    public String getTextNight(){
        return this.textNight;
    }
    public void setWind360Day(String wind360Day){
        this.wind360Day = wind360Day;
    }
    public String getWind360Day(){
        return this.wind360Day;
    }
    public void setWindDirDay(String windDirDay){
        this.windDirDay = windDirDay;
    }
    public String getWindDirDay(){
        return this.windDirDay;
    }
    public void setWindScaleDay(String windScaleDay){
        this.windScaleDay = windScaleDay;
    }
    public String getWindScaleDay(){
        return this.windScaleDay;
    }
    public void setWindSpeedDay(String windSpeedDay){
        this.windSpeedDay = windSpeedDay;
    }
    public String getWindSpeedDay(){
        return this.windSpeedDay;
    }
    public void setWind360Night(String wind360Night){
        this.wind360Night = wind360Night;
    }
    public String getWind360Night(){
        return this.wind360Night;
    }
    public void setWindDirNight(String windDirNight){
        this.windDirNight = windDirNight;
    }
    public String getWindDirNight(){
        return this.windDirNight;
    }
    public void setWindScaleNight(String windScaleNight){
        this.windScaleNight = windScaleNight;
    }
    public String getWindScaleNight(){
        return this.windScaleNight;
    }
    public void setWindSpeedNight(String windSpeedNight){
        this.windSpeedNight = windSpeedNight;
    }
    public String getWindSpeedNight(){
        return this.windSpeedNight;
    }
    public void setHumidity(String humidity){
        this.humidity = humidity;
    }
    public String getHumidity(){
        return this.humidity;
    }
    public void setPrecip(String precip){
        this.precip = precip;
    }
    public String getPrecip(){
        return this.precip;
    }
    public void setPressure(String pressure){
        this.pressure = pressure;
    }
    public String getPressure(){
        return this.pressure;
    }
    public void setVis(String vis){
        this.vis = vis;
    }
    public String getVis(){
        return this.vis;
    }
    public void setCloud(String cloud){
        this.cloud = cloud;
    }
    public String getCloud(){
        return this.cloud;
    }
    public void setUvIndex(String uvIndex){
        this.uvIndex = uvIndex;
    }
    public String getUvIndex(){
        return this.uvIndex;
    }

}