package controller;

import model.CardModel;
import model.PlayerTableModel;
import view.Area.PlayerTable;
import view.Components.Cards.PlayerCard;
import view.Components.Cards.RegularCards;

import java.util.HashMap;
import java.util.Map;

public class CardController {

    PlayerTable playerTable;
    PlayerTableModel player1;

    public CardController(PlayerTable pTable) {
       playerTable = pTable;
    }

    /**
     * Add cards to player's hand
     */
    public void addCard(){
        CardModel card = generateRandomCard();

        RegularCards regCard = new PlayerCard(card.getSuite(), card.getValue());

        playerTable.addCardView(regCard,card);
    }

    /**
     * Generate Random cards
     * @return Card object
     */
    private CardModel generateRandomCard(){
        Map<Integer, String> cardsMap = new HashMap<Integer, String>() {{
            put(1, "Diamond");
            put(2, "Clubs");
            put(3,"Hearts");
            put(4, "Spade");
        }};

        int randomSuite  = (int) (Math.random() * 4) + 1;
        int randomValue  = (int) (Math.random() * 13) + 1;

        CardModel card  = new CardModel(cardsMap.get(randomSuite), randomValue);
        return card;
    }

}

