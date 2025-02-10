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


public class CardModel {

    boolean isCurrentPlayer;
    int value;
    String suite;

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


    @Override
    public String toString() {
        return String.format("Suite: %s, Value: %d", this.suite, this.value);
    }
}
