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
package View.Area;

import View.Components.Cards.RegularCards;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Player4Table {

    private Pane currentUserTable;
    private int cardSpacing = 0;
    private int numOfCards = 0;

    /**
     * Creates the section
     */
    public Player4Table(){
        currentUserTable = new Pane();
        currentUserTable.setMaxSize(1020.00, 260.00);

    }

    /**
     * Get player 4 section
     * @return the pane of player 2
     */
    public Pane getCurrentUserTable() {
        return currentUserTable;
    }


    /**
     * To add a card on this section
     * @param card that will be added on this section
     */
    public void addCard(RegularCards card){
        Rectangle cardNode = card.getCard();
        cardNode.setLayoutX(cardSpacing);
        cardSpacing += 20;
        currentUserTable.getChildren().add(cardNode);
        numOfCards++;

    }

    /**
     * To remove a card on this section
     */
    public void removeNode(){

        //ToDo: Shift previous cards to left
    }
}
