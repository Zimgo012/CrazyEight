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

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import model.CardModel;


public class PlayerCard extends RegularCards {


    /**
     * Creates a visual representation of a card that reacts to user interaction such as mouse hover,
     * press, and release events.
     *
     * The PlayerCard enhances the base card's visual behavior with effects:
     * - Adds a yellow glowing effect and elevation when the card is hovered over.
     * - Reduces opacity when the card is pressed.
     * - Restores its original settings when released or when the mouse exits.
     *
     * @param card the model representing the card, including its suite and value, to be used in the visual representation
     */
    public PlayerCard(CardModel card) {
        super(card);

        DropShadow glow = new DropShadow();
        glow.setRadius(20);
        glow.setColor(Color.YELLOW);


        //Hover Mouse Pop out and glow
        super.getCard().setOnMouseEntered(e ->{
            super.getCard().setTranslateY(-30);
            super.getCard().setEffect(glow);

        });
        super.getCard().setOnMouseExited(e ->{

            super.getCard().setTranslateY(0);
            super.getCard().setEffect(null);
        });

        super.getCard().setOnMousePressed(e ->{
            super.getCard().setOpacity(0.5);

        });

        super.getCard().setOnMouseReleased(e ->{

            super.getCard().setOpacity(1);
        });

    }
}