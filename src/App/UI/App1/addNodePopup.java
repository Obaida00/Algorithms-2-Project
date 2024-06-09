package App.UI.App1;

import App.Logic.App1.Tree1;
import App.Logic.App2.Tree2;
import App.UI.App2.GeneralTreeEdit;
import App.UI.RunUI;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addNodePopup {

    public addNodePopup() {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 200, Color.valueOf("#FFFFFF"));

        Text text = new Text("Add text nodes and their coordinates");
        text.setLayoutX(30);
        text.setLayoutY(30);
        text.setFont(Font.font("system ui", 15));
        text.setFill(Color.valueOf("#000000"));
        root.getChildren().add(text);

        TextField add_value = new TextField(" ");
        add_value.setLayoutX(66);
        add_value.setLayoutY(70);
        add_value.setPrefSize(170, 25);
        add_value.setStyle("-fx-background-color:null; -fx-border-color:#03045E; -fx-text-fill:#03045E");
        root.getChildren().add(add_value);

        Button cancel = new Button("Cancel");
        cancel.setLayoutX(40);
        cancel.setLayoutY(120);
        cancel.setPrefSize(100, 20);
        cancel.setStyle("-fx-background-color:null; -fx-text-fill:red");
        root.getChildren().add(cancel);
        DropShadow shadow1 = new DropShadow();
        cancel.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            cancel.setEffect(shadow1);
            cancel.setCursor(Cursor.HAND);
        });

        cancel.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            cancel.setEffect(null);
            cancel.setCursor(Cursor.DEFAULT);
        });

        cancel.setOnAction(e -> {
            RunUI.goBack();
        });

        Button add = new Button("Add");
        add.setLayoutX(176);
        add.setLayoutY(120);
        add.setPrefSize(50, 20);
        add.setStyle("-fx-background-color:#023E8A; -fx-text-fill:#CAF0F8");
        root.getChildren().add(add);
        DropShadow shadow2 = new DropShadow();

        add.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            add.setEffect(shadow2);
            add.setCursor(Cursor.HAND);
        });

        add.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            add.setEffect(null);
            add.setCursor(Cursor.DEFAULT);
        });

        add.setOnAction(e -> {
            String input = add_value.getText();
            Tree1 tree = RunUI.fileHandler1.loadTree(input);
//                tree = new Tree1(input.charAt(0));
//                tree.generalRoot.children.add(new GeneralNode2(tree.generalRoot, 'c'));
            if (tree != null)
                new BinaryTree1(tree, true);
            else
                System.out.println("error");
        });


        RunUI.setScene(scene, true);
    }

}
