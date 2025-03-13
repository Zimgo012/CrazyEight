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
package view.area;

import controller.PlayerTableController;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.CardModel;
import model.PlayerTableModel;
import model.SingleGameModel;
import observers.PlayerTableObserver;
import view.area.dealer.OpenStack;
import javafx.scene.layout.Pane;
import view.components.cards.OpponentCard;
import view.components.cards.RegularCards;

import java.util.*;

/**
 * Represents the player's table area in a card game. This class manages the
 * graphical display and interaction with cards owned by a player during the game.
 * It is responsible for adding and removing cards, maintaining their positions,
 * and updating the view dynamically as cards are played or removed. The
 * PlayerTable also observes changes in its associated {@code PlayerTableModel}.
 */
public class PlayerTable implements PlayerTableObserver {

    private Pane currentUserTable;
    private OpenStack openStack;
    private PlayerTableModel model;
    private SingleGameModel singleGameModel;
    private PlayerTableController controller;
    private Map<CardModel,RegularCards> cardMap = new LinkedHashMap<>();


    /**
     * Constructs a PlayerTable object that serves as an intermediary between
     * the player table model, view, and controller. It initializes relevant components
     * such as the model, controller, and observers, and sets up the pane for the table display.
     *
     * @param model the PlayerTableModel instance that manages the player's table state
     * @param openStack the OpenStack instance representing the discard or draw pile
     * @param gameModel the SingleGameModel instance that manages the overall game state
     */
    public PlayerTable(PlayerTableModel model, OpenStack openStack, SingleGameModel gameModel) {
        this.model = model;
        this.openStack = openStack;
        this.singleGameModel = gameModel;
        controller = new PlayerTableController(model,this,openStack,gameModel);

        setPane();
        model.addObserver(this);
    }


    /**
     * Adds a card view to the player's table and sets up event handlers for interaction.
     * This method ensures that no more than 13 cards are displayed at a time. It also
     * dynamically updates the positions of the cards on the table and handles user interactions,
     * such as removing a card when clicked if it is the player's turn.
     *
     * @param card the {@code RegularCards} object representing the card's UI rectangle
     * @param newCard the {@code CardModel} object representing the logical card data
     */
    public void addCardView(RegularCards card, CardModel newCard) {
        Rectangle cardNode = card.getCard();
        int numOfCards = currentUserTable.getChildren().size();
        if(numOfCards !=13) {

            cardMap.put(newCard,card);

            currentUserTable.getChildren().add(card.getCard());
            updateCardPositions();

            cardNode.setOnMouseClicked(e ->{

                if(model.isCurrentTurn()){
                    if( controller.removeCardFromTable(newCard,card)){
                        singleGameModel.nextTurn();
                    }
                }else{
                    System.out.println("Not your turn");
                    singleGameModel.getNotification().promptNotification("Not your turn");

                }
            });
        }
    }

    /**
     * Removes a card from the player's table, resets its UI properties,
     * and updates the card positions and the open stack. This method also
     * processes specific behavior for opponent cards, such as revealing them.
     *
     * @param card the {@code RegularCards} object representing the UI component of the card to be removed
     * @param newCard the {@code CardModel} object representing the logical data of the card to be removed
     */
    public void removeCard(RegularCards card, CardModel newCard) {


        currentUserTable.getChildren().remove(card.getCard());
        updateCardPositions();

        //Reset UI Effects to no effects
        card.getCard().setLayoutX(0);
        card.getCard().setLayoutY(0);
        card.getCard().setOnMousePressed(null);
        card.getCard().setOnMouseEntered(null);
        card.getCard().setOnMouseExited(null);
        card.getCard().setOnMouseReleased(null);
        card.getCard().setOnMouseClicked(null);
        card.getCard().setRotate(Math.random() * 90 + (-45));

        if(card instanceof OpponentCard){
            ((OpponentCard) card).revealCard();
        }
        cardMap.remove(newCard);
        updateOpenStackPosition(card,newCard);

    }

    /**
     * Retrieves a {@code RegularCards} object associated with the given {@code CardModel}.
     *
     * @param cardModel the {@code CardModel} instance representing the logical data of the card
     *                  for which the corresponding {@code RegularCards} is to be retrieved
     * @return the {@code RegularCards} associated with the provided {@code CardModel},
     *         or {@code null} if no matching card is found in the card map
     */
    public RegularCards getCardByModel(CardModel cardModel) {
        return cardMap.get(cardModel);
    }
    /**
     * Initializes and sets up the pane that serves as the primary container
     * for displaying the player's current table. This method creates a new
     * {@code Pane} instance and configures its maximum size to accommodate
     * the required dimensions for displaying card components.
     *
     * The pane is specifically set with a maximum width of 1020.00 units
     * and a maximum height of 260.00 units. This configuration ensures
     * that the pane provides enough space for managing and displaying
     * visual elements related to the player's table.
     *
     * This method is typically invoked during the construction of the
     * {@code PlayerTable} class to initialize the core visual layout.
     */
    private void setPane(){
        currentUserTable = new Pane();
        currentUserTable.setMaxSize(1020.00, 260.00);
    }
    /**
     * Retrieves the current user's table pane.
     * This pane contains the visual components representing the player's table.
     *
     * @return the {@code Pane} instance representing the current user's table
     */
    public Pane getCurrentUserTable() {
        return currentUserTable;
    }

    /**
     * Dynamically updates the positions of card elements within the current user table pane.
     * This method arranges the cards in a row, ensuring they are centered and evenly spaced
     * based on the total number of cards and their dimensions. It recalculates the horizontal
     * translation values for each card to achieve the updated layout.
     *
     * The method performs the following steps:
     * 1. Calculates the total width occupied by the cards, including their spacing.
     * 2. Centers the starting X position based on the pane's width and total occupied width.
     * 3. Iterates through all child nodes in the current user's table pane, adjusting the
     *    horizontal translation (position) only for nodes of type {@code Rectangle}.
     *
     * If there are no cards in the user's table, the method exits immediately.
     *
     * This method is typically invoked whenever the number of cards changes, such as
     * when a card is added or removed, to ensure the visual layout is updated accordingly.
     */
    //Dynamically update cards positions
    private void updateCardPositions() {
        int numOfCards = currentUserTable.getChildren().size();
        if (numOfCards == 0) return;

        double cardWidth = 50;  // Adjust card width
        double cardSpacing = 10; // Adjust spacing between cards
        double totalRowWidth = (numOfCards * cardWidth) + ((numOfCards - 1) * cardSpacing);

        double startX = (currentUserTable.getWidth() - totalRowWidth) / 2; // Center dynamically

        double positionX = startX;
        for (Node node : currentUserTable.getChildren()) {
            if (node instanceof Rectangle) {
                node.setTranslateX(positionX);
                positionX += cardWidth + cardSpacing;
            }
        }
    }
    /**
     * Updates the position of a card to align with the current open stack's position
     * and brings the card to the front for visibility.
     * Also, adds the card to the open stack with its corresponding model data.
     *
     * @param card the {@code RegularCards} object representing the visual component of the card
     * @param newCard the {@code CardModel} object containing the logic and data for the card
     */
    //Randomize cards on used cards
    private void updateOpenStackPosition(RegularCards card,CardModel newCard) {
        double openStackX = openStack.getCurrentOpenStack().getTranslateX();
        double openStackY = openStack.getCurrentOpenStack().getTranslateY();

        Rectangle cardNode = card.getCard();
        cardNode.setTranslateX(openStackX);
        cardNode.setTranslateY(openStackY);
        cardNode.toFront(); // Ensures the top card is visible

        openStack.addCard(card,newCard);
    }

    /**
     * Updates the player's table by providing access to the model that manages the player's hand or state.
     * This method is an implementation of the {@code updateTable} method in the {@code PlayerTableObserver}.
     *
     * @param table the {@code PlayerTableModel} instance representing the player's table state
     *              that needs to be updated
     */
    @Override
    public void updateTable(PlayerTableModel table) {

        System.out.println(model.getHand());

    }

}
