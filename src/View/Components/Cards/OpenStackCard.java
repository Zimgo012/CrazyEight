package View.Components.Cards;


public class OpenStackCard extends RegularCards {


    public OpenStackCard(String suit, int value) {
        super(suit, value);

       super.getCard().setRotate(Math.random() * 90 + (-45));

    }

    public OpenStackCard(String suit, int value,boolean isRotated) {
        super(suit, value);

        boolean isRotatable = isRotated;

    }
}
