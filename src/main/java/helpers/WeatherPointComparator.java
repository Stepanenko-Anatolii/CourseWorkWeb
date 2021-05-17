package helpers;

import Models.WeatherPoint;

import java.util.Comparator;
import java.util.GregorianCalendar;

public class WeatherPointComparator implements Comparator<WeatherPoint>{
    @Override
    public int compare(WeatherPoint p1, WeatherPoint p2) {

        if(p1 == p2) return 0;

        GregorianCalendar calendar1 = p1.getCalendar();//
        GregorianCalendar calendar2 = p2.getCalendar();

        String country1 = p1.getLocation().getCountry();
        String country2 = p2.getLocation().getCountry();//

        if(calendar1.before(calendar2)) return -1;
        if(calendar1.after(calendar2)) return 1;

        if(country1.equals(country2)) return 0;
        return country1.compareTo(country2);
    }

}