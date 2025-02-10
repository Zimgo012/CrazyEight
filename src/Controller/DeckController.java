package Controller;

import Model.CardModel;
import Model.PlayerModel;
import View.Area.PlayerTable;
import View.Components.Cards.PlayerCard;

public class DeckController {

    PlayerTable playerTable;
    PlayerModel player1;

    public DeckController(PlayerTable pTable) {
       playerTable = pTable;
    }


    public void addCard(){
        CardModel card = new CardModel("Diamond", 1);
        player1.addCard(card);
        playerTable.addCard(new PlayerCard(card.getSuite(), card.getValue()));
    }

}

