package model;

import controller.CardController;
import controller.PlayerTableController;
import view.area.PlayerTable;
import view.area.dealer.OpenStack;
import view.area.dealer.CardsStackFaceDown;
import view.components.cards.RegularCards;

import java.util.*;

public class GameModel {
    //Players
    private List<PlayerTableModel> playerModels = new ArrayList<>();
    private List<PlayerTable> playerTables = new ArrayList<>();
    private List<PlayerTableController> playerControllers = new ArrayList<>();

    //Dealer
    private OpenStack openStack;
    private CardsStackFaceDown cardsStackFaceDown;
    private CardModel randomInitialCard;

    //In-game Controls
    private CardController cardController;
    private int currentPlayerIndex;


    public GameModel() {
        setupGameState();
        distributeStartingCards();
    }

    //Setup Game State
    private void setupGameState() {
        openStack = new OpenStack();

        // Create players and controllers
        for (int i = 0; i < 4; i++) {
            PlayerTableModel playerModel = new PlayerTableModel();
            playerModels.add(playerModel);

            PlayerTable playerTable = new PlayerTable(playerModel, openStack);
            playerTables.add(playerTable);

            PlayerTableController controller = new PlayerTableController(playerModel, playerTable);
            playerControllers.add(controller);
        }

        // Set up dealer mechanics
        cardController = new CardController(playerTables.get(0)); // Use first player as dealer for now
        cardsStackFaceDown = new CardsStackFaceDown(cardController);
    }
    //Distributes random card at the start of the game
    private void distributeStartingCards() {
        for (PlayerTableController playerController : playerControllers) {

            CardController cardController = new CardController
                    (playerTables.get(playerControllers.indexOf(playerController)));

            for (int i = 0; i < 5; i++) { // Each player gets 5 random cards
               cardController.addCardToTable();
            }
        }
        //Sets up one card to start the game
        randomInitialCard = cardController.generateRandomCard();

        //Making sure card number is not 8
        if(randomInitialCard.getValue() == 8) {
            randomInitialCard.setValue(randomInitialCard.getValue() - 1);
        }
        openStack.addCard(new RegularCards(randomInitialCard),randomInitialCard);

    }
    //Track whose turn
    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerModels.size();
    }
    //Game start

    public PlayerTableModel getCurrentPlayer() {
        return playerModels.get(currentPlayerIndex);
    }

    // Getters for game state access
    public List<PlayerTableModel> getPlayerModels() {
        return playerModels;
    }

    public List<PlayerTable> getPlayerTables() {
        return playerTables;
    }

    public OpenStack getOpenStack() {
        return openStack;
    }

    public CardsStackFaceDown getCardsStackFaceDown() {
        return cardsStackFaceDown;
    }

}
