import Models.DayWeatherModel;
import Models.Location;
import dal.WeatherData;
import dal.WeatherDataObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args){
        int quantity = 35;
        WeatherDataObject weatherDB = new WeatherData();

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("ukraine"));
        locations.add(new Location("russia"));
        locations.add(new Location("vatican"));
        locations.add(new Location("spain"));
        locations.add(new Location("italy"));


        for(Location location : locations){
            GregorianCalendar currentCalendar = new GregorianCalendar();
            for(int i = 0; i < quantity; i ++){
                DayWeatherModel newDayWeather = Randoms.createRandomWeather(currentCalendar, location);
                weatherDB.putWeather(newDayWeather);
                currentCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

    }
}
