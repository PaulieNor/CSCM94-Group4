package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * [Orders]
 * Class for receiving and displaying orders.
 * @author Patrick Rose
 * @version 1.0
 */
public class Orders {


    public Orders(int main, int side, int drink, String order_type, int reference_number) {
    }

    public static void main(String[] args) {
    }

    public Orders(){
    }

    /**
     * [showIncomplete]
     * Gets all orders that are yet to be completed to display to the chef.
     */

    public ObservableList<Orders> showIncomplete() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        ObservableList<Orders> outstandingOrders = FXCollections.observableArrayList();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM vMasterOrderSheet WHERE COMPLETED = '0'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            Orders orders;
            while (resultSet.next()){
                orders = new Orders(resultSet.getInt("Main"),
                                    resultSet.getInt("Side"),
                                    resultSet.getInt("Drink"),
                                    resultSet.getString("order_type"),
                                    resultSet.getInt("reference_number"));
                    outstandingOrders.add(orders);
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outstandingOrders;
    }

    /**
     * [orderTotal]
     * Method to return the total price of an order.
     * @param referenceNumber
     * @return total
     */
    public double orderTotal(int referenceNumber) {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        double total = 0.0;
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT Total FROM vFinanceSheet WHERE reference_number = '"
                    + referenceNumber + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            total = resultSet.getDouble("Total");

        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
