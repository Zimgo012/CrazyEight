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
package observers;

import model.CardModel;

/**
 * Defines an observer interface for monitoring changes in a {@code CardModel}.
 * Implementing classes should provide the behavior for handling updates
 * when a card's state changes.
 */
public interface CardObserver {


    /**
     * Updates the visual representation of a specified card based on its current
     * suite and value. This method is typically invoked when the card model's
     * state changes, prompting the UI to reflect the update. If the implementing
     * class is an instance of {@code OpponentCard} and the card is not yet revealed
     * (face down), a placeholder image will be shown instead.
     *
     * @param card the {@code CardModel} instance representing the card to update.
     *             It contains the current suite and value to be displayed.
     */
    void updateCard(CardModel card);
}
