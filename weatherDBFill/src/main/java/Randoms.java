import Models.DayWeatherModel;
import Models.Location;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class Randoms {
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;
    private static Random random = new Random();

    public static int randomInt(int min, int max){
        int difference = max - min;
        int rand = random.nextInt(difference + 1);
        rand += min;
        return rand;
    }

    public static String randomString(){
        int length = randomInt(4, 12);
        char[] buf = new char[length];
        for (int i = 0; i < length; ++i)
            buf[i] = alphanum.charAt(random.nextInt(62));
        return new String(buf);
    }

    public static DayWeatherModel createRandomWeather(GregorianCalendar calendar, Location location){
        double temperature = randomInt(1, 60);
        int humidity = randomInt(0, 100);
        double windSpeed = randomInt(0, 40);
        double atmospherePressure = randomInt(700, 800);
        int rainChance = randomInt(0, 100);
        double rainfall = randomInt(20, 200);
        int cloudiness = randomInt(0, 7);

        DayWeatherModel newDayWeather = new DayWeatherModel(location, calendar, temperature, humidity, windSpeed, atmospherePressure, rainChance, rainfall, cloudiness);
        return newDayWeather;
    }

}