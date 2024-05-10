package FrontEnd;

// import javax.swing.text.html.CSS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Landing extends Application {

    @Override
    public void start(Stage primeStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("one.fxml"));
        Scene scene = new Scene(root);
        primeStage.setScene(scene); 
        primeStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
