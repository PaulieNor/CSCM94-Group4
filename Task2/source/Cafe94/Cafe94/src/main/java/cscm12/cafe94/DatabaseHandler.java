package cscm12.cafe94;

/**
 * [DatabaseHandler]
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
     * [database]
     * Connects to database.
     * @author Paul Norman
     * @return database object containing the connection.
     */
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

    /**
     * [tableUpdater]
     * Used to made edits, additions, or deletions from tables.
     * @param update A valid SQL update to the Cafe94 database.
     * @param CONSTRAINT_ERROR A unique error from violating constraint rules specific to that transaction.
     */
    private static void tableUpdater(String update, String CONSTRAINT_ERROR) {
        Connection connect = database();
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(update);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(CONSTRAINT_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * [newEntry]
     * Add new entry to table.
     * @param tableName The specified table to be updated
     * @param values The values of the new entry in SQL syntax.
     * @param CONSTRAINT_ERROR A unique error from violating constraint rules specific to that transaction.
     */
    public static void newEntry(String tableName, String values, String CONSTRAINT_ERROR){
        try {
            String query = "INSERT " + tableName + " VALUES (" + values + ")";
            tableUpdater(query, CONSTRAINT_ERROR);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * [editEntry]
     * Used to made edits to entries on the table.
     * @param tableName The specified table to be updated
     * @param values The values of the new entry in SQL syntax.
     * @param column The column to search.
     * @param identifier The identifier(s) of the entry to edit.
     * @param CONSTRAINT_ERROR A unique error from violating constraint rules specific to that transaction.
     */
    public static void editEntry(String tableName, String column, String identifier, String values,
                          String CONSTRAINT_ERROR) {
        try {
            String query = "UPDATE " + tableName + " SET VALUES (" + values + ") WHERE " +
                    column + " = '" + identifier + "'";
            tableUpdater(query, CONSTRAINT_ERROR);
        } catch (Exception e) {
            System.out.println("Input error.");
        }
    }

    /**
     * [deleteEntry]
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
         * [sqlCall]
         * This is a method which makes a SQL call to the database and returns the ResultSet.
         * Intended for class example use.
         * For convenience only. Any new calls used more than once should be added to this class/children.
         * @param query A valid SQL query to the Cafe94 database.
         */
        private void sqlCall(String query) throws IOException{
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
