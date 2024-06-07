package App;

import App.Logic.App1.RunQ1;
import App.UI.RunUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        RunQ1.run();
        RunUI.run(stage);
        // RunQ2.run();
    }
}