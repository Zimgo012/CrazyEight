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

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.CardModel;
import observers.CardObserver;

/**
 * Class name: RegularCards
 * Purpose:
 * - Parent class for cards. Represents card UI in game
 * @author John Rycca Belcina
 * @since 1.8
 */

public class RegularCards implements CardObserver {

    private Rectangle cardView;
    private CardModel model;

    public RegularCards(CardModel card) {
    this.model = card;
    card.addObserver(this);
    updateCard(card);

    }

    /**
     * Getter for a card
     * @return a Rectangle object that has card template
     */
    public Rectangle getCard() {
        return this.cardView;
    }

    public void setRotation(int rotate){
        this.cardView.setRotate(rotate);
    }

    public CardModel getModel(){
        return this.model;
    }

    @Override
    public void updateCard(CardModel card) {
        String imagePath;

        if (this instanceof OpponentCard && !((OpponentCard) this).isFaceUp()) {
            // Show face-down image if the opponent hasn't played the card
            imagePath = "/com/zimgo/crazyeight/CardAssets2/OtherCards/3.png";
        } else {
            // Show face-up card when played
            imagePath = String.format("/com/zimgo/crazyeight/CardAssets2/%s/%d.png", card.getSuite(), card.getValue());
        }
        Image cardPic = new Image(getClass().getResource(imagePath).toExternalForm());

        this.cardView = new Rectangle(90, 120);
        this.cardView.setFill(new ImagePattern(cardPic));
    }
}
