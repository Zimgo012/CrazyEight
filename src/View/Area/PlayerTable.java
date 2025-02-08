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

public class PlayerTable {


    private Pane currentUserTable;
    private int cardSpacing = 0;
    private int numOfCards = 0;
    private OpenStack openStack;

    /**
     * Creates the section
     */
    public PlayerTable(OpenStack oStack){
        openStack = oStack;
        currentUserTable = new Pane();
        currentUserTable.setMaxSize(1020.00, 260.00);

    }

    /**
     * Get current player's section
     * @return the pane of the current player
     */
    public Pane getCurrentUserTable() {
        return currentUserTable;
    }


    /**
     * To add a card on this section
     * @param card that will be added on this section
     */
    public void addCard(RegularCards card){

        if(numOfCards == 13){
            System.out.println("Cannot Add!");
        }else{

            Rectangle cardNode = card.getCard();
            cardNode.setLayoutX(cardSpacing);
            cardSpacing += 50;
            currentUserTable.getChildren().add(cardNode);
            this.numOfCards++;

            cardNode.setOnMouseClicked(e -> {
                removeCard(card);
            });
        }


    }

    /**
     * To remove a card on this section
     */
    public void removeCard(RegularCards card){
        currentUserTable.getChildren().remove(card.getCard());

        this.cardSpacing -= 50;
        this.numOfCards--;

        double newSpacing = 0;
        for (int i = 0; i < currentUserTable.getChildren().size(); i++) {
            currentUserTable.getChildren().get(i).setLayoutX(newSpacing);
            newSpacing += 50;
        }


        card.getCard().setLayoutX(0);
        card.getCard().setLayoutY(0);
        card.getCard().setOnMousePressed(null);
        card.getCard().setOnMouseEntered(null);
        card.getCard().setOnMouseExited(null);
        card.getCard().setOnMouseReleased(null);
        card.getCard().setOnMouseClicked(null);
        card.getCard().setRotate(Math.random() * 90 + (-45));

        openStack.addCard(card);
    }

}
