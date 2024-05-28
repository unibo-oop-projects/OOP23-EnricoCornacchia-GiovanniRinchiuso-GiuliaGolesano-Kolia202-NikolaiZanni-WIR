package it.unibo.view.impl;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Sample GameApp application.
 */
public final class GameApp extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        StartGame startGame = new StartGame();
        startGame.start(primaryStage);
    }
    /**
     * Program's entry point.
     * 
     * @param args
     */
    public static void run(final String... args) {
        launch(args);
    }
    /**
     * Entry point's class.
     */
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        /**
         * Program's entry point.
         * 
         * @param args
         */
        public static void main(final String... args) {
            Application.launch(GameApp.class, args);
        }
    }
}
