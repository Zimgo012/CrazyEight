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

import model.PlayerTableModel;

/**
 * Represents an observer interface for objects that need to monitor changes in a
 * PlayerTableModel. Classes implementing this interface can register as observers
 * to receive updates whenever the state of a PlayerTableModel changes.
 *
 * Observers implementing this interface are typically notified via the `updateTable`
 * method, which allows them to respond dynamically to changes in the PlayerTableModel.
 *
 * For example, an observer might use this functionality to update the display of
 * a player's hand or other attributes in the user interface in response to changes.
 */
public interface PlayerTableObserver {

    /**
     * Updates the state of the observer with the provided PlayerTableModel.
     * This method is typically called to notify an observer about changes
     * in the state of the PlayerTableModel it is monitoring.
     *
     * @param table the PlayerTableModel instance containing the updated state
     */
    void updateTable(PlayerTableModel table);
}
