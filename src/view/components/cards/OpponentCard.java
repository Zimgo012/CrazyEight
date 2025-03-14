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

public class OpponentCard extends RegularCards {
    private boolean isFaceUp; // Flag to track when card should be revealed

    public OpponentCard(CardModel card) {
        super(card);
        this.isFaceUp = false; // Initially, the card is face down
    }

    public void revealCard() {
        this.isFaceUp = true;
        updateCard(getModel()); // Re-update the card to show its face
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }
}
