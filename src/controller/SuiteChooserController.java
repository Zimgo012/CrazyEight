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
