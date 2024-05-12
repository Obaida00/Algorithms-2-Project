package FrontEnd;

import java.io.IOException;

import BackEnd.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

     @FXML
    private static Pane pane;

    @FXML
    private Button testBtn;



    private Parent root;
    private Stage stage;
    private Scene scene;

    public void addBtneventTest(ActionEvent e) throws IOException {
        // treeDisplay(true);
        System.out.println("This button works!");
        root = FXMLLoader.load(getClass().getResource("third.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        treeDisplay(false);
        scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();
    }


    public static void treeDisplay(boolean isBinary) {
        Tree tree = new Tree();
        tree.binaryRoot = new BinaryNode('B');

        if (isBinary)
            displayBinary(tree.binaryRoot);
        else
            displayGeneral(tree.generalRoot);
    }

    public static void displayBinary(BinaryNode binaryRoot) { 
        Button rootButton = new Button(String.valueOf(binaryRoot.value));
        pane = new Pane();
        pane.getChildren().add(rootButton);
        

    }
    
    public static void displayGeneral(GeneralNode generalRoot) {
        Label label = new Label("There are no nodes in your tree");
        label.setStyle("-fx-font-size: 25px");
        if (generalRoot == null) {
            
        }
    }
}   