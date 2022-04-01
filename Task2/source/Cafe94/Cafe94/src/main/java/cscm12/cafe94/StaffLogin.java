package cscm12.cafe94;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

/**
 * [StaffLogin]
 * Responsible for logging in staff to their correct homepage.
 * @author Sumi Sunuwar
 * @version 1.1*/
public class StaffLogin {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField staffLoginUsername;
    @FXML
    private PasswordField staffLoginPassword;
    public static String staffSession;

    /**
     * [switchToStaffAccount]
     * Loggs staff into to their appropriate staff role homepage FXML.
     * Stores the unique username using @getStaffHomeInfo.
     * @param event triggers upon button is pressed.
     */
    @FXML
    public void switchToStaffAccount(ActionEvent event) throws IOException {
        DatabaseHandler staffDatabase = new DatabaseHandler();
        Connection connect = staffDatabase.database();
        String fxml = "";

        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM Staff WHERE StaffUsername='"
                    + staffLoginUsername.getText() + "'" + " AND "
                    + "StaffPassword='" + staffLoginPassword.getText() + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("StaffType").equals("Manager")) {
                    fxml = ("Manager.fxml");
                    staffSession = resultSet.getString("StaffUsername");
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
}
