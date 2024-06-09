package App.UI.App2;

import App.Logic.App2.BinaryNode2;
import App.Logic.App2.Tree2;
import App.UI.RunUI;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class binaryTreeEdit {
    private static final int START_DRAWING_Y = 30;
    private static final int START_DRAWING_X = 250;
    private static int NODE_WIDTH = 20;
    private static int MIN_NODE_WIDTH = 10;
    private static final int HORIZONTAL_GAP = 30;
    private static final int VERTICAL_GAP = 30;

    private Tree2 tree;
    private boolean newTree;
    private Pane visualPane;
    Scene scene;

    public binaryTreeEdit(Tree2 tree, boolean newTree) {
        //sets the parameters
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

        //set and style the closeBtn
        Button closeBtn = new Button("X");
        closeBtn.setLayoutX(460);
        closeBtn.setLayoutY(10);
        closeBtn.setBackground(null);
        closeBtn.setPrefSize(10, 20);
        closeBtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
        closeBtn.setFont(Font.font("system ui", 15));
        closeBtn.setTooltip(new Tooltip("close"));
        buttonBox.getChildren().add(closeBtn);
        DropShadow shadow = new DropShadow();

        //closeBtn hover effect
        closeBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            closeBtn.setEffect(shadow);
            closeBtn.setCursor(Cursor.HAND);
        });

        closeBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            closeBtn.setEffect(null);
            closeBtn.setCursor(Cursor.DEFAULT);
        });

        //closeBtn action
        closeBtn.setOnAction(e -> {
            this.saveTree();
            RunUI.goBack();
        });

        //set and style the saveBtn
        Button saveBtn = new Button("Save");
        saveBtn.setLayoutX(220);
        saveBtn.setLayoutY(300);
        saveBtn.setPrefSize(50, 20);
        saveBtn.setStyle("-fx-background-color:null; -fx-border-color:green; -fx-text-fill:green");
        buttonBox.getChildren().add(saveBtn);
        DropShadow shadow1 = new DropShadow();

        //saveBtn hover effect
        saveBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            saveBtn.setEffect(shadow1);
            saveBtn.setCursor(Cursor.HAND);
        });

        saveBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            saveBtn.setEffect(null);
            saveBtn.setCursor(Cursor.DEFAULT);
        });


        saveBtn.setOnAction(e -> {
            this.saveTree();
        });

        //set and style the transformBtn
        Button transformBtn = new Button("Transform into general tree");
        transformBtn.setLayoutX(290);
        transformBtn.setLayoutY(300);
        transformBtn.setPrefSize(190, 20);
        transformBtn.setStyle("-fx-background-color:#023E8A; -fx-text-fill:#CAF0F8");
        buttonBox.getChildren().add(transformBtn);
        DropShadow shadow2 = new DropShadow();

        //transformBtn hover effect
        transformBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            transformBtn.setEffect(shadow2);
            transformBtn.setCursor(Cursor.HAND);
        });

        transformBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            transformBtn.setEffect(null);
            transformBtn.setCursor(Cursor.DEFAULT);
        });

        //transformBtn hover effect
        transformBtn.setOnAction(e -> {
            this.tree.buildGeneral();
            RunUI.goBack();
        });


        RunUI.setScene(scene, true);
    }

    private void saveTree() {
        //save the Binary tree
        System.out.println();
        this.tree.saveTreeToFile(RunUI.fileHandler2, true);


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
        this.tree.calculatePositions(START_DRAWING_X, START_DRAWING_Y, NODE_WIDTH, HORIZONTAL_GAP, VERTICAL_GAP, true);

        //calling the recursive function
        drawNode(this.tree.binaryRoot);
    }

    private void drawNode(BinaryNode2 root) {
        if (root == null) {
            return;
        }


        Circle circle = new Circle(root.x, root.y, NODE_WIDTH);
        Circle circle1 = new Circle(root.x, root.y, NODE_WIDTH - 2);
        circle1.setFill(Paint.valueOf("white"));
        Label label = new Label(Character.toString(root.value));
        label.setLayoutX(root.x - 3);
        label.setLayoutY(root.y - 10);

        Line line = null;
        if (root.parent != null) {
            line = new Line(root.x, root.y, root.parent.x, root.parent.y);
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


//    private void drawTree() {
//        // Clear the previous drawing
//        this.visualPane.getChildren().clear();
//
//        // Calculate and set the positions of the nodes
//        positionNodes(this.tree.binaryRoot, this.scene.getWidth() / 2, 40, this.scene.getWidth() / 3);
//    }
//
//    private void positionNodes(BinaryNode2 node, double x, double y, double horizontalGap) {
//        if (node == null) {
//            return;
//        }
//
//        Circle circle = new Circle(x, y, 20);
//        this.visualPane.getChildren().add(circle);
//
//        if (node.left != null) {
//            // Draw a line to the left child and position it
//            Line line = new Line(x, y, x - horizontalGap, y + 50);
//            this.visualPane.getChildren().add(line);
//            positionNodes(node.left, x - horizontalGap, y + 50, horizontalGap / 2);
//        }
//
//        if (node.right != null) {
//            // Draw a line to the right child and position it
//            Line line = new Line(x, y, x + horizontalGap, y + 50);
//            this.visualPane.getChildren().add(line);
//            positionNodes(node.right, x + horizontalGap, y + 50, horizontalGap / 2);
//        }
//    }

}
