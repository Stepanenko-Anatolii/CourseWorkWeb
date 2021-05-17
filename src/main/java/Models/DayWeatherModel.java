package Models;

import java.util.GregorianCalendar;

import static helpers.DateHelper.getCalendarFromString;

public class DayWeatherModel {
    //Параметры:
    private Location location;
    private GregorianCalendar calendar;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private double atmospherePressure;
    private int rainChance;
    private double rainfall;
    private int cloudiness;
    //Конструктор:
    public DayWeatherModel(Location location,
                           GregorianCalendar calendar, double temperature,
                           int humidity, double windSpeed, double atmospherePressure,
                           int rainChance, double rainfall, int cloudiness){
        this.location = location;
        this.calendar = calendar;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.atmospherePressure = atmospherePressure;
        this.rainChance = rainChance;
        this.rainfall = rainfall;
        this.cloudiness = cloudiness;
    }
    //Get/Set-еры:
    public GregorianCalendar getCalendar() {
        return calendar;
    }
    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getAtmospherePressure() {
        return atmospherePressure;
    }
    public void setAtmospherePressure(double atmospherePressure) {
        this.atmospherePressure = atmospherePressure;
    }

    public int getRainChance() {
        return rainChance;
    }
    public void setRainChance(int rainChance) {
        this.rainChance = rainChance;
    }

    public double getRainfall() {
        return rainfall;
    }
    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public int getCloudiness() {
        return cloudiness;
    }
    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }


    public static DayWeatherModel makeWeatherModel(Location location, String date, String t, String h, String wS, String aP, String rC, String r, String c){
        //принимает дату из формата, предоставляемого html
        GregorianCalendar calendar = getCalendarFromString(date, "-");

        try{
            double temperature = Double.parseDouble(t);
            int humidity = Integer.parseInt(h);
            double windSpeed = Double.parseDouble(wS);
            double atmospherePressure = Double.parseDouble(aP);
            int rainChance = Integer.parseInt(rC);
            double rainfall = Double.parseDouble(r);
            int cloudiness = Integer.parseInt(c);

            return new DayWeatherModel(location, calendar, temperature, humidity, windSpeed, atmospherePressure, rainChance, rainfall, cloudiness);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return null;
    }
}
