package Models;

import Logic.DateStringComparator;
import Logic.WeatherProvider;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;


public class WeatherModel {
    private TreeMap<String, DayWeatherModel> daysWeather = new TreeMap<String, DayWeatherModel>(new DateStringComparator());

    public WeatherModel(){}

    public TreeMap<String, DayWeatherModel> getDaysWeather(){return this.daysWeather;}
    public  DayWeatherModel getDayWeatherModel(String date){
        return daysWeather.get(date);
    }

    public void addDayWeather(DayWeatherModel dwm){
        String dateString = WeatherProvider.getDateString(dwm.getCalendar());
        this.daysWeather.put(dateString, dwm);
        //System.out.println(dateString);
    }

    public TreeMap<String, DayWeatherModel> getDaysWeatherWeek(){
        TreeMap<String, DayWeatherModel> daysWeatherWeek = new TreeMap<String, DayWeatherModel>(new DateStringComparator());
        GregorianCalendar currentCalendar = new GregorianCalendar();

        for(int i=0; i<7; i++){
            String currentDateString = WeatherProvider.getDateString(currentCalendar);
            DayWeatherModel newDayWeather = getDayWeatherModel(currentDateString);
            if(newDayWeather != null) daysWeatherWeek.put(currentDateString, newDayWeather);
            currentCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return daysWeatherWeek;
    }

}


