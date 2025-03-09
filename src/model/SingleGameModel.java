package model;

import controller.CardController;
import controller.PlayerTableController;
import controller.SuiteChooserController;
import javafx.application.Platform;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import view.area.PlayerTable;
import view.area.dealer.OpenStack;
import view.area.dealer.CardsStackFaceDown;
import view.components.SuiteChooser;
import view.components.cards.RegularCards;


import java.util.*;
import java.util.List;

public class SingleGameModel {
    //Players
    private List<PlayerTableModel> playerModels = new ArrayList<>();
    private List<PlayerTable> playerTables = new ArrayList<>();
    private List<PlayerTableController> playerControllers = new ArrayList<>();
    private List<CardController> cardControllers = new ArrayList<>();

    //Dealer
    private OpenStack openStack;
    private CardsStackFaceDown cardsStackFaceDown;
    private CardModel randomInitialCard;
    private SuiteChooserController suiteChooserController;
    private SuiteChooser suiteChooser;

    //In-game Controls
    private int currentPlayerIndex = 0;
    private boolean reverseGameFlow = false;
    //Misc
    private DropShadow glow;

    //Instantiate game model for single player
    public SingleGameModel() {
        setupGameState();
        distributeStartingCards();
        startGame();
    }

    //Setup Game State
    private void setupGameState() {
        openStack = new OpenStack();
        suiteChooser = new SuiteChooser();
        suiteChooserController = new SuiteChooserController(this, suiteChooser);


        // Create players and controllers
        for (int i = 0; i < 4; i++) {
            PlayerTableModel playerModel = new PlayerTableModel(this);
            playerModels.add(playerModel);

            if(i != 0){
                playerModel.setIsAI(true); //First player is human for single player.
                playerModel.setIsForeign(true);
            }
            PlayerTable playerTable = new PlayerTable(playerModel, openStack, this);
            playerTables.add(playerTable);

            PlayerTableController controller = new PlayerTableController(playerModel, playerTable,openStack,this);
            playerControllers.add(controller);

            CardController cardController = new CardController(playerTable, controller);
            cardControllers.add(cardController);


        }

        cardsStackFaceDown = new CardsStackFaceDown(cardControllers.get(0),playerModels.get(currentPlayerIndex));
        playerModels.get(0).setCurrentTurn(true);

    }
    //Distributes random card at the start of the game
    private void distributeStartingCards() {
        for (PlayerTableController playerController : playerControllers) {

            CardController cardController = cardControllers.get(playerControllers.indexOf(playerController));

            for (int i = 0; i < 5; i++) { // Each player gets 5 random cards
               cardController.addCardToTable();
            }
        }
        //Sets up one card to start the game
        randomInitialCard = cardControllers.get(0).generateRandomCard();

        //Making sure card number is not 8
        if(randomInitialCard.getValue() == 8) {
            randomInitialCard.setValue(randomInitialCard.getValue() - 1);
        }

        openStack.addCard(new RegularCards(randomInitialCard),randomInitialCard);

    }
    //Starts the game
    public void startGame() {
        System.out.println("🎮 Game Started!");
        Platform.runLater(() -> nextTurn());
    }
    //Track whose turn
    public void nextTurn() {
        // Check if any player has zero cards
        if (checkForWinner()) {
            return; // Stop the game if a player has won
        }

        playerModels.get(currentPlayerIndex).setCurrentTurn(false);
        playerModels.get(currentPlayerIndex).setHasDrawnThisTurn(false);
        playerTables.get(currentPlayerIndex).getCurrentUserTable().setEffect(null);

        if(!reverseGameFlow){
            currentPlayerIndex = (currentPlayerIndex + 1) % playerModels.size();
        }else{
            currentPlayerIndex = (currentPlayerIndex - 1 + playerModels.size()) % playerModels.size();
        }

        playerModels.get(currentPlayerIndex).setCurrentTurn(true);
        playerTables.get(currentPlayerIndex).getCurrentUserTable().setEffect(getDropShadow());

        System.out.println("➡️ Turn switched to player: " + currentPlayerIndex);

        if (playerModels.get(currentPlayerIndex).isAI()) {
            System.out.println("🤖 AI Player " + currentPlayerIndex + " is taking a turn...");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> handleAITurn());
                }
            }, 1000); // ✅ AI moves after 1-second delay
        } else {
            System.out.println("🧑‍💻 Waiting for player " + currentPlayerIndex + " to play...");
        }
    }

    private void handleAITurn() {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    PlayerTableModel aiPlayer = getCurrentPlayer();
                    PlayerTable aiTable = playerTables.get(currentPlayerIndex);
                    PlayerTableController aiController = playerControllers.get(currentPlayerIndex);
                    CardController aiCardController = cardControllers.get(currentPlayerIndex);
                    CardModel topCard = openStack.getTopCard();
                    boolean playedCard = false;

                    System.out.println("🤖 AI is thinking...");

                    // Scan AI's hand for a valid card
                    for (CardModel card : aiPlayer.getHand()) {
                        if (card.getSuite().equals(topCard.getSuite()) || card.getValue() == 8) {
                            System.out.println("🤖 AI plays: " + card.getSuite() + " " + card.getValue());

                            RegularCards matchingCard = aiTable.getCardByModel(card);
                            if (matchingCard != null) {
                                aiController.removeCardFromTable(card, matchingCard);
                                playedCard = true;

                                //Check if AI won before switching turns
                                if (checkForWinner()) return;

                                nextTurn();
                                return;
                            }
                        }
                    }

                    //If AI's hand is already full (13 cards), it must pass
                    if (aiPlayer.getHand().size() >= 13) {
                        System.out.println("🤖 AI has no valid cards and cannot draw. Passing turn...");
                        nextTurn();
                        return;
                    }

                    // AI draws cards with a delay until it finds a valid one
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                if (aiPlayer.getHand().size() < 13) {
                                    System.out.println("🤖 AI draws a card...");
                                    aiCardController.addCardToTable();
                                    handleAITurn(); //Recursively check after drawing
                                } else {
                                    System.out.println("🤖 AI hand is full. Passing turn...");
                                    nextTurn();
                                }
                            });
                        }
                    }, 1000); //One-second delay per draw
                });
            }
        }, 1000); // ✅ Initial AI thinking delay
    }

    private boolean checkForWinner() {
        for (PlayerTableModel player : playerModels) {
            if (player.getHand().isEmpty()) {
                System.out.println("Player " + playerModels.indexOf(player) + " WINS! Game Over!");
                return true; //A winner is found, game stops
            }
        }
        return false; //Continue game if no winner
    }


    // Getters for game state access

    public PlayerTableModel getCurrentPlayer() {
        return playerModels.get(currentPlayerIndex);
    }
    public PlayerTableController getCurrentPlayerController() {
        return playerControllers.get(currentPlayerIndex);
    }

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

    public SuiteChooserController getSuiteChooserController() {
        return suiteChooserController;
    }

    public SuiteChooser getSuiteChooser() {
        return suiteChooser;
    }

    public void toggleReverseGameFlow() {
        reverseGameFlow = !reverseGameFlow;
    }

    //Additional
    public DropShadow getDropShadow() {
        if(glow == null) {
            glow = new DropShadow();
        }
        glow.setRadius(50);
        glow.setColor(Color.ORANGE);
        return glow;
    }

}
