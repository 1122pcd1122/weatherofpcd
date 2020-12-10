package activitytest.example.com.weather.db.bean;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Auto-generated: 2020-12-09 15:36:11
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RootToday {

    private String code;
    private Date updateTime;
    private String fxLink;
    @SerializedName ( "now" )
    private Now now;
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

    public void setNow(Now now) {
        this.now = now;
    }
    public Now getNow() {
        return now;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }
    public Refer getRefer() {
        return refer;
    }

}