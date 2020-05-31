package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherModel {
    @SerializedName("name")
    private String cityName;

    @SerializedName("main")
    private MainInfo mainInfo;
    private List<Weather> weather;

    public String getCityName() {
        return cityName;
    }

    public MainInfo getMainInfo() {
        return mainInfo;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    class MainInfo {
        private String temp;
        private Double humidity;

        public String getTemp() {
            return temp;
        }
        public Double getHumidity() {
            return humidity;
        }
    }

    class Weather {
        private String icon;
        private String description;

        public String getIcon() {
            return icon;
        }
        public String getDescription() {
            return description;
        }

    }
}
