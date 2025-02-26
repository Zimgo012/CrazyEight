package controller;

import model.CardModel;
import model.PlayerTableModel;
import view.area.PlayerTable;

public class PlayerTableController {

    PlayerTableModel tableModel;
    PlayerTable tableView;
    CardController cardController;


    public PlayerTableController(PlayerTableModel tableModel, PlayerTable tableView, CardController cardController) {
        this.tableModel = tableModel;
        this.tableView = tableView;
        this.cardController = cardController;
    }


    public void addCardToTable(CardModel card) {
        tableModel.addCard(card);
    }

    public void removeCardToTable(CardModel card) {
        tableModel.removeCard(card);
    }



}
