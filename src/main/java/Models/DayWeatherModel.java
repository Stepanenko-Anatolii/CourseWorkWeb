package Models;

import java.util.GregorianCalendar;

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
    public DayWeatherModel(Location location, GregorianCalendar gc, double t, int h, double ws, double ap, int rc, double r, int c){
        this.location = location;
        this.calendar = gc;
        this.temperature = t;
        this.humidity = h;
        this.windSpeed = ws;
        this.atmospherePressure = ap;
        this.rainChance = rc;
        this.rainfall = r;
        this.cloudiness = c;
    }
    //Get-Set-еры:
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
}
