package View.Components;

import View.Scene.InGame;
import View.Scene.Menu;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import View.Components.Popups.SettingPopUp;

public class GameButtons {

    //Todo: configure this language setter later
    public static boolean isEnglish = true;

    //Todo: configure this Music setter later
    public static boolean isMusicOn = true;



    public static Button StartButton(Stage stage){

        Image startPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_start.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(startPassive);

        Image startActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_start2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(startActive);

        //Setting
        imageViewPassive.setFitWidth(startPassive.getWidth() * 0.5);
        imageViewPassive.setFitHeight(startPassive.getHeight() * 0.5);
        imageViewActive.setFitWidth(startActive.getWidth() * 0.5);
        imageViewActive.setFitHeight(startActive.getHeight() * 0.5);

        Button startButton = new Button();
        startButton.setGraphic(imageViewPassive);
        startButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        startButton.setOnMouseEntered(e ->{
            startButton.setGraphic(imageViewActive);
        });

        startButton.setOnMouseExited(e ->{
            startButton.setGraphic(imageViewPassive);

        });

        startButton.setOnMouseClicked(e->{
        new InGame(stage);
        });

        return startButton;
    }

    public static Button QuitButton(Stage stage){
        Image quitPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_quit.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(quitPassive);

        Image quitActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_quit2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(quitActive);

        //Setting
        imageViewPassive.setFitWidth(quitPassive.getWidth() * 0.3);
        imageViewPassive.setFitHeight(quitPassive.getHeight() * 0.3);
        imageViewActive.setFitWidth(quitActive.getWidth() * 0.3);
        imageViewActive.setFitHeight(quitActive.getHeight() * 0.3);

        Button quitButton = new Button();
        quitButton.setGraphic(imageViewPassive);
        quitButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        quitButton.setOnMouseEntered(e ->{
            quitButton.setGraphic(imageViewActive);
        });

        quitButton.setOnMouseExited(e ->{
            quitButton.setGraphic(imageViewPassive);

        });

        quitButton.setOnMouseClicked(e ->{
            new Menu(stage);
        });


        return quitButton;
    }

    public static Button JoinButton(){
        Image joinPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_join.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(joinPassive);

        Image joinActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_join2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(joinActive);

        //Setting
        imageViewPassive.setFitWidth(joinPassive.getWidth() * 0.4);
        imageViewPassive.setFitHeight(joinPassive.getHeight() * 0.4);
        imageViewActive.setFitWidth(joinActive.getWidth() * 0.4);
        imageViewActive.setFitHeight(joinActive.getHeight() * 0.4);

        Button joinButton = new Button();
        joinButton.setGraphic(imageViewPassive);
        joinButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        joinButton.setOnMouseEntered(e ->{
            joinButton.setGraphic(imageViewActive);
        });

        joinButton.setOnMouseExited(e ->{
            joinButton.setGraphic(imageViewPassive);

        });


        return joinButton;
    }

    public static Button CreateButton(){
        Image createPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_create.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(createPassive);

        Image createActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_create2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(createActive);

        //Setting
        imageViewPassive.setFitWidth(createPassive.getWidth() * 0.4);
        imageViewPassive.setFitHeight(createPassive.getHeight() * 0.4);
        imageViewActive.setFitWidth(createActive.getWidth() * 0.4);
        imageViewActive.setFitHeight(createActive.getHeight() * 0.4);

        Button createButton = new Button();
        createButton.setGraphic(imageViewPassive);
        createButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        createButton.setOnMouseEntered(e ->{
            createButton.setGraphic(imageViewActive);
        });

        createButton.setOnMouseExited(e ->{
            createButton.setGraphic(imageViewPassive);

        });


        return createButton;
    }

    public static Button SettingButton(){
        Image settingPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_setting.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(settingPassive);

        Image settingActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_setting2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(settingActive);

        //Setting
        imageViewPassive.setFitWidth(settingPassive.getWidth() * 0.3);
        imageViewPassive.setFitHeight(settingPassive.getHeight() * 0.3);
        imageViewActive.setFitWidth(settingActive.getWidth() * 0.3);
        imageViewActive.setFitHeight(settingActive.getHeight() * 0.3);

        Button settingButton = new Button();
        settingButton.setGraphic(imageViewPassive);
        settingButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        settingButton.setOnMouseEntered(e ->{
            settingButton.setGraphic(imageViewActive);
        });

        settingButton.setOnMouseExited(e ->{
            settingButton.setGraphic(imageViewPassive);
        });

        settingButton.setOnMouseClicked(e -> {
            SettingPopUp.toggleSettingsPopup();

        });


        return settingButton;
    }

    public static Button SendButton(){
        Image sendPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_send.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(sendPassive);

        Image sendActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_send2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(sendActive);

        //Setting
        imageViewPassive.setFitWidth(sendPassive.getWidth() * 0.3);
        imageViewPassive.setFitHeight(sendPassive.getHeight() * 0.3);
        imageViewActive.setFitWidth(sendActive.getWidth() * 0.3);
        imageViewActive.setFitHeight(sendActive.getHeight() * 0.3);

        Button sendButton = new Button();
        sendButton.setGraphic(imageViewPassive);
        sendButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        sendButton.setOnMouseEntered(e ->{
            sendButton.setGraphic(imageViewActive);
        });

        sendButton.setOnMouseExited(e ->{
            sendButton.setGraphic(imageViewPassive);

        });


        return sendButton;
    }

    public static Button ExitButton(){
        Image exitGamePassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_exitgame.png").toExternalForm());
        ImageView imageViewPassive = new ImageView(exitGamePassive);

        Image exitgameActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_exitgame2.png").toExternalForm());
        ImageView imageViewActive = new ImageView(exitgameActive);

        //Setting
        imageViewPassive.setFitWidth(exitGamePassive.getWidth() * 0.3);
        imageViewPassive.setFitHeight(exitGamePassive.getHeight() * 0.3);
        imageViewActive.setFitWidth(exitgameActive.getWidth() * 0.3);
        imageViewActive.setFitHeight(exitgameActive.getHeight() * 0.3);

        Button exitGameButton = new Button();
        exitGameButton.setGraphic(imageViewPassive);
        exitGameButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        exitGameButton.setOnMouseEntered(e ->{
            exitGameButton.setGraphic(imageViewActive);
        });

        exitGameButton.setOnMouseExited(e ->{
            exitGameButton.setGraphic(imageViewPassive);

        });

        exitGameButton.setOnMouseClicked(e ->{
            Platform.exit();
        });


        return exitGameButton;
    }

    public static Button LanguageButton(){



        Image langPassive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_lang.png").toExternalForm());
        ImageView imageViewEnglish = new ImageView(langPassive);

        Image langActive = new Image(GameButtons.class.getResource("/com/zimgo/crazyeight/ButtonTemplate/btn_lang2.png").toExternalForm());
        ImageView imageViewTagalog = new ImageView(langActive);

        //Setting
        imageViewEnglish.setFitWidth(langPassive.getWidth() * 0.5);
        imageViewEnglish.setFitHeight(langPassive.getHeight() * 0.5);
        imageViewTagalog.setFitWidth(langActive.getWidth() * 0.5);
        imageViewTagalog.setFitHeight(langActive.getHeight() * 0.5);

        Button langButton = new Button();
        langButton.setGraphic(imageViewEnglish);

        langButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; ");

        //ToDo: change this later

        langButton.setOnMouseClicked(e ->{
            if(isEnglish){
                isEnglish = false;
                langButton.setGraphic(imageViewTagalog);
            }else if(!isEnglish){
                isEnglish = true;
                langButton.setGraphic(imageViewEnglish);
            }
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
            }else if(!isMusicOn){
                isMusicOn = true;
                musicSetButton.setGraphic(imageViewON);
            }
        });


        return musicSetButton;
    }




}
