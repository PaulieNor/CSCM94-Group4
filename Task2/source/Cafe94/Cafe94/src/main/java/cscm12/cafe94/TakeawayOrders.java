package cscm12.cafe94;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * [TakeawayOrders]
 * Sub class for handling takeaway orders.
 * @author Patrick Rose
 * @version 1.0
 */
public class TakeawayOrders{
    private int waitTime;
    private int main;
    private int side;
    private int drink;
    private int takeawayCustomerID;

    public TakeawayOrders(int waitTime,int main, int side, int drink, int takeawayCustomerID){
        this.waitTime = waitTime;
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.takeawayCustomerID = takeawayCustomerID;
    }
    public static void main(String[] args){

    }
    /**
     * [submitTakeawayOrder]
     * Adds the values of the TakeawayOrders object to the database.
     */
    public void submitTakeawayOrder(){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.newEntry("TakeawayOrders (TakeawayCustomerID, PickUpTime, TakeawayOrderCompleted,\n" +
                            "TakeawayMain, TakeawaySide, TakeawayDrink, IsCollected)",
                            "'"+ takeawayCustomerID + //TakeawayCustomerID
                            "','2022-02-05 13:00:00'" +//PickUpTime
                            ",'" + 0 +   //TakeawayOrderCompleted Always 0 when entered
                            "','" + main + //TakeawayMain
                            "','" + side + //TakeawaySide
                            "','" + drink +//TakeawayDrink
                            "','0'",   //IsCollected Always 0 when entered
                            "Database Error. Entries may be in incorrect format.");
        }catch (NullPointerException e){
            System.out.println("A field is empty.");
        }
    }
    /**
     * [markTakeawayOrderCompleted]
     * Method for the chef to mark an order that is complete.
     * @param orderID
     */
    public void markTakeawayOrderCompleted(int orderID){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE TakeawayOrders " +
                            "SET TakeawayOrderCompleted = true" +
                            " WHERE TakeawayOrderID = " + orderID + ";",
                    "Query may be incorrectly formatted");
        } catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
    /**
     * [setTakeAwayCollected]
     * Method for a waiter to mark a takeaway that has been collected
     * @param orderID
     */
    public static void setDeliveryDelivered(int orderID){
        DatabaseHandler handler = new DatabaseHandler();
        try{
            handler.tableUpdater("UPDATE TakeawayOrders" +
                            " SET IsCollected = '1'" +
                            " WHERE TakeawayOrderID = '" + orderID + "'",
                    "Query may be incorrectly formatted" );
        }catch (NullPointerException e){
            System.out.println("A field is empty");
        }
    }
    //Getters
    public int getMain(){
        return this.main;
    }
    public int getSide(){
        return this.side;
    }
    public int getDrink(){
        return this.drink;
    }
    public int getWaitTime(){
        return this.waitTime;
    }
    public int getTakeawayCustomerID(){
        return this.takeawayCustomerID;
    }
    //Setters
    /**
     * [setTakeawayWaitTime]
     * Method to retrieve the data from the database relating to
     * the wait time for the individual items in a Delivery order
     */
    public void setTakeawayWaitTime(){
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        int totalWait = 0;
        try{
            String sql1 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = '" + main + "';";//main;
            String sql2 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = '" + side  + "';";//side;
            String sql3 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = '" + drink + "';";//drink;
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
        this.waitTime = totalWait;
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
    public void setTakeawayCustomerID(int takeawayCustomerID) {
        this.takeawayCustomerID = takeawayCustomerID;
    }
}
