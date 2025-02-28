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
     * Create new deck for a player
     */
    public PlayerTableModel(SingleGameModel singleGameModel) {
        this.hand = new ArrayList<>();
        this.isAI = false;
        isCurrentTurn = false;
        this.singleGameModel = singleGameModel;
    }

    /**
     * add card to the list
     * @param card object
     */
    public void addCard(CardModel card) {
        hand.add(card);
        notifyObservers();
    }

    /**
     * Remove card from list
     * @param card object
     */
    public void removeCard(CardModel card) {
        hand.remove(card);
        notifyObservers();
    }

    /**
     * Get player name
     * @return return player's name
     */
    public String getPlayerName(){
        return this.playerName;
    }

    /**
     * Get player's list of cards
     * @return list of cards
     */
    public List<CardModel> getPlayerCards(){
        return this.hand;
    }

    /**
     * Add observer
     * @param observer for action
     */
    public void addObserver(PlayerTableObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies observers for updates
     */
    private void notifyObservers() {
        for (PlayerTableObserver observer : observers) {
            observer.updateTable(this);
        }
    }

    /**
     * Sets tables name
     * @param playerName table's name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        notifyObservers();
    }

    public void setIsForeign(boolean isForeign) {
        this.isOpponent = isForeign;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    public void setCurrentTurn(boolean isCurrentTurn) {
        this.isCurrentTurn = isCurrentTurn;
    }

    public void setHasDrawnThisTurn(boolean hasDrawn) {
        this.hasDrawnThisTurn = hasDrawn;
    }

    public boolean hasDrawnThisTurn() {
        return hasDrawnThisTurn;
    }

    public boolean isCurrentTurn() {
        return isCurrentTurn;
    }

    public boolean getIsForeign() {
        return this.isOpponent;
    }

    public boolean isAI() {
        return this.isAI;
    }

    public SingleGameModel getSingleGameModel() {
        return singleGameModel;
    }

    public void setSingleGameModel(SingleGameModel singleGameModel) {
        this.singleGameModel = singleGameModel;
    }

    public List<CardModel> getHand() {
        return hand;
    }

    /**
     * Prints list of player's cards
     */
    public void printCard(){
        for(CardModel card : hand){
            System.out.println(card);
        }
    }

}
