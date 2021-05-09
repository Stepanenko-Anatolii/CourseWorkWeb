package Logic;

import Models.DayWeatherModel;
import Models.Location;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeatherProvider {

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

    public static DayWeatherModel makeWeatherModel(Location location, String date, String t, String h, String wS, String aP, String rC, String r, String c){
        //принимает дату из формата, предоставляемого html
        GregorianCalendar calendar = getCalendarFromString(date, "-");

        double temperature = Double.parseDouble(t);
        int humidity = Integer.parseInt(h);
        double windSpeed = Double.parseDouble(wS);
        double atmospherePressure = Double.parseDouble(aP);
        int rainChance = Integer.parseInt(rC);
        double rainfall = Double.parseDouble(r);
        int cloudiness = Integer.parseInt(c);

        return new DayWeatherModel(location, calendar, temperature, humidity, windSpeed, atmospherePressure, rainChance, rainfall, cloudiness);
    }
}
