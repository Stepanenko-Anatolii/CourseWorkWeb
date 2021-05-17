package Logic;

import helpers.WeatherPointComparator;
import Models.DayWeatherModel;
import Models.Location;
import Models.WeatherPoint;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;


public class WeatherService {
    private TreeMap<WeatherPoint, DayWeatherModel> daysWeather = new TreeMap<WeatherPoint, DayWeatherModel>(new WeatherPointComparator());

    public WeatherService(){}

    public TreeMap<WeatherPoint, DayWeatherModel> getDaysWeather(){return this.daysWeather;}

    public  DayWeatherModel getDayWeatherModel(WeatherPoint weatherPoint){
        return daysWeather.get(weatherPoint);
    }

    public TreeMap<WeatherPoint, DayWeatherModel> getWeatherByDate(GregorianCalendar calendar){
        TreeMap<WeatherPoint, DayWeatherModel> weatherByDate = new TreeMap<>();
        for(WeatherPoint weatherPoint : daysWeather.keySet()){
            if(weatherPoint.getCalendar().equals(calendar)) weatherByDate.put(weatherPoint, getDayWeatherModel(weatherPoint));
        }
        return weatherByDate;
    }

    public void addDayWeather(DayWeatherModel dwm){//
        GregorianCalendar calendar = dwm.getCalendar();
        Location location = dwm.getLocation();
        WeatherPoint weatherPoint = new WeatherPoint(calendar, location);
        this.daysWeather.put(weatherPoint, dwm);
    }

    public TreeMap<WeatherPoint, DayWeatherModel> getDaysWeatherWeek(){
        TreeMap<WeatherPoint, DayWeatherModel> daysWeatherWeek = new TreeMap<WeatherPoint, DayWeatherModel>(new WeatherPointComparator());
        GregorianCalendar currentCalendar = new GregorianCalendar();

        for(int i=0; i<7; i++){
            daysWeatherWeek.putAll(getWeatherByDate(currentCalendar));
            currentCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysWeatherWeek;
    }

    public TreeMap<WeatherPoint, DayWeatherModel> getDaysWeatherWeekLocation(Location location){
        TreeMap<WeatherPoint, DayWeatherModel> daysWeatherWeek = new TreeMap<WeatherPoint, DayWeatherModel>(new WeatherPointComparator());
        GregorianCalendar currentCalendar = new GregorianCalendar();

        for(int i=0; i<7; i++){
            WeatherPoint weatherPoint = new WeatherPoint(currentCalendar, location);
            DayWeatherModel newDayWeather = daysWeather.get(weatherPoint);
            if(newDayWeather != null) {
                daysWeatherWeek.put(weatherPoint, newDayWeather);
            }
            currentCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysWeatherWeek;
    }

}


