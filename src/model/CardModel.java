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


import observers.CardObserver;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a card in a card game with a specific suite and value.
 * Observers can be added to get notified when the card's properties change.
 */
public class CardModel {

    private boolean isCurrentPlayer;
    private int value;
    private String suite;
    private List<CardObserver> observers = new ArrayList<>();

    /**
     * Constructs a new {@code CardModel} with the specified suite and number.
     *
     * @param suite the suite of the card (e.g., "Diamond", "Clubs", "Hearts", "Spade")
     * @param number the numerical value of the card (e.g., 1-13)
     */
    public CardModel(String suite, int number) {

        this.suite = suite;
        this.value = number;
    }

    /**
     * Retrieves the suite of the card.
     *
     * @return the suite of the card (e.g., "Diamond", "Clubs", "Hearts", "Spade").
     */
    public String getSuite() {
        return this.suite;
    }

    /**
     * Retrieves the numerical value of the card.
     *
     * @return the value of the card as an integer.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Adds an observer to the list of observers for this card model.
     * Observers will be notified when the card's properties change.
     *
     * @param observer the observer to be added, which implements the {@code CardObserver} interface.
     */
    public void addObserver(CardObserver observer) {
        observers.add(observer);
    }

    /**
     * Updates the value of the card and notifies all observers about the change.
     *
     * @param value the new numerical value to set for the card.
     */
    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }

    /**
     * Updates the suite of the card and notifies all observers about the change.
     *
     * @param suite the new suite to set for the card (e.g., "Diamond", "Clubs", "Hearts", "Spade").
     */
    public void setSuite(String suite) {
        this.suite = suite;
        notifyObservers();
    }

    /**
     * Notifies all registered observers about a change in the card model.
     *
     * This method iterates through the list of observers and invokes their
     * {@code updateCard} method, passing the current instance of {@code CardModel}
     * as an argument. Observers are updated typically when the card's properties,
     * such as suite or value, are modified.
     */
    private void notifyObservers() {
        for (CardObserver observer : observers) {
            observer.updateCard(this);
        }
    }


    /**
     * Returns a string representation of the card, including its suite and value.
     *
     * @return a formatted string representing the suite and value of the card.
     */
    @Override
    public String toString() {
        return String.format("Suite: %s, Value: %d", this.suite, this.value);
    }
}
