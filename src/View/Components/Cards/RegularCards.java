package View.Components.Cards;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RegularCards {

    private Rectangle card;
    public RegularCards(String suit, int value) {
        String imagePath = String.format("/com/zimgo/crazyeight/CardAssets2/%s/%d.png", suit, value);
        Image cardPic = new Image(getClass().getResource(imagePath).toExternalForm());

        card = new Rectangle(108, 154);
        card.setFill(new ImagePattern(cardPic));
    }

    public Rectangle getCard() {
        return card;
    }


}
