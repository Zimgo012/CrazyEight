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
package view.area;

import controller.PlayerTableController;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.CardModel;
import model.PlayerTableModel;
import model.SingleGameModel;
import observers.PlayerTableObserver;
import view.area.dealer.OpenStack;
import javafx.scene.layout.Pane;
import view.components.cards.OpponentCard;
import view.components.cards.RegularCards;

import java.util.*;

public class PlayerTable implements PlayerTableObserver {

    private Pane currentUserTable;
    private OpenStack openStack;
    private PlayerTableModel model;
    private SingleGameModel singleGameModel;
    private PlayerTableController controller;
    private Map<CardModel,RegularCards> cardMap = new LinkedHashMap<>();


    public PlayerTable(PlayerTableModel model, OpenStack openStack, SingleGameModel gameModel) {
        this.model = model;
        this.openStack = openStack;
        this.singleGameModel = gameModel;
        controller = new PlayerTableController(model,this,openStack,gameModel);

        setPane();
        model.addObserver(this);
    }


    /**
     * To add a card on this section
     * @param card that will be added on this section
     */
    public void addCardView(RegularCards card, CardModel newCard) {
        Rectangle cardNode = card.getCard();
        int numOfCards = currentUserTable.getChildren().size();
        if(numOfCards !=13) {

            cardMap.put(newCard,card);

            currentUserTable.getChildren().add(card.getCard());
            updateCardPositions();

            cardNode.setOnMouseClicked(e ->{

                if(model.isCurrentTurn()){
                    if( controller.removeCardFromTable(newCard,card)){
                        singleGameModel.nextTurn();
                    }
                }else{
                    System.out.println("Not your turn");
                }
            });
        }
    }

    /**
     * To remove a card on this section
     */
    public void removeCard(RegularCards card, CardModel newCard) {


        currentUserTable.getChildren().remove(card.getCard());
        updateCardPositions();

        //Reset UI Effects to no effects
        card.getCard().setLayoutX(0);
        card.getCard().setLayoutY(0);
        card.getCard().setOnMousePressed(null);
        card.getCard().setOnMouseEntered(null);
        card.getCard().setOnMouseExited(null);
        card.getCard().setOnMouseReleased(null);
        card.getCard().setOnMouseClicked(null);
        card.getCard().setRotate(Math.random() * 90 + (-45));

        if(card instanceof OpponentCard){
            ((OpponentCard) card).revealCard();
        }
        cardMap.remove(newCard);
        updateOpenStackPosition(card,newCard);

    }


    public RegularCards getCardByModel(CardModel cardModel) {
        return cardMap.get(cardModel);
    }
    private void setPane(){
        currentUserTable = new Pane();
        currentUserTable.setMaxSize(1020.00, 260.00);
    }
    public Pane getCurrentUserTable() {
        return currentUserTable;
    }

    //Dynamically update cards positions
    private void updateCardPositions() {
        int numOfCards = currentUserTable.getChildren().size();
        if (numOfCards == 0) return;

        double cardWidth = 50;  // Adjust card width
        double cardSpacing = 10; // Adjust spacing between cards
        double totalRowWidth = (numOfCards * cardWidth) + ((numOfCards - 1) * cardSpacing);

        double startX = (currentUserTable.getWidth() - totalRowWidth) / 2; // Center dynamically

        double positionX = startX;
        for (Node node : currentUserTable.getChildren()) {
            if (node instanceof Rectangle) {
                node.setTranslateX(positionX);
                positionX += cardWidth + cardSpacing;
            }
        }
    }
    //Randomize cards on used cards
    private void updateOpenStackPosition(RegularCards card,CardModel newCard) {
        double openStackX = openStack.getCurrentOpenStack().getTranslateX();
        double openStackY = openStack.getCurrentOpenStack().getTranslateY();

        Rectangle cardNode = card.getCard();
        cardNode.setTranslateX(openStackX);
        cardNode.setTranslateY(openStackY);
        cardNode.toFront(); // Ensures the top card is visible

        openStack.addCard(card,newCard);
    }

    @Override
    public void updateTable(PlayerTableModel table) {

        System.out.println(model.getHand());
    }

}
