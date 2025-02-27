package view.components;


import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.components.popups.SettingPopUp;

public class SuiteChooser {

    public StackPane suiteChooser() {
        StackPane suites = new StackPane();
        Rectangle diamondB = new Rectangle(69, 60);
        Rectangle clubsB = new Rectangle(69, 60);
        Rectangle heartB = new Rectangle(69, 60);
        Rectangle spadeB = new Rectangle(69, 60);


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

        diamondB.setFill(diamond);
        diamondB.setTranslateX(55);
        clubsB.setFill(clubs);
        clubsB.setTranslateX(-55);
        heartB.setFill(heart);
        heartB.setTranslateY(55);
        spadeB.setFill(spade);
        spadeB.setTranslateY(-55);


        suites.setPrefSize(190, 180);
        suites.getChildren().addAll(diamondB, clubsB, heartB, spadeB);
        suites.setStyle("-fx-background-color: #e7e2d7; -fx-padding: 0; -fx-background-radius: 50px; " +
                "-fx-border-color: #9db2bb; -fx-border-width: 5; -fx-border-style: solid inside; -fx-border-radius: 50px");
        return suites;
    }

    public void setVisible(boolean visible) {
        suiteChooser().setVisible(visible);
    }
}
