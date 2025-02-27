/**
 * Student Name: JOHN RYCCA BELCINA
 * Student Number: 041128039
 * Course: CST 8221 – JAP, Lab Section: 300/303
 * Professor: Cormier, Daniel | Singh, Ramanjeet
 * Date: 2025-02-05
 * Compiler: IntelliJ IDEA
 * Purpose:
 * This project involves developing a software version of the Crazy Eights card game using a standard deck of playing cards.
 * The game will follow the first three variation rules listed on Wikipedia, along with additional custom rules.
 * Understanding these variations is essential for proper implementation.
 * This project is a mandatory requirement for passing the Algonquin CST 8221 – JAP course.
 * Copyright © 2025 John Rycca Belcina. All rights reserved.
 */
package view.area.dealer;
import controller.CardController;
import controller.PlayerTableController;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Class name: CardsStackFaceDown
 * Purpose: Represents section of unused card (where we want to draw from, Facing down).
 * @author John Rycca Belcina
 * @since 1.8
 */
public class CardsStackFaceDown {
    private CardController cardController;
    private StackPane stackPane;
    private PlayerTableController playerTableController;

    public CardsStackFaceDown(){}

    /**
     * Default Constructor
     */
    public CardsStackFaceDown(CardController deckControl) {
        cardController = deckControl;
    };

    /**
     * Method that can is used to represent unused cards
     * @return an area filled with unused cards (facing down)
     */
    public StackPane getCardsStackFaceDown (){
        setupDeck();


        stackPane.setOnMouseClicked(e->{
            cardController.addCardToTable();

        });


        return stackPane;
    }

    /**
     * Setup stack of cards facing down
     */
    private void setupDeck(){
        stackPane = new StackPane();

        Rectangle cardStackFaceDown = new Rectangle();
        cardStackFaceDown = new Rectangle(108, 154);
        Rectangle cardStackFaceDown1 = new Rectangle();
        cardStackFaceDown1 = new Rectangle(108, 154);
        Rectangle cardStackFaceDown2 = new Rectangle();
        cardStackFaceDown2 = new Rectangle(108, 154);
        Rectangle cardStackFaceDown3 = new Rectangle();
        cardStackFaceDown3 = new Rectangle(108, 154);
        Rectangle cardStackFaceDown4 = new Rectangle();
        cardStackFaceDown4 = new Rectangle(108, 154);
        Rectangle cardStackFaceDown5 = new Rectangle();
        cardStackFaceDown5 = new Rectangle(108, 154);

        StackPane.setMargin(cardStackFaceDown1, new Insets(0, 0, 0, 10)); // 50px from the left
        StackPane.setMargin(cardStackFaceDown2, new Insets(0, 0, 0, 20)); // 100px from the left
        StackPane.setMargin(cardStackFaceDown3, new Insets(0, 0, 0, 30)); // 150px from the left
        StackPane.setMargin(cardStackFaceDown4, new Insets(0, 0, 0, 40)); // 200px from the left
        StackPane.setMargin(cardStackFaceDown5, new Insets(0, 0, 0, 50)); // 200px from the left
        Image image = new Image(CardsStackFaceDown.class.getResource("/com/zimgo/crazyeight/CardAssets2/OtherCards/3.png").toExternalForm());
        cardStackFaceDown.setFill(new ImagePattern(image));
        cardStackFaceDown1.setFill(new ImagePattern(image));
        cardStackFaceDown2.setFill(new ImagePattern(image));
        cardStackFaceDown3.setFill(new ImagePattern(image));
        cardStackFaceDown4.setFill(new ImagePattern(image));
        cardStackFaceDown5.setFill(new ImagePattern(image));



        stackPane.getChildren().add(cardStackFaceDown);
        stackPane.getChildren().add(cardStackFaceDown1);
        stackPane.getChildren().add(cardStackFaceDown2);
        stackPane.getChildren().add(cardStackFaceDown3);
        stackPane.getChildren().add(cardStackFaceDown4);
        stackPane.getChildren().add(cardStackFaceDown5);
    }
}
