package dal;

import Models.DayWeatherModel;
import Models.Location;
import Logic.WeatherService;

import java.util.GregorianCalendar;

public interface WeatherDataObject {
    void putWeather(DayWeatherModel weather);
    DayWeatherModel getWeather(GregorianCalendar date, Location location);
    WeatherService getAllWeather();
    WeatherService getAllWeatherByLocation(Location location);
}
