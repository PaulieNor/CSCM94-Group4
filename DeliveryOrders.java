package cscm12.cafe94;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * [DeliveryOrders]
 * Sub class for handling delivery orders.
 * @author Patrick Rose
 * @version 1.0
 */
public class DeliveryOrders{
    private int waitTime;
    private String address;
    private int main;
    private int side;
    private int drink;
    private int deliveryCustomerID;

    public DeliveryOrders(int waitTime, String address, int main, int side, int drink, int deliveryCustomerID){
        this.waitTime = waitTime;
        this.address = address;
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.deliveryCustomerID = deliveryCustomerID;
    }
    /**
     * [submitDeliveryOrder]
     * Adds the values of the DeliveryOrders object to the database.
     */
    public void submitDeliveryOrder(){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.newEntry("DeliveryOrders (DeliveryCustomerID, DeliveryAddress, DeliveryOrderCompleted,\n" +
                            "DeliveryMain, DeliverySide, DeliveryDrink, IsDelivered)",
                    "'"+ deliveryCustomerID + //TakeawayCustomerID value entered in the front end
                            "','" + address + "'" +//deliveryAddress
                            ",'" + 0 +   //TakeawayOrderCompleted is always 0 when entered
                            "','" + main + //TakeawayMain
                            "','" + side + //TakeawaySide
                            "','" + drink +//TakeawayDrink
                            "','" + 0 + "'",//IsDelivered is always 0 when entered
                    "Database Error. Entries may be in incorrect format.");
        }catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }
    /**
     * [markDeliveryOrderCompleted]
     * Method for the chef to mark an order that is complete.
     * @param orderID
     */
    public void markDeliveryOrderCompleted(int orderID){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE DeliveryOrders" +
                            " SET DeliveryOrderCompleted = '1'" +
                            " WHERE DeliveryOrderID = '" + orderID + "';",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
    /**
     * [assignDriver]
     * Method to assign a driver to an order
     * @param orderID
     * @param driverUserName
     */
    public void assignDriver(int orderID, String driverUserName){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE DeliveryOrders" +
                            " SET DriverAssigned = '" + driverUserName + "'" +
                            " WHERE DeliveryOrderID = '" + orderID + "';",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
    /**
     * [setDeliveryDelivered]
     * Method for a driver to mark a delivery that has been delivered
     * @param orderID
     */
    public void setDeliveryDelivered(int orderID){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE DeliveryOrders" +
                            " SET IsDelivered = '1'" +
                            " WHERE DeliveryOrderID = '" + orderID + "'",
                    "Query may be incorrectly formatted" );
        }catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
    //Getters
    public int getWaitTime(){
        return this.waitTime;
    }
    public String getAddress(){
        return this.address;
    }
    public int getMain(){
        return this.main;
    }
    public int getSide(){
        return this.side;
    }
    public int getDrink(){
        return this.drink;
    }
    public int getDeliveryCustomerID(){
        return this.deliveryCustomerID;
    }
    //Setters
    /**
     * [setDeliveryWaitTime]
     * Method to retrieve the data from the database relating to
     * the wait time for the individual items in a Delivery order
     */
    public void setDeliveryWaitTime(){
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        int totalWait = 0;
        try{
            String sql1 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = " + main;
            String sql2 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = " + side;
            String sql3 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = " + drink;
            PreparedStatement checkDatabase1 = connect.prepareStatement(sql1);
            ResultSet resultSet1 = checkDatabase1.executeQuery();
            resultSet1.next();
            totalWait = totalWait + resultSet1.getInt("TimeToMake");

            PreparedStatement checkDatabase2 = connect.prepareStatement(sql2);
            ResultSet resultSet2 = checkDatabase2.executeQuery();
            resultSet2.next();
            totalWait = totalWait + resultSet2.getInt("TimeToMake");

            PreparedStatement checkDatabase3 = connect.prepareStatement(sql3);
            ResultSet resultSet3 = checkDatabase3.executeQuery();
            resultSet3.next();
            totalWait = totalWait + resultSet3.getInt("TimeToMake");
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.waitTime = totalWait + 15;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setMain(int main){
        this.main = main;
    }
    public void setSide(int side){
        this.side = side;
    }
    public void setDrink(int drink){
        this.drink = drink;
    }
    public void setDeliveryCustomerID(int deliveryCustomerID){
        this.deliveryCustomerID = deliveryCustomerID;
    }
}
