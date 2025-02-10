package test;

import model.CardModel;
import model.PlayerTableModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;


class PlayerTableModelTest {
        PlayerTableModel playerTableModel;
        CardModel card1  = new CardModel("Diamond",2);
        CardModel card2  = new CardModel("Diamond",5);
        CardModel card3  = new CardModel("Diamond",8);


    @BeforeEach
    void initCard(){
        playerTableModel = new PlayerTableModel();

    }

    @Test
    void getName(){
        String pName = playerTableModel.getPlayerName();

        assertEquals(pName, "John Doe");
        System.out.println(playerTableModel.getPlayerName());
    }

    @Test
    void addCardView(){
        playerTableModel.addCard(card1);
        playerTableModel.addCard(card2);
        playerTableModel.addCard(card3);

        assertEquals(playerTableModel.getPlayerCards().size(),3);
        System.out.println("Correct number of cards!");

    }

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

    @Test
    void getPrintCards(){
        playerTableModel.addCard(card1);
        playerTableModel.addCard(card2);
        playerTableModel.addCard(card3);

        playerTableModel.printCard();
    }
}