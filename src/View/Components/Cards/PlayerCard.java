package View.Components.Cards;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;


public class PlayerCard extends RegularCards {

    public PlayerCard(String suit, int value) {
        super(suit, value);

        DropShadow glow = new DropShadow();
        glow.setRadius(20);
        glow.setColor(Color.YELLOW);


        //Hover Mouse Pop out and glow
        super.getCard().setOnMouseEntered(e ->{
            super.getCard().setTranslateY(-30);
            super.getCard().setEffect(glow);

        });
        super.getCard().setOnMouseExited(e ->{

            super.getCard().setTranslateY(0);
            super.getCard().setEffect(null);
        });

        super.getCard().setOnMousePressed(e ->{
            super.getCard().setOpacity(0.5);

        });

        super.getCard().setOnMouseReleased(e ->{

            super.getCard().setOpacity(1);


        });



    }
}
