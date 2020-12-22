package activitytest.example.com.weather.db.bean.city;


import java.util.List;

import activitytest.example.com.weather.db.bean.Refer;

/**
 * Auto-generated: 2020-12-13 21:13:40
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JSONRootCity {

    private String code;
    private List<Location> location;
    private Refer refer;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
    public List<Location> getLocation() {
        return location;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }
    public Refer getRefer() {
        return refer;
    }

}