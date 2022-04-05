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

public class MenuController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableView<Menu> mainItems;
    @FXML
    private TableColumn<Menu, String> Mains;

    @FXML
    private TableView<Menu> sideItems;
    @FXML
    private TableColumn<Menu, String> Sides;

    @FXML
    private TableView<Menu> drinkItems;
    @FXML
    private TableColumn<Menu, String> Drinks;


    public ObservableList<Menu> getMains() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        ObservableList<Menu> mainItems = FXCollections.observableArrayList();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT ItemName FROM SimpleMenu WHERE ItemType = 'Main';";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            Menu menu;
            while (resultSet.next()) {
                menu = new Menu(resultSet.getString("ItemName"));
                mainItems.add(menu);
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainItems;
    }

    public ObservableList<Menu> getSides() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        ObservableList<Menu> sideItems = FXCollections.observableArrayList();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT ItemName FROM SimpleMenu WHERE ItemType = 'Side';";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            Menu menu;
            while (resultSet.next()) {
                menu = new Menu(resultSet.getString("ItemName"));
                sideItems.add(menu);
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sideItems;
    }

    public ObservableList<Menu> getDrinks() {
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        ObservableList<Menu> drinkItems = FXCollections.observableArrayList();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT ItemName FROM SimpleMenu WHERE ItemType = 'Drink';";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            Menu menu;
            while (resultSet.next()) {
                menu = new Menu(resultSet.getString("ItemName"));
                drinkItems.add(menu);
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drinkItems;
    }

    public void getMainTable() {
        ObservableList<Menu> mains = getMains();
        try {
            Mains.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
            mainItems.setItems(mains);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    public void getSideTable() {
        ObservableList<Menu> sides = getSides();
        try {
            Sides.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
            sideItems.setItems(sides);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    public void getDrinkTable() {
        ObservableList<Menu> drinks = getDrinks();
        try {
            Drinks.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
            drinkItems.setItems(drinks);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }
    /**
     * [switchToStaffLogin]
     * Switches to staffs login page.
     *
     * @param event is to trigger fxml swap
     */
    @FXML
    public void switchToStaffLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * [switchToAccount]
     * Switches to Account page.
     *
     * @param event is to trigger fxml swap
     */
    @FXML
    public void switchToAccounts(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Accounts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getMainTable();
        getDrinkTable();
        getSideTable();
    }
    /**   [switchToDelivery]
     Switches to Delivery Orders page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToDelivery(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Delivery.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToTableService]
     Switches to Table Service page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToTableService(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TableService.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToTakeaway]
     Switches to Takeaway page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToTakeaway(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("cscm12/cafe94/Takeaway.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToStartScreen]
     Switches to the start page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToStartScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Cafe94.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}