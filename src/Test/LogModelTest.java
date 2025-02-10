package Test;

import Model.CardModel;
import Model.LogModel;
import Model.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.smartcardio.Card;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class LogModelTest {

       LogModel logModel = new LogModel();

    @Test
    void addLog(){

        PlayerModel player1 = new PlayerModel("John Doe");

        CardModel cardModel1 = new CardModel("Diamond", 4);
        player1.addCard(cardModel1);

        logModel.addString(player1.getPlayerName() + ": Added " + cardModel1.getSuite() + " " + cardModel1.getValue());

        assertEquals(logModel.getLogList().size(), 1);
    }

    @Test
    void printRecentLog(){

        System.out.println(logModel.getRecentLog());

        PlayerModel player1 = new PlayerModel("John Doe");

        CardModel cardModel1 = new CardModel("Diamond", 4);
        player1.addCard(cardModel1);

        logModel.addString(player1.getPlayerName() + ": Added " + cardModel1.getSuite() + " " + cardModel1.getValue());

        System.out.println(logModel.getRecentLog());
    }
}