package Test;

import Model.CardModel;
import Model.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;


class PlayerModelTest {
        PlayerModel playerModel;
        CardModel card1  = new CardModel("Diamond",2);
        CardModel card2  = new CardModel("Diamond",5);
        CardModel card3  = new CardModel("Diamond",8);


    @BeforeEach
    void initCard(){
        playerModel = new PlayerModel("John Doe");

    }

    @Test
    void getName(){
        String pName = playerModel.getPlayerName();

        assertEquals(pName, "John Doe");
        System.out.println(playerModel.getPlayerName());
    }

    @Test
    void addCard(){
        playerModel.addCard(card1);
        playerModel.addCard(card2);
        playerModel.addCard(card3);

        assertEquals(playerModel.getPlayerCards().size(),3);
        System.out.println("Correct number of cards!");

    }

    @Test
    void removeCard(){
        playerModel.addCard(card1);
        playerModel.addCard(card2);
        playerModel.addCard(card3);

        System.out.println("Before: " + playerModel.getPlayerCards().size());
        playerModel.removeCard(card1);
        System.out.println("After: " + playerModel.getPlayerCards().size());

        assertEquals(playerModel.getPlayerCards().size(),2);
        System.out.println("Correct number of cards!");

    }

    @Test
    void getPrintCards(){
        playerModel.addCard(card1);
        playerModel.addCard(card2);
        playerModel.addCard(card3);

        playerModel.printCard();
    }
}