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

import model.SingleGameModel;
import view.area.*;
import view.area.dealer.OpenStack;
import view.components.GameButtons;
import view.components.Notification;
import view.components.SuiteChooser;
import view.components.popups.SettingPopUp;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;


/**
 * The InGame class represents the main game scene for the Crazy Eight game.
 * It manages the initialization and setup of all game components, user interface (UI),
 * and player interactions. This class works closely with the game model and other
 * supporting classes to render the game state and enable user interaction.
 */
public class InGame {

    private Stage stage;
    private AnchorPane table;
    private StackPane chatBox;
    private Notification notification;
    private SuiteChooser suiteChooser;
    private SingleGameModel singleGameModel;

    /**
     * Constructs an instance of the InGame class, initializing the game user interface.
     *
     * @param stage the primary stage of the application, used for managing the game scene
     */
    public InGame(Stage stage){
        this.stage = stage;
        initGame();
    }

    /**
     * Initializes the game by setting up the user interface components, game board,
     * buttons, additional components, and the game scene.
     *
     * This method prepares all necessary elements for the gameplay, combining various
     * setup methods to create a functional and visually complete game interface.
     *
     * Steps involved in initialization:
     * 1. Sets up the game board, including the main table, player areas, and game components
     *    like decks and open stacks.
     * 2. Adds buttons such as the quit and settings buttons to the interface.
     * 3. Sets up supplementary components including the chat box, notification banners,
     *    settings, and suite chooser.
     * 4. Configures the main scene using the base layout to display all the components.
     */
    private void initGame(){
        table = new AnchorPane();

        setupGameBoard();
        setupButtons();
        setupOtherComp();
        setupScene();
    }

    /**
     * Configures and initializes additional user interface components for the game, including
     * the chat interface, settings popup, notification system, and suite chooser. These elements
     * are added to the main display area and their positions are set using anchor points.
     *
     * The method performs the following steps:
     * 1. Creates and adds the chat area, which includes chat and log displays, to the main table.
     * 2. Creates and positions the settings popup pane in the UI.
     * 3. Retrieves and integrates the notification system, setting its position in the UI.
     * 4. Retrieves and positions the suite chooser component on the main table.
     * 5. Adds all the created components (chat area, settings popup, notification, suite chooser)
     *    to the main container.
     */
    private void setupOtherComp(){
        ChatArea chatArea = new ChatArea();
        chatBox = chatArea.getChatArea(singleGameModel.getLogView());
        table.getChildren().add(chatBox);

        SettingPopUp settingPopUp = new SettingPopUp();
        Pane settingsPane = settingPopUp.createSettingsPopup();

        //Notification
        notification = singleGameModel.getNotification();
        StackPane tempNotification = notification.outputNotification();

        //SuiteChooser
        suiteChooser = singleGameModel.getSuiteChooser();
        StackPane suiteArea = singleGameModel.getSuiteChooser().generateSuiteChooser();

        //Anchor for suite chooser
        AnchorPane.setRightAnchor(suiteArea, 280.0);
        AnchorPane.setBottomAnchor(suiteArea, 300.0);
        // Anchor setting for popup
        AnchorPane.setRightAnchor(settingsPane, 350.0);
        AnchorPane.setTopAnchor(settingsPane, 50.0);

        //Notification positioning
        AnchorPane.setLeftAnchor(tempNotification, 360.0);
        AnchorPane.setBottomAnchor(tempNotification, 170.0);

        //Notification positioning
        AnchorPane.setLeftAnchor(tempNotification, 360.0);
        AnchorPane.setBottomAnchor(tempNotification, 170.0);

        //Adding to table
        table.getChildren().add(tempNotification);
        table.getChildren().add(settingsPane);
        table.getChildren().add(suiteArea);
    }

    /**
     * Sets up the game board layout and components for the Crazy Eight game.
     *
     * This method initializes and arranges the graphical elements that form the game board,
     * such as the background, player tables, dealer deck, and open card stack.
     *
     * The following steps are performed:
     * - Creates and configures an AnchorPane as the base layout for the game board.
     * - Sets up the main table background with dimensions and a tablecloth image.
     * - Initializes the game model, including player tables, open stack, and dealer stack.
     * - Positions the dealer's deck and open stack components on the game board using
     *   specific coordinates.
     * - Adds the dealer's deck and open stack to the game board layout.
     * - Calls the `positionPlayerTables` method to place player areas on the game board
     *   based on the list of `PlayerTable` objects retrieved from the game model.
     */
    private void setupGameBoard(){
        table = new AnchorPane();

        Rectangle mainTable = new Rectangle(1300, 900);
        Image tablecloth = new Image(getClass().getResource("/com/zimgo/crazyeight/tablecloth1.png").toExternalForm());
        mainTable.setFill(new ImagePattern(tablecloth));

        table.getChildren().add(mainTable);

        // Use GameModel for game state
         singleGameModel = new SingleGameModel();

        // Now, gameModel contains all necessary models and controllers
        List<PlayerTable> playerTables = singleGameModel.getPlayerTables();
        OpenStack openStack = singleGameModel.getOpenStack();
        StackPane deck = singleGameModel.getCardsStackFaceDown().getCardsStackFaceDown();
        Pane OSNode = openStack.getCurrentOpenStack();

        // Position dealer's deck
        AnchorPane.setLeftAnchor(deck, 450.0);
        AnchorPane.setBottomAnchor(deck, 400.0);
        AnchorPane.setRightAnchor(OSNode, 500.0);
        AnchorPane.setBottomAnchor(OSNode, 400.0);

        table.getChildren().add(deck);
        table.getChildren().add(OSNode);

        // Position player tables
        positionPlayerTables(playerTables);
    }

    /**
     * Configures and adds the quit and settings buttons to the game interface.
     *
     * This method creates the quit and settings buttons using predefined methods from
     * the GameButtons class and positions them within the user interface using the
     * AnchorPane layout. The buttons are then added to the main table.
     *
     * The following actions are performed:
     * - Creates the quit button using GameButtons.QuitButton and passes required parameters.
     * - Creates the settings button using GameButtons.SettingButton.
     * - Positions the quit button at the bottom-right of the screen with specific anchor values.
     * - Positions the settings button below the quit button with specific anchor values.
     * - Adds both buttons to the main table container.
     */
    private void setupButtons(){
        Button quitButton = GameButtons.QuitButton(stage,singleGameModel);
        Button settingButton = GameButtons.SettingButton();


        AnchorPane.setRightAnchor(quitButton, 40.0);
        AnchorPane.setBottomAnchor(quitButton, 50.0);
        AnchorPane.setRightAnchor(settingButton, 40.0);
        AnchorPane.setBottomAnchor(settingButton, 10.0);

        table.getChildren().addAll(quitButton, settingButton);
    }

    /**
     * Positions the player tables on the game board and adjusts their rotation and anchor points
     * according to the predefined layout.
     *
     * @param playerTables the list of PlayerTable objects representing the tables
     *                     of players in the game to be positioned on the board
     */
    private void positionPlayerTables(List<PlayerTable> playerTables) {
        Pane PT1 = playerTables.get(0).getCurrentUserTable();
        Pane PT2 = playerTables.get(1).getCurrentUserTable();
        Pane PT3 = playerTables.get(2).getCurrentUserTable();
        Pane PT4 = playerTables.get(3).getCurrentUserTable();

        AnchorPane.setLeftAnchor(PT1, 630.0);
        AnchorPane.setBottomAnchor(PT1, 20.0);

        PT2.setRotate(90);
        AnchorPane.setLeftAnchor(PT2, 40.0);
        AnchorPane.setTopAnchor(PT2, 375.0);

        PT4.setRotate(-90);
        AnchorPane.setRightAnchor(PT4, 40.0);
        AnchorPane.setBottomAnchor(PT4, 430.0);

        AnchorPane.setLeftAnchor(PT3, 630.0);
        AnchorPane.setTopAnchor(PT3, 20.0);

        table.getChildren().addAll(PT1, PT2, PT3, PT4);
    }

    /**
     * Configures and sets up the main scene for the Crazy Eight game interface.
     *
     * This method initializes the base layout of the application by positioning
     * the game table in the center and the chat box on the right side of the screen.
     * It then creates a Scene object with the specified dimensions (1600x900),
     * assigns it to the primary stage, and sets the title of the application window.
     * The method also disables the resizable property of the stage and displays
     * the window to the user.
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

