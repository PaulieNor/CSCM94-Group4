package cscm12.cafe94;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @Author Ryan Noscoe
 * @Version v1.0
 */

public class StartScreen {
    /**[Field Variables]
     * These are for setting up stages to be displayed in the application. */
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button switchStaffButton, switchCustButton;

    /**   [switchToStaffLogin]
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
    /**   [switchToOrderType]
     Switches to page where customers select their order type.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToOrderType(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

