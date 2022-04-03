package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

/**
 * @Author Christian Piri
 * @Version v1.5
 *
 * Database connector class.
 */

public class DBConnector extends DatabaseHandler{


    public static Connection getConnection() throws SQLException {
        Connection database = null;
        try {
            String url = "jdbc:sqlserver://cafe94.database.windows.net:1433;"
                    + "database=cafe94;"
                    + "user=adminCafe@cafe94;"
                    + "password=cscm94Group4;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
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
    }*/

}
