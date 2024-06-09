package App.UI.App1;

import App.Logic.App1.Tree1;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Diagram {
    private Tree1 tree;

    public Diagram(Tree1 tree){
        Group root= new Group();
        Scene scene= new Scene(root,500,350, Color.valueOf("#FFFFFF"));

        Button toBinary=new Button("Transform into binary tree");
        toBinary.setLayoutX(300);
        toBinary.setLayoutY(300);
        toBinary.setPrefSize(160,33);
        toBinary.setStyle("-fx-background-color:#03045E; -fx-border-color:#03045E; -fx-text-fill:#FFFFFF");
        root.getChildren().add(toBinary);
        DropShadow shadow1 =new DropShadow();
        toBinary.addEventHandler(MouseEvent.MOUSE_ENTERED,(MouseEvent e)->{
            toBinary.setEffect(shadow1);
            toBinary.setCursor(Cursor.HAND);
        });
        toBinary.addEventHandler(MouseEvent.MOUSE_EXITED,(MouseEvent e)->{
            toBinary.setEffect(null);
            toBinary.setCursor(Cursor.DEFAULT);
        });
        toBinary.setOnAction(e ->{
            RunUI.goBack();
        });

        Button save=new Button("Save");
        save.setLayoutX(210);
        save.setLayoutY(300);
        save.setPrefSize(80,33);
        save.setStyle("-fx-background-color:null; -fx-border-color:#00FF00; -fx-text-fill:#00FF00");
        root.getChildren().add(save);
        DropShadow shadow2 =new DropShadow();
        save.addEventHandler(MouseEvent.MOUSE_ENTERED,(MouseEvent e)->{
            save.setEffect(shadow2);
            save.setCursor(Cursor.HAND);
        });
        save.addEventHandler(MouseEvent.MOUSE_EXITED,(MouseEvent e)->{
            save.setEffect(null);
            save.setCursor(Cursor.DEFAULT);
        });
//        toBinary.setOnAction(e ->{
//            new TextNodes(window);
//        });

        Button close=new Button("Close");
        close.setLayoutX(20);
        close.setLayoutY(300);
        close.setBackground(null);
        close.setUnderline(true);
        close.setPrefSize(150,20);
        close.setStyle("-fx-background-color:null; -fx-text-fill:red");
        root.getChildren().add(close);
        DropShadow shadow =new DropShadow();
        close.addEventHandler(MouseEvent.MOUSE_ENTERED,(MouseEvent e)->{
            close.setEffect(shadow);
            close.setCursor(Cursor.HAND);
        });
        close.addEventHandler(MouseEvent.MOUSE_EXITED,(MouseEvent e)->{
            close.setEffect(null);
            close.setCursor(Cursor.DEFAULT);
        });
        close.setOnAction(e ->{
            RunUI.goBack();
        });


        RunUI.setScene(scene, true);
    }
}
