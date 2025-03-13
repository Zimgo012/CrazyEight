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
package view.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.LogModel;
import model.SingleGameModel;

/**
 * The Log class is responsible for generating and managing the display of a log UI component
 * within a JavaFX application. It interacts with the LogModel and SingleGameModel to retrieve
 * and display log messages in a scrollable format.
 */
public class Log {
    private LogModel logModel;
    private SingleGameModel singleGameModel;
    private VBox vbox; // Store VBox globally

    /**
     * Constructs an instance of the Log class, which is responsible for generating and managing
     * the display of a log UI component within a JavaFX application. It interacts with the
     * LogModel and SingleGameModel to handle and display log messages.
     *
     * @param logModel the model containing the log messages to be displayed
     * @param singleGameModel the model representing a single game context, used for interaction
     */
    public Log(LogModel logModel, SingleGameModel singleGameModel) {
        this.logModel = logModel;
        this.singleGameModel = singleGameModel;
        this.vbox = new VBox(10); // Adjust spacing

        vbox.setPrefSize(250, 550);
        vbox.setPadding(new Insets(20));
    }

    /**
     * Generates and configures a scrollable pane to display log messages within a specified
     * visual style and size constraints.
     *
     * @return a ScrollPane configured with a transparent background, limited width and height,
     *         and containing a VBox for displaying the log messages.
     */
    public ScrollPane generateLog() {
        ScrollPane log = new ScrollPane();
        log.setContent(vbox);
        log.setStyle("-fx-background: transparent; -fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 20px;");
        log.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        log.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        log.setMaxHeight(200);
        log.setMaxWidth(260);

        return log;
    }

    /**
     * Updates the UI component responsible for displaying the log messages.
     *
     * This method clears the current visual representation of the log messages
     * in the VBox and replaces them with the latest messages retrieved from the
     * log model. Each message is rendered as a new Label added to the VBox.
     *
     * The method ensures that the displayed log messages reflect the most recent
     * state of the log list managed by the log model.
     *
     * It is typically called when the log content has been modified or needs to
     * be refreshed to provide updated information to the user.
     */
    public void refreshLog() {
        vbox.getChildren().clear(); // Remove old logs
        for (String message : logModel.getLogList()) {
            vbox.getChildren().add(new Label(message));
        }
    }
}
