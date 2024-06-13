package it.unibo.view.impl;

import it.unibo.utilities.GameState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * HomeMenu, it represents the home menu of the game.
 */
public class HomeMenu extends Application {
    private final int WIDTH = 800;
    private final int HEIGH = 600;
    private final int FONT = 100;
    /**
     * start, it starts the home menu.
     * 
     * @param primaryStage the stage of the home menu.
     * @throws Exception if an error occurs.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        // Set the game state to HOME
        GameState.setGameState(GameState.HOME);

        Label message = new Label("Hello, HomeMenu!");
        message.setFont(new Font(FONT));

        // Create a Start button
        Button startButton = new Button("Start");

        // Add an event handler to the Start button
        startButton.setOnAction(event -> {
            // Close the HomeMenu window
            primaryStage.close();

            // Open the WindowGame class
            try {
                new WindowGame().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Add the Start button to the StackPane
        StackPane root = new StackPane(message, startButton);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGH));
        primaryStage.setTitle("StartGame");
        primaryStage.show();
    }
}
