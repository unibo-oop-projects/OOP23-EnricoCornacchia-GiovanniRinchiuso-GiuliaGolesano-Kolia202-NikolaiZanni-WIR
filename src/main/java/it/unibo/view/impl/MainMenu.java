package it.unibo.view.impl;

import java.util.logging.Logger;
import it.unibo.utilities.Constaints;
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

/**
 * MainMenu, it represents the main menu of the game.
 */
public class MainMenu extends Application {
    private static final Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    /**
     * start, it starts the main menu.
     * 
     * @param primaryStage the stage of the main menu.
     * @throws Exception if an error occurs.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        final ImageView pauseButton = new ImageView(new Image("pauseButton.png"));
        pauseButton.setFitWidth(Constaints.WIDTH_PAUSE_BUTTON);
        pauseButton.setFitHeight(Constaints.HEIGHT_PAUSE_BUTTON);
        pauseButton.setOnMouseClicked(mouseEvent -> {
            GameState.setGameState(GameState.PAUSED);
            LOGGER.info(Constaints.GAMESTATE + GameState.getGameState());
            final AnotherStage secondStage = new AnotherStage();
            final Scene secondScene = secondStage.getScene();
            secondStage.setScene(secondScene);
            primaryStage.hide();
            secondStage.show();
        });
        final StackPane root = new StackPane(pauseButton);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("PROVA");
        primaryStage.show();
    }

    /**
     * run, it starts the main menu.
     * 
     * @param args the arguments of the main.
     */
    private static void run(final String[] args) {
        launch(args);
    }

    /**
     * Main, it starts the main menu.
     */
    public static final class Main {
        private Main() {
        }

        /**
         * main, it starts the main menu.
         * 
         * @param args the arguments of the main.
         */
        public static void main(final String... args) {
            MainMenu.run(args);
        }
    }

    private static class AnotherStage extends Stage {
        private static final int WIDTH = 500;
        private static final int HEIGHT = 400;
        private static final String BACKGROUND = "backgroundMainMenu.png";
        private static final String TOP_IMAGE = "gamePause.png";
        private static final String UNDER_IMAGE = "underImage.png";

        AnotherStage() {
            super();
            initStyle(StageStyle.UNDECORATED);
            final Image backgroundMainMenu = new Image(BACKGROUND);
            final Image topImage = new Image(TOP_IMAGE);
            final Image underImage = new Image(UNDER_IMAGE);
            final StackPane root = new StackPane();
            final BackgroundSize backgroundSize = new BackgroundSize(WIDTH, HEIGHT, false, false, false, false);
            final BackgroundImage backgroundImage = new BackgroundImage(
                    backgroundMainMenu,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);
            root.setBackground(new Background(backgroundImage));
            final ImageView topImageView = new ImageView(topImage);
            topImageView.setFitWidth(Constaints.TOP_IMAGE_WIDTH);
            topImageView.setFitHeight(Constaints.TOP_IMAGE_HEIGHT);
            StackPane.setAlignment(topImageView, Pos.TOP_CENTER);
            root.getChildren().add(topImageView);
            final ImageView underImageView = new ImageView(underImage);
            underImageView.setFitHeight(Constaints.UNDER_IMAGE_HEIGHT);
            underImageView.setFitWidth(Constaints.UNDER_IMAGE_WIDTH);
            StackPane.setAlignment(underImageView, Pos.BOTTOM_CENTER);
            root.getChildren().add(underImageView);
            final HBox pane = new HBox(20);
            pane.setAlignment(Pos.CENTER);
            final ImageView continueButton = new ImageView(new Image("continueButton.png"));
            final ImageView quitButton = new ImageView(new Image("quitButton.png"));
            final ImageView homeButton = new ImageView(new Image("homeButton.png"));
            continueButton.setFitHeight(100);
            continueButton.setFitWidth(100);
            quitButton.setFitHeight(100);
            quitButton.setFitWidth(100);
            homeButton.setFitHeight(100);
            homeButton.setFitWidth(100);
            continueButton.setOnMouseClicked(event -> {
                GameState.setGameState(GameState.PLAYING);
                LOGGER.info(Constaints.GAMESTATE + GameState.getGameState());
            });
            quitButton.setOnMouseClicked(event -> {
                GameState.setGameState(GameState.GAMEOVER);
                LOGGER.info(Constaints.GAMESTATE + GameState.getGameState());
                close();
            });
            homeButton.setOnMouseClicked(event -> {
                GameState.setGameState(GameState.HOME);
                LOGGER.info(Constaints.GAMESTATE + GameState.getGameState());
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
