package activitytest.example.com.weather.db.bean;


import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-12-09 15:39:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Root7D {

    private String code;
    private Date updateTime;
    private String fxLink;
    @SerializedName ( "daily" )
    private List<Daily> daily;
    private Refer refer;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }
    public String getFxLink() {
        return fxLink;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }
    public List<Daily> getDaily() {
        return daily;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }
    public Refer getRefer() {
        return refer;
    }

}