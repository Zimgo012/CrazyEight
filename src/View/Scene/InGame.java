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

package View.Scene;

import View.Area.*;
import View.Components.Cards.OpenStackCard;
import View.Components.Cards.PlayerCard;
import View.Components.GameButtons;
import View.Components.Popups.SettingPopUp;
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

    public InGame(Stage stage){

    //Chat
        StackPane chatBox = ChatArea.ChatArea();

    // Main Area (Center)
        AnchorPane table = new AnchorPane();

    // Table (Background)
        Rectangle mainTable = new Rectangle(1300, 900);
    // Background Image for table
        Image tablecloth = new Image(getClass().getResource("/com/zimgo/crazyeight/tablecloth1.png").toExternalForm());
        mainTable.setFill(new ImagePattern(tablecloth));

        // Add the table to the AnchorPane first
        table.getChildren().add(mainTable);

        Pane settingsPane = SettingPopUp.createSettingsPopup();

        // Anchor setting for popup
        AnchorPane.setRightAnchor(settingsPane, 350.0);
        AnchorPane.setTopAnchor(settingsPane, 50.0);


        //Quit Button
        Button quitButton = GameButtons.QuitButton(stage);
        Button settingButton = GameButtons.SettingButton();


        //Anchor Button
        AnchorPane.setRightAnchor(quitButton, 60.0);
        AnchorPane.setBottomAnchor(quitButton, 90.0);
        AnchorPane.setRightAnchor(settingButton, 45.0);
        AnchorPane.setBottomAnchor(settingButton, 30.0);


        StackPane cardsStackFaceDown = CardsStackFaceDown.CardsStackFaceDown();

        //  Anchor Card Deck facing Down
        AnchorPane.setLeftAnchor(cardsStackFaceDown, 450.0);
        AnchorPane.setBottomAnchor(cardsStackFaceDown, 400.0);

        //Player 1 Table
        PlayerTable playerTable = new PlayerTable();
        Pane PTNode = playerTable.getCurrentUserTable();

        playerTable.addCard(new PlayerCard("Diamond",1));
        playerTable.addCard(new PlayerCard("Hearts",1));
        playerTable.addCard(new PlayerCard("Spade",4));
        playerTable.addCard(new PlayerCard("Clubs",5));
        playerTable.addCard(new PlayerCard("Diamond",11));
        playerTable.addCard(new PlayerCard("Hearts",12));
        playerTable.addCard(new PlayerCard("Spade",13));
        playerTable.addCard(new PlayerCard("Clubs",12));
        playerTable.addCard(new PlayerCard("Diamond",7));
        playerTable.addCard(new PlayerCard("Hearts",6));
        playerTable.addCard(new PlayerCard("Diamond",11));
        playerTable.addCard(new PlayerCard("Hearts",12));
        playerTable.addCard(new PlayerCard("Spade",8));
        playerTable.addCard(new PlayerCard("Clubs",12));
        playerTable.addCard(new PlayerCard("Diamond",7));
        playerTable.addCard(new PlayerCard("Hearts",6));
        playerTable.addCard(new PlayerCard("Spade",13));
        playerTable.addCard(new PlayerCard("Clubs",12));

        //Anchor PTNode
        AnchorPane.setLeftAnchor(PTNode, 80.0);
        AnchorPane.setBottomAnchor(PTNode, 20.0);

        //Player 2 Table
        Player2Table playerTable2 = new Player2Table();
        Pane PT2Node = playerTable2.getCurrentUserTable();

        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable2.addCard(new OpenStackCard("OtherCards",3,false));

        //Anchor PTNode2
        PT2Node.setRotate(90);
        AnchorPane.setLeftAnchor(PT2Node, 10.0);
        AnchorPane.setBottomAnchor(PT2Node, 600.0);

        //Player 2 Table
        Player2Table playerTable3 = new Player2Table();
        Pane PT3Node = playerTable3.getCurrentUserTable();

        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable3.addCard(new OpenStackCard("OtherCards",3,false));

        //Anchor PTNode4
        PT3Node.setRotate(90);
        AnchorPane.setRightAnchor(PT3Node, 10.0);
        AnchorPane.setBottomAnchor(PT3Node, 600.0);

        //Player 4 Table
        Player2Table playerTable4 = new Player2Table();
        Pane PT4Node = playerTable4.getCurrentUserTable();

        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));
        playerTable4.addCard(new OpenStackCard("OtherCards",3,false));

        //Anchor PTNode4
        AnchorPane.setRightAnchor(PT4Node, 490.0);
        AnchorPane.setTopAnchor(PT4Node, 40.0);

        OpenStack openStack = new OpenStack();
        Pane OSNode = openStack.getCurrentOpenStack();
        openStack.addCard(new OpenStackCard("Hearts",6));
        openStack.addCard(new OpenStackCard("Clubs",12));
        openStack.addCard(new OpenStackCard("Diamond",11));

        //Anchor OSNode
        AnchorPane.setRightAnchor(OSNode, 500.0);
        AnchorPane.setBottomAnchor(OSNode, 400.0);


        //Add all stuffs in anchor
        table.getChildren().add(chatBox);
        table.getChildren().add(cardsStackFaceDown);
        table.getChildren().addAll(quitButton,settingButton);
        table.getChildren().addAll(PTNode,PT2Node,PT3Node,PT4Node);
        table.getChildren().add(OSNode);
        table.getChildren().add(settingsPane);

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
