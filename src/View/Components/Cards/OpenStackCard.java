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
package View.Components.Cards;

/**
 * Class name: OpenStackCard
 * Purpose: Represents used cards that is facing up on the table
 * @author John Rycca Belcina
 * @since 1.8
 */

public class OpenStackCard extends RegularCards {

    /**
     * Card Maker with random rotations to represent card  facing up and stacking.
     * @param suit 1-4 (Represents spades,hearts,diamonds,clovers)
     * @param value 1-13 (represents ace to king)
     */
    public OpenStackCard(String suit, int value) {
        super(suit, value);

       super.getCard().setRotate(Math.random() * 90 + (-45));

    }

    /**
     * - Card Maker without random rotations to represent card  facing up and stacking.
     * - Dummy Card for now.
     * @param suit  1-4 (Represents spades,hearts,diamonds,clovers)
     * @param value 1-13 (represents ace to king)
     * @param isRotated rotate flag
     */
    public OpenStackCard(String suit, int value,boolean isRotated) {
        super(suit, value);

        boolean isRotatable = isRotated;

    }
}