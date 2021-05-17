package dal;

import Models.DayWeatherModel;
import Models.Location;
import Models.WeatherModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
            //close connection ,stmt and result set here
            try { con.close(); } catch(SQLException se) {}
            try { stmt.close(); } catch(SQLException se) {}
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
    public DayWeatherModel getWeather(GregorianCalendar date) {
        DayWeatherModel dayWeather = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);// opening database connection to MySQL server
            stmt = con.createStatement();// getting Statement object to execute query

            dayWeather = doGetWeather(date, stmt);

        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and result set here
            try { con.close(); } catch(SQLException se) {}
            try { stmt.close(); } catch(SQLException se) {}
            try { rs.close(); } catch(SQLException se) {}
        }
        return dayWeather;
    }

    private DayWeatherModel doGetWeather(GregorianCalendar date, Statement stmt) throws SQLException {
        DayWeatherModel dayWeather = null;

        int day = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1;
        int year = date.get(Calendar.YEAR);

        String query1 = "select * from weatherdate where year like " + year + " and month like "+month+" and day like "+day+";";
        rs = stmt.executeQuery(query1);// executing SELECT query
        rs.next();
        int index = rs.getInt("idweatherDate");//-------------------------------//

        String query3 = "select * from location where idlocation like "+index+";";
        rs = stmt.executeQuery(query3);
        rs.next();
        String country = rs.getString("country");
        Location location = new Location(country);

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
    public WeatherModel getAllWeather(){
        WeatherModel weathers = new WeatherModel();

        String query1 = "select * from weatherdate;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);// opening database connection to MySQL server
            stmt = con.createStatement();// getting Statement object to execute query
            rs = stmt.executeQuery(query1);// executing SELECT query

            ArrayList<GregorianCalendar> calendars = new ArrayList<>();
            while(rs.next()){
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                int day = rs.getInt("day");
                GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
                calendars.add(calendar);
            }

            for(GregorianCalendar gregorianCalendar: calendars){
                DayWeatherModel dayWeather = doGetWeather(gregorianCalendar, stmt);//----------------------------//
                weathers.addDayWeather(dayWeather);
            }


        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and result set here
            try { con.close(); } catch(SQLException se) {}
            try { stmt.close(); } catch(SQLException se) {}
            try { rs.close(); } catch(SQLException se) {}
        }

        return weathers;
    }

    @Override
    public WeatherModel getAllWeatherByLocation(Location location){
        WeatherModel weathersByLocation = new WeatherModel();
        WeatherModel weathers = getAllWeather();
        for(String dayWeatherName : weathers.getDaysWeather().keySet()){
            DayWeatherModel dayWeather = weathers.getDaysWeather().get(dayWeatherName);
            if(dayWeather.getLocation().getCountry().equals(location.getCountry())){
                weathersByLocation.addDayWeather(dayWeather);
            }
        }

        return weathersByLocation;
    }

}
