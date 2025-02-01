import View.Scene.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//       InGame inGame = new InGame(stage);
       Menu menu = new Menu(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}

