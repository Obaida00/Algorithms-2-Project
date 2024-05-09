package FrontEnd;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainTree extends Application {

    @Override
    public void start(Stage primeStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.AZURE);

        Label label = new Label("Tree is empty");
        label.setLayoutX(200);
        label.setLayoutY(150);
        label.setStyle("-fx-font-size: 30");
        
        Button addBtn = new Button("Add a tree");
        addBtn.setStyle("-fx-font-size: 25;");
        addBtn.setLayoutX(210);
        addBtn.setLayoutY(270);
        addBtn.backgroundProperty().setValue(Background.EMPTY);
        addBtn.cursorProperty().set(Cursor.HAND);

        root.getChildren().addAll(label,addBtn);

        primeStage.setScene(scene);
        primeStage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
