package View.Scene;

import View.Components.GameButtons;
import View.Components.Popups.SettingPopUp;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Menu {
    public Menu(Stage stage) {

        // Main Area
        AnchorPane table = new AnchorPane();

        // Table (Background)
        Rectangle mainTable = new Rectangle(1600, 900);
        // Background Image for table
        Image tablecloth = new Image(getClass().getResource("/com/zimgo/crazyeight/mainmenubg.png").toExternalForm());
        mainTable.setFill(new ImagePattern(tablecloth));

        // Add the table to the AnchorPane first
        table.getChildren().add(mainTable);

        //Quit Button
        Button startButton = GameButtons.StartButton(stage);
        Button joinButton = GameButtons.JoinButton();
        Button createButton = GameButtons.CreateButton();
        Button exitButton = GameButtons.ExitButton();
        Button settingButton = GameButtons.SettingButton();


        Pane settingsPane = SettingPopUp.createSettingsPopup();

        // Anchor settings for Button
        AnchorPane.setRightAnchor(exitButton, 750.0);
        AnchorPane.setBottomAnchor(exitButton, 100.0);

        AnchorPane.setRightAnchor(settingButton, 180.0);
        AnchorPane.setBottomAnchor(settingButton, 30.0);

        AnchorPane.setRightAnchor(joinButton, 725.0);
        AnchorPane.setBottomAnchor(joinButton, 250.0);

        AnchorPane.setRightAnchor(createButton, 725.0);
        AnchorPane.setBottomAnchor(createButton, 170.0);

        AnchorPane.setRightAnchor(startButton, 700.0);
        AnchorPane.setBottomAnchor(startButton, 350.0);

        // Anchor setting for popup
        AnchorPane.setRightAnchor(settingsPane, 350.0);
        AnchorPane.setTopAnchor(settingsPane, 50.0);


        //Add all stuffs in anchor
        table.getChildren().addAll(
                settingButton,joinButton,createButton,startButton,exitButton);

        //adding setting pop up on top
        table.getChildren().add(settingsPane);


        // Base
        BorderPane base = new BorderPane();
        base.setCenter(table);


        Scene scene = new Scene(base, 1600, 900);
        stage.setScene(scene);
        stage.setTitle("Crazy Eight");
        stage.setResizable(false);
        stage.show();


    }
}
