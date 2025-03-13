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

package view.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.MusicManager;
import model.SingleGameModel;
import view.scene.InGame;
import view.scene.Menu;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import view.components.popups.SettingPopUp;
import view.scene.SplashScreen;

/**
 * The GameButtons class is responsible for creating and managing various buttons
 * used throughout the game. It provides static methods for creating and retrieving
 * different types of buttons, as well as methods to handle language and music settings.
 * This class also contains utility methods for updating the game's language.
 */
public class GameButtons {


    public static boolean isEnglish = true;


    public static boolean isMusicOn = true;

    private static Button joinButton;
    private static Text joinText;
    private static Text startText;
    private static Text quitText;
    private static Text createText;
    private static Text settingText;
    private static Text sendText;
    private static Text exitText;



    /**
     * Creates a "Start" button with customized images, text, and behaviors for a game interface.
     * The button includes hover effects for visual feedback and an action to transition to the game
     * when clicked.
     *
     * @param stage the primary stage of the application, used for transitioning to the game scene
     * @return a configured Button object representing the "Start" button
     */
    public static Button StartButton(Stage stage) {
        Image startPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base1.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(startPassive);

        Image startActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(startActive);

        double scaleFactor = 0.5;
        imageViewPassive.setFitWidth(startPassive.getWidth() * scaleFactor);
        imageViewPassive.setFitHeight(startPassive.getHeight() * scaleFactor);
        imageViewActive.setFitWidth(startActive.getWidth() * scaleFactor);
        imageViewActive.setFitHeight(startActive.getHeight() * scaleFactor);

        startText = new Text(isEnglish ? "Start" : "Simula");
        startText.setFill(Color.BLACK);
        startText.setFont(Font.font("Monospaced", FontWeight.BOLD, 32));

        StackPane stack = new StackPane(imageViewPassive, startText);
        stack.setStyle("-fx-alignment: center;");

        Button startButton = new Button();
        startButton.setGraphic(stack);
        startButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        startButton.setOnMouseEntered(e -> {
            stack.getChildren().set(0, imageViewActive);
            startText.setFill(Color.YELLOW);
        });

        startButton.setOnMouseExited(e -> {
            stack.getChildren().set(0, imageViewPassive);
            startText.setFill(Color.BLACK);
        });

        startButton.setOnMouseClicked(e -> {
            new SplashScreen(stage, () -> new InGame(stage));
        });

        return startButton;
    }

    /**
     * Creates a "Quit" button with customized images, text, and behaviors for a game interface.
     * The button includes hover effects for visual feedback and an action to end the game and return
     * to the main menu when clicked.
     *
     * @param stage the primary stage of the application, used for transitioning to the menu scene
     * @param gameModel an instance of SingleGameModel to manage the current game's lifecycle
     * @return a configured Button object representing the "Quit" button
     */
    public static Button QuitButton(Stage stage, SingleGameModel gameModel) {
        Image quitPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base1.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(quitPassive);

        Image quitActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(quitActive);


        double scaleFactor = 0.2;
        imageViewPassive.setFitWidth(quitPassive.getWidth() * scaleFactor);
        imageViewPassive.setFitHeight(quitPassive.getHeight() * scaleFactor);
        imageViewActive.setFitWidth(quitActive.getWidth() * scaleFactor);
        imageViewActive.setFitHeight(quitActive.getHeight() * scaleFactor);


        quitText = new Text(isEnglish ? "Quit" : "Umalis");
        quitText.setFill(Color.BLACK);
        quitText.setFont(Font.font("Monospaced", FontWeight.BOLD, 25));


        StackPane stack = new StackPane(imageViewPassive, quitText);
        stack.setStyle("-fx-alignment: center;");

        Button quitButton = new Button();
        quitButton.setGraphic(stack);
        quitButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        quitButton.setOnMouseEntered(e -> {
            stack.getChildren().set(0, imageViewActive);
            quitText.setFill(Color.YELLOW);
        });

        quitButton.setOnMouseExited(e -> {
            stack.getChildren().set(0, imageViewPassive);
            quitText.setFill(Color.BLACK);
        });

        quitButton.setOnMouseClicked(e -> {
           gameModel.stopGame();
            new Menu(stage);
        });

        return quitButton;
    }

    /**
     * Creates a "Join" button with customized images, text, and behaviors for a game interface.
     * The button includes hover effects, changing its appearance and text color when hovered over.
     *
     * @return a configured Button object representing the "Join" button
     */
    public static Button JoinButton(){
        Image joinPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base1.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(joinPassive);

        Image joinActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(joinActive);


        double scaleFactor = 0.4;
        imageViewPassive.setFitWidth(joinPassive.getWidth() * scaleFactor);
        imageViewPassive.setFitHeight(joinPassive.getHeight() * scaleFactor);
        imageViewActive.setFitWidth(joinActive.getWidth() * scaleFactor);
        imageViewActive.setFitHeight(joinActive.getHeight() * scaleFactor);


        joinText = new Text(isEnglish ? "Join" : "Sali");
        joinText.setFill(Color.BLACK);
        joinText.setFont(Font.font("Monospaced", FontWeight.BOLD, 32));

        StackPane stack = new StackPane(imageViewPassive, joinText);
        stack.setStyle("-fx-alignment: center;");

        joinButton = new Button();
        joinButton.setGraphic(stack);
        joinButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        joinButton.setOnMouseEntered(e -> {
            stack.getChildren().set(0, imageViewActive); // Change image
            joinText.setFill(Color.YELLOW);
        });

        joinButton.setOnMouseExited(e -> {
            stack.getChildren().set(0, imageViewPassive); // Restore image
            joinText.setFill(Color.BLACK);
        });

        return joinButton;
    }

    /**
     * Creates a "Create" button with customized images, text, and hover effects for a game interface.
     * The button changes its appearance and text color when the mouse enters or exits its boundaries.
     *
     * @return a configured Button object representing the "Create" button
     */
    public static Button CreateButton() {
        Image createPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base1.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(createPassive);

        Image createActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(createActive);


        double scaleFactor = 0.4;
        imageViewPassive.setFitWidth(createPassive.getWidth() * scaleFactor);
        imageViewPassive.setFitHeight(createPassive.getHeight() * scaleFactor);
        imageViewActive.setFitWidth(createActive.getWidth() * scaleFactor);
        imageViewActive.setFitHeight(createActive.getHeight() * scaleFactor);


        createText = new Text(isEnglish ? "Create" : "Gumawa");
        createText.setFill(Color.BLACK);
        createText.setFont(Font.font("Monospaced", FontWeight.BOLD, 32));


        StackPane stack = new StackPane(imageViewPassive, createText);
        stack.setStyle("-fx-alignment: center;");

        Button createButton = new Button();
        createButton.setGraphic(stack);
        createButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        createButton.setOnMouseEntered(e -> {
            stack.getChildren().set(0, imageViewActive);
            createText.setFill(Color.YELLOW);
        });

        createButton.setOnMouseExited(e -> {
            stack.getChildren().set(0, imageViewPassive);
            createText.setFill(Color.BLACK);
        });

        return createButton;
    }

    /**
     * Creates a "Settings" button with customized images, text, and hover effects for a game interface.
     * The button changes its appearance and text color when the mouse enters or exits its boundaries.
     * It toggles the visibility of the settings popup when clicked.
     *
     * @return a configured Button object representing the "Settings" button
     */
    public static Button SettingButton() {
        Image settingPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base1.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(settingPassive);

        Image settingActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(settingActive);


        double scaleFactor = 0.2;
        imageViewPassive.setFitWidth(settingPassive.getWidth() * scaleFactor);
        imageViewPassive.setFitHeight(settingPassive.getHeight() * scaleFactor);
        imageViewActive.setFitWidth(settingActive.getWidth() * scaleFactor);
        imageViewActive.setFitHeight(settingActive.getHeight() * scaleFactor);


        settingText = new Text(isEnglish ? "Settings" : "Itakda");
        settingText.setFill(Color.BLACK);
        settingText.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));


        StackPane stack = new StackPane(imageViewPassive, settingText);
        stack.setStyle("-fx-alignment: center;");

        Button settingButton = new Button();
        settingButton.setGraphic(stack);
        settingButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        settingButton.setOnMouseEntered(e -> {
            stack.getChildren().set(0, imageViewActive);
            settingText.setFill(Color.YELLOW);
        });

        settingButton.setOnMouseExited(e -> {
            stack.getChildren().set(0, imageViewPassive);
            settingText.setFill(Color.BLACK);
        });

        settingButton.setOnMouseClicked(e -> {
            SettingPopUp.toggleSettingsPopup();
        });

        return settingButton;
    }

    /**
     * Creates a "Send" button with customized images, text, and hover effects for a game interface.
     * The button changes its appearance and text color when the mouse enters or exits its boundaries.
     *
     * @return a configured Button object representing the "Send" button
     */
    public static Button SendButton() {
        Image sendPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/sendBase1.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(sendPassive);

        Image sendActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/sendBase2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(sendActive);


        double scaleFactor = 0.4;
        imageViewPassive.setFitWidth(sendPassive.getWidth() * scaleFactor);
        imageViewPassive.setFitHeight(sendPassive.getHeight() * scaleFactor);
        imageViewActive.setFitWidth(sendActive.getWidth() * scaleFactor);
        imageViewActive.setFitHeight(sendActive.getHeight() * scaleFactor);


        sendText = new Text(isEnglish ? "Send" : "Dala");
        sendText.setFill(Color.BLACK);
        sendText.setFont(Font.font("Monospaced", FontWeight.BOLD, 10));


        StackPane stack = new StackPane(imageViewPassive, sendText);
        stack.setStyle("-fx-alignment: center;");

        Button sendButton = new Button();
        sendButton.setGraphic(stack);
        sendButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");

        sendButton.setOnMouseEntered(e -> {
            stack.getChildren().set(0, imageViewActive);
            sendText.setFill(Color.YELLOW);
        });

        sendButton.setOnMouseExited(e -> {
            stack.getChildren().set(0, imageViewPassive);
            sendText.setFill(Color.BLACK);
        });

        return sendButton;
    }

    /**
     * Creates an "Exit" button for a game interface. The button includes hover effects,
     * changing its appearance and text color when hovered over or clicked.
     * When clicked, the application exits.
     *
     * @return a configured Button object representing the "Exit" button
     */
    public static Button ExitButton(){
            Image exitGamePassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base1.png").toExternalForm());
            ImageView imageViewPassive = new ImageView(exitGamePassive);

            Image exitGameActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/base2.png").toExternalForm());
            ImageView imageViewActive = new ImageView(exitGameActive);


            double scaleFactor = 0.3;
            imageViewPassive.setFitWidth(exitGamePassive.getWidth() * scaleFactor);
            imageViewPassive.setFitHeight(exitGamePassive.getHeight() * scaleFactor);
            imageViewActive.setFitWidth(exitGameActive.getWidth() * scaleFactor);
            imageViewActive.setFitHeight(exitGameActive.getHeight() * scaleFactor);


            exitText = new Text(isEnglish ? "Exit" : "Lumabas");
            exitText.setFill(Color.BLACK);
            exitText.setFont(Font.font("Monospaced", FontWeight.BOLD, 24));


            StackPane stack = new StackPane(imageViewPassive, exitText);
            stack.setStyle("-fx-alignment: center;");

            Button exitGameButton = new Button();
            exitGameButton.setGraphic(stack);
            exitGameButton.setStyle("-fx-background-color: transparent; -fx-padding: 0;");


            exitGameButton.setOnMouseEntered(e -> {
                stack.getChildren().set(0, imageViewActive);
                exitText.setFill(Color.YELLOW);
            });

            exitGameButton.setOnMouseExited(e -> {
                stack.getChildren().set(0, imageViewPassive);
                exitText.setFill(Color.BLACK);
            });


            exitGameButton.setOnMouseClicked(e -> {
                Platform.exit();
            });

            return exitGameButton;
    }

    /**
     * Creates a language toggle button that allows the user to switch between
     * two languages. The button visually represents the current language
     * with different images and updates associated text elements when the language toggles.
     *
     * @return a configured Button object that toggles application language
     */
    public static Button LanguageButton() {
        Image langPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_lang.png").toExternalForm());
        ImageView imageViewEnglish = new ImageView(langPassive);

        Image langActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_lang2.png").toExternalForm());
        ImageView imageViewTagalog = new ImageView(langActive);

        imageViewEnglish.setFitWidth(langPassive.getWidth() * 0.5);
        imageViewEnglish.setFitHeight(langPassive.getHeight() * 0.5);
        imageViewTagalog.setFitWidth(langActive.getWidth() * 0.5);
        imageViewTagalog.setFitHeight(langActive.getHeight() * 0.5);

        Button langButton = new Button();
        langButton.setGraphic(isEnglish ? imageViewEnglish : imageViewTagalog);
        langButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        langButton.setOnMouseClicked(e -> {
            isEnglish = !isEnglish; // ✅ Toggle language
            langButton.setGraphic(isEnglish ? imageViewEnglish : imageViewTagalog);
            updateLanguage();
        });

        return langButton;
    }

    /**
     * Creates a "Close" button with customized hover effects for a game interface.
     * The button changes its appearance when the mouse enters or exits its boundaries.
     *
     * @return a configured Button object representing the "Close" button
     */
    public static Button CloseButton(){



        Image closePassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_close.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(closePassive);

        Image closeActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_close2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(closeActive);

        //Setting
        imageViewPassive.setFitWidth(closePassive.getWidth() * 0.3);
        imageViewPassive.setFitHeight(closePassive.getHeight() * 0.3);
        imageViewActive.setFitWidth(closeActive.getWidth() * 0.3);
        imageViewActive.setFitHeight(closeActive.getHeight() * 0.3);

        Button closeButton = new Button();
        closeButton.setGraphic(imageViewPassive);

        closeButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        closeButton.setOnMouseEntered(e ->{
            closeButton.setGraphic(imageViewActive);
        });

        closeButton.setOnMouseExited(e ->{
            closeButton.setGraphic(imageViewPassive);

        });


        return closeButton;
    }

    /**
     * Creates a button for toggling the music state in the game interface. The button displays
     * different images based on whether the music is currently enabled or muted. Clicking the
     * button toggles the music state and updates the button's appearance accordingly.
     *
     * The button is styled with a transparent background and no padding. It uses
     * preloaded images for on and off states, scaled to 50% of their original dimensions.
     *
     * @return a configured Button object for toggling the music state
     */
    public static Button MusicSetButton(){

        Image musicSetPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_musicSet2.png").toExternalForm());
        ImageView imageViewON = new ImageView(musicSetPassive);

        Image musicSetActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_musicSet.png").toExternalForm());
        ImageView imageViewOFF = new ImageView(musicSetActive);

        //Setting
        imageViewON.setFitWidth(musicSetPassive.getWidth() * 0.5);
        imageViewON.setFitHeight(musicSetPassive.getHeight() * 0.5);
        imageViewOFF.setFitWidth(musicSetActive.getWidth() * 0.5);
        imageViewOFF.setFitHeight(musicSetActive.getHeight() * 0.5);

        Button musicSetButton = new Button();
        musicSetButton.setGraphic(imageViewON);

        musicSetButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        musicSetButton.setOnMouseClicked(e->{
            if(isMusicOn){
                isMusicOn = false;
                musicSetButton.setGraphic(imageViewOFF);
                MusicManager.getInstance().toggleMute();
            }else if(!isMusicOn){
                isMusicOn = true;
                musicSetButton.setGraphic(imageViewON);
                MusicManager.getInstance().toggleMute();
            }
        });


        return musicSetButton;
    }


    /**
     * Updates the displayed text of various UI elements based on the current language setting.
     * The language is toggled between English and another language (e.g., Tagalog)
     * controlled by the `isEnglish` boolean field.
     *
     * If a UI text element is not null, its displayed text will be updated
     * according to the language preference. The affected elements include:
     * - Join button text
     * - Start button text
     * - Quit button text
     * - Create button text
     * - Settings button text
     * - Send button text
     * - Exit button text
     *
     * This method is called when the application language preference changes.
     */
    public static void updateLanguage() {
        if (joinText != null) joinText.setText(isEnglish ? "Join" : "Sali");
        if (startText != null) startText.setText(isEnglish ? "Start" : "Simula");
        if (quitText != null) quitText.setText(isEnglish ? "Quit" : "Umalis");
        if (createText != null) createText.setText(isEnglish ? "Create" : "Gumawa");
        if (settingText != null) settingText.setText(isEnglish ? "Settings" : "Itakda");
        if (sendText != null) sendText.setText(isEnglish ? "Send" : "Dala");
        if (exitText != null) exitText.setText(isEnglish ? "Exit" : "Lumabas");
    }

}
