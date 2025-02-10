package test;

import model.CardModel;
import model.LogModel;
import model.PlayerTableModel;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


class LogModelTest {

       LogModel logModel = new LogModel();

    @Test
    void addLog(){

        PlayerTableModel player1 = new PlayerTableModel();

        CardModel cardModel1 = new CardModel("Diamond", 4);
        player1.addCard(cardModel1);

        logModel.addString(player1.getPlayerName() + ": Added " + cardModel1.getSuite() + " " + cardModel1.getValue());

        assertEquals(logModel.getLogList().size(), 1);
    }

    @Test
    void printRecentLog(){

        System.out.println(logModel.getRecentLog());

        PlayerTableModel player1 = new PlayerTableModel();

        CardModel cardModel1 = new CardModel("Diamond", 4);
        player1.addCard(cardModel1);

        logModel.addString(player1.getPlayerName() + ": Added " + cardModel1.getSuite() + " " + cardModel1.getValue());

        System.out.println(logModel.getRecentLog());
    }
}