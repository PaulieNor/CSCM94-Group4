package src.main.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import static javafx.scene.paint.Color.*;

/**
 * @Author Christian Piri
 * @Version v1.3
 */

public class ManagerReportController {
    @FXML
    private Label sqlConnectionText, cSittingText;
    @FXML
    private Circle circleConnection;
    @FXML
    private javafx.scene.control.Button btnExit, btnTodaysSitting,
            btnShowMenu, btnStaff;
    @FXML
    private TableView<CurrentMenu> tblData;
    @FXML
    private TableColumn<CurrentMenu, String> columnOne, columnTwo, columnThree,
            columnFour;

    Connection con = null;
    //SQL select queries.
    String queryCurrentMenu = "SELECT * FROM menu";
    String queryStaff = "SELECT * FROM staff";
    String queryTodaysSitting = "SELECT * FROM bookings";
    String queryTS = "SELECT * FROM total_sitting";

    ObservableList<CurrentMenu> data = FXCollections.observableArrayList();

    /**
     * Connects to database and activates all buttons.
     * @throws SQLException
     */
    @FXML
    protected void connectSQL () throws SQLException{
        con = DBConnector.getConnection();
        circleConnection.setFill(GREEN);
        sqlConnectionText.setText("SQL Database Connection: Active");

        enableButtons();
    }

    @FXML
    protected void showCurrentMenu(ActionEvent e) throws SQLException {
        columnOne.setText("Item ID");
        columnTwo.setText("Item Name");
        columnThree.setText("Item Description");
        columnFour.setText("Item Price (£)");

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTable();
        loadDataMenu();
    }

    @FXML
    protected void showStaff(ActionEvent e) throws SQLException {
        columnOne.setText("Staff ID");
        columnTwo.setText("First name");
        columnThree.setText("Surname");
        columnFour.setText("Position");

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTable();
        loadDataStaff();
    }

    @FXML
    protected void showTodaySitting(ActionEvent e) throws SQLException {
        columnOne.setText("Booking ID");
        columnTwo.setText("Customer ID");
        columnThree.setText("Date & Time");
        columnFour.setText("Table number");

        tblData.getItems().clear();
        tblData.setColumnResizePolicy(tblData.CONSTRAINED_RESIZE_POLICY);
        setCellTable();
        loadTodaySitting();
        loadDataTS();
    }

    @FXML
    protected void closeCW (ActionEvent e) {
        Stage stage = (Stage) btnExit.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            stage.close();
        }
    }

    private void loadDataMenu () throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(queryCurrentMenu);

        while (resultSet.next()) {
            data.add(new CurrentMenu(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4)));
        }
        tblData.setItems(data);
    }

    private void loadDataStaff () throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(queryStaff);

        while (resultSet.next()) {
            data.add(new CurrentMenu(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4)));
        }
        tblData.setItems(data);
    }

    private void loadTodaySitting () throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(queryTodaysSitting);

        while (resultSet.next()) {
            data.add(new CurrentMenu(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4)));
        }
        tblData.setItems(data);
    }

    /**
     * Collects count on number of bookings.
     *
     * @throws SQLException
     */
    private void loadDataTS () throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(queryTS);
        String sitting = null;

        while (resultSet.next()) {
            sitting = "";
            sitting += resultSet.getInt(1);
        }
        cSittingText.setText(sitting);

    }

    private void enableButtons () {
        btnShowMenu.setDisable(false);
        btnTodaysSitting.setDisable(false);
        btnStaff.setDisable(false);
    }

    /**
     * Assigns cells a value for data to pointed at.
     */
    private void setCellTable () {
        columnOne.setCellValueFactory(new PropertyValueFactory<>("columnOne"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("columnTwo"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("columnThree"));
        columnFour.setCellValueFactory(new PropertyValueFactory<>("columnFour"));
    }
}