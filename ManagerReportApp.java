package src.main.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;

/**
 * @Author Christian Piri
 * @Version v1.3
 */

public class ManagerReportApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ManagerReportApp.class.getResource("managerReportFXML.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 850);
        stage.setTitle("Manager Report");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}