package App;

import App.UI.RunUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static final String input1 = "src/App/Data/input1.txt";
    public static final String output1 = "src/App/Data/output1.txt";

    public static final String input2 = "src/App/Data/input2.txt";
    public static final String output2 = "src/App/Data/output2.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //RunQ1.run();
        RunUI.run(stage);
        // RunQ2.run();

    }
}