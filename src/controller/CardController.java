/**
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-09
 * Compiler: IntelliJ IDEA
 * Purpose:
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards.
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules.
 * Understanding these variations is essential for proper implementation.
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */

package controller;

import model.CardModel;
import view.area.PlayerTable;
import view.components.cards.PlayerCard;
import view.components.cards.RegularCards;
import java.util.HashMap;
import java.util.Map;

/**
 * The CardController class is responsible for managing card-related logic, such as
 * generating cards and adding them to a player's table. This class interacts with
 * the PlayerTable and PlayerTableController to update the view and manage the state.
 */
public class CardController {

    PlayerTableController playerTableController;
    PlayerTable playerTable;


    /**
     * Constructs a CardController object that manages card-related logic
     * and interactions with the player's table and its corresponding controller.
     *
     * @param playerTable the player's table that this CardController will manage
     * @param playerTableController the controller for the player's table, used for updating view and state
     */
    public CardController(PlayerTable playerTable, PlayerTableController playerTableController) {
       this.playerTable = playerTable;
       this.playerTableController = playerTableController;
    }

    /**
     * Adds a randomly generated card to the player's table.
     *
     * This method generates a random card using the {@link #generateRandomCard()} method,
     * wraps it as a {@link PlayerCard} instance (a subclass of {@link RegularCards}), and
     * then delegates the addition of the card onto the player's table through the
     * {@code playerTableController}.
     *
     * Functionality:
     * - Creates a new card with randomized values (suite and number).
     * - Wraps the card in a PlayerCard instance for player interaction effects (e.g., hover effects).
     * - Passes the new card to the player's table controller, updating the game state accordingly.
     *
     * Interactions:
     * - The method utilizes the {@code playerTableController}'s {@code addCardToTable} method
     *   to manage the addition to the table and ensure proper game state updates.
     *
     * Pre-conditions:
     * - The {@code playerTableController} must be properly initialized prior to calling this method.
     * - The table should have room for additional cards (typically fewer than a maximum table size limit, e.g., 12 cards).
     */
    public void addCardToTable(){

        CardModel newCard = generateRandomCard();  // Generate a new card
        RegularCards regCard = new PlayerCard(newCard); //

        playerTableController.addCardToTable(newCard); //

    }

    /**
     * Generates a random card by selecting a random suite and value.
     *
     * The suite is chosen from the predefined options: "Diamond", "Clubs", "Hearts", and "Spade".
     * The card value is generated randomly between 1 and 13. The generated card is encapsulated
     * in a {@code CardModel} object containing the suite and value.
     *
     * @return a {@code CardModel} instance representing the randomly generated card
     *         with a randomly assigned suite and value.
     */
    public CardModel generateRandomCard(){
        Map<Integer, String> cardsMap = new HashMap<Integer, String>() {{
            put(1, "Diamond");
            put(2, "Clubs");
            put(3,"Hearts");
            put(4, "Spade");
        }};

        int randomSuite  = (int) (Math.random() * 4) + 1;
        int randomValue  = (int) (Math.random() * 13) + 1;

        CardModel card  = new CardModel(cardsMap.get(randomSuite), randomValue);
        return card;
    }

}

