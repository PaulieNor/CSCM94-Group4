package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** Handles food and menu related database calls.
 * @author Paul Norman
 * @version 1.0
 */
public class KitchenHandler extends DatabaseHandler {

    /**
     * Sets new special and reverts any current specials to non-special.
     * @param newSpecial The ItemName of new special.
     */
    public void changeSpecial(int newSpecial){
        try {
            String query = "UPDATE MenuItems SET isSpecial=0 WHERE isSpecial= 1";
            tableUpdater(query, "Database key error.");
            query = "UPDATE MenuItems SET isSpecial=1 WHERE MenuItemID=" + newSpecial;
            tableUpdater(query, "Database key error.");
        } catch (Exception e){
            System.out.println("Something went wrong");
        }
    }

    /**
     * Fetches <code>ObservableList</code> of food items which need to be prepared by the chef.
     * @return All active food tickets.
     */
    public ObservableList<KitchenTicket> getTickets() {
        ObservableList<KitchenTicket> ticketList = FXCollections.observableArrayList();
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            String query = "SELECT * from vKitchenTickets";
            PreparedStatement checkDatabase = connect.prepareStatement(query);
            ResultSet resultSet = checkDatabase.executeQuery();
            KitchenTicket ticket = null;

            while (resultSet.next()) {
                ticket = new KitchenTicket(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        false);
                ticketList.add(ticket);
            }
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    /** Finishes a kitchen ticket.
     * @param orderID
     * @param orderType
     */
    public void finishCook(int orderID, String orderType){
        String id = String.valueOf(orderID);
        try {
            if (orderType.toLowerCase().contains("delivery")) {
                editEntry("DeliveryOrders", "DeliveryOrderID", id,
                        "DeliveryOrderCompleted=1", "Value error.");
            } else if (orderType.toLowerCase().contains("in house")) {
                editEntry("SitDownOrders", "SitDownOrderID", id,
                        "SitDownCompletedOrder=1", "Value error.");
            } else if (orderType.toLowerCase().contains("takeaway")){
                editEntry("TakeawayOrders", "TakeawayOrderID", id,
                        "TakeawayOrderCompleted=1", "Value error.");
            } else {
                System.out.println("Bad orderType input. Delivery, In house, and Takeaway are only valid options.");
                return;
            }
        } catch (Exception e){
            System.out.println("Something went wrong");
        }
    }
}
