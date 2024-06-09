package App.UI.App1;

import App.UI.RunUI;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TreeEmpty1 {

    public TreeEmpty1(){
        Group root= new Group();
        Scene scene= new Scene(root,500,350, Color.valueOf("#FFFFFF"));

        Text welcome1=new Text("The tree is empty");
        welcome1.setLayoutX(135);
        welcome1.setLayoutY(130);
        welcome1.setFont(Font.font("system ui",30));
        welcome1.setFill(Color.valueOf("#000000"));
        root.getChildren().add(welcome1);

        Button add_node=new Button("Enter a prompt for the tree");
        add_node.setLayoutX(170);
        add_node.setLayoutY(170);
        add_node.setPrefSize(180,33);
        add_node.setStyle("-fx-background-color:null; -fx-border-color:#03045E; -fx-text-fill:#03045E ;-fx-border-radius:3");
        root.getChildren().add(add_node);
        DropShadow shadow1 =new DropShadow();
        add_node.addEventHandler(MouseEvent.MOUSE_ENTERED,(MouseEvent e)->{
            add_node.setEffect(shadow1);
            add_node.setCursor(Cursor.HAND);
        });
        add_node.addEventHandler(MouseEvent.MOUSE_EXITED,(MouseEvent e)->{
            add_node.setEffect(null);
            add_node.setCursor(Cursor.DEFAULT);
        });
        add_node.setOnAction(e ->{
            new addNodeTreeEmpty1();
        });

        Button close=new Button("Close");
        close.setLayoutX(175);
        close.setLayoutY(240);
        close.setBackground(null);
        close.setUnderline(true);
        close.setPrefSize(150,20);
        close.setStyle("-fx-background-color:null; -fx-text-fill:red");
        root.getChildren().add(close);
        DropShadow dropShadow =new DropShadow();
        close.addEventHandler(MouseEvent.MOUSE_ENTERED,(MouseEvent e)->{
            close.setEffect(dropShadow);
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
