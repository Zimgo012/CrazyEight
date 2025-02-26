package model;

import controller.CardController;
import controller.PlayerTableController;
import view.area.PlayerTable;
import view.area.dealer.OpenStack;
import view.area.dealer.CardsStackFaceDown;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private List<PlayerTableModel> playerModels = new ArrayList<>();
    private List<PlayerTable> playerTables = new ArrayList<>();
    private OpenStack openStack;
    private CardsStackFaceDown cardsStackFaceDown;
    private CardController cardController;
    private PlayerTableController playerTableController;

    public GameModel() {
        setupGameState();
    }

    private void setupGameState() {
        openStack = new OpenStack();

        // Create players and controllers
        for (int i = 0; i < 4; i++) {
            PlayerTableModel playerModel = new PlayerTableModel();
            playerModels.add(playerModel);

            PlayerTable playerTable = new PlayerTable(playerModel, openStack);
            playerTables.add(playerTable);

            PlayerTableController controller = new PlayerTableController(playerModel, playerTable);
        }

        // Set up dealer mechanics
        cardController = new CardController(playerTables.get(0)); // Use first player as dealer for now
        cardsStackFaceDown = new CardsStackFaceDown(cardController);
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

    public CardController getCardController() {
        return cardController;
    }

    public PlayerTableController getPlayerTableController() {
        return playerTableController;
    }
}
