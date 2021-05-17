package helpers;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHelper {

    public static String getWeekDay(GregorianCalendar calendar){
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        String result = "UNKNOWN";
        switch(weekDay){
            case Calendar.MONDAY: result = "Monday"; break;
            case Calendar.TUESDAY: result = "Tuesday"; break;
            case Calendar.WEDNESDAY: result = "Wednesday"; break;
            case Calendar.THURSDAY: result = "Thursday"; break;
            case Calendar.FRIDAY: result = "Friday"; break;
            case Calendar.SATURDAY: result = "Saturday"; break;
            case Calendar.SUNDAY: result = "Sunday"; break;
        }
        return result;
    }

    public static String getDateString(GregorianCalendar calendar){
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        String result = year + "." + month + "." + day;
        return result;
    }

    public static String getDemonstrationDateString(GregorianCalendar calendar){
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        day = (calendar.get(Calendar.DAY_OF_MONTH)>=10) ? day : ("0"+day);
        month = ((calendar.get(Calendar.MONTH) + 1)>=10) ? month : ("0"+month);

        String result = day + "." + month + "." + year;
        return result;
    }

    public static GregorianCalendar getCalendarFromString(String date, String separator){
        String[] dateArr = date.split(separator);
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        return new GregorianCalendar(year, month-1, day);
    }

}
