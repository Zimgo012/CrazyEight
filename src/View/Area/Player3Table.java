package View.Area;

import View.Components.Cards.RegularCards;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Player3Table {

    private Pane currentUserTable;
    private int cardSpacing = 0;
    private int numOfCards = 0;

    public Player3Table(){

        currentUserTable = new Pane();
        currentUserTable.setMaxSize(1020.00, 260.00);

    }

    // Get current user table
    public Pane getCurrentUserTable() {
        return currentUserTable;
    }


    // Add card to the table
    public void addCard(RegularCards card){
        Rectangle cardNode = card.getCard();
        cardNode.setLayoutX(cardSpacing);
        cardSpacing += 20;
        currentUserTable.getChildren().add(cardNode);
        numOfCards++;

    }

    // Remove card to the table
    public void removeNode(){


        //ToDo: Shift previous cards to left
    }
}
