package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/** Helps with delivery related database calls.
 * @author Paul Norman
 * @version 1.0
 */
public class DeliveryHandler {


    /**
     * Gets result table of orders assigned to a specific driver.
     * @return
     */
    public ObservableList<DriverTicket> getOrdersForDriver(String driverID) {
        ObservableList<DriverTicket> ticketList = FXCollections.observableArrayList();
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            String query = "SELECT * from vDriverTickets WHERE DriverAssigned=" + driverID;
            PreparedStatement checkDatabase = connect.prepareStatement(query);
            ResultSet resultSet = checkDatabase.executeQuery();
            DriverTicket ticket = null;

            while (resultSet.next()) {
                ticket = new DriverTicket(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                ticketList.add(ticket);
            }
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketList;
    }

}
