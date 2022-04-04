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

public class CompleteOrder {
    /**[Field Variables]
     * These are for setting up stages to be displayed in the application. */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**   [switchToStartScreen]
     Switches to Start page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToStartScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Cafe94.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToCompleteOrderTak]
     Switches to completed Takeaway page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToFinishOrderTak(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FinishOrderTakeaway.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToCompleteOrderTab]
     Switches to completed sit in page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToFinishOrderTab(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FinishOrderTableService.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToCompleteOrderDel]
     Switches to completed delivery page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToFinishOrderDel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FinishOrderDelivery.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**   [switchToOrders]
     Switches to landing page page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToOrders(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OrderType.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
