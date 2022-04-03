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

    public DeliveryOrders(int waitTime, String address, int main, int side, int drink){
        this.waitTime = waitTime;
        this.address = address;
        this.main = main;
        this.side = side;
        this.drink = drink;
    }

    /**
     * [submitDelieryOrder]
     * Adds the values of the DeliveryOrders object to the database.
     */
    public void submitDeliveryOrder(){
        DatabaseHandler handler = new DatabaseHandler();
        //Can someone help with getting the custID from the customer class
        try{
            handler.newEntry("DeliveryOrders", "mainID='" + main +
                            "', sideID='" + side +
                            "', drinkID='" + drink +
                            "', custID = '" + custID + "'",
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
                            "SET DeliveryOrderCompleted = true" +
                            "WHERE DeliveryOrderID = " + orderID + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }

    /**
     * [assignDriver]
     * Method to assign a driver to an order
     * @param orderID
     */
    public void assignDriver(int orderID){

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
            String sql1 = "SELECT TimeToMake FROM MenuItems" +
                    "WHERE MenuItemID = " + main;
            String sql2 = "SELECT TimeToMake FROM MenuItems" +
                    "WHERE MenuItemID = " + side;
            String sql3 = "SELECT TimeToMake FROM MenuItems" +
                    "WHERE MenuItemID = " + drink;
            PreparedStatement checkDatabase1 = connect.prepareStatement(sql1);
            ResultSet resultSet1 = checkDatabase1.executeQuery();
            totalWait = totalWait + resultSet1.getInt("TimeToMake");

            PreparedStatement checkDatabase2 = connect.prepareStatement(sql2);
            ResultSet resultSet2 = checkDatabase2.executeQuery();
            totalWait = totalWait + resultSet2.getInt("TimeToMake");

            PreparedStatement checkDatabase3 = connect.prepareStatement(sql3);
            ResultSet resultSet3 = checkDatabase3.executeQuery();
            totalWait = totalWait + resultSet3.getInt("TimeToMake");
        } catch (NullPointerException n) {
        System.out.println(" ");
        } catch (Exception e) {
        e.printStackTrace();
    }
        this.waitTime = totalWait;
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
}
