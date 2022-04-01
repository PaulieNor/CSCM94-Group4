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

public class DBConnector {

    public static Connection getConnection() throws SQLException {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/cafe";
        Connection con = DriverManager.getConnection(url, username, password);

        return con;
    }

}
