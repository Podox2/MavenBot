package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class WeatherApi {
    private final static String APP_WEATHER_ID = "ecf5b97597a9fcd5af1f7b370c62b477";

    public static String getWeather(String city) {
        //KWeatherModel weatherModel = getWeatherModel(city);
        WeatherModel weatherModel = getWeatherModel(city);
        return "Місто: " + weatherModel.getCityName() + "\n" +
                "Температура: " + weatherModel.getMainInfo().getTemp() + "°С\n" +
                "Вологість: " + weatherModel.getMainInfo().getHumidity() + "%\n" +
                "Опис: " + weatherModel.getWeather().get(0).getDescription() + "\n" +
                "http://openweathermap.org/img/wn/" + weatherModel.getWeather().get(0).getIcon() + ".png";

    }

    private static WeatherModel getWeatherModel(String city) {
        Gson gson = new Gson();
        WeatherModel weatherModel = null;

        try {
            weatherModel = gson.fromJson(getJson(city), WeatherModel.class);
        } catch (Exception e) {
            e.getMessage();
        }
        return weatherModel;
    }

    private static String getJson(String city) throws IOException {
        String jsonString = "";
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + APP_WEATHER_ID);
        Scanner in = new Scanner((InputStream) url.getContent());
        while (in.hasNext()) {
            jsonString += in.nextLine();
        }
        return jsonString;
    }
}
