package activitytest.example.com.weather.db.bean.comfort;



import java.util.Date;
import java.util.List;

import activitytest.example.com.weather.db.bean.Refer;

/**
 * Auto-generated: 2020-12-13 12:6:11
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RootComfort {

    private String code;
    private Date updateTime;
    private String fxLink;
    private List<ComfortNow> daily;
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

    public void setComfortNow(List<ComfortNow> daily) {
        this.daily = daily;
    }
    public List<ComfortNow> getComfortNow() {
        return daily;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }
    public Refer getRefer() {
        return refer;
    }

}

