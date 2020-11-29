package activitytest.example.com.weather.db.model;


import java.util.Date;

/**
 * Auto-generated: 2020-11-24 15:39:36
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class    Future {

    private Date date;
    private String temperature;
    private String weather;
    private Wid wid;
    private String direct;
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getTemperature() {
        return temperature;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    public String getWeather() {
        return weather;
    }

    public void setWid(Wid wid) {
        this.wid = wid;
    }
    public Wid getWid() {
        return wid;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
    public String getDirect() {
        return direct;
    }

}