package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.User;

public class UserData implements UserDataObject{
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/weather";
    private static final String userDB = "root";
    private static final String password = "admin";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @Override
    public User getUser() {
        /*
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
        */
        return null;
    }
/*
    private User doGetUser(GregorianCalendar date, Statement stmt) throws SQLException {
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
 */

    @Override
    public void putUser(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, userDB, password);// opening database connection to MySQL server
            stmt = con.createStatement();// getting Statement object to execute query

            doPutUser(user, stmt);

        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and result set here
            try { con.close(); } catch(SQLException se) {}
            try { stmt.close(); } catch(SQLException se) {}
        }
    }

    private void doPutUser(User user, Statement stmt) throws SQLException {
        String query1;

        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();

        query1 = "insert into users(login, password, name, surname) values (\""+login+"\", \""+password+"\", \""+name+"\", \""+surname+"\");";
        stmt.executeUpdate(query1);
    }
}
