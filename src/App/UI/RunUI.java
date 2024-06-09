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
//        window.setResizable(true);
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

    public static Scene popCallStack() {
        return callStack.pop();
    }

    public static void pushCallStack(Scene s) {
        callStack.push(s);
    }

    private static void initApp1() {
        //todo initApp2
    }

    private static void initApp2() {
        fileHandler2 = new FileHandler2(App.input2, App.output2);
    }

    public static void migrateOutput(){
        fileHandler2.migrateOutput();
    }

    public static void close() {
        fileHandler2.close();
        window.close();
    }

    //todo maybe set working tree
}
