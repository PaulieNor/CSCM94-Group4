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
 * [Orders]
 * Class for receiving and displaying orders.
 * @author Patrick Rose
 * @version 1.0
 */

public class Orders{

    private SimpleStringProperty main;
    private SimpleStringProperty side;
    private SimpleStringProperty drink;
    private SimpleStringProperty orderTypes;
    private SimpleIntegerProperty referenceNumber;

    public Orders(String main, String side, String drink, String orderTypes, int referenceNumber) {
        this.main = new SimpleStringProperty(main);
        this.side = new SimpleStringProperty(side);
        this.drink = new SimpleStringProperty(drink);
        this.orderTypes = new SimpleStringProperty(orderTypes);
        this.referenceNumber = new SimpleIntegerProperty(referenceNumber);
    }

    /**
     * [orderTotal]
     * Method to return the total price of an order.
     * @param referenceNumber
     * @param orderType
     * @return total
     */
    /**
    public static double getOrderTotal(int referenceNumber, String orderType) {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        double total = 0.0;
        try {
            String sql = "SELECT Total FROM vFinanceSheet WHERE reference_number = '"
                    + referenceNumber + "' AND order_type = '" + orderType + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            resultSet.next();
            total = resultSet.getDouble("Total");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
     */

    public String getMain() {
        return main.get();
    }

    public void setMain(String aMain) {
        main.set(aMain);
    }

    public SimpleStringProperty mainProperty() {
        return main;
    }

    public String side() {
        return side.get();
    }

    public void setSide(String aSide) {
        side.set(aSide);
    }

    public SimpleStringProperty sideProperty() {
        return side;
    }

    public String drink() {
        return drink.get();
    }

    public void setDrink(String aDrink) {
        drink.set(aDrink);
    }

    public SimpleStringProperty drinkProperty() {
        return drink;
    }

    public String orderTypes() {
        return orderTypes.get();
    }

    public void setOrderTypes(String anOrderTypes) {
        orderTypes.set(anOrderTypes);
    }

    public SimpleStringProperty orderTypesProperty() {
        return orderTypes;
    }

    public int referenceNumber() {
        return referenceNumber.get();
    }

    public void setReferenceNumber(int aReferenceNumber) {
        referenceNumber.set(aReferenceNumber);
    }

    public SimpleIntegerProperty referenceNumberProperty() {
        return referenceNumber;
    }
}

