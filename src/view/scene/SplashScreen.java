package view.scene;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen {
    private Stage stage;
    private Runnable onFinish; // Callback to start the main game

    public SplashScreen(Stage stage, Runnable onFinish) {
        this.stage = stage;
        this.onFinish = onFinish;
        showSplash();
    }



    private void showSplash(){

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);


        StackPane base = new StackPane();
        Rectangle mainTable = new Rectangle(1600, 900);
        Image tablecloth = new Image(getClass().getResource("/com/zimgo/crazyeight/tablecloth2.png").toExternalForm());
        mainTable.setFill(new ImagePattern(tablecloth));

        // Positioning elements
        base.getChildren().addAll(mainTable, progressBar);
        StackPane.setMargin(progressBar, new javafx.geometry.Insets(50, 0, 0, 0));

        // Create Scene
        Scene scene = new Scene(base, 1600, 900);
        stage.setScene(scene);
        stage.setTitle("Crazy Eight");
        stage.setResizable(false);
        stage.show();

        // Create a timeline to update the progress bar smoothly
        Timeline timeline = new Timeline();
        for (int i = 1; i <= 30; i++) {
            double progress = i / 30.0;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 100), e -> progressBar.setProgress(progress)));
        }

        // After the timeline completes, transition to the game
        timeline.setOnFinished(e -> Platform.runLater(onFinish));
        timeline.play();
    }
}
