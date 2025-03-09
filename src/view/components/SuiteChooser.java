package view.components;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.components.popups.SettingPopUp;

public class SuiteChooser {

    private StackPane suites = null;
    Rectangle diamondB = new Rectangle(69, 60);
    Rectangle clubsB = new Rectangle(69, 60);
    Rectangle heartB = new Rectangle(69, 60);
    Rectangle spadeB = new Rectangle(69, 60);

    public SuiteChooser() {

    }

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

    public StackPane getSuites() {
        return suites;
    }

    public Rectangle getDiamondB() {
        return diamondB;
    }

    public Rectangle getClubsB() {
        return clubsB;
    }

    public Rectangle getHeartB() {
        return heartB;
    }

    public Rectangle getSpadeB() {
        return spadeB;
    }
}
