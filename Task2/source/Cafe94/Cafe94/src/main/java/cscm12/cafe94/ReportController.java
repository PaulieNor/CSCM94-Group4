package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static javafx.scene.paint.Color.*;

/**
 * @Author Christian Piri
 * @Version v1.5
 */

public class ReportController {
    @FXML
    private Label txtSQLConnection, lblSitting, lblTotalCost,
            lblPopularItem, lblTopCustomer;
    @FXML
    private Circle circleConnection;
    @FXML
    private javafx.scene.control.Button btnMenu, btnShowCustomers,
            btnShowBookings, btnDelivery, btnSitDown, btnTakeaway,
            btnHomepage, btnConnect;
    @FXML
    private TableView<ReportTableView> tblData;
    @FXML
    private TableColumn<ReportTableView, String> columnOne, columnTwo, columnThree,
    columnFour, columnFive, columnSix, columnSeven, columnEight;

    Connection con = null;

    ObservableList<ReportTableView> data = FXCollections.observableArrayList();

    /**
     * Connects to database and activates all buttons.
     * @throws SQLException
     */
    @FXML
    protected void connectSQL () throws SQLException{

        try {
            con = DBConnector.getConnection();
            circleConnection.setFill(GREEN);
            txtSQLConnection.setText("SQL Database Connection: Active");
            btnConnect.setDisable(true);

            statistics();
            enableButtons();
        }
        catch (SQLException e) {
            circleConnection.setFill(ORANGE);
            txtSQLConnection.setText("SQL Database Connection: Failed");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("An error has occcured (Connector): \r\n" + e.toString());
            alert.show();
        }
    }

    /**
     * Displays data on items ordered.
     * @param e
     * @throws SQLException
     */

    @FXML
    protected void showBookings(ActionEvent e) throws SQLException {
        columnOne.setText("Booking ID");
        columnTwo.setText("Booking Time");
        columnThree.setText("Customer ID");
        columnFour.setText("No. of Guests");
        columnFive.setText("Table ID");

        columnSix.setText(null);
        columnSeven.setText(null);
        columnEight.setText(null);

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableFive();
        ReportHandler.getBookings(con, data, tblData);
    }

    /**
     * Displays data on customer orders.
     * @param e
     * @throws SQLException
     */

    @FXML
    protected void showDelivery(ActionEvent e) throws SQLException {
        columnOne.setText("Order ID");
        columnTwo.setText("Customer ID");
        columnThree.setText("Address");
        columnFour.setText("Order Complete");
        columnFive.setText("Delivery Main");
        columnSix.setText("Delivery Side");
        columnSeven.setText("Delivery Drink");

        columnEight.setText(null);

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableSeven();
        ReportHandler.getDelivery(con, data, tblData);
    }

    @FXML
    protected void showSitDown(ActionEvent e) throws SQLException {
        columnOne.setText("Order ID");
        columnTwo.setText("Customer ID");
        columnThree.setText("Table Number");
        columnFour.setText("Order Complete");
        columnFive.setText("Main");
        columnSix.setText("Side");
        columnSeven.setText("Drink");
        columnEight.setText("Is Server");

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableEight();
        ReportHandler.getSitDownOrders(con, data, tblData);
    }

    @FXML
    protected void showTakeaway(ActionEvent e) throws SQLException {
        columnOne.setText("Order ID");
        columnTwo.setText("Customer ID");
        columnThree.setText("Pickup Time");
        columnFour.setText("Order Complete");
        columnFive.setText("Main");
        columnSix.setText("Side");
        columnSeven.setText("Drink");
        columnEight.setText("Is Collected");

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableEight();
        ReportHandler.getTakeaway(con, data, tblData);
    }

    /**
     * Displays data on restaurant menu.
     * @param e
     * @throws SQLException
     */

    @FXML
    protected void showMenu (ActionEvent e) throws SQLException {
        columnOne.setText("Menu Item ID");
        columnTwo.setText("Item Name");
        columnThree.setText("Item Type");
        columnFour.setText("Price (£)");
        columnFive.setText("Waiting Time");
        columnSix.setText("Vegetarian");

        columnSeven.setText(null);
        columnEight.setText(null);

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableSix();
        ReportHandler.getDataMenu(con, data, tblData);
    }

    @FXML
    protected void showCustomers (ActionEvent e) throws SQLException {
        columnOne.setText("Customer Reference Number");
        columnTwo.setText("Customer ID");
        columnThree.setText("First Name");
        columnFour.setText("Surname");
        columnFive.setText("Address");
        columnSix.setText("Postcode");

        columnSeven.setText(null);
        columnEight.setText(null);

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableSix();
        ReportHandler.getDataCustomers(con, data, tblData);
    }

    @FXML
    protected void returnToHomepage (ActionEvent e) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Manager.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays information in right pane.
     * @throws SQLException
     */
    private void statistics () throws SQLException {

        lblTotalCost.setText( "£" + ReportHandler.getTotalCost(con));
        lblSitting.setText(ReportHandler.getTotalSitting(con));
        lblTopCustomer.setText(ReportHandler.getTopCustomer(con));
        lblPopularItem.setText(ReportHandler.getMPI(con));

    }

    private void enableButtons () {

        btnShowBookings.setDisable(false);
        btnMenu.setDisable(false);
        btnDelivery.setDisable(false);
        btnSitDown.setDisable(false);
        btnTakeaway.setDisable(false);
        btnShowCustomers.setDisable(false);

    }

    //BELOW Code for setting Table View columns

    /**
     * Assigns columns value for data to points at (8 columns).
     */
    private void setCellTableEight () {

        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("columnTwo"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("columnThree"));
        columnFour.setCellValueFactory(new PropertyValueFactory<>("columnFour"));
        columnFive.setCellValueFactory(new PropertyValueFactory<>("columnFive"));
        columnSix.setCellValueFactory(new PropertyValueFactory<>("columnSix"));
        columnSeven.setCellValueFactory(new PropertyValueFactory<>("columnSeven"));
        columnEight.setCellValueFactory(new PropertyValueFactory<>("columnEight"));

        columnOne.setSortable(false);
        columnTwo.setSortable(false);
        columnThree.setSortable(false);
        columnFour.setSortable(false);
        columnFive.setSortable(false);
        columnSix.setSortable(false);
        columnSeven.setSortable(false);
        columnEight.setSortable(false);

    }

    /**
     * Assigns columns value for data to points at (7 columns).
     */
    private void setCellTableSeven () {

        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("columnTwo"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("columnThree"));
        columnFour.setCellValueFactory(new PropertyValueFactory<>("columnFour"));
        columnFive.setCellValueFactory(new PropertyValueFactory<>("columnFive"));
        columnSix.setCellValueFactory(new PropertyValueFactory<>("columnSix"));
        columnSeven.setCellValueFactory(new PropertyValueFactory<>("columnSeven"));

        columnEight.setCellValueFactory(null);

        columnOne.setSortable(false);
        columnTwo.setSortable(false);
        columnThree.setSortable(false);
        columnFour.setSortable(false);
        columnFive.setSortable(false);
        columnSix.setSortable(false);
        columnSeven.setSortable(false);

    }

    /**
     * Assigns columns value for data to points at (6 columns).
     */
    private void setCellTableSix () {

        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("columnTwo"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("columnThree"));
        columnFour.setCellValueFactory(new PropertyValueFactory<>("columnFour"));
        columnFive.setCellValueFactory(new PropertyValueFactory<>("columnFive"));
        columnSix.setCellValueFactory(new PropertyValueFactory<>("columnSix"));

        columnSeven.setCellValueFactory(null);
        columnEight.setCellValueFactory(null);

        columnOne.setSortable(false);
        columnTwo.setSortable(false);
        columnThree.setSortable(false);
        columnFour.setSortable(false);
        columnFive.setSortable(false);
        columnSix.setSortable(false);

    }

    /**
     * Assigns columns value for data to points at (5 columns).
     */
    private void setCellTableFive () {

        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("columnTwo"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("columnThree"));
        columnFour.setCellValueFactory(new PropertyValueFactory<>("columnFour"));
        columnFive.setCellValueFactory(new PropertyValueFactory<>("columnFive"));

        columnSix.setCellValueFactory(null);
        columnSeven.setCellValueFactory(null);
        columnEight.setCellValueFactory(null);

        columnOne.setSortable(false);
        columnTwo.setSortable(false);
        columnThree.setSortable(false);
        columnFour.setSortable(false);
        columnFive.setSortable(false);
        columnSix.setSortable(false);

    }

}