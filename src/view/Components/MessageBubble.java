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


package view.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class name: MessageBubble
 * Purpose: Represent chat bubble
 * @author John Rycca Belcina
 * @since 1.8
 */

public class MessageBubble {

    /**
     * Bubble for the current player using the game
     * @param message message from the user
     * @param playerName name of the player
     * @param position position of the bubble
     * @return Vbox for bubble
     */
    public static VBox currentPlayerMessageBubble(String message, String playerName, Pos position){

        VBox inside = new VBox();
        inside.setPadding(new Insets(10));
        Label label = new Label(playerName);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        label.setStyle("-fx-text-fill: #f38731;");
        Text text2 = new Text(message);
        text2.setFill(Color.WHITE);
        text2.setWrappingWidth(200);
        inside.setAlignment(position);

        inside.setStyle("-fx-background-color: rgba(21, 61, 88, 0.8); " +
                "-fx-border-color: black; " +       // Set border color
                "-fx-background-radius: 20px;" +
                "-fx-border-width: 0px;");
        inside.getChildren().addAll(label,text2);

        return inside;
    }

    /**
     * Bubble for the other player using the game
     * @param message message from the user
     * @param playerName name of the player
     * @param position position of the bubble
     * @return Vbox for bubble
     */
    public static VBox otherPlayerMessageBubble(String message, String playerName, Pos position){

        VBox inside = new VBox();
        inside.setPadding(new Insets(10));
        Label label = new Label(playerName);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        label.setStyle("-fx-text-fill: #803946;");
        Text text2 = new Text(message);
        text2.setFill(Color.WHITE);
        text2.setWrappingWidth(200);
        inside.setAlignment(position);

        inside.setStyle("-fx-background-color: rgba(85, 78, 106, 0.8); " +
                "-fx-border-color: black; " +       // Set border color
                "-fx-background-radius: 20px;" +
                "-fx-border-width: 0px;");
        inside.getChildren().addAll(label,text2);

        return inside;
    }
}
