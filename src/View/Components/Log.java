/**
 * File name: Chat.java
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-07
 * Compiler: IntelliJ IDEA 
 * Purpose: 
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards. 
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules. 
 * Understanding these variations is essential for proper implementation. 
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */
package View.Components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Class name: Log
 * Purpose: To contain movement log
 * @author John Rycca Belcina
 * @since 1.8
 */

public class Log {

    /**
     * This component has logs.
     * @return ScrollPane that logs movement
     */
    public static ScrollPane logScrollPane(){

        ScrollPane log = new ScrollPane();

        VBox vbox = new VBox(20);
        vbox.setPrefSize(250,550);
        vbox.setPadding(new Insets(20));

        // List of log
        ObservableList<String> messages = FXCollections.observableArrayList("Player 1 draw queen!", "Player 4 draw SUPER EIGHT","A","A","A","A","A","A","A","A","A","A");
        for (String name : messages ) {
            vbox.getChildren().add(new Label(name));
        }

        log.setStyle("-fx-background: transparent; -fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 20px;");

        log.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        log.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        log.setContent(vbox);
        log.setMaxHeight(200);
        log.setMaxWidth(260);


        return log;
    }
}
