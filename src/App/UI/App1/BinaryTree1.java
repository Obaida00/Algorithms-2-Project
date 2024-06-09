package App.UI.App1;

import App.Logic.App1.Tree1;
import App.Logic.App1.TreeNode1;
import App.Logic.App2.BinaryNode2;
import App.UI.RunUI;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class BinaryTree1 {
    private static final int OFFSET = 100;
    private static final int START_DRAWING_Y = 30;
    private static final int START_DRAWING_X = 250 - OFFSET;
    private static int NODE_WIDTH = 15;
    private static int MIN_NODE_WIDTH = 10;
    private static final int HORIZONTAL_GAP = 40;
    private static final int VERTICAL_GAP = 50;

    private Tree1 tree;
    private boolean newTree;
    private Pane visualPane;
    Scene scene;

    public BinaryTree1(Tree1 tree, boolean newTree) {
        this.newTree = newTree;
        this.tree = tree;

        //initialize the root and scene
        BorderPane root = new BorderPane();
        this.visualPane = new Pane();
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        scene = new Scene(root, 500, 350, Color.valueOf("#CAF0F8"));
//        buttonBox.setStyle("-fx-background-color:white; -fx-margin-right:50px");

        scene.widthProperty().addListener((obs, oldVal, newVal) -> drawTree());
        scene.heightProperty().addListener((obs, oldVal, newVal) -> drawTree());

        this.drawTree();

        root.setCenter(visualPane);
        root.setBottom(buttonBox);


        Button toDiagram = new Button("Transform into diagram");
        toDiagram.setLayoutX(300);
        toDiagram.setLayoutY(300);
        toDiagram.setPrefSize(160, 33);
        toDiagram.setStyle("-fx-background-color:#03045E; -fx-border-color:#03045E; -fx-text-fill:#FFFFFF");
        buttonBox.getChildren().add(toDiagram);
        DropShadow shadow1 = new DropShadow();
        toDiagram.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            toDiagram.setEffect(shadow1);
            toDiagram.setCursor(Cursor.HAND);
        });
        toDiagram.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            toDiagram.setEffect(null);
            toDiagram.setCursor(Cursor.DEFAULT);
        });
        toDiagram.setOnAction(e -> {
            new Diagram(this.tree);
        });

        Button save = new Button("Save");
        save.setLayoutX(210);
        save.setLayoutY(300);
        save.setPrefSize(80, 33);
        save.setStyle("-fx-background-color:null; -fx-border-color:#00FF00; -fx-text-fill:#00FF00");
        buttonBox.getChildren().add(save);
        DropShadow shadow2 = new DropShadow();
        save.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            save.setEffect(shadow2);
            save.setCursor(Cursor.HAND);
        });
        save.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            save.setEffect(null);
            save.setCursor(Cursor.DEFAULT);
        });
        save.setOnAction(e -> {
            this.saveTree();
        });

        Button close = new Button("Close");
        close.setLayoutX(20);
        close.setLayoutY(300);
        close.setBackground(null);
        close.setUnderline(true);
        close.setPrefSize(150, 20);
        close.setStyle("-fx-background-color:null; -fx-text-fill:red");
        buttonBox.getChildren().add(close);
        DropShadow shadow = new DropShadow();
        close.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            close.setEffect(shadow);
            close.setCursor(Cursor.HAND);
        });
        close.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            close.setEffect(null);
            close.setCursor(Cursor.DEFAULT);
        });
        close.setOnAction(e -> {
            RunUI.goBack();
        });


        RunUI.setScene(scene, true);
    }

    private void saveTree() {
        //save the Binary tree
        System.out.println();
        this.tree.saveTreeToFile(RunUI.fileHandler1, true);

        //update callstack
        if (newTree) {
            Scene s = RunUI.popCallStack();
            RunUI.popCallStack();
            RunUI.popCallStack();
            RunUI.pushCallStack(s);
            newTree = false;
        }
    }

    private void drawTree() {

        // Clear the previous drawing
        this.visualPane.getChildren().clear();

        // Calculate and set the positions of the nodes
        this.tree.calculatePositions(START_DRAWING_X, START_DRAWING_Y, NODE_WIDTH, HORIZONTAL_GAP, VERTICAL_GAP);

        //calling the recursive function
        drawNode(this.tree.root);
    }

    private void drawNode(TreeNode1 root) {
        if (root == null) {
            return;
        }


        Circle circle = new Circle(root.x, root.y, NODE_WIDTH);
        Circle circle1 = new Circle(root.x, root.y, NODE_WIDTH - 2);
        circle1.setFill(Paint.valueOf("white"));
        Label label = new Label(Character.toString(root.value));
        label.setLayoutX(root.x - 3);
        label.setLayoutY(root.y - 10);

        if (root.parent != null) {
            Line line = new Line(root.x, root.y, root.parent.x, root.parent.y);
            this.visualPane.getChildren().addFirst(line);
        }

        this.visualPane.getChildren().addAll(circle, circle1, label);


        if ((root.x + NODE_WIDTH > this.scene.getWidth() ||
                root.x + NODE_WIDTH < 0 ||
                root.y + NODE_WIDTH > this.scene.getHeight()) && NODE_WIDTH > MIN_NODE_WIDTH) {
            NODE_WIDTH -= 1;
            drawTree();
        } else {
            drawNode(root.left);
            drawNode(root.right);
        }
    }

}
