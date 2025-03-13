/**
 * File name: PlayerModel.java
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


import observers.PlayerTableObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's table in a single game. Manages the player's hand of cards,
 * attributes such as name, AI status, opponent status, current turn, and observers
 * monitoring the player's table state.
 */
public class PlayerTableModel {
    private SingleGameModel singleGameModel;
    private String playerName;
    private List<CardModel> hand;
    private List<PlayerTableObserver> observers = new ArrayList<>();
    private boolean isOpponent;
    private boolean isAI;
    private boolean isCurrentTurn;
    private boolean hasDrawnThisTurn = false;

    /**
     * Constructs a new PlayerTableModel with the given SingleGameModel.
     * Initializes the player's hand, AI status, and turn status.
     *
     * @param singleGameModel the game model associated with this player table
     */
    public PlayerTableModel(SingleGameModel singleGameModel) {
        this.hand = new ArrayList<>();
        this.isAI = false;
        isCurrentTurn = false;
        this.singleGameModel = singleGameModel;
    }

    /**
     * Adds a card to the player's hand and notifies all observers of the change.
     *
     * @param card the card to be added to the player's hand
     */
    public void addCard(CardModel card) {
        hand.add(card);
        notifyObservers();
    }

    /**
     * Removes the specified card from the player's hand and notifies all observers of the change.
     *
     * @param card the card to be removed from the player's hand
     */
    public void removeCard(CardModel card) {
        hand.remove(card);
        notifyObservers();
    }

    /**
     * Retrieves the name of the player associated with this instance.
     *
     * @return the player's name as a String
     */
    public String getPlayerName(){
        return this.playerName;
    }

    /**
     * Retrieves the list of cards currently held by the player.
     *
     * @return a List of CardModel objects representing the player's current hand.
     */
    public List<CardModel> getPlayerCards(){
        return this.hand;
    }

    /**
     * Adds an observer to the list of observers for this PlayerTableModel.
     * The observer will be notified of any updates or changes to the model.
     *
     * @param observer the PlayerTableObserver instance to be added
     */
    public void addObserver(PlayerTableObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers of changes to the PlayerTableModel.
     *
     * This method iterates through the list of observers and invokes their
     * `updateTable` method, passing the current instance of the PlayerTableModel
     * as an argument to ensure they are informed about the latest state of the model.
     *
     * This is typically called whenever a significant change occurs in the model,
     * such as when a card is added or removed from the player's hand,
     * or when other relevant attributes are modified.
     */
    private void notifyObservers() {
        for (PlayerTableObserver observer : observers) {
            observer.updateTable(this);
        }
    }

    /**
     * Sets the name of the player associated with this PlayerTableModel.
     * Updates the player name and notifies all observers of the change.
     *
     * @param playerName the name of the player to be set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        notifyObservers();
    }

    /**
     * Sets whether the player is considered a foreign entity in the game.
     *
     * @param isForeign a boolean value indicating whether the player is foreign;
     *                  true if the player is foreign, false otherwise.
     */
    public void setIsForeign(boolean isForeign) {
        this.isOpponent = isForeign;
    }

    /**
     * Sets whether the player is controlled by an AI.
     *
     * @param isAI a boolean indicating whether the player is AI-controlled;
     *             true if the player is controlled by an AI, false otherwise.
     */
    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    /**
     * Sets the current turn state for the player.
     * Updates whether it is the player's turn in the game.
     *
     * @param isCurrentTurn a boolean indicating if it is the player's turn;
     *                      true if it is the player's turn, false otherwise.
     */
    public void setCurrentTurn(boolean isCurrentTurn) {
        this.isCurrentTurn = isCurrentTurn;
    }

    /**
     * Sets the drawn status for the current turn of the player.
     * Updates whether the player has drawn a card during their turn.
     *
     * @param hasDrawn a boolean indicating if the player has drawn a card;
     *                 true if the player has drawn, false otherwise.
     */
    public void setHasDrawnThisTurn(boolean hasDrawn) {
        this.hasDrawnThisTurn = hasDrawn;
    }

    /**
     * Checks whether the player has drawn a card during the current turn.
     *
     * @return true if the player has drawn a card this turn, false otherwise
     */
    public boolean hasDrawnThisTurn() {
        return hasDrawnThisTurn;
    }

    /**
     * Checks whether it is the player's current turn.
     *
     * @return true if it is the player's turn, false otherwise
     */
    public boolean isCurrentTurn() {
        return isCurrentTurn;
    }

    /**
     * Checks whether the player associated with this model is considered a foreign entity
     * (e.g., an opponent) in the game.
     *
     * @return true if the player is foreign (opponent), false otherwise
     */
    public boolean getIsForeign() {
        return this.isOpponent;
    }

    /**
     * Determines if the player associated with this model is controlled by an AI.
     *
     * @return true if the player is controlled by an AI, false otherwise
     */
    public boolean isAI() {
        return this.isAI;
    }

    /**
     * Retrieves the SingleGameModel associated with this PlayerTableModel.
     *
     * @return the SingleGameModel instance representing the game model linked to this player table.
     */
    public SingleGameModel getSingleGameModel() {
        return singleGameModel;
    }

    /**
     * Sets the SingleGameModel associated with this PlayerTableModel.
     * Updates the game model reference for this player table.
     *
     * @param singleGameModel the SingleGameModel instance to be associated with this PlayerTableModel
     */
    public void setSingleGameModel(SingleGameModel singleGameModel) {
        this.singleGameModel = singleGameModel;
    }

    /**
     * Retrieves the list of cards currently held by the player.
     *
     * @return a List of CardModel objects representing the cards in the player's hand.
     */
    public List<CardModel> getHand() {
        return hand;
    }

    /**
     * Prints all the cards currently in the player's hand to the console.
     *
     * This method iterates over the list of cards in the player's hand and
     * outputs the string representation of each card using the {@code toString()}
     * method of the {@code CardModel} class.
     */
    public void printCard(){
        for(CardModel card : hand){
            System.out.println(card);
        }
    }

}
