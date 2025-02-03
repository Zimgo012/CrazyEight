package View.Area;

import View.Components.Cards.RegularCards;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class OpenStack {

    private Pane openStackDeck;
    private int numOfCards = 0;

    public OpenStack() {
        openStackDeck = new Pane();
        openStackDeck.setMaxSize(400,260);
    }

    // Get current user table
    public Pane getCurrentOpenStack() {
        return openStackDeck;
    }

    // Add card to open deck
    public void addCard(RegularCards card){
        Rectangle cardNode = card.getCard();
        openStackDeck.getChildren().add(cardNode);
        numOfCards++;

    }

    // Remove card to the table
    public void removeNode(){

        //ToDo: Shift previous cards to left
    }

}
