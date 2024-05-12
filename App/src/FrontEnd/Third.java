package FrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Third extends Application {

    @Override
    public void start(Stage primeStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("third.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Controller.treeDisplay(true);
        primeStage.setScene(scene);
        primeStage.setTitle("Tree Display");
        primeStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
