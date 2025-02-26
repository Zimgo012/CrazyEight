/**
 * File name: Notification.java
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
package view.components;


import view.components.popups.SettingPopUp;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class name: Notification
 * Purpose: This class represent notification banner that pop outs if there is notification
 * @author John Rycca Belcina
 * @since 1.8
 */
public class Notification {

    /**
     *  This represent notification banner
     * @return StackPane that represent the notification
     */
    public  StackPane getNotification(){

        StackPane notification = new StackPane();
        Rectangle background = new Rectangle(550, 70);

        Image image = new Image(SettingPopUp.class.getResource("/com/zimgo/crazyeight/notificationBanner.png").toExternalForm());
        ImagePattern backgroundDes = new ImagePattern(image);

        background.setFill(backgroundDes);

        Text text = new Text(" Invalid move! You cannot play this card!");
        text.setFont(new Font("Arial", 20));
        text.setFill(Color.BLACK);

        notification.setPrefSize(500, 150);
        notification.getChildren().add(background);
        notification.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");
        notification.getChildren().add(text);


        return notification;

    }
}

