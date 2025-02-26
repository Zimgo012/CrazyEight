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

//import controller.CardController;
import controller.CardController;
import model.PlayerTableModel;
import view.area.*;
//import view.Area.Deck.CardsStackFaceDown;
//import view.Area.Deck.OpenStack;
import view.area.dealer.CardsStackFaceDown;
import view.area.dealer.OpenStack;
import view.components.GameButtons;
import view.components.Notification;
import view.components.popups.SettingPopUp;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


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
    private PlayerTable playerTable;
    private CardController cardController;
    private OpenStack openStack;
    private PlayerTableModel playerTableModel;
    private CardsStackFaceDown cardsStackFaceDown;

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
        setupPlayerTables();
        setupButtons();
        setupOtherComp();
        setupScene();
    }

    /**
     * Set ups other components
     */
    private void setupOtherComp(){
        ChatArea chatArea = new ChatArea();
        chatBox = chatArea.getChatArea();
        table.getChildren().add(chatBox);

        SettingPopUp settingPopUp = new SettingPopUp();
        Pane settingsPane = settingPopUp.createSettingsPopup();

        notification = new Notification();
        StackPane tempNotification = notification.getNotification();

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
    }

    /**
     * Set ups game board
     */
    private void setupGameBoard(){

        // Table (Background)
        Rectangle mainTable = new Rectangle(1300, 900);
        // Background Image for table
        Image tablecloth = new Image(getClass().getResource("/com/zimgo/crazyeight/tablecloth1.png").toExternalForm());
        mainTable.setFill(new ImagePattern(tablecloth));

        // Add the table to the AnchorPane first
        table.getChildren().add(mainTable);
    }

    /**
     * Set ups Buttons
     */
    private void setupButtons(){
        Button quitButton = GameButtons.QuitButton(stage);
        Button settingButton = GameButtons.SettingButton();


        AnchorPane.setRightAnchor(quitButton, 60.0);
        AnchorPane.setBottomAnchor(quitButton, 90.0);
        AnchorPane.setRightAnchor(settingButton, 45.0);
        AnchorPane.setBottomAnchor(settingButton, 30.0);

        table.getChildren().addAll(quitButton, settingButton);
    }

    /**
     * Sets up tables
     */
    private void setupPlayerTables(){
        //Player 1 Table

        playerTableModel = new PlayerTableModel();
        openStack = new OpenStack();
        playerTable = new PlayerTable(playerTableModel,openStack);


        cardController = new CardController(playerTable);

        cardsStackFaceDown = new CardsStackFaceDown(cardController);
        StackPane deck = cardsStackFaceDown.getCardsStackFaceDown();

        //  Anchor Card Deck facing Down
        AnchorPane.setLeftAnchor(deck, 450.0);
        AnchorPane.setBottomAnchor(deck, 400.0);


        Pane PTNode = playerTable.getCurrentUserTable();

        //Anchor PTNode
        AnchorPane.setLeftAnchor(PTNode, 280.0);
        AnchorPane.setBottomAnchor(PTNode, 20.0);

        //Player 2 Table
        PlayerTable playerTable2 = new PlayerTable(new PlayerTableModel(),openStack);
        Pane PT2Node = playerTable2.getCurrentUserTable();

        //Anchor PTNode2
        PT2Node.setRotate(90);
        AnchorPane.setLeftAnchor(PT2Node, 10.0);
        AnchorPane.setBottomAnchor(PT2Node, 400.0);

        //Player 2 Table
        PlayerTable playerTable3 = new PlayerTable(new PlayerTableModel(),openStack);
        Pane PT3Node = playerTable3.getCurrentUserTable();

        //Anchor PTNode3
        PT3Node.setRotate(-90);
        AnchorPane.setRightAnchor(PT3Node, 10.0);
        AnchorPane.setBottomAnchor(PT3Node, 400.0);

        //Player 4 Table
        PlayerTable playerTable4 = new PlayerTable(new PlayerTableModel(),openStack);
        Pane PT4Node = playerTable4.getCurrentUserTable();

        //Anchor PTNode4
        AnchorPane.setRightAnchor(PT4Node, 490.0);
        AnchorPane.setTopAnchor(PT4Node, 40.0);


        Pane OSNode = openStack.getCurrentOpenStack();

        //Anchor OSNode
        AnchorPane.setRightAnchor(OSNode, 500.0);
        AnchorPane.setBottomAnchor(OSNode, 400.0);

        table.getChildren().add(deck);
        table.getChildren().addAll(PTNode,PT2Node,PT3Node,PT4Node);
        table.getChildren().add(OSNode);

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

