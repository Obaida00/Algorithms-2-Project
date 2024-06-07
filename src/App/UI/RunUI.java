package App.UI;

import App.App;
import App.Logic.App2.FileHandler2;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Stack;

public class RunUI {
    static Stage window;
    private static Stack<Scene> callStack;
    public static FileHandler2 fileHandler2;
//    public static FileHandler1 fileHandler1;

    //    static{
//        //todo make the close buttons global here
//    }
    public static void run(Stage stage) {
        callStack = new Stack<>();
//        initApp1();
        initApp2();
        initWindow(stage);
        new Landing();
    }

    private static void initWindow(Stage stage) {
        window = stage;
        window.initStyle(StageStyle.UNDECORATED);
        window.centerOnScreen();
        window.show();
    }

    public static void setScene(Scene scene, boolean addToCallStack) {
        if (addToCallStack)
            callStack.push(scene);
        window.setScene(scene);
        window.centerOnScreen();
    }

    public static void goBack() {
        callStack.pop();
        if (!callStack.isEmpty())
            setScene(callStack.peek(), false);
    }

    public static void popCallStack() {
        callStack.pop();
    }

    private static void initApp1() {
        //todo initApp2
    }

    private static void initApp2() {
        fileHandler2 = new FileHandler2(App.input1, App.input1);
    }

    public static void close() {
        fileHandler2.close();
        window.close();
    }
}
