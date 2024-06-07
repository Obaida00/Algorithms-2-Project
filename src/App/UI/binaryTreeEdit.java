package App.UI;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class binaryTreeEdit {
    public binaryTreeEdit() {
        //initialize the root and scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 350, Color.valueOf("#CAF0F8"));

        //todo draw the tree

        //set and style the closeBtn
        Button close = new Button("X");
        close.setLayoutX(460);
        close.setLayoutY(10);
        close.setBackground(null);
        close.setPrefSize(10, 20);
        close.setStyle("-fx-background-color:null; -fx-text-fill:red");
        close.setFont(Font.font("system ui", 15));
        close.setTooltip(new Tooltip("close"));
        root.getChildren().add(close);
        DropShadow shadow = new DropShadow();

        //closeBtn hover effect
        close.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            close.setEffect(shadow);
            close.setCursor(Cursor.HAND);
        });

        close.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            close.setEffect(null);
            close.setCursor(Cursor.DEFAULT);
        });

        //closeBtn action
        close.setOnAction(e -> {
            RunUI.close();
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

        //todo saveBtn action
        saveBtn.setOnAction(e -> {

        });

        //set and style the transformBtn
        Button transformBtn = new Button("Transform into general tree");
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

        //transformBtn hover effect
        transformBtn.setOnAction(e -> {
            RunUI.goBack();
        });


        RunUI.setScene(scene, true);
    }
}
