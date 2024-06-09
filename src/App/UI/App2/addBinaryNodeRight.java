package App.UI.App2;

import App.Logic.App2.BinaryNode2;
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

public class addBinaryNodeRight {
    Scene scene;
    private String nodeValue;

    public addBinaryNodeRight(BinaryNode2 node) {
        //Pane root = new Pane();
        //initialize the root and scene
        Group root = new Group();
        Scene scene = new Scene(root, 300, 170, Color.valueOf("white"));

        //set and style the addBtn a value text
        Text addValTxt = new Text("Add a value for the node:");
        addValTxt.setLayoutX(42);
        addValTxt.setLayoutY(45);
        addValTxt.setStyle("-fx-font-size:20px;");
        addValTxt.setFont(Font.font("system ui", 19));
        addValTxt.setFill(Color.valueOf("#03045E"));
        root.getChildren().add(addValTxt);

        //set and style the addVal text field
        TextField addValue = new TextField("Enter a value :");
        addValue.setLayoutX(66);
        addValue.setLayoutY(70);
        addValue.setPrefSize(170, 25);
        addValue.selectAll();
        addValue.setStyle("-fx-background-color:null; -fx-border-color:#03045E; -fx-text-fill:#03045E;  -fx-border-radius:3");
        root.getChildren().add(addValue);

        //set and style the cancelBtn
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setLayoutX(40);
        cancelBtn.setLayoutY(120);
        cancelBtn.setPrefSize(100, 20);
        cancelBtn.setStyle("-fx-background-color:null; -fx-text-fill:red");
        root.getChildren().add(cancelBtn);
        DropShadow shadow1 = new DropShadow();

        //cancelBtn hover effect
        cancelBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            cancelBtn.setEffect(shadow1);
            cancelBtn.setCursor(Cursor.HAND);
        });

        cancelBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            cancelBtn.setEffect(null);
            cancelBtn.setCursor(Cursor.DEFAULT);
        });

        //cancelBtn action
        cancelBtn.setOnAction(e -> {
            //make the popup disappear
            RunUI.goBack();
        });

        //set and style the addBtn
        Button addBtn = new Button("Add");
        addBtn.setLayoutX(180);
        addBtn.setLayoutY(120);
        addBtn.setPrefSize(50, 20);
        addBtn.setStyle("-fx-background-color:#023E8A; -fx-text-fill:#CAF0F8");
        root.getChildren().add(addBtn);
        DropShadow shadow2 = new DropShadow();

        //addBtn hover effect
        addBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            addBtn.setEffect(shadow2);
            addBtn.setCursor(Cursor.HAND);
        });

        addBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            addBtn.setEffect(null);
            addBtn.setCursor(Cursor.DEFAULT);
        });

        //addBtn action
        addBtn.setOnAction(e -> {
            String input = addValue.getText();
            if (input.length() > 1 || !Character.isAlphabetic(input.charAt(0))) {
                System.out.println("only characters are allowed");
            } else {
                node.left= new BinaryNode2(node, input.charAt(0));
            }
            RunUI.goBack();
        });




        //todo convert to a popup, you will need line 23 & 68

//    root.setBackground(Background.fill(Color.valueOf("#FFFFFF")));
//        Popup popup = new Popup();
//        popup.centerOnScreen();
//        popup.getContent().addBtn(root);
//        popup.show(window);
        RunUI.setScene(scene, true);
    }


}
