package cscm12.cafe94;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * [Main]
 * Main place where the stage starts to branch out, the login page (root).
 *
 * @author Sumi Sunuwar
 * @version 1.0
 */
public class Main extends Application {

    /**
     * [start]
     * Main place stage, this is used as the beginning page.
     * Can be used to plug in the direct FXML page if you dont want to login all the time.
     */
    @Override
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Cafe94.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Cafe94");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
