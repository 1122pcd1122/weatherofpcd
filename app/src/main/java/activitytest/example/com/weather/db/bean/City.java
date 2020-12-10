package activitytest.example.com.weather.db.bean;

import org.jetbrains.annotations.NotNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City {

    @ColumnInfo(name ="Adm1")
    public String adm1;

    @ColumnInfo(name = "CityName")
    public String cityName;

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "CityID")
    public int cityID = 0;


    @ColumnInfo(name = "Adm2")
    public String adm2;


    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAdm1() {
        return adm1;
    }

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }

    public String getAdm2() {
        return adm2;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }
}
