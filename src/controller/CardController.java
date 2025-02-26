package controller;

import model.CardModel;
import view.area.PlayerTable;
import view.components.cards.PlayerCard;
import view.components.cards.RegularCards;
import java.util.HashMap;
import java.util.Map;

public class CardController {

    PlayerTable playerTable;
    CardModel card;

    public CardController(PlayerTable playerTable) {
       this.playerTable = playerTable;
    }

    /**
     * Add cards to player's hand
     */
    public void addCardToTable(){
        card = generateRandomCard();
        RegularCards regCard = new PlayerCard(card);

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

