package controller;

import model.CardModel;
import model.PlayerTableModel;
import view.area.PlayerTable;

public class PlayerTableController {

    PlayerTableModel tableModel;
    PlayerTable tableView;


    public PlayerTableController(PlayerTableModel tableModel, PlayerTable tableView) {
        this.tableModel = tableModel;
        this.tableView = tableView;
    }


    public void addCardToTable(CardModel card) {
        tableModel.addCard(card);
    }

    public void removeCardToTable(CardModel card) {
        tableModel.removeCard(card);
    }



}
