package cscm12.cafe94;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import static cscm12.cafe94.StaffLoginController.staffSession;

/**  [StaffHomepage]
 * Class which handles staffs custom homepage with functions based on their role.
 * @author Sumi Sunuwar
 * @version 1.1 */
public class StaffHomepageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label staffType, staffFName, staffLName, staffHoursToWork, staffHoursToWorkDay;

    /**  [getStaffHomeInfo]
     This method is used to get the staff homepage information.
     This includes the staffs first name, surname, hours and the staff type.
     @throws SQLException for errors */
    public void getStaffHomeInfo() throws SQLException {
        DBConnector staffDatabase = new DBConnector();
        Connection connect = staffDatabase.getConnection();

        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM Staff WHERE StaffUsername='" + staffSession + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();

            while (resultSet.next()) {
                staffType.setText(resultSet.getString("StaffType"));
                staffFName.setText(resultSet.getString("StaffFirst_Name"));
                staffLName.setText(resultSet.getString("StaffLast_Name"));
                staffHoursToWork.setText(String.valueOf(resultSet.getInt("HoursToWork")));
                int hoursToWorkWeek = resultSet.getInt("HoursToWork");
                staffHoursToWorkDay.setText(String.valueOf(hoursToWorkWeek / 5));
            }
            statement.close();
            connect.close();
        } catch (NullPointerException n) {
            System.out.println(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**  [switchToStaffLogin]
     Switches to staffs login page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToStaffLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffLogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**  [switchToManageStaff]
     Switches to manage staff page.
     @param event is to trigger fxml swap.
     @throws IOException for errors. */
    @FXML
    public void switchToManageStaff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageStaff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**  [switchToManagerReport]
     Switches to Manager Report page.
     @param event is to trigger fxml swap.
     @throws IOException for errors. */
    @FXML
    public void switchToManagerReport(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManagerReport.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**  [initialize]
     Switches to Managers page.
     @param location location of the method.
     @param resources resources of the method. */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getStaffHomeInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
