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
import model.PlayerTableModel;
import view.area.PlayerTable;
import model.SingleGameModel;
import view.area.dealer.OpenStack;
import view.components.cards.OpponentCard;
import view.components.cards.PlayerCard;
import view.components.cards.RegularCards;

public class PlayerTableController {

    private PlayerTableModel tableModel;
    private PlayerTable tableView;
    private CardController cardController;
    private SingleGameModel gameModel;
    private OpenStack openStack;



    /**
     * Constructs a PlayerTableController instance to manage the interaction
     * between the PlayerTableModel, PlayerTable, OpenStack, and SingleGameModel.
     *
     * @param tableModel the data model representing the player's table
     * @param tableView the view interface representing the player's table UI
     * @param openStack the card stack where open or used cards are managed
     * @param gameModel the single game context managing the overall game state
     */
    public PlayerTableController(PlayerTableModel tableModel,
                                 PlayerTable tableView,OpenStack openStack,SingleGameModel gameModel) {
        this.tableModel = tableModel;
        this.tableView = tableView;
        this.openStack = openStack;
        this.gameModel = gameModel;
        this.cardController = new CardController(tableView, this);
    }


    /**
     * Adds a card to the player's table while considering certain rules and conditions.
     *
     * If the table has fewer than 12 cards, the method adds the given card to the
     * table model and updates the corresponding table view based on the player's
     * or opponent's table type. If the card is added to an opponent's table, it
     * creates an `OpponentCard` for the view. Otherwise, a `PlayerCard` is used.
     * After adding the card, it checks whether the turn should be passed.
     *
     * @param card the card to add to the player's table
     */
    public void addCardToTable(CardModel card) {


        if (tableModel.getHand().size() < 12) {
            if(this.tableModel.getIsForeign()){
                tableModel.addCard(card);
                tableView.addCardView(new OpponentCard(card),card);
            }else{
                tableModel.addCard(card);
                tableView.addCardView(new PlayerCard(card), card);
            }
        }
        checkIfTurnShouldBePassed();
    }

    /**
     * Removes a card from the player's table and updates the game state based on
     * specific game rules and conditions.
     *
     * This method handles various card-specific game mechanics, such as special
     * effects for cards like 1, 2, 4, 8, and 12. It also manages suite selection
     * if required (e.g., when playing an 8), updates the table model and view,
     * and determines whether the card is successfully removed.
     *
     * @param card the card to be removed from the player's table
     * @param regularCards the UI representation of the card being removed
     * @return true if the card was successfully removed and the game state was updated,
     *         false otherwise (e.g., when waiting for suite selection or if the card
     *         does not match the required criteria)
     */
    public boolean removeCardFromTable(CardModel card, RegularCards regularCards) {

        SuiteChooserController chooserController = gameModel.getSuiteChooserController();


        if (card.getValue() == 8 && regularCards instanceof PlayerCard && !chooserController.isSuiteWasChosen()){
            chooserController.prepareSuiteSelection(card, regularCards);
            return false; // waiting for suite selection
        }


        if (chooserController.isSuiteWasChosen()) {
            CardModel pendingCard = chooserController.getPendingCard();
            RegularCards pendingRegularCard = chooserController.getPendingRegularCard();

            if (pendingCard != null && pendingRegularCard != null) {
                tableModel.removeCard(pendingCard);

                if (pendingRegularCard instanceof OpponentCard) {
                    tableView.removeCard(pendingRegularCard, pendingCard);
                    pendingRegularCard = new PlayerCard(pendingCard);
                }

                tableView.removeCard(pendingRegularCard, pendingCard);
                chooserController.clearPendingSelection();

                return true;
            }
        }


        if (cardChecker(card)) {
            tableModel.removeCard(card);

            //if player plays ace
            if(card.getValue() == 1){
                gameModel.toggleReverseGameFlow();
            }

            //if player plays four
            if(card.getValue() == 4){
                gameModel.toggleIsPlayFour();
            }

            //if player plays two
            if(card.getValue() == 2){
                gameModel.isPlayTwo(true);
                gameModel.incrementTwoCard();
            }else{
                gameModel.isPlayTwoRelease(true);
                gameModel.isPlayTwo(false);
            }

            if(card.getValue() == 12){
                gameModel.toggleSkipTurn();
            }

            if (regularCards instanceof OpponentCard) {
                tableView.removeCard(regularCards, card);
                regularCards = new PlayerCard(card);
            }


            tableView.removeCard(regularCards, card);
            chooserController.clearPendingSelection();

            return true;
        }

        chooserController.clearPendingSelection();
        System.out.println("Card does not match!");
        gameModel.getNotification().promptNotification("Card does not match!");
        return false;


    }

    /**
     * Determines whether the current player's turn should be passed based on specific game conditions.
     *
     * This method checks if the current player is not an AI and holds 12 or more cards in their hand.
     * If these conditions are met, it evaluates whether the player has any playable cards. A card is
     * considered playable if either:
     * - It shares the same suite as the top card on the open stack, or
     * - It has a value of 8.
     *
     * If no playable cards are available, a notification is displayed to the player indicating their turn
     * will be passed. The game then proceeds to the next player's turn.
     *
     * Conditions Triggering Turn Pass:
     * 1. The player holds 12 or more cards in hand.
     * 2. None of the cards in the player's hand meet the conditions for being playable.
     *
     * Effects of Turn Pass:
     * - A system message is printed to the console.
     * - A notification is displayed to the user in the game UI.
     * - The game moves to the next player's turn by invoking the `nextTurn` method from the game model.
     */
    private void checkIfTurnShouldBePassed() {
        if (!tableModel.isAI() && tableModel.getHand().size() >= 12) {
            boolean hasPlayableCard = tableModel.getHand().stream()
                    .anyMatch(card -> card.getSuite().equals(openStack.getTopCard().getSuite()) || card.getValue() == 8);

            if (!hasPlayableCard) {
                System.out.println("🔄 No playable cards! Passing turn...");
                gameModel.getNotification().promptNotification("You cannot draw more and you dont have cards to play! Passing move ");
                gameModel.nextTurn();
            }
        }
    }

    /**
     * Determines if a given card can be played based on specific game rules.
     *
     * This method checks if the card meets one of two conditions:
     * 1. The card has a value of 8.
     * 2. The card shares the same suite as the most recently played card in the open stack.
     *
     * @param card the card to be evaluated
     * @return true if the card meets the conditions to be played; false otherwise
     */
    private boolean cardChecker(CardModel card) {
        CardModel prevCard = openStack.getTopCard();

        boolean valuesIsEight = card.getValue() == 8;
        boolean sameValueFromRecent = prevCard.getSuite().equals(card.getSuite());

        return valuesIsEight || sameValueFromRecent;
    }


}
