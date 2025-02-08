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

package View.Components.Popups;

import View.Components.GameButtons;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Class name: SettingPopUp
 * Purpose:
 * - Used for pop-up for setting controls.
 * @author John Rycca Belcina
 * @since 1.8
 */

public class SettingPopUp {
    private static Pane settingsPopup = null;

    public Pane createSettingsPopup() {
        if (settingsPopup == null) {

            settingsPopup = new Pane();
            Rectangle background = new Rectangle(900, 800);

            Image image = new Image(SettingPopUp.class.getResource("/com/zimgo/crazyeight/settingsbg.png").toExternalForm());
            ImagePattern backgroundDes = new ImagePattern(image);

            background.setFill(backgroundDes);
            settingsPopup.setPrefSize(900, 800);
            settingsPopup.setVisible(false);
            settingsPopup.getChildren().add(background);
            settingsPopup.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

            // Save & Close Buttons
            // Button saveButton = new Button("Save");
            Button closeButton = GameButtons.CloseButton();

            Button musicButton =  GameButtons.MusicSetButton();
            musicButton.setLayoutX(320);
            musicButton.setLayoutY(250);

            Button changeLang = GameButtons.LanguageButton();
            changeLang.setLayoutX(320);
            changeLang.setLayoutY(350);


            // Close Button
            closeButton.setOnAction(e -> settingsPopup.setVisible(false));

            settingsPopup.getChildren().addAll(closeButton, changeLang,musicButton);
        }
        return settingsPopup;
    }

    public static void toggleSettingsPopup() {
        if (settingsPopup != null) {
            settingsPopup.setVisible(!settingsPopup.isVisible());
        }
    }
}
