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
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.PlayerTableModel;

/**
 * CardsStackFaceDown is a class responsible for managing and visually representing
 * a stack of unused cards in a game. The cards in this stack are represented as
 * being faced down and are displayed as a stacked group of card images.
 * The class interacts with a CardController and PlayerTableModel to manage
 * the behavior of the card stack and its integration with the player's table
 * during their turn in the game.
 */
public class CardsStackFaceDown {
    private CardController cardController;
    private StackPane stackPane;
    private PlayerTableModel playerTableModel;


    /**
     * Constructs a CardsStackFaceDown object to manage a stack of cards facing down.
     *
     * @param deckControl the CardController instance responsible for managing card-related logic
     * @param playerTableModel the PlayerTableModel instance representing the player's table state
     */
    public CardsStackFaceDown(CardController deckControl, PlayerTableModel playerTableModel) {
        this.cardController = deckControl;
        this.playerTableModel = playerTableModel;
    };

    /**
     * Retrieves the stack pane containing the face-down stack of cards.
     *
     * This method sets up the visual representation of the cards stack
     * and adds functionality for handling mouse click events. If the
     * player's turn is active, clicking the stack triggers the addition
     * of a card to the player's table. Otherwise, it logs a message indicating
     * it is not the player's turn.
     *
     * @return a StackPane object representing the face-down stack of cards.
     */
    public StackPane getCardsStackFaceDown (){
        setupDeck();


        stackPane.setOnMouseClicked(e->{
            if(playerTableModel.isCurrentTurn()){

                cardController.addCardToTable();
            }else{
                System.out.println("Not Your Current Turn");
            }

        });


        return stackPane;
    }

    /**
     * Sets up the deck by initializing and configuring a visual representation of a stack
     * of cards facing down. This method creates multiple rectangular elements to represent
     * cards, each styled and positioned within a StackPane to give a stacked effect.
     *
     * Functionality:
     * - Creates six rectangles of fixed dimensions (90x120) to represent card shapes.
     * - Applies an image pattern to all rectangles to visually simulate the back side of the cards.
     * - Adjusts the horizontal margins of each rectangle in the stack to achieve an offset effect,
     *   i.e., each subsequent card is visually shifted to simulate a stack.
     * - Adds all rectangles as children to a StackPane for layering.
     *
     * Key Points:
     * - The image used for the card pattern is loaded from a predefined resource path.
     * - Margins are set incrementally to create a visual representation of depth in the stack.
     * - The StackPane object is the container and graphical container for all the card components.
     *
     * This method is typically invoked internally to initialize or reset the graphical representation of the card stack.
     */
    private void setupDeck(){
        stackPane = new StackPane();

        Rectangle cardStackFaceDown = new Rectangle();
        cardStackFaceDown = new Rectangle(90, 120);
        Rectangle cardStackFaceDown1 = new Rectangle();
        cardStackFaceDown1 = new Rectangle(90, 120);
        Rectangle cardStackFaceDown2 = new Rectangle();
        cardStackFaceDown2 = new Rectangle(90, 120);
        Rectangle cardStackFaceDown3 = new Rectangle();
        cardStackFaceDown3 = new Rectangle(90, 120);
        Rectangle cardStackFaceDown4 = new Rectangle();
        cardStackFaceDown4 = new Rectangle(90, 120);
        Rectangle cardStackFaceDown5 = new Rectangle();
        cardStackFaceDown5 = new Rectangle(90, 120);

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
