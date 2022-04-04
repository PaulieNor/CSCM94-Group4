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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * [ManageStaff]
 * Responsible for filling up with staff information.
 * @author Sumi Sunuwar
 * @version 1.1*/
public class StaffController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField newStaffFName, newStaffLName, newStaffType,
            newHoursToWork, newStaffUsername, newStaffPassword;
    @FXML
    private Button insertStaffButton, updateStaffButton, deleteStaffButton;
    @FXML
    private TableView<Staff> staffTable;
    @FXML
    private TableColumn<Staff, String> fieldStaffFName, fieldStaffLName,
            fieldStaffType, fieldStaffUsername, fieldStaffPassword;
    @FXML
    private TableColumn<Staff, Number> fieldHoursToWork;

    /**
     [getManageStaffTable]
     This is a method is used to extract the data from the SQL database.
     @return returns staff list for table
     @throws SQLException for errors*/
    public ObservableList<Staff> getManageStaffTable() throws SQLException {
        ObservableList<Staff> staffList = FXCollections.observableArrayList();
        DBConnector staffDatabase = new DBConnector();
        Connection connect = staffDatabase.getConnection();
        try {
            String sql = "SELECT * FROM Staff";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            Staff staff;

            while (resultSet.next()) {
                staff = new Staff(resultSet.getString("StaffFirst_Name"),
                        resultSet.getString("StaffLast_Name"),
                        resultSet.getString("StaffType"),
                        resultSet.getInt("HoursToWork"),
                        resultSet.getString("StaffUsername"),
                        resultSet.getString("StaffPassword"));
                staffList.add(staff);
            }
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffList;
    }

    /**
     [getStaffTable]
     This is a method is used to get the extracted SQL data.
     This is for @switchToManageStaff.
     @throws SQLException for errors */
    public void getStaffTable() throws SQLException {
        ObservableList<Staff> staff = getManageStaffTable();
        try {
            fieldStaffFName.setCellValueFactory(cellData
                    -> cellData.getValue().staffFNameProperty());
            fieldStaffLName.setCellValueFactory(cellData
                    -> cellData.getValue().staffLNameProperty());
            fieldStaffType.setCellValueFactory(cellData
                    -> cellData.getValue().staffTypeProperty());
            fieldHoursToWork.setCellValueFactory(cellData
                    -> cellData.getValue().staffHoursToWorkProperty());
            fieldStaffUsername.setCellValueFactory(cellData
                    -> cellData.getValue().staffUsernameProperty());
            fieldStaffPassword.setCellValueFactory(cellData
                    -> cellData.getValue().staffPasswordProperty());
            staffTable.setItems(staff);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    /**
     * [staffTableSQLCommand]
     * This is a method is used to execute SQL queries in the staff table.
     * @param staff takes in a staff data to manipulate */
    private void staffTableSQLCommand(String staff) throws SQLException {
        DBConnector staffDatabase = new DBConnector();
        Connection connect = staffDatabase.getConnection();
        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(staff);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * [newStaff]
     * Adds new staff data and updates table in @switchToManageStaff.
     * The username is the primary key, no duplicates allowed.
     * @throws SQLException for errors */
    private void newStaff() throws SQLException {
        String staff = "INSERT Staff VALUES ('" + newStaffUsername.getText() + "',  '"
                + newStaffType.getText() + "','" + newStaffFName.getText() + "','"
                + newStaffLName.getText() + "','" + newStaffPassword.getText() + "','"
                + newHoursToWork.getText() + "')";
        staffTableSQLCommand(staff);
        getStaffTable();
    }

    /**
     * [editStaff]
     * Edits current staff's data and updates table in @switchToManageStaff.
     * @throws SQLException for errors */
    private void editStaff() throws SQLException {
        String staff = "UPDATE Staff SET StaffType = '" + newStaffType.getText()
                + "', StaffFirst_Name = '" + newStaffFName.getText() + "',StaffLast_Name = '"
                + newStaffLName.getText() + "', StaffPassword = '" + newStaffPassword.getText()
                + "', HoursToWork = '" + newHoursToWork.getText() + "' "
                + "WHERE StaffUsername = '" + newStaffUsername.getText() + "'";
        staffTableSQLCommand(staff);
        getStaffTable();
    }

    /**
     * [deleteStaff]
     * Deletes staff data and updates table in @switchToManageStaff.
     * @throws SQLException for errors*/
    private void deleteStaff() throws SQLException {
        String staff = "DELETE FROM Staff WHERE StaffUsername = '"
                + newStaffUsername.getText() + "'";
        staffTableSQLCommand(staff);
        getStaffTable();
    }

    /**
     * [ManageStaffButtonActionEvents]
     * If and else for action events when using the @switchToManageStaff table buttons.
     * @param e is a trigger for button presses
     * @throws SQLException for errors */
    @FXML
    private void ManageStaffButtonActionEvents(ActionEvent e) throws SQLException {
        if (e.getSource() == insertStaffButton) {
            newStaff();
        } else if (e.getSource() == updateStaffButton) {
            editStaff();
        } else if (e.getSource() == deleteStaffButton) {
            deleteStaff();
        } else {
            System.out.println("Null e.getSource() if else case.");
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

    /**   [switchToManager]
     Switches to Managers page.
     @param event is to trigger fxml swap */
    @FXML
    public void switchToManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Manager.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**   [switchToManager]
     Switches to Cafe94 report page.
     @param event is to trigger fxml swap */
    @FXML
    public void switchToManagerReport(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManagerReport.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getStaffTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
