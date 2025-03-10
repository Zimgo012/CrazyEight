package controller;

import model.CardModel;
import model.PlayerTableModel;
import view.area.PlayerTable;
import model.SingleGameModel;
import view.area.dealer.OpenStack;
import view.components.cards.OpponentCard;
import view.components.cards.PlayerCard;
import view.components.cards.RegularCards;

public class PlayerTableController {

    private PlayerTableModel tableModel;
    private PlayerTable tableView;
    private CardController cardController;
    private SingleGameModel gameModel;
    private OpenStack openStack;



    public PlayerTableController(PlayerTableModel tableModel,
                                 PlayerTable tableView,OpenStack openStack,SingleGameModel gameModel) {
        this.tableModel = tableModel;
        this.tableView = tableView;
        this.openStack = openStack;
        this.gameModel = gameModel;
        this.cardController = new CardController(tableView, this);
    }


    public void addCardToTable(CardModel card) {


        if (tableModel.getHand().size() < 12) {
            if(this.tableModel.getIsForeign()){
                tableModel.addCard(card);
                tableView.addCardView(new OpponentCard(card),card);
            }else{
                tableModel.addCard(card);
                tableView.addCardView(new PlayerCard(card), card);
            }
        }
        checkIfTurnShouldBePassed();
    }

    public boolean removeCardFromTable(CardModel card, RegularCards regularCards) {

        SuiteChooserController chooserController = gameModel.getSuiteChooserController();


        if (card.getValue() == 8 && regularCards instanceof PlayerCard && !chooserController.isSuiteWasChosen()){
            chooserController.prepareSuiteSelection(card, regularCards);
            return false; // waiting for suite selection
        }


        if (chooserController.isSuiteWasChosen()) {
            CardModel pendingCard = chooserController.getPendingCard();
            RegularCards pendingRegularCard = chooserController.getPendingRegularCard();

            if (pendingCard != null && pendingRegularCard != null) {
                tableModel.removeCard(pendingCard);

                if (pendingRegularCard instanceof OpponentCard) {
                    tableView.removeCard(pendingRegularCard, pendingCard);
                    pendingRegularCard = new PlayerCard(pendingCard);
                }

                tableView.removeCard(pendingRegularCard, pendingCard);
                chooserController.clearPendingSelection();

                return true;
            }
        }


        if (cardChecker(card)) {
            tableModel.removeCard(card);

            //if player plays ace
            if(card.getValue() == 1){
                gameModel.toggleReverseGameFlow();
            }

            //if player plays four
            if(card.getValue() == 4){
                gameModel.toggleIsPlayFour();
            }

            //if player plays two
            if(card.getValue() == 2){
                gameModel.isPlayTwo(true);
                gameModel.incrementTwoCard();
            }else{
                gameModel.isPlayTwoRelease(true);
                gameModel.isPlayTwo(false);
            }

            if(card.getValue() == 12){
                gameModel.toggleSkipTurn();
            }

            if (regularCards instanceof OpponentCard) {
                tableView.removeCard(regularCards, card);
                regularCards = new PlayerCard(card);
            }


            tableView.removeCard(regularCards, card);
            chooserController.clearPendingSelection();

            return true;
        }

        chooserController.clearPendingSelection();
        System.out.println("Card does not match!");
        gameModel.getNotification().promptNotification("Card does not match!");
        return false;


    }

    private void checkIfTurnShouldBePassed() {
        if (!tableModel.isAI() && tableModel.getHand().size() >= 12) {
            boolean hasPlayableCard = tableModel.getHand().stream()
                    .anyMatch(card -> card.getSuite().equals(openStack.getTopCard().getSuite()) || card.getValue() == 8);

            if (!hasPlayableCard) {
                System.out.println("🔄 No playable cards! Passing turn...");
                gameModel.getNotification().promptNotification("You cannot draw more and you dont have cards to play! Passing move ");
                gameModel.nextTurn();
            }
        }
    }

    private boolean cardChecker(CardModel card) {
        CardModel prevCard = openStack.getTopCard();

        boolean valuesIsEight = card.getValue() == 8;
        boolean sameValueFromRecent = prevCard.getSuite().equals(card.getSuite());

        return valuesIsEight || sameValueFromRecent;
    }


}
