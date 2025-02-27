package controller;

import model.CardModel;
import model.PlayerTableModel;
import view.area.PlayerTable;
import model.SingleGameModel;
import view.area.dealer.OpenStack;
import view.components.cards.PlayerCard;
import view.components.cards.RegularCards;

public class PlayerTableController {

    private PlayerTableModel tableModel;
    private PlayerTable tableView;
    private CardController cardController;
    private SingleGameModel gameModel;
    private OpenStack openStack;


    public PlayerTableController(PlayerTableModel tableModel,
                                 PlayerTable tableView,OpenStack openStack) {
        this.tableModel = tableModel;
        this.tableView = tableView;
        this.openStack = openStack;
        this.cardController = new CardController(tableView, this);
    }


    public void addCardToTable(CardModel card) {
        if (tableModel.getHand().size() < 13) {
            tableModel.addCard(card);
            tableView.addCardView(new PlayerCard(card), card);
        }
    }


    public void removeCardToTable(CardModel card, RegularCards regularCards) {

        if(cardChecker(card)) {

            tableModel.removeCard(card);
            tableView.removeCard(regularCards,card);
        }else{
            System.out.println("Card not match or eight!");
        }

    }

    private boolean cardChecker(CardModel card) {
        CardModel prevCard = openStack.getTopCard();

        boolean valuesIsEight = card.getValue() == 8;
        boolean sameValueFromRecent = prevCard.getSuite().equals(card.getSuite());

        return valuesIsEight || sameValueFromRecent;
    }





}
