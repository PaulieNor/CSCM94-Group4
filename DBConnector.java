package src.main.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

/**
 * @Author Christian Piri
 * @Version v1.3
 */

public class DBConnector {

    public static Connection getConnection() throws SQLException {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/Cafe94";
        Connection con = DriverManager.getConnection(url, username, password);

        return con;
    }

}
