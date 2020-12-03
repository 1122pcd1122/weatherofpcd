package activitytest.example.com.weather.db.model;


import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Auto-generated: 2020-11-24 15:39:36
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */

@Entity
public class    Future {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String temperature;
    private String weather;
//    private Wid wid;
    private String direct;
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

//    public void setWid(Wid wid) {
//        this.wid = wid;
//    }
//    public Wid getWid() {
//        return wid;
//    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
    public String getDirect() {
        return direct;
    }

}