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
package view.area.dealer;

import model.CardModel;
import view.components.cards.RegularCards;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javax.smartcardio.Card;
import java.util.*;

/**
 * The OpenStack class represents the open stack area in the game, where players
 * can interact with a section of cards that are facing up. It provides methods
 * to manage the cards in this section, including adding new cards and retrieving
 * the current state of the open stack.
 */

public class OpenStack {

    private Pane openStackDeck;
    private List<RegularCards> listOfCards = new ArrayList<>();
    private List<CardModel> cardModelList = new ArrayList<>();
    private int numberOfCards = 0;
    private int index = 0;

    /**
     * Constructs an instance of the OpenStack class.
     *
     * This constructor initializes the open stack pane as a graphical container
     * used to represent the deck of cards in the game's open stack. The pane
     * is initialized with a maximum size of 400x260 pixels.
     */
    public OpenStack() {
        openStackDeck = new Pane();
        openStackDeck.setMaxSize(400,260);
    }

    /**
     * Retrieves the current open stack pane.
     *
     * The open stack pane represents a graphical container
     * that displays the open stack of cards in the game.
     *
     * @return the Pane object representing the current state of the open stack.
     */
    public Pane getCurrentOpenStack() {
        return openStackDeck;
    }

    /**
     * Adds a card to the open stack, updating both the stack's visual representation
     * and internal data structures. If the number of cards in the open stack exceeds
     * a predefined limit (5 cards), the oldest card in the stack is removed.
     *
     * @param card the {@code RegularCards} instance representing the card's UI representation.
     *             This card is added to the open stack's graphical container.
     * @param newCardModel the {@code CardModel} instance representing the data model of the card,
     *                     including its suite and value. This model is added to the internal list
     *                     for tracking cards in the open stack.
     */
    public void addCard(RegularCards card, CardModel newCardModel) {
            cardModelList.add(newCardModel);
            Rectangle cardNode = card.getCard();
            openStackDeck.getChildren().add(cardNode);


            listOfCards.add(card);
            numberOfCards++;

            if(numberOfCards > 5){
                listOfCards.remove(index);
                openStackDeck.getChildren().remove(0);
            }
    }


    /**
     * Retrieves the list of all card models currently stored in the open stack.
     *
     * @return a list of {@code CardModel} objects representing the cards currently in the open stack.
     */
    public List<CardModel> getCardList(){
        return cardModelList;
    }

    public CardModel getTopCard() {
        return cardModelList.isEmpty() ? null : cardModelList.get(cardModelList.size() - 1);
    }

}
