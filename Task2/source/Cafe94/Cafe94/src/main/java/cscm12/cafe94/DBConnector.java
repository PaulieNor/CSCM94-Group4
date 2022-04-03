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


    private String placeholder;

    public static Connection getConnection() throws SQLException {
        return database();
        }
    }

}
