/**
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
package view.components;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.components.popups.SettingPopUp;

/**
 * The SuiteChooser class is responsible for creating and managing
 * a graphical user interface component that allows users to select
 * a card suit (Diamond, Clubs, Heart, Spade).
 * It provides a visual representation of the suits with interactive
 * effects.
 */
public class SuiteChooser {

    private StackPane suites = null;
    Rectangle diamondB = new Rectangle(69, 60);
    Rectangle clubsB = new Rectangle(69, 60);
    Rectangle heartB = new Rectangle(69, 60);
    Rectangle spadeB = new Rectangle(69, 60);

    /**
     * Constructor for the SuiteChooser class.
     *
     * This method initializes a new instance of the SuiteChooser class, which is
     * used to represent and manage the suite selection functionality in the
     * application. It sets up any necessary internal structures or references
     * required for managing suit-related visual elements or interactions.
     */
    public SuiteChooser() {

    }

    /**
     * Generates and configures a suite chooser component for selecting card suites in the application.
     * The suite chooser is represented as a StackPane, containing four clickable shapes (diamond,
     * clubs, heart, spade) with associated visual effects and event handlers for interaction.
     *
     * The component includes specific icons for each card suite, custom styles for layout and
     * appearance, and mouse hover effects such as a glow.
     *
     * @return a configured StackPane representing the suite chooser component
     */
    public StackPane generateSuiteChooser(){

        if(suites == null){
            suites  = new StackPane();
        }


        suites.setVisible(false);
        Image diamondIco = new Image(SettingPopUp.class.getResource(
                        "/com/zimgo/crazyeight/suiteIcons/diamond.png")
                .toExternalForm());
        ImagePattern diamond = new ImagePattern(diamondIco);


        Image clubsIco = new Image(SettingPopUp.class.getResource(
                        "/com/zimgo/crazyeight/suiteIcons/clubs.png")
                .toExternalForm());
        ImagePattern clubs = new ImagePattern(clubsIco);

        Image heartIco = new Image(SettingPopUp.class.getResource(
                        "/com/zimgo/crazyeight/suiteIcons/heart.png")
                .toExternalForm());
        ImagePattern heart = new ImagePattern(heartIco);

        Image spadeIco = new Image(SettingPopUp.class.getResource(
                        "/com/zimgo/crazyeight/suiteIcons/spade.png")
                .toExternalForm());
        ImagePattern spade = new ImagePattern(spadeIco);
        DropShadow glow = new DropShadow();
        glow.setRadius(20);
        glow.setColor(Color.YELLOW);


        //EFFECTS
        diamondB.setFill(diamond);
        diamondB.setTranslateX(55);
        diamondB.setOnMouseEntered( e->{
            diamondB.setEffect(glow);
        });
        diamondB.setOnMouseExited( e->{
            diamondB.setEffect(null);
        });

        clubsB.setFill(clubs);
        clubsB.setTranslateX(-55);
        clubsB.setOnMouseEntered( e->{
            clubsB.setEffect(glow);
        });
        clubsB.setOnMouseExited( e->{
            clubsB.setEffect(null);
        });

        heartB.setFill(heart);
        heartB.setTranslateY(55);
        heartB.setOnMouseEntered( e->{
            heartB.setEffect(glow);
        });
        heartB.setOnMouseExited( e->{
            heartB.setEffect(null);
        });


        spadeB.setFill(spade);
        spadeB.setTranslateY(-55);
        spadeB.setOnMouseEntered( e->{
            spadeB.setEffect(glow);
        });
        spadeB.setOnMouseExited(e->{
            spadeB.setEffect(null);
        });


        suites.setPrefSize(190, 180);
        suites.getChildren().addAll(diamondB, clubsB, heartB, spadeB);
        suites.setStyle("-fx-background-color: #e7e2d7; -fx-padding: 0; -fx-background-radius: 50px; " +
                "-fx-border-color: #9db2bb; -fx-border-width: 5; -fx-border-style: solid inside; -fx-border-radius: 50px");
        return suites;
    }

    /**
     * Retrieves the StackPane that represents the suite chooser.
     *
     * This method provides access to the suite chooser component, which
     * allows users to select a card suite (e.g., Diamond, Clubs, Hearts, Spade)
     * in a card game application. It may include visual elements and interaction
     * handlers for managing suite selection.
     *
     * @return the StackPane representing the suite chooser component
     */
    public StackPane getSuites() {
        return suites;
    }

    /**
     * Retrieves the rectangle representing the "Diamond" shape in the suite chooser component.
     * This method provides access to the visual element associated with the "Diamond" suite
     * for interaction or configuration purposes.
     *
     * @return the rectangle corresponding to the "Diamond" suite within the suite chooser
     */
    public Rectangle getDiamondB() {
        return diamondB;
    }

    /**
     * Retrieves the rectangle representing the "Clubs" shape in the suite chooser component.
     * This method provides access to the visual element associated with the "Clubs" suite
     * for interaction or configuration purposes.
     *
     * @return the rectangle corresponding to the "Clubs" suite within the suite chooser
     */
    public Rectangle getClubsB() {
        return clubsB;
    }

    /**
     * Retrieves the rectangle representing the "Heart" shape in the suite chooser component.
     * This method provides access to the visual element associated with the "Heart" suite
     * for interaction or configuration purposes.
     *
     * @return the rectangle corresponding to the "Heart" suite within the suite chooser
     */
    public Rectangle getHeartB() {
        return heartB;
    }

    /**
     * Retrieves the rectangle representing the "Spade" shape in the suite chooser component.
     * This method provides access to the visual element associated with the "Spade" suite
     * for interaction or configuration purposes.
     *
     * @return the rectangle corresponding to the "Spade" suite within the suite chooser
     */
    public Rectangle getSpadeB() {
        return spadeB;
    }
}
