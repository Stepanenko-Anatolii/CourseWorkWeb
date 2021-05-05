package dal;

import Models.DayWeatherModel;
import Models.Location;
import Models.WeatherModel;

import java.util.GregorianCalendar;

public interface WeatherDataObject {
    void putWeather(DayWeatherModel weather);
    DayWeatherModel getWeather(GregorianCalendar date);
    WeatherModel getAllWeather();
    WeatherModel getAllWeatherByLocation(Location location);
}
