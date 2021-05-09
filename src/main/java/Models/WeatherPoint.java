package Models;

import Logic.WeatherProvider;

import java.util.GregorianCalendar;
import java.util.Objects;

public class WeatherPoint {
    private Location location;
    //private GregorianCalendar calendar;
    private String dateString;

    public WeatherPoint(GregorianCalendar calendar, Location location){
        //this.calendar = calendar;
        this.dateString = WeatherProvider.getDateString(calendar);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public GregorianCalendar getCalendar() {
        return WeatherProvider.getCalendarFromString(dateString, "\\.");//?????????????????????????????????????????
    }
    public void setCalendar(GregorianCalendar calendar) {
        this.dateString = WeatherProvider.getDateString(calendar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherPoint that = (WeatherPoint) o;
        return location.getCountry().equals(that.getLocation().getCountry()) && (dateString.equals(that.dateString));
        //return Objects.equals(location, that.location) && Objects.equals(calendar, that.calendar);
    }

}
