
import java.io.IOException;

import BackEnd.BinaryNode;
import BackEnd.GeneralNode;
import BackEnd.Tree;
import BackEnd.TreeNode;
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
        treeDisplay(true);
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

    public static void treeDisplay(boolean isBinary) {
        Tree tree = new Tree();
        tree.binaryRoot = new BinaryNode('B');
        
        tree.binaryRoot.left = new BinaryNode('C');
        tree.binaryRoot.left.left = new BinaryNode('R');
        tree.binaryRoot.left.right = new BinaryNode('T');
        
        tree.binaryRoot.right = new BinaryNode('D');
        tree.binaryRoot.right.right = new BinaryNode('E');
        tree.binaryRoot.right.left = new BinaryNode('F');

        if (isBinary) {
            calCoordinatesBin(tree.binaryRoot, rootX, rootY, 100, 100);
            displayBinary(tree.binaryRoot);
        } else {
            displayGeneral(tree.generalRoot);
        }
    }

    public static void calCoordinatesBin(BinaryNode node, float x, float y, float offsetX, float offsetY) {
        if (node == null) {
            return;
        }
        // if (offsetY > 50) {
        // offsetY = 50;
        // }
        // if (offsetX > 40) {
        // offsetX = 40;
        // }
        node.x = x;
        node.y = y;

        calCoordinatesBin(node.left, x - offsetX, y + offsetY, offsetX + 20, offsetY - 25);
        calCoordinatesBin(node.right, x + offsetX, y + offsetY, offsetX - 20, offsetY - 25);
    }

    
    public static void displayBinary(BinaryNode binaryRoot) {
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
    
    public void calCoordinatesGen(GeneralNode node, float x, float y) {
        // TO DO
    }
    public static void displayGeneral(GeneralNode generalRoot) {
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
