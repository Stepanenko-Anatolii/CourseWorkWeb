package Models;

import helpers.DateHelper;

import java.util.GregorianCalendar;

public class WeatherPoint {
    private Location location;
    private String dateString;

    public WeatherPoint(GregorianCalendar calendar, Location location){
        this.dateString = DateHelper.getDateString(calendar);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public GregorianCalendar getCalendar() {
        return DateHelper.getCalendarFromString(dateString, "\\.");//?????????????????????????????????????????
    }
    public void setCalendar(GregorianCalendar calendar) {
        this.dateString = DateHelper.getDateString(calendar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherPoint that = (WeatherPoint) o;
        return location.getCountry().equals(that.getLocation().getCountry()) && (dateString.equals(that.dateString));
    }

}
