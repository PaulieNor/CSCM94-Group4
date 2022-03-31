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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * [Controller]
 * Where you put in all the methods + actionevents + connect FXML gui for the api.
 * This class also connects to the SQL database.
 * I believe Tom stated that this is how it's meant to be, so this class WILL be messy.
 *
 * @author Sumi Sunuwar, Ryan Noscoe
 * @version 1.0
 */
public class Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * [FXML]
     * These are the variables for datatypes that connect the gui elements into java methods.
     * This includes your labels, messages, textboxes, buttons, lists, etc.
     * They all need to be tagged as FXML's (or so I've seen).
     */

    /** These are the buttons and the choice boxes for Driver.fxml*/
   

    /** These are the buttons and the choice boxes for Waiter.fxml*/

    /** These are the buttons and the choice boxes for Waiter.fxml*/
    @FXML
    private Button setSpecialButton;
    @FXML
    private ChoiceBox setSpecialList;

    /** These are the buttons and the choice boxes for StaffLogin.fxml, Manager.fxml and  ManageStaff.fxml*/
    @FXML
    private static String staffSession;
    @FXML
    private TextField staffLoginUsername;
    @FXML
    private PasswordField staffLoginPassword;
    @FXML
    private Label staffType;
    @FXML
    private Label staffFName;
    @FXML
    private Label staffLName;
    @FXML
    private Label staffHoursToWork;
    @FXML
    private Label staffHoursWorked;
    @FXML
    private TextField newStaffFName;
    @FXML
    private TextField newStaffLName;
    @FXML
    private TextField newStaffType;
    @FXML
    private TextField newHoursToWork;
    @FXML
    private TextField newStaffUsername;
    @FXML
    private TextField newStaffPassword;
    @FXML
    private Button insertStaffButton;
    @FXML
    private Button updateStaffButton;
    @FXML
    private Button deleteStaffButton;
    @FXML
    private TableView<ManageStaff> staffTable;
    @FXML
    private TableColumn<ManageStaff, String> fieldStaffFName;
    @FXML
    private TableColumn<ManageStaff, String> fieldStaffLName;
    @FXML
    private TableColumn<ManageStaff, String> fieldStaffType;
    @FXML
    private TableColumn<ManageStaff, Number> fieldHoursToWork;
    @FXML
    private TableColumn<ManageStaff, String> fieldStaffUsername;
    @FXML
    private TableColumn<ManageStaff, String> fieldStaffPassword;

    /**
     * [staffDatabase]
     * This is a method where users can connect to the staff database.
     * This method is called whenever you want to do a query for the staff database.
     * This includes staff login, staff management, staff homepage.
     */
    public Connection staffDatabase() {
        Connection StaffDatabase;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            StaffDatabase = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/staffdatabase", "root", "cleardog50");
            return StaffDatabase;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * [switchToStaffAccount]
     * This is a method where staff login to their appropriate staff role homepage FXML.
     * It also stores their unique username to extract their infomation using @getStaffHomeInfo.
     * This is done so that this and @getStaffHomeInfo can be used by any staff member whilst
     * the FXML page can be different depending on their own role and functions.
     */
    @FXML
    public void switchToStaffAccount(ActionEvent event) throws IOException {
        String fxml = "";
        Connection connect = staffDatabase();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM ManageStaff WHERE StaffUsername='"
                    + staffLoginUsername.getText() + "'" + " AND " +
                    "StaffPassword='" + staffLoginPassword.getText() + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("StaffType").equals("Manager")) {
                    fxml = ("Manager.fxml");
                   // staffSession = resultSet.getString("StaffUsername");
                }
                else if (resultSet.getString( "StaffType").equals("Cook")) {
                    fxml = ("Chef.fxml");
                    //staffSession = resultSet.getString("StaffUsername");
                }
                else if (resultSet.getString( "StaffType").equals("Driver")) {
                    fxml = ("Driver.fxml");
                   // staffSession = resultSet.getString("StaffUsername");
                }
                else if (resultSet.getString( "StaffType").equals("Waiter")) {
                    fxml = ("Waiter.fxml");
                    //staffSession = resultSet.getString("StaffUsername");
                }
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * [getStaffHomeInfo]
     * This method is used to get the staff homepage information.
     * This includes the staffs first name, surname and the staff type.
     * This method needs to be extended in terms of supplying hours to work and hours worked.
     * I won't be doing the real time stuff since it's more focused to the waiters, chiefs and drivers.
     * Consider using simpledateformat or something, Java has realtime date and time library in it.
     */
    public void getStaffHomeInfo() {
        Connection connect = staffDatabase();
        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM ManageStaff WHERE StaffUsername='" + staffSession + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();

            while (resultSet.next()) {
                staffType.setText(resultSet.getString("StaffType"));
                staffFName.setText(resultSet.getString("First_Name"));
                staffLName.setText(resultSet.getString("Last_Name"));
                staffHoursToWork.setText(String.valueOf(resultSet.getInt("HoursToWork")));
            }
            statement.close();
            connect.close();
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [getManageStaffTable]
     * This is a method is used to extract the data from the SQL database.
     * This is so that it's formatted into a list of staff objects.
     * This will be used for @getStaffTable in @switchToManageStaff.
     */
    public ObservableList<ManageStaff> getManageStaffTable() {
        ObservableList<ManageStaff> staffList = FXCollections.observableArrayList();
        Connection connect = staffDatabase();
        try {
            String sql = "SELECT * FROM ManageStaff";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            ManageStaff staff;

            while (resultSet.next()) {
                staff = new ManageStaff(resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
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
     * [getStaffTable]
     * This is a method is used to get the extracted SQL data.
     * It converts the list we've created and assigns it to the ManageStaff class.
     * This also assigns and fills up the table based on it's assigned staff and column.
     * This is for @switchToManageStaff.
     */
    public void getStaffTable() {
        ObservableList<ManageStaff> staff = getManageStaffTable();
        try {
            fieldStaffFName.setCellValueFactory(cellData -> cellData.getValue().staffFNameProperty());
            fieldStaffLName.setCellValueFactory(cellData -> cellData.getValue().staffLNameProperty());
            fieldStaffType.setCellValueFactory(cellData -> cellData.getValue().staffTypeProperty());
            fieldHoursToWork.setCellValueFactory(cellData -> cellData.getValue().staffHoursToWorkProperty());
            fieldStaffUsername.setCellValueFactory(cellData -> cellData.getValue().staffUsernameProperty());
            fieldStaffPassword.setCellValueFactory(cellData -> cellData.getValue().staffPasswordProperty());
            staffTable.setItems(staff);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    /**
     * [staffTableSQLCommand]
     * This is a method is used to execute SQL queries in the staff table.
     * Mainly used for @newStaff, @editStaff and @deleteStaff buttons.
     * This is for @switchToManageStaff.
     */
    private void staffTableSQLCommand(String staff) {
        Connection connect = staffDatabase();
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
     */
    private void newStaff() {
        String staff = "INSERT ManageStaff VALUES ('" + newStaffUsername.getText() + "',  '" +
                newStaffType.getText() + "','" + newStaffFName.getText() + "','" + newStaffLName.getText() +
                "','" + newStaffPassword.getText() + "','" + newHoursToWork.getText() + "')";
        staffTableSQLCommand(staff);
        getStaffTable();
    }

    /**
     * [newStaff]
     * Edits current staff's data and updates table in @switchToManageStaff.
     * The username is the primary key, no updates allowed.
     */
    private void editStaff() {
        String staff = "UPDATE ManageStaff SET StaffType = '" + newStaffType.getText() + "', First_Name = '" +
                newStaffFName.getText() + "',Last_Name = '" + newStaffLName.getText() + "', StaffPassword = '"
                + newStaffPassword.getText() + "', HoursToWork = '" + newHoursToWork.getText() + "' " +
                "WHERE StaffUsername = '" + newStaffUsername.getText() + "'";
        staffTableSQLCommand(staff);
        getStaffTable();
    }

    /**
     * [deleteStaff]
     * Deletes staff data and updates table in @switchToManageStaff.
     * The username is the primary key, can only delete from it.
     */
    private void deleteStaff() {
        String staff = "DELETE FROM ManageStaff WHERE StaffUsername = '" + newStaffUsername.getText() + "'";
        staffTableSQLCommand(staff);
        getStaffTable();
    }

    /**
     * [ManageStaffButtonActionEvents]
     * If and else for action events when using the @switchToManageStaff table buttons.
     */
    @FXML
    private void ManageStaffButtonActionEvents(ActionEvent e) {
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

    /**
     * [switchToStaffLogin]
     * Switches to staffs login page.
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
     * [switchToManager]
     * Switches to managers page.
     * This page gives access to @switchToManager and @switchToManageStaff.
     * This page should give access to the report Cafe94 class.
     */
    @FXML
    public void switchToManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Manager.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * [switchToManageStaff]
     * Switches to manage staff page.
     * This page gives access to @getStaffTable, @newStaff, @editStaff and deleteStaff.
     * This page should give access and manipulation to the entire staff database.
     */
    @FXML
    public void switchToManageStaff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageStaff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*[switchToChef]
    * Switches to the chef page
    * This gives access to the @setSpecial, @viewOrderSitIn, @viewOrderTakeaway,
    * @viewOrderDeliver @completeOrder and @viewProfile
    * This allows a chef to choose an order and complete it, set
    * a special and view their own profile*/
    @FXML
    public void switchToChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Chef.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*[switchToWaiter]
     * Switches to the waiter page
     * This gives access to the @viewMenu, @viewOrderSitIn, @viewOrderTakeaway
     * @viewOrderDeliver, @viewProfile, @viewTables, @viewBookings
     * and @viewDeliveryReq
     * This allows a waiter view the menu, view orders, view tables, view bookings,
     * view delivery requests and view their own profile*/
    @FXML
    public void switchToWaiter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Waiter.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*[switchToDriver]
    * Switches to driver page
    * This gives access to the @viewProfile, @viewDelivery
    * and @completeDelivery
    * This allows the driver to view their profile, view deliveries
    * and complete a delivery*/
    @FXML
    public void switchToDriver(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Driver.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * [initialize]
     * Loads @getStaffHomeInfo and getStaffTable.
     * Why? Wouldn't know, it just works.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getStaffHomeInfo();
        getStaffTable();
    }


    public void serveFood(){
        // for waiter to serve food that is set as table service and complete
        //find oldest order that is both complete and marked for table service
        //mark as served
    }
    public void getBooking(){
        // waiter stores information on booking id, booker id, menu items, delivery/in person, time of order,customer id
        //set chosen table to booked so that it cannot be selected
    }
    public void completeOrder(){
        //Chef selects and order of their choosing and presses complete button
        //this set order to complete so that it can be delivered/ served
    }
    public void deliverFood(){
        //delivery driver chooses an order that is both complete and marked for delivery
        //press deliver button
        //mark as delivered
    }
    public void setSpecial(){

    }
}
