package App.UI.App2;

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
import javafx.scene.text.Text;

public class TreeEmpty2 {

    public TreeEmpty2() {
        //initialize the root and scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 350, Color.valueOf("white"));

        //set and style the Tree is empty text
        Text emptyTreeTxt = new Text("The tree is empty");
        emptyTreeTxt.setLayoutX(135);
        emptyTreeTxt.setLayoutY(130);
        emptyTreeTxt.setFont(Font.font("system ui", 30));
        emptyTreeTxt.setFill(Color.valueOf("#03045E"));
        root.getChildren().add(emptyTreeTxt);

        //set and style the addNodeBtn
        Button addNodeBtn = new Button("Add a new node");
        addNodeBtn.setLayoutX(175);
        addNodeBtn.setLayoutY(170);
        addNodeBtn.setPrefSize(152, 33);
        addNodeBtn.setStyle("-fx-background-color:null; -fx-border-color:#03045E; -fx-text-fill:#03045E;  -fx-border-radius:3");
        root.getChildren().add(addNodeBtn);
        DropShadow shadow1 = new DropShadow();

        //addNodeBtn hover effect
        addNodeBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            addNodeBtn.setEffect(shadow1);
            addNodeBtn.setCursor(Cursor.HAND);
        });
        addNodeBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            addNodeBtn.setEffect(null);
            addNodeBtn.setCursor(Cursor.DEFAULT);
        });

        //addNodeBtn action
        addNodeBtn.setOnAction(e -> {
            new addNodeTreeEmpty2();

        });

        //set and style the closeBtn
        Button closeBtn = new Button("Close");
        closeBtn.setLayoutX(175);
        closeBtn.setLayoutY(225);
        closeBtn.setBackground(null);
        closeBtn.setUnderline(true);

        closeBtn.setPrefSize(150, 20);
        closeBtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
       // closeBtn.setFont(Font.font("system ui", 15));
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
            RunUI.goBack();
        });


        RunUI.setScene(scene, true);
    }
}
