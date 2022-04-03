package cscm12.cafe94;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * [Orders]
 * Class for recieving and displaying orders.
 * @author Patrick Rose
 * @version 1.0
 */
public class Orders {


    public static void main(String[] args) {
    }

    public Orders(){
    }

    /**
     * [showIncomplete]
     * Gets all orders that are yet to be completed to display to the chef.
     */

    public void showIncomplete() throws IOException{
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM vMasterOrderSheet WHERE COMPLETED = 0";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery(); // What you should return.
            // Do stuff.
            while (resultSet.next()){
                // TO DO
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String sql = "SELECT Total FROM vFinanceSheet WHERE reference_number = "
                    + referenceNumber;
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            total = resultSet.getDouble("Total");

        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
