package cscm12.cafe94;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.net.URL;
import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * [OrdersController]
 * Controlling the methods for the Orders class.
 * @author Patrick Rose
 * @version 1.1*/

public class OrdersController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Orders> outstandingOrders;
    @FXML
    private TableColumn<Orders, Number> orderRef;
    @FXML
    private TableColumn<Orders, String> orderMain;
    @FXML
    private TableColumn<Orders, String> orderSide;
    @FXML
    private TableColumn<Orders, String> orderDrink;
    @FXML
    private TableColumn<Orders, String> orderType;


    /**
     * [getOutstanding]
     * Gets all orders that are yet to be completed to display to the chef.
     */
    public ObservableList<Orders> getOutstanding() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        ObservableList<Orders> outstandingOrders =  FXCollections.observableArrayList();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM vKitchenTickets ;";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            Orders orders;
            while (resultSet.next()){
                orders = new Orders(resultSet.getString("Main"),
                        resultSet.getString("Side"),
                        resultSet.getString("Drink"),
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
     * [getOutstandingTable]
     * This is a method is used to get the extracted SQL data.
     * This is for @switchToManageStaff.*/
    public void getOutstandingTable() {
        ObservableList<Orders> order = getOutstanding();
        try {
            orderRef.setCellValueFactory(cellData -> cellData.getValue().referenceNumberProperty());
            orderMain.setCellValueFactory(cellData -> cellData.getValue().mainProperty());
            orderSide.setCellValueFactory(cellData -> cellData.getValue().sideProperty());
            orderDrink.setCellValueFactory(cellData -> cellData.getValue().drinkProperty());
            orderType.setCellValueFactory(cellData -> cellData.getValue().orderTypesProperty());
            outstandingOrders.setItems(order);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }





    /**   [switchToStaffLogin]
     Switches to staffs login page.
     @param event is to trigger fxml swap */
    @FXML
    public void switchToStaffLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**   [switchToAccount]
     Switches to Account page.
     @param event is to trigger fxml swap */
    @FXML
    public void switchToAccounts(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Accounts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @return
     */
     public void markAsComplete() {
         int index = outstandingOrders.getSelectionModel().getSelectedIndex();
         int referenceNum;
         String theOrderType;
         Orders order = outstandingOrders.getItems().get(index);
         referenceNum = order.referenceNumber();
         theOrderType = order.orderTypes();
         System.out.println(theOrderType);
         KitchenHandler handler = new KitchenHandler();
         handler.finishCook(referenceNum,theOrderType);
         getOutstandingTable();
     }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getOutstandingTable();
    }
}
