package App.UI.App1;

import App.Logic.App1.Tree1;
import App.Logic.App1.TreeNode1;
import App.Logic.App2.BinaryNode2;
import App.UI.RunUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BinaryTree1 {
    private static final int START_DRAWING_Y = 30;
    private static final int START_DRAWING_X = 540 - 100;
    private static int NODE_WIDTH = 30;
    private static int MIN_NODE_WIDTH = 20;
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
        buttonBox.setStyle("-fx-padding:10");

        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        scene = new Scene(root, 1080, 650, Color.valueOf("white"));
//        buttonBox.setStyle("-fx-background-color:white; -fx-margin-right:50px");

        scene.widthProperty().addListener((obs, oldVal, newVal) -> drawTree());
        scene.heightProperty().addListener((obs, oldVal, newVal) -> drawTree());

        this.drawTree();

        root.setCenter(visualPane);
        root.setBottom(buttonBox);


        Button savebtn = new Button("Save");
        savebtn.setLayoutX(220);
        savebtn.setLayoutY(300);
        savebtn.setPrefSize(50, 20);
        savebtn.setStyle("-fx-background-color:null; -fx-border-color:green; -fx-text-fill:green; -fx-border-radius:3");
        buttonBox.getChildren().add(savebtn);
        DropShadow shadow2 = new DropShadow();
        savebtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            savebtn.setEffect(shadow2);
            savebtn.setCursor(Cursor.HAND);
        });
        savebtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            savebtn.setEffect(null);
            savebtn.setCursor(Cursor.DEFAULT);
        });
        savebtn.setOnAction(e -> {
            this.saveTree();
        });

        Button toDiagramBtn = new Button("Transform into diagram");
        toDiagramBtn.setLayoutX(290);
        toDiagramBtn.setLayoutY(300);
        toDiagramBtn.setPrefSize(190, 20);
        toDiagramBtn.setStyle("-fx-background-color:#023E8A; -fx-text-fill:#CAF0F8");
        buttonBox.getChildren().add(toDiagramBtn);
        DropShadow shadow1 = new DropShadow();
        toDiagramBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            toDiagramBtn.setEffect(shadow1);
            toDiagramBtn.setCursor(Cursor.HAND);
        });
        toDiagramBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            toDiagramBtn.setEffect(null);
            toDiagramBtn.setCursor(Cursor.DEFAULT);
        });
        toDiagramBtn.setOnAction(e -> {
            new Diagram(this.tree);
        });


        Button closebtn = new Button("Close");
        closebtn.setLayoutX(1);
        closebtn.setLayoutY(300);
        closebtn.setBackground(null);
        closebtn.setUnderline(true);
        closebtn.setPrefSize(100, 20);
        closebtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
        closebtn.setFont(Font.font("system ui", 15));
        closebtn.setTooltip(new Tooltip("close"));
        HBox.setMargin(closebtn,new Insets(0 ,700, 0 ,0));

        DropShadow shadow = new DropShadow();
        closebtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            closebtn.setEffect(shadow);
            closebtn.setCursor(Cursor.HAND);
        });
        closebtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            closebtn.setEffect(null);
            closebtn.setCursor(Cursor.DEFAULT);
        });
        closebtn.setOnAction(e -> {
            this.saveTree();
            RunUI.goBack();
        });

        buttonBox.getChildren().addFirst(closebtn);

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
