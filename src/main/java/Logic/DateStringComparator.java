package Logic;

import java.util.Comparator;
import java.util.GregorianCalendar;

public class DateStringComparator implements Comparator<String> {
    @Override
    public int compare(String date1, String date2) {
        if(date1.isEmpty() || date2.isEmpty()) return 0;

        GregorianCalendar calendar1 = WeatherProvider.getCalendarFromString(date1, "\\.");
        GregorianCalendar calendar2 = WeatherProvider.getCalendarFromString(date2, "\\.");

        if(calendar1.before(calendar2)) return -1;
        if(calendar1.after(calendar2)) return 1;
        return 0;
    }

}