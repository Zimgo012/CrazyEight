/**
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-09
 * Compiler: IntelliJ IDEA
 * Purpose:
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards.
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules.
 * Understanding these variations is essential for proper implementation.
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */
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

/**
 * Represents the game model for a single-player card game.
 * The game model manages players, AI players, in-game controls,
 * card distribution, and gameplay mechanics.
 */
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


    /**
     * Constructor for the SingleGameModel class.
     * This initializes a game model specifically designed for a single-player mode.
     *
     * The constructor performs the following core operations:
     * 1. Initializes the game state, including setup of the player models, tables, controllers,
     *    card stack, and other necessary game components by invoking the setupGameState() method.
     * 2. Distributes starting cards randomly to all players at the beginning of the game using
     *    the distributeStartingCards() method.
     * 3. Begins the gameplay by calling the startGame() method.
     */
    //Instantiate game model for single player
    public SingleGameModel() {
        setupGameState();
        distributeStartingCards();
        startGame();
    }

    /**
     * Initializes and sets up the game state for a single-player game session.
     *
     * This method performs the following operations:
     * 1. Creates and initializes a new `OpenStack` to manage the cards that are played.
     * 2. Initializes the `SuiteChooser` for choosing card suits during gameplay.
     * 3. Initializes the `SuiteChooserController` to handle logic related to suit selection.
     * 4. Creates a `Notification` instance for game updates and player notifications.
     * 5. Sets up player models, player tables, and their corresponding controllers:
     *    - Four players are created, where the first player is controlled by a human,
     *      and the rest are marked as AI and foreign.
     *    - For each player, a `PlayerTableModel`, `PlayerTable`, `PlayerTableController`,
     *      and `CardController` are instantiated and added to their respective collections.
     * 6. Initializes the stack of cards face-down (`CardsStackFaceDown`), associating it
     *    with the first player who starts the game.
     * 7. Sets the first player as the current turn holder, ensuring gameplay begins with them.
     */
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

    /**
     * Distributes starting cards to all players at the beginning of the game and initializes
     * the open stack with a starting card.
     *
     * The method performs the following operations:
     * 1. Iterates through each player controller and associates it with a corresponding card controller.
     * 2. Distributes 5 random cards to each player by invoking the addCardToTable method on the card controller.
     * 3. Sets up a random initial card to begin the game using the generateRandomCard method of the first card controller.
     * 4. If the randomly generated initial card has a value of 8, adjusts it by decreasing its value by 1.
     * 5. Adds the adjusted initial card to the open stack as a starting card.
     */
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

    /**
     * Initiates and starts the gameplay by signaling the beginning of the game.
     *
     * This method performs the following operations:
     * 1. Prints a confirmation message to indicate the game has started.
     * 2. Schedules the first turn of the gameplay to begin using the `nextTurn()` method.
     *
     * It is typically invoked after the game state has been initialized and the
     * necessary cards have been distributed to players. This method ensures that
     * gameplay progresses seamlessly from the initial state.
     */
    //Starts the game
    public void startGame() {
        System.out.println("🎮 Game Started!");
        Platform.runLater(() -> nextTurn());
    }

    /**
     * Handles the transition to the next player's turn in the game, updating the game state based on
     * specific game rules and conditions.
     *
     * This method performs the following core operations:
     * 1. Ensures the game is still running. If the game has ended, further turn processing is halted.
     * 2. Checks if a player has won the game. If so, the game is concluded.
     * 3. Resets the open stack if all players' hands are full, generating a random initial card and
     *    adding it to the open stack for continued gameplay.
     * 4. Updates the previous player's state by:
     *    - Marking the player's turn as ended.
     *    - Resetting the flag indicating whether the player has drawn a card during their turn.
     *    - Clearing any active effect on the player's table.
     * 5. Handles special game rules, including:
     *    - Skipping turns for certain rules.
     *    - Reversing game flow for directional rules.
     *    - Executing effects like playing consecutive "four" or "two" cards.
     * 6. Adjusts the current player index to align with game flow, ensuring the turn moves to the correct player.
     * 7. Updates the current player's state to reflect their active turn and applies any visual effects
     *    (e.g., drop shadow) to indicate their turn status.
     * 8. Determines whether the current player is an AI or a human:
     *    - If it's an AI player, initiates AI-specific logic asynchronously.
     *    - For human players, waits for the player's manual interaction.
     *
     * This method orchestrates turn transitions while accommodating game-specific effects and rules,
     * ensuring smooth gameplay progression.
     */
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


    /**
     * Executes the AI player's turn during the game.
     *
     * This method controls the AI logic and actions in the game flow.
     * The AI's turn follows these steps:
     *
     * 1. The AI will pause briefly (simulating a "thinking" delay) before making a move.
     * 2. Once active, the AI evaluates its hand and compares its cards to the game's open stack.
     *    - If a card in the AI's hand matches the suite of the top card on the open stack,
     *      or if the card is a wildcard (e.g., value 8), the AI will play that card.
     *    - The played card will be logged, removed from the AI player's table, and processed in the game.
     *    - If playing the card results in a winning condition, the game will end.
     * 3. If the AI cannot play a card and its hand size is less than the maximum limit (12 cards),
     *    it will draw a new card to its hand from the stack.
     *    - The drawing process involves a delay to simulate gameplay pacing.
     *    - After drawing, the AI reevaluates its hand and may decide to play a card.
     * 4. If the AI's hand is full (12 cards) and it cannot play a card, it will pass its turn
     *    to the next player.
     * 5. The current player's turn is transitioned using the `nextTurn()` method once
     *    the AI's move is completed or skipped.
     *
     * Note:
     * - The AI's decisions are made asynchronously on a separate thread, ensuring that
     *   the game runs smoothly without freezing or delaying other components.
     * - The AI logic will not execute if the game is not running or if the player terminates the game.
     */
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


    /**
     * Checks if there is a winner in the game by determining if any player's hand is empty.
     * If a player wins, displays a notification, stops the game, and returns true.
     *
     * @return true if a player has won the game and the gameplay should stop, false otherwise
     */
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

    /**
     * Stops the ongoing single-player game and terminates any active AI processes.
     *
     * This method performs the following actions:
     * 1. Sets the game state to not running by setting the `gameRunning` flag to false.
     * 2. Interrupts the AI thread if it is actively running, ensuring AI processing is stopped.
     * 3. Logs a message indicating the game has been stopped.
     *
     * It is typically invoked when the player decides to end the game prematurely
     * or when the game needs to be concluded programmatically.
     */
    public void stopGame() {
        gameRunning = false; // Stop AI and game turns

        if (aiThread != null) {
            aiThread.interrupt(); // Stop AI processing
        }

        System.out.println("⛔ Game Stopped!");
    }


    /**
     * Retrieves the PlayerTableModel instance representing the current player
     * based on the current player index in the player models list.
     *
     * @return the PlayerTableModel of the current player.
     */
    // Getters for game state access
    public PlayerTableModel getCurrentPlayer() {
        return playerModels.get(currentPlayerIndex);
    }

    /**
     * Retrieves the controller for the current player based on the current player index.
     *
     * @return the PlayerTableController instance corresponding to the current player
     */
    public PlayerTableController getCurrentPlayerController() {
        return playerControllers.get(currentPlayerIndex);
    }

    /**
     * Retrieves the list of PlayerTableModel objects.
     *
     * @return a list containing PlayerTableModel objects.
     */
    public List<PlayerTableModel> getPlayerModels() {
        return playerModels;
    }

    /**
     * Retrieves the list of player tables.
     *
     * @return a list of PlayerTable objects representing the player tables.
     */
    public List<PlayerTable> getPlayerTables() {
        return playerTables;
    }

    /**
     * Retrieves the instance of OpenStack.
     *
     * @return the OpenStack instance associated with this object.
     */
    public OpenStack getOpenStack() {
        return openStack;
    }

    /**
     * Retrieves the stack of cards that are placed face down.
     *
     * @return an instance of CardsStackFaceDown representing the stack of face-down cards.
     */
    public CardsStackFaceDown getCardsStackFaceDown() {
        return cardsStackFaceDown;
    }

    /**
     * Retrieves the SuiteChooserController instance.
     *
     * @return the SuiteChooserController instance associated with this class.
     */
    public SuiteChooserController getSuiteChooserController() {
        return suiteChooserController;
    }

    /**
     * Retrieves the suite chooser instance.
     *
     * @return the SuiteChooser instance associated with this class
     */
    public SuiteChooser getSuiteChooser() {
        return suiteChooser;
    }

    /**
     * Toggles the state of the reverse game flow.
     * This method switches the value of the reverseGameFlow flag
     * between true and false, effectively enabling or disabling
     * reverse game flow behavior.
     */
    public void toggleReverseGameFlow() {
        reverseGameFlow = !reverseGameFlow;
    }

    /**
     * Toggles the value of the isPlayFour variable.
     * If isPlayFour is currently true, it will be set to false.
     * If isPlayFour is currently false, it will be set to true.
     */
    public void toggleIsPlayFour() {
        isPlayFour = !isPlayFour;
    }

    /**
     * Sets the value indicating whether PlayTwoRelease is enabled.
     *
     * @param isPlayTwoRelease the boolean value to set for PlayTwoRelease
     */
    public void isPlayTwoRelease(boolean isPlayTwoRelease) {
        this.isPlayTwoRelease = isPlayTwoRelease;
    }

    /**
     * Increments the value of cardsCountForPlayTwo by 2.
     * This method is used to update the count of cards
     * designated for the "play two" functionality or scenario.
     */
    public void incrementTwoCard(){
        cardsCountForPlayTwo = cardsCountForPlayTwo + 2;
    }

    /**
     * Sets the state of whether the play is enabled for a second player or not.
     *
     * @param isPlayTwo A boolean value indicating if the play for the second player is enabled (true) or disabled (false).
     */
    public void isPlayTwo(boolean isPlayTwo) {
        this.isPlayTwo = isPlayTwo;
    }

    /**
     * Toggles the state of the skipTurn variable.
     * If skipTurn is currently true, this method sets it to false.
     * If skipTurn is currently false, this method sets it to true.
     */
    public void toggleSkipTurn(){
        skipTurn = !skipTurn;
    }


    /**
     * Retrieves the current instance of the LogModel.
     *
     * @return the LogModel instance associated with this object.
     */
    public LogModel getLogModel(){
        return logModel;
    }

    /**
     * Retrieves the current instance of the log view.
     *
     * @return the Log object representing the current log view
     */
    public Log getLogView(){
        return logView;
    }

    // Additional

    /**
     * Retrieves the current notification instance.
     *
     * @return the current Notification object
     */
    public Notification getNotification() {
        return notification;
    }

    //Additional

    /**
     * Retrieves a DropShadow effect with pre-configured properties.
     * If the DropShadow is not already initialized, it initializes it, sets its radius to 50,
     * and color to orange.
     *
     * @return a DropShadow instance with a radius of 50 and an orange color.
     */
    public DropShadow getDropShadow() {
        if (glow == null) {
            glow = new DropShadow();
        }
        glow.setRadius(50);
        glow.setColor(Color.ORANGE);
        return glow;
    }
    /**
     * Adds four cards to the current player's hand when a draw-four card is played.
     * If the player's hand cannot accommodate all four cards without exceeding the
     * maximum limit of 12 cards, the remaining cards are distributed to the adjacent
     * player's hand based on the game flow direction.
     *
     * This method considers the following scenarios:
     * 1. If the current player's hand can safely accommodate all four cards
     *    without exceeding the maximum limit, all cards are added to their hand.
     * 2. If adding four cards exceeds the maximum hand limit of the current player,
     *    the overflow cards are distributed to the adjacent player's hand based
     *    on the direction of game flow.
     *
     * The game flow direction is determined by the `reverseGameFlow` flag:
     * - If `reverseGameFlow` is false, the overflow cards are distributed to the
     *   previous player.
     * - If `reverseGameFlow` is true, the overflow cards are distributed to the
     *   next player.
     *
     * The method ensures that no player's hand exceeds the maximum size of 12 cards.
     */
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

    /**
     * Adds two cards for the next player. If sequential +2 cards are played,
     * the count of cards continues to increment until the stack is reset.
     *
     * This method determines the next player based on the current game flow direction
     * and assigns the accumulated count of +2 cards to that player. After processing,
     * the play two stack is reset to zero.
     */
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

    /**
     * Updates the current player index and manages the flow of the game
     * by adjusting the turn order depending on the direction of gameplay.
     * If the game flow is not reversed, the current player index is incremented
     * accordingly. In a reversed game flow, the index is decremented.
     * The method also toggles the skip turn status to ensure proper turn management.
     */
    private void addQueen(){
        if (!reverseGameFlow) {
            currentPlayerIndex = (currentPlayerIndex + 2) % playerModels.size();
            toggleSkipTurn();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 2 + playerModels.size()) % playerModels.size();
            toggleSkipTurn();
        }
    }


    /**
     * Checks if all player hands are full by verifying if each player's hand contains a specific number of cards.
     *
     * @return true if all player hands have 12 cards, false otherwise.
     */
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
