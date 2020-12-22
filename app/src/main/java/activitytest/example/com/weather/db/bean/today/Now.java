package activitytest.example.com.weather.db.bean.today;




import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Auto-generated: 2020-12-09 22:15:0
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Now {

    private Date obsTime;
    private String temp;
    private String feelsLike;
    private String icon;
    private String text;
    private String wind360;
    private String windDir;
    private String windScale;
    private String windSpeed;
    private String humidity;
    private String precip;
    private String pressure;
    private String vis;
    private String cloud;
    private String dew;

    public void setObsTime(Date obsTime) {


        this.obsTime = obsTime ;
    }
    public String getObsTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("hh:mm aa", Locale.ENGLISH );
        return simpleDateFormat.format ( obsTime );

    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getTemp() {
        return temp;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }
    public String getFeelsLike() {
        return feelsLike;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setWind360(String wind360) {
        this.wind360 = wind360;
    }
    public String getWind360() {
        return wind360;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }
    public String getWindDir() {
        return windDir;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }
    public String getWindScale() {
        return windScale;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
    public String getWindSpeed() {
        return windSpeed;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getHumidity() {
        return humidity;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }
    public String getPrecip() {
        return precip;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
    public String getPressure() {
        return pressure;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }
    public String getVis() {
        return vis;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }
    public String getCloud() {
        return cloud;
    }

    public void setDew(String dew) {
        this.dew = dew;
    }
    public String getDew() {
        return dew;
    }

}