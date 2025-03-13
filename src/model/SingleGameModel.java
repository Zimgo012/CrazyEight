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
import view.components.Log;
import view.components.Notification;
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
    private Thread aiThread;
    private boolean gameRunning = true;

    //plays four
    private boolean isPlayFour = false;
    //plays two
    private int cardsCountForPlayTwo = 0;
    private boolean isPlayTwo = false; // flag for stacking
    private boolean isPlayTwoRelease = false;
    //plays queen
    private boolean skipTurn = false;

    //Misc
    private DropShadow glow;

    private LogModel logModel = new LogModel();
    private Log logView = new Log(logModel, this);
    private Notification notification;


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
        notification = new Notification(this);


        // Create players and controllers
        for (int i = 0; i < 4; i++) {
            PlayerTableModel playerModel = new PlayerTableModel(this);
            playerModels.add(playerModel);

            if (i != 0) {
                playerModel.setIsAI(true); //First player is human for single player.
                playerModel.setIsForeign(true);
            }
            PlayerTable playerTable = new PlayerTable(playerModel, openStack, this);
            playerTables.add(playerTable);

            PlayerTableController controller = new PlayerTableController(playerModel, playerTable, openStack, this);
            playerControllers.add(controller);

            CardController cardController = new CardController(playerTable, controller);
            cardControllers.add(cardController);


        }

        cardsStackFaceDown = new CardsStackFaceDown(cardControllers.get(0), playerModels.get(currentPlayerIndex));
        playerModels.get(0).setCurrentTurn(true);

    }

    //Distributes random card at the start of the game
    private void distributeStartingCards() {
        for (PlayerTableController playerController : playerControllers) {

            CardController cardController = cardControllers.get(playerControllers.indexOf(playerController));

            for (int i = 0; i < 5; i++) { // Each player gets 5 random cards at start
                cardController.addCardToTable();
            }
        }
        //Sets up one card to start the game
        randomInitialCard = cardControllers.get(0).generateRandomCard();

        //Making sure card number is not 8
        if (randomInitialCard.getValue() == 8) {
            randomInitialCard.setValue(randomInitialCard.getValue() - 1);
        }

        openStack.addCard(new RegularCards(randomInitialCard), randomInitialCard);

    }

    //Starts the game
    public void startGame() {
        System.out.println("🎮 Game Started!");
        Platform.runLater(() -> nextTurn());
    }

    //Track whose turn
    public void nextTurn() {
        if (!gameRunning) return; // Stop if game is ending

        // Check if any player has won
        if (checkForWinner()) {
            return;
        }

        // Reset open stack if all hands are full
        if (checkAllHandsAreFull()) {
            System.out.println("🔄 All hands are full! Resetting the open stack...");
            randomInitialCard = cardControllers.get(0).generateRandomCard();
            openStack.addCard(new RegularCards(randomInitialCard), randomInitialCard);
        }

        // Update previous player's state
        playerModels.get(currentPlayerIndex).setCurrentTurn(false);
        playerModels.get(currentPlayerIndex).setHasDrawnThisTurn(false);
        playerTables.get(currentPlayerIndex).getCurrentUserTable().setEffect(null);

        // Handle game rules like reverse or skip
        if (skipTurn) {
            addQueen();
        } else {
            currentPlayerIndex = reverseGameFlow
                    ? (currentPlayerIndex - 1 + playerModels.size()) % playerModels.size()
                    : (currentPlayerIndex + 1) % playerModels.size();
        }

        if (isPlayFour) {
            addFour();
            toggleIsPlayFour();
        }

        if (!isPlayTwo && isPlayTwoRelease) {
            addTwo();
            isPlayTwo(false);
            isPlayTwoRelease(false);
            cardsCountForPlayTwo = 0; // Reset playTwo count
        }

        // Update current player's turn state
        playerModels.get(currentPlayerIndex).setCurrentTurn(true);
        playerTables.get(currentPlayerIndex).getCurrentUserTable().setEffect(getDropShadow());
        System.out.println("➡️ Turn switched to player: " + currentPlayerIndex);

        // If it's an AI player's turn, start AI logic in a new thread
        if (playerModels.get(currentPlayerIndex).isAI()) {
            System.out.println("🤖 AI Player " + currentPlayerIndex + " is thinking...");
            handleAITurn();
        } else {
            System.out.println("🧑‍💻 Waiting for player " + currentPlayerIndex + " to play...");
        }
    }


    private void handleAITurn() {
        if (!gameRunning) return; // Stop AI if game is stopped

        aiThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // AI thinking delay
                Platform.runLater(() -> {
                    if (!gameRunning) return; // Stop if quitting

                    PlayerTableModel aiPlayer = getCurrentPlayer();
                    PlayerTable aiTable = playerTables.get(currentPlayerIndex);
                    PlayerTableController aiController = playerControllers.get(currentPlayerIndex);
                    CardController aiCardController = cardControllers.get(currentPlayerIndex);
                    CardModel topCard = openStack.getTopCard();
                    boolean playedCard = false;

                    System.out.println("🤖 AI is thinking...");

                    for (CardModel card : aiPlayer.getHand()) {
                        if (card.getSuite().equals(topCard.getSuite()) || card.getValue() == 8) {
                            System.out.println("🤖 AI plays: " + card.getSuite() + " " + card.getValue());
                            logModel.addString("AI plays: " + card.getSuite() + " " + card.getValue());
                            logView.refreshLog();

                            RegularCards matchingCard = aiTable.getCardByModel(card);
                            if (matchingCard != null) {
                                aiController.removeCardFromTable(card, matchingCard);
                                playedCard = true;

                                if (checkForWinner()) return;
                                nextTurn();
                                return;
                            }
                        }
                    }

                    if (aiPlayer.getHand().size() >= 12) {
                        System.out.println("🤖 AI has no valid cards and cannot draw. Passing turn...");
                        nextTurn();
                        return;
                    }

                    // AI draws a card and rechecks its hand
                    aiThread = new Thread(() -> {
                        try {
                            Thread.sleep(1000); // AI draws a card delay
                            Platform.runLater(() -> {
                                if (!gameRunning) return;

                                if (aiPlayer.getHand().size() < 12) {
                                    System.out.println("🤖 AI draws a card...");
                                    aiCardController.addCardToTable();
                                    handleAITurn(); // Continue AI logic
                                } else {
                                    System.out.println("🤖 AI hand is full. Passing turn...");
                                    nextTurn();
                                }
                            });
                        } catch (InterruptedException ignored) {}
                    });
                    aiThread.setDaemon(true);
                    aiThread.start();

                });
            } catch (InterruptedException ignored) {}
        });

        aiThread.setDaemon(true);
        aiThread.start();
    }


    private boolean checkForWinner() {
        for (PlayerTableModel player : playerModels) {
            if (player.getHand().isEmpty()) {
                System.out.println("Player " + playerModels.indexOf(player) + " WINS! Game Over!");
                getNotification().promptNotification("Player " + playerModels.indexOf(player) + " WINS! Game Over!");
                stopGame();
                return true; //A winner is found, game stops
            }
        }
        return false; //Continue game if no winner
    }

    public void stopGame() {
        gameRunning = false; // Stop AI and game turns

        if (aiThread != null) {
            aiThread.interrupt(); // Stop AI processing
        }

        System.out.println("⛔ Game Stopped!");
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

    public void toggleIsPlayFour() {
        isPlayFour = !isPlayFour;
    }

    public void isPlayTwoRelease(boolean isPlayTwoRelease) {
        this.isPlayTwoRelease = isPlayTwoRelease;
    }

    public void incrementTwoCard(){
        cardsCountForPlayTwo = cardsCountForPlayTwo + 2;
    }

    public void isPlayTwo(boolean isPlayTwo) {
        this.isPlayTwo = isPlayTwo;
    }

    public void toggleSkipTurn(){
        skipTurn = !skipTurn;
    }


    public LogModel getLogModel(){
        return logModel;
    }

    public Log getLogView(){
        return logView;
    }

    // Additional

    public Notification getNotification() {
        return notification;
    }

    //Additional

    public DropShadow getDropShadow() {
        if (glow == null) {
            glow = new DropShadow();
        }
        glow.setRadius(50);
        glow.setColor(Color.ORANGE);
        return glow;
    }
    //Adds four cards if four is drawn
    private void addFour() {

        CardController cardController = cardControllers.get(playerControllers.indexOf(getCurrentPlayerController()));

        int currentHandSize = getCurrentPlayer().getHand().size();
        int cardToDistribute = 4;
        int remainingCards = 0;


        if (currentHandSize + cardToDistribute < 12) {
            //Loop 4 times
            for (int i = 0; i < cardToDistribute; i++) {
                cardController.addCardToTable();
            }
        } else {
            int cardsThatFit = 12 -  currentHandSize; //12-4
            remainingCards = cardToDistribute - cardsThatFit;

            for (int i = 0; i < cardsThatFit; i++) {
                cardController.addCardToTable();
            }

            if (!reverseGameFlow) {
                //set previous player hand
                currentPlayerIndex = (currentPlayerIndex - 1) % playerModels.size();
                for (int i = 0; i < remainingCards; i++) {
                    if (currentPlayerIndex < 12) {
                        cardController.addCardToTable();
                        //Message here
                    }
                }
                //set current player hand
                currentPlayerIndex = (currentPlayerIndex + 1) % playerModels.size();
            } else {
                //set previous player hand
                currentPlayerIndex = (currentPlayerIndex + 1 + playerModels.size()) % playerModels.size();
                for (int i = 0; i < remainingCards; i++) {
                    if (currentPlayerIndex < 12) {
                        cardController.addCardToTable();
                        //Message here
                    }
                    //set current player hand
                    currentPlayerIndex = (currentPlayerIndex - 1 + playerModels.size()) % playerModels.size();
                }

            }
        }

    }

    //Add two cards that increment if players keep getting card 2. ex. (2+2+2) 6
    private void addTwo() {
    int nextPlayerIndex = reverseGameFlow
            ? (currentPlayerIndex - 1 + playerModels.size()) % playerModels.size()
            : (currentPlayerIndex + 1) % playerModels.size();

    CardController cardController = cardControllers.get(nextPlayerIndex);
    PlayerTableModel nextPlayer = playerModels.get(nextPlayerIndex);

    // Ensure the next player gets all stacked +2 cards
    for (int i = 0; i < cardsCountForPlayTwo; i++) {
        cardController.addCardToTable();
    }

    // Reset playTwo stack
    cardsCountForPlayTwo = 0;
}

    private void addQueen(){
        if (!reverseGameFlow) {
            currentPlayerIndex = (currentPlayerIndex + 2) % playerModels.size();
            toggleSkipTurn();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 2 + playerModels.size()) % playerModels.size();
            toggleSkipTurn();
        }
    }


    //checks if all hands is full
    private boolean checkAllHandsAreFull(){
        for (PlayerTableModel playerModel : playerModels) {
           if(playerModel.getHand().size() < 12){
               return false;
           }
        }

        return true;
    }
}
