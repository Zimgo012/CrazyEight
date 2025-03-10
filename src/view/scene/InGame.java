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
 * Class name: InGame
 * Purpose:
 * - In game scene for to play the game
 * @author John Rycca Belcina
 * @since 1.8
 */
public class InGame {

    private Stage stage;
    private AnchorPane table;
    private StackPane chatBox;
    private Notification notification;
    private SuiteChooser suiteChooser;
    private SingleGameModel singleGameModel;

    /**
     * Initialize Stage
     * @param stage stage
     */
    public InGame(Stage stage){
        this.stage = stage;
        initGame();
    }

    /**
     * Initialize UI
     */
    private void initGame(){
        table = new AnchorPane();

        setupGameBoard();
        setupButtons();
        setupOtherComp();
        setupScene();
    }

    /**
     * Set ups other components
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
     * Set ups game board
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
     * Set ups Buttons
     */
    private void setupButtons(){
        Button quitButton = GameButtons.QuitButton(stage);
        Button settingButton = GameButtons.SettingButton();


        AnchorPane.setRightAnchor(quitButton, 40.0);
        AnchorPane.setBottomAnchor(quitButton, 50.0);
        AnchorPane.setRightAnchor(settingButton, 30.0);
        AnchorPane.setBottomAnchor(settingButton, 10.0);

        table.getChildren().addAll(quitButton, settingButton);
    }

    /**
     * Sets up tables
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
     * Sets up Scene
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

