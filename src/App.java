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

import view.Scene.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class name: App
 * Purpose: Initialize JavaFX Program
 * @author John Rycca Belcina
 * @since 1.8
 */
public class App extends Application {

    /**
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     * @throws IOException handles input and output exeception.
     */
    @Override
    public void start(Stage stage) throws IOException {
       Menu menu = new Menu(stage);


    }

    /**
     * Entry point for JavaFX Application
     * @param args command line argument
     */
    public static void main(String[] args) {
        launch();
    }
}

