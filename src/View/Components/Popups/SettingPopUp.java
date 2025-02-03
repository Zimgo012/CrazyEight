package View.Components.Popups;

import View.Components.GameButtons;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class SettingPopUp {
    private static Pane settingsPopup = null;

    public static Pane createSettingsPopup() {
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
