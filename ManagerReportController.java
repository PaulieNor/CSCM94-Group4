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

public class ManagerReportController {
    @FXML
    private Label txtSQLConnection, lblSitting, lblTotalCost,
            lblPopularItem, lblTopCustomer;
    @FXML
    private Circle circleConnection;
    @FXML
    private javafx.scene.control.Button btnHomepage, btnMenu,
            btnShowItems, btnOrders, btnConnect;
    @FXML
    private TableView<ReportTableView> tblData;
    @FXML
    private TableColumn<ReportTableView, String> columnOne, columnTwo, columnThree,
    columnFour, columnFive;


    DatabaseHandler handler = new DatabaseHandler();
    Connection con = DatabaseHandler.database();

    ObservableList<ReportTableView> data = FXCollections.observableArrayList();

    /**
     * Connects to database and activates all buttons.
     * @throws SQLException
     */
    @FXML
    protected void connectSQL () throws SQLException{

        try {
            con = DatabaseHandler.database();
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
    protected void showItemsOrdered(ActionEvent e) throws SQLException {
        columnOne.setText("ID");
        columnTwo.setText("Menu ID");
        columnThree.setText("Order ID");
        columnFour.setText("Customer ID");
        columnFive.setText("Quantity");

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableOne();
        ReportHandler.getDataOrderedItems(con, data, tblData);
    }

    /**
     * Displays data on customer orders.
     * @param e
     * @throws SQLException
     */

    @FXML
    protected void showOrders(ActionEvent e) throws SQLException {
        columnOne.setText("Order ID");
        columnTwo.setText("Customer ID");
        columnThree.setText("Type");
        columnFour.setText("Address");
        columnFive.setText("Driver Number");

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableOne();
        ReportHandler.getOrders(con, data, tblData);
    }

    /**
     * Displays data on restaurant menu.
     * @param e
     * @throws SQLException
     */

    @FXML
    protected void showMenu (ActionEvent e) throws SQLException {
        columnOne.setText("Menu ID");
        columnTwo.setText("Item Cost (£)");
        columnThree.setText("Item Wait Time");
        columnFour.setText(null);
        columnFive.setText(null);

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTableTwo();
        ReportHandler.getDataMenu(con, data, tblData);
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

        btnShowItems.setDisable(false);
        btnMenu.setDisable(false);
        btnOrders.setDisable(false);

    }

    /**
     * Assigns columns value for data to points at (5 columns).
     */
    private void setCellTableOne () {

        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("columnTwo"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("columnThree"));
        columnFour.setCellValueFactory(new PropertyValueFactory<>("columnFour"));
        columnFive.setCellValueFactory(new PropertyValueFactory<>("columnFive"));

        columnOne.setSortable(false);
        columnTwo.setSortable(false);
        columnThree.setSortable(false);
        columnFour.setSortable(false);
        columnFive.setSortable(false);

    }

    /**
     * Assigns columns value for data to points at (3 columns).
     */
    private void setCellTableTwo () {

        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("columnTwo"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("columnThree"));

        columnFour.setCellValueFactory(null);
        columnFive.setCellValueFactory(null);

        columnOne.setSortable(false);
        columnTwo.setSortable(false);
        columnThree.setSortable(false);
        columnFour.setSortable(false);
        columnFive.setSortable(false);

    }

}