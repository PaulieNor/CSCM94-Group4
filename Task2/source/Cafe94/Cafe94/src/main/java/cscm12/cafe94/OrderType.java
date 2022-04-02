package cscm12.cafe94;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OrderType {
    /**[Field Variables]
     * These are for setting up stages to be displayed in the application. */
    private Stage stage;
    private Scene scene;
    private Parent root;

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
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Takeaway.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
