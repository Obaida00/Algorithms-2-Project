package App.UI.App1;

import App.UI.RunUI;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Diagram {
    public Diagram(){
        Group root= new Group();
        Scene scene= new Scene(root,500,350, Color.valueOf("#FFFFFF"));

        Label labA=new Label("A");
        labA.setLayoutX(100);
        labA.setLayoutY(50);
        labA.setPrefSize(100,33);
        labA.setStyle("-fx-background-color:#03045E; -fx-border-color:#000000; -fx-text-fill:#FFFFFF");
        labA.setAlignment(Pos.CENTER);
        root.getChildren().add(labA);

        Label labB=new Label("B");
        labB.setLayoutX(200);
        labB.setLayoutY(50);
        labB.setPrefSize(100,33);
        labB.setStyle("-fx-background-color:#03045E; -fx-border-color:#000000; -fx-text-fill:#FFFFFF");
        labB.setAlignment(Pos.CENTER);
        root.getChildren().add(labB);

        Label labC=new Label("C");
        labC.setLayoutX(300);
        labC.setLayoutY(50);
        labC.setPrefSize(100,33);
        labC.setStyle("-fx-background-color:#03045E; -fx-border-color:#000000; -fx-text-fill:#FFFFFF");
        labC.setAlignment(Pos.CENTER);
        root.getChildren().add(labC);

        Label labD=new Label("D");
        labD.setLayoutX(100);
        labD.setLayoutY(83);
        labD.setPrefSize(110,99);
        labD.setStyle("-fx-background-color:#03045E; -fx-border-color:#000000; -fx-text-fill:#FFFFFF");
        labD.setAlignment(Pos.CENTER);
        root.getChildren().add(labD);

        Label labE=new Label("E");
        labE.setLayoutX(210);
        labE.setLayoutY(83);
        labE.setPrefSize(190,70);
        labE.setStyle("-fx-background-color:#03045E; -fx-border-color:#000000; -fx-text-fill:#FFFFFF");
        labE.setAlignment(Pos.CENTER);
        root.getChildren().add(labE);

        Label labF=new Label("F");
        labF.setLayoutX(210);
        labF.setLayoutY(153);
        labF.setPrefSize(190,29);
        labF.setStyle("-fx-background-color:#03045E; -fx-border-color:#000000; -fx-text-fill:#FFFFFF");
        labF.setAlignment(Pos.CENTER);
        root.getChildren().add(labF);

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
            new BinaryTree1();
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
