package it.unibo.view.impl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * WindowGame, it represents the window of the game.
 */
public class WindowGame extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create an instance of the MainMenu class
        MainMenu mainMenu = new MainMenu();

        // Create an AnchorPane to align the MainMenu button in the top right corner
        AnchorPane root = new AnchorPane();
        AnchorPane.setRightAnchor(mainMenu, 0.0);
        AnchorPane.setTopAnchor(mainMenu, 0.0);
        root.getChildren().add(mainMenu);

        // Set the scene and stage properties
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("StartGame");
        primaryStage.show();
    }
}