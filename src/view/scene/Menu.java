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

package view.scene;
import view.components.GameButtons;
import view.components.Notification;
import view.components.popups.SettingPopUp;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * The Menu class represents the user interface for the main menu of the "Crazy Eight" application.
 * It is responsible for setting up and managing various components such as the background, buttons,
 * settings, and chat interface.
 */

public class Menu {

    private Stage stage;
    private AnchorPane table;
    private StackPane chatBox;
    private StackPane cardsStack;
    private Notification notification;

    /**
     * Constructs a new instance of the Menu class.
     * Initializes the user interface components for the menu.
     *
     * @param stage the primary stage of the application, onto which the menu components
     * will be added. The stage is the main window of the JavaFX application.
     */
    public Menu(Stage stage) {
        this.stage = stage;
        initMenu();
    }

    /**
     * Initializes the menu components by setting up the background, buttons,
     * additional components, and the scene layout. This method is responsible
     * for assembling the graphical interface of the main menu.
     *
     * The method performs the following:
     * - Creates an AnchorPane as the base container for the menu.
     * - Calls `setupBackground()` to set the visual background for the menu.
     * - Calls `setupButtons()` to configure and place interactive buttons.
     * - Calls `setupOtherComp()` to add additional UI components, such as settings popups.
     * - Calls `setupScene()` to finalize the scene layout and display the menu on the application stage.
     */
    private void initMenu(){
        table = new AnchorPane();

        setupBackground();
        setupButtons();
        setupOtherComp();
        setupScene();
    }

    /**
     * Set ups background
     */
    private void setupBackground(){
        // Table (Background)
        Rectangle mainTable = new Rectangle(1600, 900);
        // Background Image for table
        Image tablecloth = new Image(getClass().getResource("/com/zimgo/crazyeight/mainmenubg.png").toExternalForm());
        mainTable.setFill(new ImagePattern(tablecloth));

        // Add the table to the AnchorPane first
        table.getChildren().add(mainTable);
    }

    /**
     * Setting other components
     */
    private void setupOtherComp() {
        SettingPopUp settingPopUp = new SettingPopUp();
        Pane settingsPane = settingPopUp.createSettingsPopup();

        // Anchor setting for popup
        AnchorPane.setRightAnchor(settingsPane, 350.0);
        AnchorPane.setTopAnchor(settingsPane, 50.0);

        //adding setting pop up on top
        table.getChildren().add(settingsPane);
    }

    /**
     * Setting up button
     */
    private void setupButtons(){

        //Quit Button
        Button startButton = GameButtons.StartButton(stage);
        Button joinButton = GameButtons.JoinButton();
        Button createButton = GameButtons.CreateButton();
        Button exitButton = GameButtons.ExitButton();
        Button settingButton = GameButtons.SettingButton();

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


        //Add all stuffs in anchor
        table.getChildren().addAll(settingButton,joinButton,createButton,startButton,exitButton);
    }

    /**
     * Set ups scene
     */
    private void setupScene(){
        // Base
        BorderPane base = new BorderPane();
        base.setCenter(table);
        base.setRight(chatBox);

        //Adding Scene
        Scene scene = new Scene(base, 1600, 900);
        stage.setScene(scene);
        stage.setTitle("Crazy Eight");
        stage.setResizable(false);
        stage.show();
    }
}
