/**
 * File name: CardModel.java
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


public class CardModel {

    private boolean isCurrentPlayer;
    private int value;
    private String suite;
    private List<CardObserver> observers = new ArrayList<>();

    public CardModel(String suite, int number) {

        this.suite = suite;
        this.value = number;
    }

    public String getSuite() {
        return this.suite;
    }

    public int getValue() {
        return this.value;
    }

    public void addObserver(CardObserver observer) {
        observers.add(observer);
    }

    public void setValue(int value) {
        this.value = value;
        notifyObservers();
    }

    public void setSuite(String suite) {
        this.suite = suite;
        notifyObservers();
    }

    private void notifyObservers() {
        for (CardObserver observer : observers) {
            observer.updateCard(this);
        }
    }


    @Override
    public String toString() {
        return String.format("Suite: %s, Value: %d", this.suite, this.value);
    }
}
