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
import view.scene.InGame;
import view.scene.Menu;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import view.components.popups.SettingPopUp;
import view.scene.SplashScreen;

public class GameButtons {

    //Todo: configure this language setter later
    public static boolean isEnglish = true;

    //Todo: configure this Music setter later
    public static boolean isMusicOn = true;

    private static Button joinButton;
    private static Text joinText;
    private static Text startText;
    private static Text quitText;
    private static Text createText;
    private static Text settingText;
    private static Text sendText;
    private static Text exitText;



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

    public static Button QuitButton(Stage stage) {
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
            new Menu(stage);
        });

        return quitButton;
    }

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

    public static Button MusicSetButton(){

        Image musicSetPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_musicSet.png").toExternalForm());
        ImageView imageViewON = new ImageView(musicSetPassive);

        Image musicSetActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_musicSet2.png").toExternalForm());
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
