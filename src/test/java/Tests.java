import model.WeatherApi;
import org.junit.Test;

public class Tests {
    @Test
    public void checkGson(){
        String city = "";
        try {
            city = WeatherApi.getWeather("Moscow");
        } catch (Exception e ){
            e.getMessage();
        }
        System.out.println(city);
    }
}
