/**
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-09
 * Compiler: IntelliJ IDEA
 * Purpose:
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards.
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules.
 * Understanding these variations is essential for proper implementation.
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */
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

/**
 * The SplashScreen class is responsible for displaying a splash screen
 * with a progress bar when the application starts. It provides a user-friendly
 * loading interface before transitioning to the main application or game.
 */
public class SplashScreen {
    private Stage stage;
    private Runnable onFinish; // Callback to start the main game

    /**
     * Constructs a new SplashScreen object responsible for displaying a splash screen
     * with a progress bar and transitioning to the main application after completion.
     *
     * @param stage the primary stage for displaying the splash screen
     * @param onFinish a callback to be executed after the splash screen completes
     */
    public SplashScreen(Stage stage, Runnable onFinish) {
        this.stage = stage;
        this.onFinish = onFinish;
        showSplash();
    }



    /**
     * Displays the splash screen with a progress bar and a styled background.
     * The splash screen is shown when the application starts and transitions
     * to the main application upon completion.
     *
     * The background consists of a tablecloth image applied to a rectangle,
     * and a progress bar is layered on top. A timeline animates the progress
     * bar to provide visual feedback during the loading process. Once the
     * animation is complete, a callback is triggered to start the main application.
     *
     * This method sets the scene, applies the splash screen layout, and starts
     * the animation to update the progress bar incrementally.
     */
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
