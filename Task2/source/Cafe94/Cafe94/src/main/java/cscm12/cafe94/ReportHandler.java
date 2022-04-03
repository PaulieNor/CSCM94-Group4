package cscm12.cafe94;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Christian Piri
 * @Version v1.5
 */

public class ReportHandler {

    //SQL queries for tblData.
    static String queryBookings = "SELECT * FROM bookingtables";
    static String queryOrders = "SELECT * FROM orders";
    static String queryMenu = "SELECT * FROM menuitems";
    static String queryCustomers = "SELECT * FROM customers";

    //SQL queries for labels.
    static String queryTS = "SELECT SUM(quantity) FROM items_ordered";
    static String queryTotalCost = "SELECT SUM(Total) from vFinanceSheet";
    static String queryMPI = "select menu_id, SUM(quantity) from items_ordered " +
            "GROUP BY menu_id ORDER BY quantity ASC";
    static String queryTopCustomer = "SELECT c.CustomerUserID FROM Customers as c " +
            "INNER JOIN ( " +
            "SELECT TOP 1 " +
            "customer_id, COUNT(customer_id) AS 'value_occurrence' " +
            "FROM vMasterOrderSheet " +
            "GROUP BY customer_id " +
            "ORDER BY 'value_occurrence' DESC) as mos " +
            "ON c.CustomerReferenceNumber=mos.customer_id";

    /**
     * Collects data on ordered items.
     * @param data
     * @param con
     * @param tblData
     * @throws SQLException
     */

    public static void getBookings(Connection con, ObservableList data,
                                    TableView tblData) throws SQLException {

        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryBookings);

            while (resultSet.next()) {
                data.add(new ReportTableView(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(1)));
            }
            tblData.setItems(data);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occured (getBookings): \r\n" + e.toString());
            alert.show();
        }
    }

    /**
     * Collect data on menu.
     * @param con
     * @param data
     * @param tblData
     * @throws SQLException
     */

    public static void getDataMenu (Connection con, ObservableList data,
                                    TableView tblData) throws SQLException {

        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryMenu);

            while (resultSet.next()) {
                data.add(new ReportTableView(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6)));
            }
            tblData.setItems(data);
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (getDataMenu): \r\n" + e.toString());
            alert.show();
        }
    }

    /**
     * Collects data on customers.
     * @param con
     * @param data
     * @param tblData
     * @throws SQLException
     */
    public static void getDataCustomers (Connection con, ObservableList data,
                                    TableView tblData) throws SQLException {

        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryCustomers);

            while (resultSet.next()) {
                data.add(new ReportTableView(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6)));
            }
            tblData.setItems(data);
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (getCustomers): \r\n" + e.toString());
            alert.show();
        }
    }

    /**
     * Collects data on customer orders.
     * @param con
     * @param data
     * @param tblData
     * @throws SQLException
     */

    public static void getOrders (Connection con, ObservableList data,
                                   TableView tblData) throws SQLException {
        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryOrders);

            while (resultSet.next()) {
                data.add(new ReportTableView(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(5)));
            }
            tblData.setItems(data);
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (getOrders): \r\n" + e.toString());
            alert.show();
        }
    }

    /**
     * Collects data for top customer.
     * @param con
     * @return topCustomer
     * @throws SQLException
     */

    public static String getTopCustomer (Connection con) throws SQLException {

        String topCustomer = "";

        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryTopCustomer);

            while(resultSet.next()) {
                topCustomer += resultSet.getString(1);
            }

        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (getTopCustomer): \r\n" + e.toString());
            alert.show();
        }
        return topCustomer;
    }

    /**
     * Collects data on total number of orders.
     * @param con
     * @return
     * @throws SQLException
     */

    public static String getTotalSitting (Connection con) throws SQLException {

        String totalOrdered = "";

        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryTS);


            while (resultSet.next()) {
                totalOrdered = "";
                totalOrdered += resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (getTotalSitting): \r\n" + e.toString());
            alert.show();
        }
        return totalOrdered;
    }

    /**
     * Collect data on total income.
     * @param con
     * @return
     * @throws SQLException
     */

    public static String getTotalCost (Connection con) throws SQLException {

        String totalCost = null;

        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryTotalCost);


            while (resultSet.next()) {
                totalCost = "";
                totalCost += resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (getTotalCost): \r\n" + e.toString());
            alert.show();
        }
        return totalCost;
    }

    /**
     * Collects data on most popular items.
     * @param con
     * @return
     * @throws SQLException
     */

    public static String getMPI (Connection con) throws SQLException {

        String mostPopularItem = null;

        try {
            ResultSet resultSet = con.createStatement().executeQuery(queryMPI);


            while (resultSet.next()) {
                mostPopularItem = "";
                mostPopularItem += resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (getMPI): \r\n" + e.toString());
            alert.show();
        }
        return mostPopularItem;
    }


}
