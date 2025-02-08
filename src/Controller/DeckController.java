package Controller;

import View.Area.PlayerTable;
import View.Components.Cards.PlayerCard;

public class DeckController {
    PlayerTable playerTable;
    public DeckController(PlayerTable pTable) {
       playerTable = pTable;
    }


    public void addCard(){
     playerTable.addCard(new PlayerCard("Diamond",1));
    }


}

