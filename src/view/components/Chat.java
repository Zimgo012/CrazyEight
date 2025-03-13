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
package view.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * The Chat class provides functionality for creating a ScrollPane-based chat user interface.
 * This chat UI displays messages from multiple players in the form of chat bubbles with
 * player-specific styling and alignment.
 */

public class Chat {

    /**
     * Creates and configures a ScrollPane for displaying chat messages. The ScrollPane includes
     * a VBox containing message bubbles for different players, styled and aligned based on player identity.
     * The chat area supports dynamically adding messages with predefined styling and alignment.
     *
     * @return a ScrollPane containing the chat interface with styled message bubbles
     */
    public static ScrollPane chatScrollPane(){

        ScrollPane chat = new ScrollPane();

        VBox vbox = new VBox(20);
        vbox.setPrefSize(260,550);
        vbox.setPadding(new Insets(20));


        // List of Chat
        //Temporary
        ObservableList<String> names = FXCollections.observableArrayList("Player 1","B","B","B","Player 1","Player 1","C","C","Player 1","Player 1","B","B","B","B","B","Player 1","D","D","Player 1");
        for (String name : names) {

            if(name.equals("Player 1")){


                String message = "this is a message, sample sample";
                Pos position = Pos.CENTER_RIGHT;

                vbox.getChildren().add(MessageBubble.currentPlayerMessageBubble(message,"Player1",position));

            }else if(name.equals("B")){

                String message = "this is a message, sample sample";
                Pos position = Pos.CENTER_LEFT;


                vbox.getChildren()
                        .add(MessageBubble.otherPlayerMessageBubble(message,"Player2",position));


            }else if (name.equals("C")){

                String message = "this is a message, sample sample";
                Pos position = Pos.CENTER_LEFT;

                vbox.getChildren()
                        .add(MessageBubble.otherPlayerMessageBubble(message,"Player3",position));

            }else if(name.equals("D")){

                String message = "this is a message, sample sample";
                Pos position = Pos.CENTER_LEFT;

                vbox.getChildren()
                        .add(MessageBubble.otherPlayerMessageBubble(message,"Player4",position));
            }

        }

        chat.setStyle("-fx-background: transparent; -fx-background-color: rgba(0,0,0,0.0);");

        chat.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chat.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        chat.setContent(vbox);
        chat.setMaxHeight(600);
        chat.setMaxWidth(260);


        return chat;
    }

}
