package controller;

import model.CardModel;
import model.PlayerTableModel;
import view.area.PlayerTable;
import view.components.cards.PlayerCard;
import view.components.cards.RegularCards;
import java.util.HashMap;
import java.util.Map;

public class CardController {

    PlayerTableController playerTableController;
    PlayerTable playerTable;


    public CardController(PlayerTable playerTable, PlayerTableController playerTableController) {
       this.playerTable = playerTable;
       this.playerTableController = playerTableController;
    }

    /**
     * Add cards to player's hand
     */
    public void addCardToTable(){
        CardModel newCard = generateRandomCard();  // Generate a new card
        RegularCards regCard = new PlayerCard(newCard); //

        playerTableController.addCardToTable(newCard); //

    }

    /**
     * Generate Random cards
     * @return Card object
     */
    public CardModel generateRandomCard(){
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

