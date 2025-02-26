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
import javafx.scene.shape.Rectangle;
import model.CardModel;
import model.PlayerTableModel;
import observers.PlayerTableObserver;
import view.area.dealer.OpenStack;
import javafx.scene.layout.Pane;
import view.components.cards.RegularCards;

import java.util.LinkedHashMap;

public class PlayerTable implements PlayerTableObserver {

    private Pane currentUserTable;
    private int cardSpacing = 0;
    private int numOfCards = 0;
    private OpenStack openStack;
    private PlayerTableModel model;
    private PlayerTableController controller;
    private LinkedHashMap<RegularCards, CardModel> cardMapping = new LinkedHashMap<>();


    public PlayerTable(PlayerTableModel model, OpenStack openStack) {
        this.model = model;
        this.openStack = openStack;
        controller = new PlayerTableController(model,this);

        setPane();
        model.addObserver(this);
    }

    public Pane getCurrentUserTable() {
        return currentUserTable;
    }

    /**
     * To add a card on this section
     * @param card that will be added on this section
     */
    public void addCardView(RegularCards card, CardModel newCard) {

        if(numOfCards != 13){
            Rectangle cardNode = card.getCard();
            cardNode.setLayoutX(cardSpacing);
            cardSpacing += 50;
            currentUserTable.getChildren().add(cardNode);
            this.numOfCards++;

            controller.addCardToTable(newCard);
            cardMapping.put(card, newCard);

            cardNode.setOnMouseClicked(e -> {
                removeCard(card);
            });
        }

    }

    /**
     * To remove a card on this section
     */
    public void removeCard(RegularCards card){
        CardModel cardFromMap= cardMapping.get(card);

        if(!cardChecker(cardFromMap.getSuite(), cardFromMap.getValue())){
            System.out.println("Card not same type");
            return;
        }

        currentUserTable.getChildren().remove(card.getCard());

        this.cardSpacing -= 50;
        this.numOfCards--;

        double newSpacing = 0;
        for (int i = 0; i < currentUserTable.getChildren().size(); i++) {
            currentUserTable.getChildren().get(i).setLayoutX(newSpacing);
            newSpacing += 50;
        }
        model.removeCard(cardFromMap);
        cardMapping.remove(card);

        card.getCard().setLayoutX(0);
        card.getCard().setLayoutY(0);
        card.getCard().setOnMousePressed(null);
        card.getCard().setOnMouseEntered(null);
        card.getCard().setOnMouseExited(null);
        card.getCard().setOnMouseReleased(null);
        card.getCard().setOnMouseClicked(null);
        card.getCard().setRotate(Math.random() * 90 + (-45));

        openStack.addCard(card,cardFromMap);


    }

    private boolean cardChecker(String cardSuite,int cardValue){
        CardModel cardFromMap = openStack.getCardList().get(openStack.getCardList().size()-1);

        boolean valuesIsEight = cardValue == 8;
        boolean sameValueFromRecent = cardFromMap.getSuite().equals(cardSuite);

        return valuesIsEight || sameValueFromRecent;
    }
    private void setPane(){
        currentUserTable = new Pane();
        currentUserTable.setMaxSize(1020.00, 260.00);
    }

    @Override
    public void updateTable(PlayerTableModel table) {
        //Some notifications here

    }
}
