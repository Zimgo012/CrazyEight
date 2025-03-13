/**
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-05
 * Compiler: IntelliJ IDEA
 * Purpose:
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards.
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules.
 * Understanding these variations is essential for proper implementation.
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */
package view.area;

import view.components.Chat;
import view.components.Log;
import view.components.GameButtons;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * The ChatArea class is responsible for creating and organizing the user interface elements
 * for the chat feature in the application. It includes a background, components for viewing
 * chat logs, a text field for message input, and a send button.
 */
public class ChatArea {

    private Log log;

    public ChatArea(){

    }
    /**
     * Creates and returns a chat area UI component comprised of a background,
     * scrollable sections for chat and log displays, a text input field, and a send button.
     *
     * @param log the Log instance used to generate the log display component within the chat area
     * @return a StackPane containing all the components of the chat area
     */
    public StackPane getChatArea(Log log){

        StackPane chatBox = new StackPane();

        //Background
        Rectangle mainBackground = new Rectangle(300, 900);
        Image tablecloth = new Image(ChatArea.class.getResource("/com/zimgo/crazyeight/chatOverlay.png").toExternalForm());
        mainBackground.setFill(new ImagePattern(tablecloth));

        AnchorPane anchorPane = new AnchorPane();


        ScrollPane logScrollPane = log.generateLog();
        VBox vBox = new VBox();
        vBox.getChildren().add(logScrollPane);

        AnchorPane.setTopAnchor(vBox, 30.0);
        AnchorPane.setRightAnchor(vBox, 20.0);


        ScrollPane chatScrollPane = Chat.chatScrollPane();
        VBox vBox2 = new VBox();
        vBox2.getChildren().add(chatScrollPane);


        AnchorPane.setBottomAnchor(vBox2, 90.0);
        AnchorPane.setRightAnchor(vBox2, 20.0);

        //Text Field
        TextField textField = new TextField();

        //Text Field Properties
        textField.setStyle("-fx-background-color: #f38731; "
                + "-fx-border-color: #803946; "
                + "-fx-border-width: 4px; "
                + "-fx-border-radius: 0px; "
                + "-fx-background-radius: 0px; "
                + "-fx-prompt-text-fill: rgba(34,34,34,0.48); "
                + "-fx-text-fill: #020202;");
        textField.setPromptText("Enter text here...");
        textField.setMinWidth(200);
        textField.setMinHeight(30);
        textField.setFont(Font.font("Verdana",15));
        AnchorPane.setBottomAnchor(textField,20.0);
        AnchorPane.setLeftAnchor(textField,20.0);

        //Send Button
        Button sendButton = GameButtons.SendButton();
        AnchorPane.setRightAnchor(sendButton,15.0);
        AnchorPane.setBottomAnchor(sendButton,20.0);

        anchorPane.getChildren().addAll(textField,sendButton,vBox2,vBox);

        //Adding to chat area
        chatBox.getChildren().add(mainBackground);
        chatBox.getChildren().add(anchorPane);


        return chatBox;
    }
}
