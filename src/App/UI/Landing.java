package App.UI;

import App.Logic.App1.Tree1;
import App.Logic.App2.Tree2;
import App.UI.App1.BinaryTree1;
import App.UI.App1.TreeEmpty1;
import App.UI.App2.GeneralTreeEdit;
import App.UI.App2.TreeEmpty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Landing {

    public Landing() {
        //initialize the root and scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 350, Color.valueOf("white"));


        //set and style the welcomeTxt text
        Text welcomeTxt = new Text("Welcome! Choose one of the two apps :");
        welcomeTxt.setLayoutX(80);
        welcomeTxt.setLayoutY(100);
        welcomeTxt.setFont(Font.font("system ui", 20));
        welcomeTxt.setFill(Color.valueOf("#03045E"));
        root.getChildren().add(welcomeTxt);

        //set and style the app1Btn
        Button app1Btn = new Button("App1");
        app1Btn.setLayoutX(170);
        app1Btn.setLayoutY(150);
        app1Btn.setPrefSize(177, 35);
        app1Btn.setStyle("-fx-background-color:#023E8A; -fx-text-fill:#CAF0F8");
        root.getChildren().add(app1Btn);
        DropShadow shadow1 = new DropShadow();

        //app1Btn hover effect
        app1Btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            app1Btn.setEffect(shadow1);
            app1Btn.setCursor(Cursor.HAND);
        });

        app1Btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            app1Btn.setEffect(null);
            app1Btn.setCursor(Cursor.DEFAULT);
        });

        //app1Btn action
        app1Btn.setOnAction(e -> {
            Tree1 tree = this.readTree1();
            if (tree == null) {
                new TreeEmpty1();
            } else {
                new BinaryTree1(tree, false);
            }
        });


        //set and style the app2Btn
        Button app2Btn = new Button("App2");
        app2Btn.setLayoutX(170);
        app2Btn.setLayoutY(200);
        app2Btn.setPrefSize(177, 35);
        app2Btn.setStyle("-fx-background-color:#023E8A; -fx-text-fill:#CAF0F8");
        root.getChildren().add(app2Btn);
        DropShadow shadow2 = new DropShadow();

        //app2Btn hover effect
        app2Btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            app2Btn.setEffect(shadow2);
            app2Btn.setCursor(Cursor.HAND);
        });

        app2Btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            app2Btn.setEffect(null);
            app2Btn.setCursor(Cursor.DEFAULT);
        });

        //app2Btn action
        app2Btn.setOnAction(e -> {
            Tree2 tree = this.readTree2();
            if (tree == null) {
                new TreeEmpty();
            } else {
                new GeneralTreeEdit(tree, false);
            }
        });


        //set and style the closeBtn
        Button closeBtn = new Button("Close");
        closeBtn.setLayoutX(181);
        closeBtn.setLayoutY(250);
        closeBtn.setBackground(null);
        closeBtn.setUnderline(true);
        closeBtn.setPrefSize(150, 20);
        closeBtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
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
            RunUI.close();
        });


        RunUI.setScene(scene, true);
    }

    private Tree1 readTree1() {
        return RunUI.fileHandler1.loadTree();
    }

    private Tree2 readTree2() {
        return RunUI.fileHandler2.loadTree();
    }
}
