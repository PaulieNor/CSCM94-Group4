package cscm12.cafe94;
import java.sql.*;

/**
 * @Author Christian Piri
 * @Version v1.5
 *
 * Database connector class.
 */

public class DBConnector {
    public static Connection getConnection() throws SQLException {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/cafe";
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

    public static Connection database() {
        Connection database;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CAFE94database", "root", "");
            return database;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
