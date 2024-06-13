package it.unibo.view.impl;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * StartGame represents the initial screen of the game.
 */
public final class StartGame extends Application {

    /**
     * Starts the initial screen of the game.
     *
     * @param primaryStage the primary stage for the application
     * @throws Exception if an error occurs during startup
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        /*Label message = new Label("Hello, StartGame!");
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(new StackPane(message), 800, 600));
        primaryStage.setTitle("StartGame");
        primaryStage.show();

        // Create a delay of 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> {
            // Close the current stage
            primaryStage.close();

            // Open the homeMenu class
            HomeMenu homeMenu = new HomeMenu();
            try {
                homeMenu.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        delay.play();*/

        try {
            new WindowGame().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
