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

package view.components.cards;
import model.CardModel;

/**
 * Represents an opponent's card in the game, which starts face down and can be revealed.
 * The class extends the functionality of RegularCards and introduces behavior specific to opponent cards.
 * This class is primarily used to handle the visual representation and state of an opponent's card.
 */
public class OpponentCard extends RegularCards {
    private boolean isFaceUp; // Flag to track when card should be revealed

    /**
     * Constructs an instance of OpponentCard associated with the provided card model.
     * This card is initially set to be face down.
     *
     * @param card the card model to be associated with this opponent card
     */
    public OpponentCard(CardModel card) {
        super(card);
        this.isFaceUp = false; // Initially, the card is face down
    }

    /**
     * Reveals the card by setting its state to "face up" and updating its visual representation.
     * This method sets the {@code isFaceUp} flag to {@code true}, indicating that the card
     * is now visible, and re-renders the card to display its front face using the associated
     * card model.
     */
    public void revealCard() {
        this.isFaceUp = true;
        updateCard(getModel()); // Re-update the card to show its face
    }

    /**
     * Returns the current state of the card, indicating whether it is face up or face down.
     *
     * @return {@code true} if the card is face up, {@code false} if the card is face down.
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }
}
