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

import java.util.*;

/**
 * Class name: OpenStack
 * Purpose: A section of used cards facing up.
 * @author John Rycca Belcina
 * @since 1.8
 */

public class OpenStack {

    private Pane openStackDeck;
    private List<RegularCards> listOfCards = new ArrayList<>();
    private List<CardModel> cardModelList = new ArrayList<>();
    private int numberOfCards = 0;
    private int index = 0;

    /**
     * Creates open stack area
     */
    public OpenStack() {
        openStackDeck = new Pane();
        openStackDeck.setMaxSize(400,260);
    }

    /**
     * Get the section
     * @return the pane of cards facing up
     */
    public Pane getCurrentOpenStack() {
        return openStackDeck;
    }

    /**
     * To add a card on this section
     * @param card that will be added on this section
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


    public List<CardModel> getCardList(){
        return cardModelList;
    }

}
