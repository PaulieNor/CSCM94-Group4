package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * [TakeawayOrders]
 * Sub class for handling takeaway orders.
 * @author Patrick Rose
 * @version 1.0
 */
public class TakeawayOrders extends MenuController implements Initializable {
    private int waitTime;
    private int main;
    private int side;
    private int drink;
    private int takeawayCustomerID;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public TakeawayOrders(int waitTime,int main, int side, int drink, int takeawayCustomerID){
        this.waitTime = waitTime;
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.takeawayCustomerID = takeawayCustomerID;
    }


    @FXML
    private TableView<Menu> mains;
    @FXML
    private TableColumn<Menu, String> Mains;

    @FXML
    private TableView<Menu> sides;
    @FXML
    private TableColumn<Menu, String> Sides;

    @FXML
    private TableView<Menu> drinks;
    @FXML
    private TableColumn<Menu, String> Drinks;

    @Override
    public void getMainTable() {
        super.getMainTable();
    }

    @Override
    public void getSideTable() {
        super.getSideTable();
    }

    @Override
    public void getDrinkTable() {
        super.getDrinkTable();
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
    public void setDeliveryDelivered(int orderID){
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
                    "WHERE MenuItemID = '" + main + "';";
            String sql2 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = '" + side  + "';";
            String sql3 = "SELECT TimeToMake FROM MenuItems " +
                    "WHERE MenuItemID = '" + drink + "';";
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

    /**   [switchToCompleteOrderTak]
     Switches to completed Takeaway page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToFinishOrderTak(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FinishOrderTakeaway.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**   [switchToCompleteOrderTab]
     Switches to completed sit in page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToFinishOrderTab(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FinishOrderTableService.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**   [switchToOrderType]
     Switches to landing page page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToOrders(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Error1");
        getMainTable();
        getDrinkTable();
        getSideTable();
    }
}
