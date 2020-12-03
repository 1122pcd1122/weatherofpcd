package activitytest.example.com.weather.db.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Auto-generated: 2020-11-24 15:39:36
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Entity
public class Realtime {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String temperature;
    private String humidity;
    private String info;
    private String wid;
    private String direct;
    private String power;
    private String aqi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getTemperature() {
        return temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getHumidity() {
        return humidity;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }
    public String getWid() {
        return wid;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
    public String getDirect() {
        return direct;
    }

    public void setPower(String power) {
        this.power = power;
    }
    public String getPower() {
        return power;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
    public String getAqi() {
        return aqi;
    }

}
