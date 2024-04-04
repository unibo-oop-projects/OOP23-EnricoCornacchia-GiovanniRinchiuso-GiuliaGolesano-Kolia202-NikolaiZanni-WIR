package it.unibo.view.impl;

import it.unibo.utilities.GameState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Button configButton = new Button("CONFIG");
        configButton.setFont(new Font(100));
        configButton.setOnMouseClicked(mouseEvent -> {
            GameState.setGameState(GameState.PAUSED);
            System.out.println("GameState" + GameState.getGameState());
            new AnotherStage().show();
        });
        primaryStage.setScene(new Scene(configButton));
        primaryStage.setTitle("PROVA");
        primaryStage.show();
    }
    private static void run(final String[] args) {
        launch(args);
    }
    public static final class Main {
        private Main() {
        }
    
    public static void main(final String... args) {
        MainMenu.run(args);
    }
}

    private static class AnotherStage extends Stage {
        private static final int WIDTH = 500;
        private static final int HEIGHT = 300;

        AnotherStage() {
            super();
            setTitle("new Stage created" + System.currentTimeMillis());
            final VBox pane = new VBox();
            Button resumeButton = new Button("RESUME");
            Button quitButton = new Button("QUIT");
            Button homeButton = new Button("HOME");
            resumeButton.setFont(new Font(20));
            quitButton.setFont(new Font(20));
            homeButton.setFont(new Font(20));
            resumeButton.setOnMouseClicked(event -> {
                GameState.setGameState(GameState.PLAYING);
                System.out.println(("GameState" + GameState.getGameState()));
            });
            quitButton.setOnMouseClicked(event -> {
                GameState.setGameState(GameState.GAMEOVER);
                System.out.println("GameState" + GameState.getGameState());
            });
            homeButton.setOnMouseClicked(event -> {
                GameState.setGameState(GameState.HOME);
                System.out.println("GameState" + GameState.getGameState());
            });
            pane.getChildren().add(resumeButton);
            pane.getChildren().add(quitButton);
            pane.getChildren().add(homeButton);
            setScene(new Scene(pane, WIDTH, HEIGHT));
            show();
        }
    }
}

  
