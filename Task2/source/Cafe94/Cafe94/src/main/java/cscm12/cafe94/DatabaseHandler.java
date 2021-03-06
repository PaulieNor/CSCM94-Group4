package cscm12.cafe94;

/**
 * Class which handles SQL queries to the database.
 *
 * @author Paul Norman
 * @version 1.0
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class DatabaseHandler {

    /**
     * Connects to database.
     * @author Paul Norman
     * @return <code>Connection</code> object pointing to the cloud database connection.
     */


    public static Connection database() {
        try {
            String url = "jdbc:sqlserver://cafe94.database.windows.net:1433;DatabaseName=cafe94" +
                    ";user=adminCafe@cafe94;" +
                    "password=cscm94Group4;" +
                    "encrypt=false;" +
                    "trustServerCertificate=false;" +
                    "hostNameInCertificate=*.database.windows.net;" +
                    "loginTimeout=60;;";
            Connection con = DriverManager.getConnection(url);
            System.out.println("Connection established.");
            return con;
        } catch (Exception e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
            return null;
        }
    }


/**
    public static Connection database() {
        Connection database;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/localCafe", "root", "");
            return database;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/
    /**
     * Used to made edits, additions, or deletions from tables.
     * @param update A valid SQL update to the Cafe94 database.
     * @param CONSTRAINT_ERROR A unique error from violating constraint rules specific to that transaction.
     */
    public static void tableUpdater(String update, String CONSTRAINT_ERROR) {
        Connection connect = database();
        try {
            System.out.println("connecting");
            Statement statement = connect.createStatement();
            System.out.println("Statement made");
            statement.execute(update);
            System.out.println("updated");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(CONSTRAINT_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Add new entry to table.
     * @param tableName The specified table to be updated
     * @param values The values of the new entry in SQL syntax.
     * @param CONSTRAINT_ERROR A custom error from violating constraint rules specific to that transaction.
     */
    public static void newEntry(String tableName, String values, String CONSTRAINT_ERROR){
        try {
            String query = "INSERT INTO " + tableName + " VALUES (" + values + ");";
            System.out.println(query);
            tableUpdater(query, CONSTRAINT_ERROR);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Used to made edits to entries on the table.
     * @param tableName The specified table to be updated
     * @param column The column to search for the identifier.
     * @param identifier The identifier(s) of the entry to edit in SQL syntax.
     * @param values The values of the new entry in SQL syntax.
     * @param CONSTRAINT_ERROR A unique error from violating constraint rules specific to that transaction.
     */
    public static void editEntry(String tableName, String column, String identifier, String values,
                          String CONSTRAINT_ERROR) {
        try {
            String query = "UPDATE " + tableName + " SET " + values + " WHERE " +
                    column + " = '" + identifier + "'";
            System.out.println(query);
            tableUpdater(query, CONSTRAINT_ERROR);
        } catch (Exception e) {
            System.out.println("Input error.");
        }
    }

    /**
     * Used to delete entries.
     * @param tableName The specified table to be updated
     * @param column The column to search.
     * @param identifier The identifier(s) of the entry to delete.
     * @param CONSTRAINT_ERROR A unique error from violating constraint rules specific to that transaction.
     */
    public static void deleteEntry(String tableName, String column, String identifier,
                          String CONSTRAINT_ERROR) {
        try {
            String query = "DELETE FROM " + tableName + " WHERE " +
                    column + " = '" + identifier + "'";
            tableUpdater(query, CONSTRAINT_ERROR);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

        /**
         * This is a method which makes a SQL call to the database and returns the ResultSet.
         * Intended for class example use.
         * For convenience only. Any new calls used more than once should be added to this class/children.
         * @param query A valid SQL query to the Cafe94 database.
         */
        private void callSql(String query) throws IOException{
                Connection connect = database();
        try {
            Statement statement = connect.createStatement();
            String sql = query;
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery(); // What you should return.
            // Do stuff.
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
