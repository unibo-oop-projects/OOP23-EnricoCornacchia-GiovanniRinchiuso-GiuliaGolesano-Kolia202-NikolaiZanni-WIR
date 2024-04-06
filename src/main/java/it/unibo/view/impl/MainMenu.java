package it.unibo.view.impl;

import it.unibo.utilities.GameState;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        final ImageView pauseButton = new ImageView(new Image("pause.png"));
        pauseButton.setFitWidth(80);
        pauseButton.setFitHeight(80);
        pauseButton.setOnMouseClicked(mouseEvent -> {
            GameState.setGameState(GameState.PAUSED);
            System.out.println("GameState" + GameState.getGameState());
            AnotherStage secondStage = new AnotherStage();
            Scene secondScene = secondStage.getScene();
            secondStage.setScene(secondScene);
            primaryStage.hide();
            secondStage.show();
        });
        StackPane root = new StackPane(pauseButton);
        primaryStage.setScene(new Scene(root));
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
        private static final int HEIGHT = 400;
        private static final String BACKGROUND = "backgroundMainMenu.png";
        private static final String TOP_IMAGE = "PauseText.png";

        AnotherStage() {
            super();
            initStyle(StageStyle.UNDECORATED);
            Image backgroundMainMenu = new Image(BACKGROUND);
            Image topImage = new Image(TOP_IMAGE);
            StackPane root = new StackPane();
            BackgroundSize backgroundSize = new BackgroundSize(WIDTH, HEIGHT, false, false, false, false);
            BackgroundImage backgroundImage = new BackgroundImage(backgroundMainMenu, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            root.setBackground(new Background(backgroundImage));
            ImageView topImageView = new ImageView(topImage);
            topImageView.setFitWidth(300);
            topImageView.setFitHeight(150);
            StackPane.setAlignment(topImageView, Pos.TOP_CENTER);
            root.getChildren().add(topImageView);
            final HBox pane = new HBox(20);
            pane.setAlignment(Pos.CENTER);
            final ImageView continueButton = new ImageView(new Image("continue.png"));
            final ImageView quitButton = new ImageView(new Image("quit.png"));
            final ImageView homeButton = new ImageView(new Image("home.png"));
            continueButton.setFitHeight(100);
            continueButton.setFitWidth(100);
            quitButton.setFitHeight(100);
            quitButton.setFitWidth(100);
            homeButton.setFitHeight(100);
            homeButton.setFitWidth(100);
            continueButton.setOnMouseClicked(event -> {
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
            pane.getChildren().add(continueButton);
            pane.getChildren().add(quitButton);
            pane.getChildren().add(homeButton);
            root.getChildren().add(pane);
            setScene(new Scene(root, WIDTH, HEIGHT));
            show();
        }
    }
}

  
