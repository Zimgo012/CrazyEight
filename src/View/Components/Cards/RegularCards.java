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

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Class name: RegularCards
 * Purpose:
 * - Parent class for cards. Represents card UI in game
 * @author John Rycca Belcina
 * @since 1.8
 */

public class RegularCards {

    private Rectangle card;

    /**
     * Card Creator
     * @param suit 1-4 (Represents spades,hearts,diamonds,clovers)
     * @param value 1-13 (represents ace to king)
     */
    public RegularCards(String suit, int value) {

        String imagePath = String.format("/com/zimgo/crazyeight/CardAssets2/%s/%d.png", suit, value);
        Image cardPic = new Image(getClass().getResource(imagePath).toExternalForm());

        card = new Rectangle(108, 154);
        card.setFill(new ImagePattern(cardPic));
    }

    /**
     * Getter for a card
     * @return a Rectangle object that has card template
     */
    public Rectangle getCard() {
        return card;
    }


}
