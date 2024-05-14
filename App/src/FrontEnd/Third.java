
import java.io.IOException;

import BackEnd.App2.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Third extends Application {

    public static Pane pane;
    private Stage stage;
    private Scene scene;

    // global logical vars
    static float rootX = 250;
    static float rootY = 50;

    @Override
    public void start(Stage primeStage) throws Exception {

        pane = new Pane();
        Scene scene = new Scene(pane, 600, 600);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        // treeDisplay(true);
        primeStage.setScene(scene);
        primeStage.setTitle("Tree Display");
        primeStage.show();
    }

    public void addBtneventTest(ActionEvent e) throws IOException {

        // treeDisplay(true);
        scene = new Scene(Third.pane, 600, 400);
        stage.setScene(scene);
        stage.show();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    }

    public static void treeDisplay(Tree2 tree, boolean isBinary) {

        if (isBinary) {
            displayBinary(tree.binaryRoot);
        } else {
            displayGeneral(tree.generalRoot);
        }
    }
    
    public static void displayBinary(BinaryNode2 binaryRoot) {
        if (binaryRoot == null) {
            return;
        } else {
            Button rootButton = new Button(String.valueOf(binaryRoot.value));
            rootButton.getStyleClass().setAll("nodeBtn");
            rootButton.setLayoutX(binaryRoot.x);
            rootButton.setLayoutY(binaryRoot.y);
            
            pane.getChildren().add(rootButton);
            displayBinary(binaryRoot.left);
            displayBinary(binaryRoot.right);
        }
    }
    
    public static void displayGeneral(GeneralNode2 generalRoot) {
        Third.pane = new AnchorPane();
        if (generalRoot == null) {
            Label label = new Label("There are no nodes in your tree");
            label.setStyle("-fx-font-size: 25px");
        }
    }

    public static void main(String[] args) {
    launch(args);
    }

}
