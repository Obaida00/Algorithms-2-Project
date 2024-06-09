package App.UI.App2;

import App.Logic.App2.BinaryNode2;
import App.Logic.App2.GeneralNode2;
import App.Logic.App2.Tree2;
import App.UI.RunUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class GeneralTreeEdit {
    private static final int START_DRAWING_Y = 30;
    private static final int START_DRAWING_X = 540;
    private static int NODE_WIDTH = 20;
    private static final int MIN_NODE_WIDTH = 15;
    private static final int HORIZONTAL_GAP = 100;
    private static final int VERTICAL_GAP = 50;

    private Tree2 tree;
    private Pane visualPane;
    private boolean newTree;
    Scene scene;

    public GeneralTreeEdit(Tree2 tree, boolean newTree) {
        //sets the parameters
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

        //set and style the closeBtn
        Button closeBtn = new Button("Close");
        closeBtn.setLayoutX(1);
        closeBtn.setLayoutY(300);
        closeBtn.setBackground(null);
        closeBtn.setUnderline(true);

        closeBtn.setPrefSize(100, 20);
        closeBtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
        closeBtn.setFont(Font.font("system ui", 15));
        closeBtn.setTooltip(new Tooltip("close"));
        buttonBox.getChildren().add(closeBtn);
        HBox.setMargin(closeBtn,new Insets(0 ,600, 0 ,0));
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
            this.saveAndMigrate();
            RunUI.goBack();

        });
        Button refreshbtn = new Button("Refresh");
        refreshbtn.setLayoutX(170);
        refreshbtn.setLayoutY(300);
        refreshbtn.setPrefSize(70, 20);
        refreshbtn.setStyle("-fx-background-color:null; -fx-border-color:green; -fx-text-fill:green; -fx-border-radius:3");
        buttonBox.getChildren().add(refreshbtn);
        DropShadow shadow3 = new DropShadow();

        //saveBtn hover effect
        refreshbtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            refreshbtn.setEffect(shadow3);
            refreshbtn.setCursor(Cursor.HAND);
        });

        refreshbtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            refreshbtn.setEffect(null);
            refreshbtn.setCursor(Cursor.DEFAULT);
        });
        refreshbtn.setOnAction(event ->{
            this.refresh();
        });

        //set and style the saveBtn
        Button saveBtn = new Button("Save");
        saveBtn.setLayoutX(220);
        saveBtn.setLayoutY(300);
        saveBtn.setPrefSize(50, 20);
        saveBtn.setStyle("-fx-background-color:null; -fx-border-color:green; -fx-text-fill:green; -fx-border-radius:3");
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
        Button transformBtn = new Button("Transform into binary tree");
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

        //transformBtn action
        transformBtn.setOnAction(e -> {
            new binaryTreeEdit(tree.buildBinary(), false);
        });


        RunUI.setScene(scene, true);
    }

    private void refresh() {
        this.drawTree();
    }

    private void saveAndMigrate() {
        this.saveTree();

        //migrate output file to input file cause its saved
        RunUI.migrateOutput();
    }

    private void saveTree() {
        //save the General tree
        this.tree.saveTreeToFile(RunUI.fileHandler2, false);

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
        this.tree.calculatePositions(START_DRAWING_X, START_DRAWING_Y, NODE_WIDTH, HORIZONTAL_GAP, VERTICAL_GAP, false);
        //calling the recursive function
        drawNode(this.tree.generalRoot);
    }

    private void drawNode(GeneralNode2 root) {
        if (root == null) {
            return;
        }

        Circle circle = new Circle(root.x, root.y, NODE_WIDTH);
       // circle.setStyle("-fx-fill-color:#023E8A ");
        circle.setFill(Paint.valueOf("#023E8A"));
       // Circle circle1 = new Circle(root.x, root.y, NODE_WIDTH - 2);
        //circle1.setFill(Paint.valueOf("white"));
        Label label = new Label(Character.toString(root.value));
        label.setStyle("-fx-background-color:#023E8A ;-fx-text-fill:#CAF0F8");
        label.setLayoutX(root.x - 3);
        label.setLayoutY(root.y - 10);

        ContextMenu cm=new ContextMenu();
        MenuItem addLeft=new MenuItem("Add left node");
        MenuItem addRight=new MenuItem("Add right node");
        MenuItem delete=new MenuItem("Delete node");
        label.setContextMenu(cm);
        cm.getItems().addAll(addRight,addLeft);
        if(root.parent != null)
            cm.getItems().add(delete);

        addLeft.setOnAction(event ->{
            addLeft(root);
        });

        addRight.setOnAction(event ->{
            addRight(root);

        });

        delete.setOnAction(event ->{
            delete(root);
        });

        this.visualPane.getChildren().addAll(circle, label);


        if ((root.x + NODE_WIDTH > this.scene.getWidth() ||
                root.x + NODE_WIDTH < 0 ||
                root.y + NODE_WIDTH > this.scene.getHeight()) && NODE_WIDTH > MIN_NODE_WIDTH) {
            NODE_WIDTH -= 1;
            drawTree();
        } else {
            for (int i = 0; i < root.children.size(); i++) {
                Line line = new Line(root.x, root.y, root.children.get(i).x, root.children.get(i).y);
                this.visualPane.getChildren().addFirst(line);

                drawNode(root.children.get(i));
            }
        }


    }


    private void delete(GeneralNode2 root) {
        root.delete();
        this.refresh();
    }

    private void addRight(GeneralNode2 root) {
        new addGeneralNodeRight(root);
        this.refresh();
    }

    private void addLeft(GeneralNode2 root) {
        new addGeneralNodeLeft(root);
        this.refresh();
    }


}


