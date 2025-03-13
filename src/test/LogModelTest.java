 /**
  * File name: GameController.java
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
import model.LogModel;
import model.PlayerTableModel;
import model.SingleGameModel;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test class for LogModel functionality.
 * This class contains unit tests for verifying the behavior of LogModel,
 * including adding log messages and retrieving the most recent log.
 */
class LogModelTest {

       LogModel logModel = new LogModel();
       SingleGameModel singleGameModel = new SingleGameModel();

    /**
     * Tests the functionality of adding a log message to the LogModel.
     *
     * This method verifies that a new log message is properly added to the LogModel's
     * log list. It creates a PlayerTableModel instance, adds a card to the player's hand,
     * generates a corresponding log message, and appends it to the LogModel.
     * The test ensures that the size of the log list increases as expected.
     */
    @Test
    void addLog(){

        PlayerTableModel player1 = new PlayerTableModel(singleGameModel);

        CardModel cardModel1 = new CardModel("Diamond", 4);
        player1.addCard(cardModel1);

        logModel.addString(player1.getPlayerName() + ": Added " + cardModel1.getSuite() + " " + cardModel1.getValue());

        assertEquals(logModel.getLogList().size(), 1);
    }

    /**
     * Tests the functionality of retrieving and printing the most recent log message
     * from the LogModel after performing operations that generate log entries.
     *
     * This method prints the initial state of the most recent log, performs actions
     * such as adding a card to a player's hand, generates a corresponding log message,
     * and appends it to the LogModel. It then prints the updated most recent log message
     * to verify that the log was successfully added.
     */
    @Test
    void printRecentLog(){

        System.out.println(logModel.getRecentLog());

        PlayerTableModel player1 = new PlayerTableModel(singleGameModel);

        CardModel cardModel1 = new CardModel("Diamond", 4);
        player1.addCard(cardModel1);

        logModel.addString(player1.getPlayerName() + ": Added " + cardModel1.getSuite() + " " + cardModel1.getValue());

        System.out.println(logModel.getRecentLog());
    }
}