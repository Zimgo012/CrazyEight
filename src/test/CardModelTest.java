package test;

import model.CardModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CardModelTest {
        CardModel cardModel;
        CardModel cardModel2;

    @Test
    void initCard(){

        cardModel  = new CardModel("Diamond",2);

        System.out.println(cardModel.getSuite());
        System.out.println(cardModel.getValue());
    }
    @Test
    void compareCard(){
        cardModel  = new CardModel("Diamond",2);
        cardModel2  = new CardModel("Diamond",3);

        System.out.println("Model 1: " + cardModel + " and Model 2: " + cardModel2);
        assertNotEquals(cardModel,cardModel2);
        System.out.println("Card not same");



    }
}