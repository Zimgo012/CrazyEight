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
package test;

import model.CardModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the {@code CardModel} class, validating its functionalities and behavior.
 */
class CardModelTest {
        CardModel cardModel;
        CardModel cardModel2;

    /**
     * Initializes a new {@code CardModel} instance with a specified suite and value,
     * and verifies its properties by accessing and printing its suite and value.
     *
     * This method serves as a unit test to validate the correct instantiation and
     * property retrieval behavior of the {@code CardModel} class.
     *
     * The suite and value of the card are printed to the console as part of the validation process.
     */
    @Test
    void initCard(){

        cardModel  = new CardModel("Diamond",2);

        System.out.println(cardModel.getSuite());
        System.out.println(cardModel.getValue());
    }
    /**
     * Compares two instances of {@code CardModel} to verify that they are not equivalent.
     *
     * This method initializes two {@code CardModel} objects with the same suite but
     * different values. It then asserts that the two objects are not equal, which serves as a
     * validation that different value assignments result in different card representations.
     *
     * The method outputs the string representation of both {@code CardModel} instances to
     * the console for reference, followed by a confirmation message if the assertion passes.
     *
     * Use this method to validate the behavior of the {@code CardModel} equals method
     * or the lack of default equivalence between different cards with unequal value attributes.
     */
    @Test
    void compareCard(){
        cardModel  = new CardModel("Diamond",2);
        cardModel2  = new CardModel("Diamond",3);

        System.out.println("Model 1: " + cardModel + " and Model 2: " + cardModel2);
        assertNotEquals(cardModel,cardModel2);
        System.out.println("Card not same");



    }
}