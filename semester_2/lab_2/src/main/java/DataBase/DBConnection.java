package DataBase;

import TableElements.Tour;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DBConnection {
    private static Connection currentConnection = null;
    private static String currentUserName = null;
    private static Random random = new Random();
    private static String role = null;

    public static String createConnection(String login, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            currentConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tour_agency",
                    login, password);
            currentUserName = login;
            currentConnection.setAutoCommit(true);
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT r.rolname as username,r1.rolname as \"role\"\n" +
                    "FROM pg_catalog.pg_roles r JOIN pg_catalog.pg_auth_members m\n" +
                    "ON (m.member = r.oid)\n" +
                    "JOIN pg_roles r1 ON (m.roleid=r1.oid)\n" +
                    "WHERE r.rolcanlogin AND r.rolname = '" + login + "'\n" +
                    "ORDER BY 1;\n";
            ResultSet loginSet = statement.executeQuery(sql);
            if (loginSet.next())
            {
                role = loginSet.getString("role");
                return role;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        currentUserName = null;
        currentConnection = null;
        return null;
    }

    public static boolean exists(String roleName)
    {
        if (currentConnection != null && roleName.equals(role))
            return true;
        else
            return false;
    }

    public static void close()
    {
        currentConnection = null;
        currentUserName = null;
        role = null;

    }

    public static List<Tour> getAllTours() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT * FROM tours";
            ResultSet curSet = statement.executeQuery(sql);
            List<Tour> tours = new LinkedList<>();
            while (curSet.next()) {
                Tour newTour = new Tour(curSet.getInt("tour_id"),       curSet.getString("tour_type"),
                                        curSet.getString("tour_name"),  curSet.getBoolean("is_hot"));
                tours.add(newTour);
            }
            return tours;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Tour> getOwnTours() {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT x.tour_id, x.tour_type, x.tour_name, x.is_hot " +
                         "FROM (tours x INNER JOIN user_buy_tour y ON x.tour_id = y.tour_id) " +
                                       "INNER JOIN users z ON y.user_id = z.user_id " +
                         "WHERE z.username = '" + currentUserName + "';";

            ResultSet curSet = statement.executeQuery(sql);
            List<Tour> tours = new LinkedList<>();
            while (curSet.next()) {
                Tour newTour = new Tour(curSet.getInt("tour_id"),       curSet.getString("tour_type"),
                        curSet.getString("tour_name"),  curSet.getBoolean("is_hot"));
                tours.add(newTour);
            }
            return tours;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void addTourToUser(int tourId) {
        try {
            Statement statement = currentConnection.createStatement();
            String sql = "SELECT users.user_id FROM users WHERE users.username='" +currentUserName + "';";
            ResultSet curSet = statement.executeQuery(sql);
            curSet.next();
            int userId = curSet.getInt("user_id");
            sql = "INSERT INTO user_buy_tour (user_id, tour_id) " +
                         "VALUES ('" + userId +"', '" + tourId + "')";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
