package App.UI.App1;

import App.UI.RunUI;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BinaryTree1 {
    public BinaryTree1(){
        Group root= new Group();
        Scene scene= new Scene(root,500,350, Color.valueOf("#FFFFFF"));

        Button toDiagram=new Button("Transform into diagram");
        toDiagram.setLayoutX(300);
        toDiagram.setLayoutY(300);
        toDiagram.setPrefSize(160,33);
        toDiagram.setStyle("-fx-background-color:#03045E; -fx-border-color:#03045E; -fx-text-fill:#FFFFFF");
        root.getChildren().add(toDiagram);
        DropShadow shadow1 =new DropShadow();
        toDiagram.addEventHandler(MouseEvent.MOUSE_ENTERED,(MouseEvent e)->{
            toDiagram.setEffect(shadow1);
            toDiagram.setCursor(Cursor.HAND);
        });
        toDiagram.addEventHandler(MouseEvent.MOUSE_EXITED,(MouseEvent e)->{
            toDiagram.setEffect(null);
            toDiagram.setCursor(Cursor.DEFAULT);
        });
        toDiagram.setOnAction(e ->{
            new Diagram();
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
