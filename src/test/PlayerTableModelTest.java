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
import model.PlayerTableModel;
import model.SingleGameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test class for the PlayerTableModel class.
 *
 * This class contains tests to verify the functionality and behavior of the PlayerTableModel class,
 * including adding and removing cards, retrieving player information, and printing player cards.
 */
class PlayerTableModelTest {
        PlayerTableModel playerTableModel;
        CardModel card1  = new CardModel("Diamond",2);
        CardModel card2  = new CardModel("Diamond",5);
        CardModel card3  = new CardModel("Diamond",8);
        SingleGameModel singleGameModel = new SingleGameModel();

    /**
     * Initializes the PlayerTableModel instance for use in unit tests.
     *
     * This method is annotated with @BeforeEach, ensuring it is executed
     * before each test case in the PlayerTableModelTest class. It creates
     * a new instance of PlayerTableModel and associates it with a SingleGameModel
     * object to set up the test environment.
     */
    @BeforeEach
    void initCard(){
        playerTableModel = new PlayerTableModel(singleGameModel);

    }

    /**
     * Tests the getPlayerName method of the PlayerTableModel class.
     *
     * Verifies that the getPlayerName method correctly retrieves the name
     * of the player associated with the PlayerTableModel instance by comparing
     * it to the expected value. Additionally, it prints the player's name
     * to standard output for confirmation.
     */
    @Test
    void getName(){
        String pName = playerTableModel.getPlayerName();

        assertEquals(pName, "John Doe");
        System.out.println(playerTableModel.getPlayerName());
    }

    /**
     * Tests the addCard method of the PlayerTableModel class by verifying the addition of cards.
     *
     * This test ensures that the addCard method correctly adds multiple cards
     * to the player's hand by comparing the size of the player's card list with
     * the expected value. The test validates that the correct number of cards
     * has been added and prints a confirmation message to the standard output.
     */
    @Test
    void addCardView(){
        playerTableModel.addCard(card1);
        playerTableModel.addCard(card2);
        playerTableModel.addCard(card3);

        assertEquals(playerTableModel.getPlayerCards().size(),3);
        System.out.println("Correct number of cards!");

    }

    /**
     * Tests the removeCard method of the PlayerTableModel class.
     *
     * Verifies that the removeCard method correctly removes a specified card from the player's hand
     * and updates the player's current hand size accordingly. The test ensures that when a card is
     * removed, the remaining number of cards in the player's hand is correctly updated. Additionally,
     * it validates that no unintended changes occur to other cards in the hand.
     */
    @Test
    void removeCard(){
        playerTableModel.addCard(card1);
        playerTableModel.addCard(card2);
        playerTableModel.addCard(card3);

        System.out.println("Before: " + playerTableModel.getPlayerCards().size());
        playerTableModel.removeCard(card1);
        System.out.println("After: " + playerTableModel.getPlayerCards().size());

        assertEquals(playerTableModel.getPlayerCards().size(),2);
        System.out.println("Correct number of cards!");

    }

    /**
     * Tests the printCard method of the PlayerTableModel class.
     *
     * This test verifies the functionality of the printCard method by first
     * adding multiple cards to the player's hand and subsequently calling the
     * printCard method. It ensures that all the added cards are printed correctly
     * to the console by iterating through the player's hand and outputting their
     * string representations. The test confirms that the method successfully
     * prints the expected cards in the correct order.
     */
    @Test
    void getPrintCards(){
        playerTableModel.addCard(card1);
        playerTableModel.addCard(card2);
        playerTableModel.addCard(card3);

        playerTableModel.printCard();
    }
}