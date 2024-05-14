package it.unibo.view.impl;

import it.unibo.utilities.GameState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * EndGame represents the end game screen.
 * This class provides a simple "Game Over" message.
 */
public final class EndGame extends Application {

    /**
     * Starts the end game screen.
     *
     * @param primaryStage the primary stage for the application
     * @throws Exception if an error occurs during startup
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        GameState.setGameState(GameState.GAMEOVER);
        Label message = new Label("Game Over, Start Game!");
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(new StackPane(message), 800, 600));
        primaryStage.setTitle("Start Game");
        primaryStage.show();
    }
}
