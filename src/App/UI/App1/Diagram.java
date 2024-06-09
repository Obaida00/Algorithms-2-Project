package App.UI.App1;

import App.Logic.App1.Tree1;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Diagram {
    private Scene scene;
    private Pane visualPane;
    private Tree1 tree;

    public Diagram(Tree1 tree){
        //initialize the root and scene
        BorderPane root = new BorderPane();
        this.visualPane = new Pane();
        HBox buttonBox = new HBox(10);
        buttonBox.setStyle("-fx-padding:10");
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        scene = new Scene(root, 1080, 650, Color.valueOf("white"));
//        buttonBox.setStyle("-fx-background-color:white; -fx-margin-right:50px");


        root.setCenter(visualPane);
        root.setBottom(buttonBox);

        //set and style the closeBtn
        Button closebtn = new Button("Close");
        closebtn.setLayoutX(1);
        closebtn.setLayoutY(300);
        closebtn.setBackground(null);
        closebtn.setPrefSize(100, 20);
        closebtn.setUnderline(true);
        closebtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
        closebtn.setFont(Font.font("system ui", 15));
        closebtn.setTooltip(new Tooltip("close"));
        HBox.setMargin(closebtn,new Insets(0 ,700, 0 ,0));

        buttonBox.getChildren().add(closebtn);
        DropShadow shadow = new DropShadow();

        //closeBtn hover effect
        closebtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            closebtn.setEffect(shadow);
            closebtn.setCursor(Cursor.HAND);
        });

        closebtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            closebtn.setEffect(null);
            closebtn.setCursor(Cursor.DEFAULT);
        });

        //closeBtn action
        closebtn.setOnAction(e -> {
            RunUI.goBack();
        });


        //set and style the saveBtn
        Button saveBtn = new Button("Save");
        saveBtn.setLayoutX(220);
        saveBtn.setLayoutY(300);
        saveBtn.setPrefSize(50, 20);
        saveBtn.setStyle("-fx-background-color:null; -fx-border-color:green; -fx-text-fill:green;-fx-border-radius:3");
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

        });

        //set and style the transformBtn
        Button transformBtn = new Button("Transform into Binary tree");
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
            RunUI.goBack();
        });

        RunUI.setScene(scene, true);
    }
}
