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
package View.Area;


import View.Components.GameButtons;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Class name: ChatArea
 * Purpose: A Chat Area UI is the section of a messaging application where users can send and receive messages.
 * @author John Rycca Belcina
 * @since 1.8
 */

public class ChatArea {

    /**
     * Creates a section of chat
     * @return the stackPane of this section
     */
    public static StackPane ChatArea(){

        StackPane chatBox = new StackPane();

        //Background
        Rectangle mainBackground = new Rectangle(300, 900);
        Image tablecloth = new Image(ChatArea.class.getResource("/com/zimgo/crazyeight/chatOverlay.png").toExternalForm());
        mainBackground.setFill(new ImagePattern(tablecloth));

        AnchorPane anchorPane = new AnchorPane();


        //Text Field

        TextField textField = new TextField();

        //Text Field Properties
        textField.setStyle("-fx-background-color: #f38731; " // Change background
                + "-fx-border-color: #803946; "         // Change border color
                + "-fx-border-width: 4px; "         // Border thickness
                + "-fx-border-radius: 0px; "       // Rounded corners
                + "-fx-background-radius: 0px; "
                + "-fx-prompt-text-fill: rgba(34,34,34,0.48); "   // Placeholder text color
                + "-fx-text-fill: #020202;");       // Text color
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


        //Adding to Anchor Pane
        anchorPane.getChildren().addAll(textField,sendButton);

        //Adding to chat area
        chatBox.getChildren().add(mainBackground);
        chatBox.getChildren().add(anchorPane);


        return chatBox;
    }
}
