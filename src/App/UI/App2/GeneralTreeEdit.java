package App.UI.App2;

import App.Logic.App2.Tree2;
import App.UI.RunUI;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GeneralTreeEdit {

    private Tree2 tree;
    private boolean newTree;

    public GeneralTreeEdit(Tree2 tree, boolean newTree) {
        //sets the parameters
        this.newTree = newTree;
        this.tree = tree;

        //initialize the root and scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 350, Color.valueOf("#CAF0F8"));

        //todo draw the tree

        //set and style the closeBtn
        Button closeBtn = new Button("X");
        closeBtn.setLayoutX(460);
        closeBtn.setLayoutY(10);
        closeBtn.setBackground(null);
        closeBtn.setPrefSize(10, 20);
        closeBtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
        closeBtn.setFont(Font.font("system ui", 15));
        closeBtn.setTooltip(new Tooltip("close"));
        root.getChildren().add(closeBtn);
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

        //set and style the saveBtn
        Button saveBtn = new Button("Save");
        saveBtn.setLayoutX(220);
        saveBtn.setLayoutY(300);
        saveBtn.setPrefSize(50, 20);
        saveBtn.setStyle("-fx-background-color:null; -fx-border-color:green; -fx-text-fill:green");
        root.getChildren().add(saveBtn);
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
        root.getChildren().add(transformBtn);
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
            new binaryTreeEdit();
        });


        RunUI.setScene(scene, true);
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
}
