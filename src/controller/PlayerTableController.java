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


        if (tableModel.getHand().size() < 13) {
            if(this.tableModel.getIsForeign()){
                tableModel.addCard(card);
                tableView.addCardView(new OpponentCard(card),card);
            }else{
                tableModel.addCard(card);
                tableView.addCardView(new PlayerCard(card), card);
            }
        }
    }


    public boolean removeCardFromTable(CardModel card, RegularCards regularCards) {

            if (cardChecker(card)) {
                tableModel.removeCard(card);

                //Remove the facedown card if it's an opponent's card
                if (regularCards instanceof OpponentCard) {
                    tableView.removeCard(regularCards, card); // Remove the facedown version
                    regularCards = new PlayerCard(card); // Create the face-up version
                }

                tableView.removeCard(regularCards, card);
                return true;
            } else {
                System.out.println("Card does not match!");
                return false;
            }

    }

    private boolean cardChecker(CardModel card) {
        CardModel prevCard = openStack.getTopCard();

        boolean valuesIsEight = card.getValue() == 8;
        boolean sameValueFromRecent = prevCard.getSuite().equals(card.getSuite());

        return valuesIsEight || sameValueFromRecent;
    }





}
