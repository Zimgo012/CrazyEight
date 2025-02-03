package View.Area;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CardsStackFaceDown {

    public CardsStackFaceDown() {};

    public static StackPane CardsStackFaceDown (){

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


        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(cardStackFaceDown);
        stackPane.getChildren().add(cardStackFaceDown1);
        stackPane.getChildren().add(cardStackFaceDown2);
        stackPane.getChildren().add(cardStackFaceDown3);
        stackPane.getChildren().add(cardStackFaceDown4);
        stackPane.getChildren().add(cardStackFaceDown5);


       return stackPane;
    }
}
