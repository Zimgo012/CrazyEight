/**
 * File name: GameController.java
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
package controller;

import model.CardModel;
import model.SingleGameModel;
import view.area.dealer.OpenStack;
import view.components.SuiteChooser;
import view.components.cards.RegularCards;


public class SuiteChooserController {
    private OpenStack openStack;
    private SuiteChooser suiteChooser;
    private SingleGameModel singleGameModel;
    private boolean suiteWasChosen = false;

    private RegularCards pendingRegularCard;
    private CardModel pendingCard;

    public SuiteChooserController(SingleGameModel singleGameModel, SuiteChooser suiteChooser){
        this.singleGameModel = singleGameModel;
        this.suiteChooser = suiteChooser;
        this.openStack = singleGameModel.getOpenStack();
        attachEventHandlers();
    }

    private void attachEventHandlers(){
        suiteChooser.getClubsB().setOnMouseClicked(e->{
            handleSuiteSelection("Clubs");
        });
        suiteChooser.getDiamondB().setOnMouseClicked(e->{handleSuiteSelection("Diamond");});
        suiteChooser.getHeartB().setOnMouseClicked(e->{handleSuiteSelection("Hearts");});
        suiteChooser.getSpadeB().setOnMouseClicked(e->{handleSuiteSelection("Spade");});
    }


    public void prepareSuiteSelection(CardModel card, RegularCards regularCard) {
        this.pendingCard = card;
        this.pendingRegularCard = regularCard;
        suiteWasChosen = false;
        suiteChooser.getSuites().setVisible(true);
    }

    private void handleSuiteSelection(String suite){
        suiteWasChosen = true;
        toggleSuiteChooser(); // Hide suite chooser

        if (pendingCard != null && pendingRegularCard != null) {
            boolean removalSuccess = singleGameModel.getCurrentPlayerController()
                    .removeCardFromTable(pendingCard, pendingRegularCard);

            if (removalSuccess) {
                // 🚀 Now that we confirmed removal, update the suite!
                openStack.getTopCard().setSuite(suite);
                clearPendingSelection();
                singleGameModel.nextTurn();
            }
        }
    }


    public void clearPendingSelection(){
        pendingCard = null;
        pendingRegularCard = null;
        suiteWasChosen = false;
        suiteChooser.getSuites().setVisible(false);
    }


    public void toggleSuiteChooser() {
        System.out.println("toggled!");
        suiteChooser.getSuites().setVisible(!suiteChooser.getSuites().isVisible());
    }


    public boolean isSuiteWasChosen() {
        return suiteWasChosen;
    }

    public CardModel getPendingCard() {
        return pendingCard;
    }

    public RegularCards getPendingRegularCard() {
        return pendingRegularCard;
    }


}
