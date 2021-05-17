package dal;

import Models.DayWeatherModel;
import Models.Location;
import Logic.WeatherService;
import Models.WeatherPoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeSet;

public class WeatherData implements WeatherDataObject{
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/weather";
    private static final String user = "root";
    private static final String password = "admin";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    @Override
    public void putWeather(DayWeatherModel weather) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);// opening database connection to MySQL server
            stmt = con.createStatement();// getting Statement object to execute query

            doPutWeather(weather, stmt);

        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch(SQLException se) {}
        }
    }

    private void doPutWeather(DayWeatherModel weather, Statement stmt) throws SQLException {
        String query1, query2;

        Location loc = weather.getLocation();
        String countr = loc.getCountry();
        GregorianCalendar calendar = weather.getCalendar();
        double temperature = weather.getTemperature();
        int humidity = weather.getHumidity();
        double windSpeed = weather.getWindSpeed();
        double atmospherePressure = weather.getAtmospherePressure();
        int rainChance = weather.getRainChance();
        double rainfall = weather.getRainfall();
        int cloudiness = weather.getCloudiness();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        query1 = "insert into weatherdate(year, month, day) values ("+year+", "+month+", "+day+");";
        stmt.executeUpdate(query1);

        String query3 = "insert into location(country) values (\""+ countr+"\");";
        stmt.executeUpdate(query3);

        query2 = "insert into weather(temperature, humidity, windSpeed, atmospherePressure, rainChance, rainfall, cloudiness) values("
                + temperature+", "+humidity+", "+windSpeed+", "+atmospherePressure+", "+rainChance+", "+rainfall+", "+cloudiness+");";
        stmt.executeUpdate(query2);
    }

    @Override
    public DayWeatherModel getWeather(GregorianCalendar date, Location location) {
        DayWeatherModel dayWeather = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);// opening database connection to MySQL server
            stmt = con.createStatement();// getting Statement object to execute query

            dayWeather = doGetWeather(date, location, stmt);

        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch(SQLException se) {}
        }
        return dayWeather;
    }

    private DayWeatherModel doGetWeather(GregorianCalendar date, Location location, Statement stmt) throws SQLException {
        DayWeatherModel dayWeather = null;

        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1;
        int year = date.get(Calendar.YEAR);

        String query1 = "select * from weatherdate where year like " + year + " and month like "+month+" and day like "+day+";";
        rs = stmt.executeQuery(query1);// executing SELECT query

        ArrayList<Integer> indexes = new ArrayList<>();
        while (rs.next()){
            int currIndex = rs.getInt("idweatherDate");
            indexes.add(currIndex);
        }
        int index = -1;
        for(int i : indexes){
            String query3 = "select * from location where idlocation like "+i+";";
            rs = stmt.executeQuery(query3);
            rs.next();
            String country = rs.getString("country");
            if(country.equals(location.getCountry())) index = i;
        }
        if(index == -1) return null;

        String query2 = "select * from weather where idweather like "+index+";";
        rs = stmt.executeQuery(query2);
        rs.next();

        GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
        double temperature = rs.getDouble("temperature");
        int humidity = rs.getInt("humidity");
        double windSpeed = rs.getDouble("windSpeed");
        double atmospherePressure = rs.getDouble("atmospherePressure");
        int rainChance = rs.getInt("rainChance");
        double rainfall = rs.getDouble("rainfall");
        int cloudiness = rs.getInt("cloudiness");

        dayWeather = new DayWeatherModel(location, calendar, temperature, humidity, windSpeed, atmospherePressure, rainChance, rainfall, cloudiness);

        return dayWeather;
    }

    @Override
    public WeatherService getAllWeather(){
        WeatherService weathers = new WeatherService();

        String query1 = "select * from weatherdate;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);// opening database connection to MySQL server
            stmt = con.createStatement();// getting Statement object to execute query
            rs = stmt.executeQuery(query1);// executing SELECT query

            TreeSet<GregorianCalendar> calendars = new TreeSet<>();
            while(rs.next()){
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                int day = rs.getInt("day");
                GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
                calendars.add(calendar);
            }

            ArrayList<Location> locations = new ArrayList<>();
            TreeSet<String> countries = new TreeSet<>();
            String query2 = "select * from location;";
            rs = stmt.executeQuery(query2);
            while(rs.next()){
                String country = rs.getString("country");
                countries.add(country);
            }
            for(String country : countries) locations.add(new Location(country));

            for(Location location : locations){
                for(GregorianCalendar gregorianCalendar: calendars){
                    DayWeatherModel dayWeather = doGetWeather(gregorianCalendar, location, stmt);
                    if(dayWeather!=null) weathers.addDayWeather(dayWeather);
                }
            }
        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch(SQLException se) {}
        }
        return weathers;
    }

    @Override
    public WeatherService getAllWeatherByLocation(Location location){
        WeatherService weathersByLocation = new WeatherService();

        WeatherService weathers = getAllWeather();

        for(WeatherPoint dayWeatherPoint : weathers.getDaysWeather().keySet()){//Set пускает данные только для одной страны!!!!!!!!!
            DayWeatherModel dayWeather = weathers.getDaysWeather().get(dayWeatherPoint);
            if(dayWeather.getLocation().getCountry().equals(location.getCountry())){
                weathersByLocation.addDayWeather(dayWeather);
            }
        }
        return weathersByLocation;
    }

}
