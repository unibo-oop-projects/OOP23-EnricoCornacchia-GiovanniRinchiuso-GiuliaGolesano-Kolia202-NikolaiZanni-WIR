package it.unibo.view.impl;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.GameState;

/**
 * This class is used to show the view of the game when the player starts the game.
 */
public final class StartGameView extends Application {
    private Button level1Button;
    private Button level2Button;
    private Button level3Button;
    private Button infoButton;
    private static boolean firstLaunch = true;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final int IMAGE1_WIDTH = 350;
    private static final int IMAGE1_HEIGHT = 150;
    private static final int BUTTON_WIDTH = 125;
    private static final int BUTTON_HEIGHT = 10;
    private static final int IMAGE2_WIDTH = 225;
    private static final int IMAGE2_HEIGHT = 25;
    private static final int V_BOX = 50;
    private static final int BUTTONS_SPACING = 20;
    private static final int V_BOX_SPACING = 10;
    private static final double DURATION_FIRST = 0.5;
    private static final double DURATION_SECOND = 1.0;
    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 400;
    private static final int TRANSLATE_X = 700;
    private static final int TRANSLATE_Y = 200;
    private static final int FIT_WIDTH = 200;
    private static final int FIT_HEIGHT = 100;
    private static final int CAR1_DE_WIDTH = 75;
    private static final int CAR1_DE_HEIGHT = 50;
    private static final int CAR2_SI_WIDTH = 100;
    private static final int CAR2_SI_HEIGHT = 75;
    private static final int CAR1_DE_TRANSLATE_Y = 225;
    private static final int CAR2_SI_TRANSLATE_Y = 210;
    private static final int PADDING = 20;
    private static final int MOVE = 5;





    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("StartGame");
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        if (firstLaunch) {
            PointsComponent.resetHighScoreOnFirstLaunch();
            firstLaunch = false;
        }
        Image imageA = new Image("fix-it_felix_title.png");
        ImageView imageViewA = new ImageView(imageA);
        imageViewA.setFitWidth(IMAGE1_WIDTH);
        imageViewA.setFitHeight(IMAGE1_HEIGHT);

        Image startImage1 = new Image("press_start.png");
        Image startImage2 = new Image("press_start2.png");
        ImageView startButtonImageView = new ImageView(startImage1);
        startButtonImageView.setFitWidth(BUTTON_WIDTH);
        startButtonImageView.setFitHeight(BUTTON_HEIGHT);

        Button startButton = new Button("", startButtonImageView);
        startButton.setStyle("-fx-background-color: transparent;");

        Image imageB = new Image("tobikomi.png");
        ImageView imageViewB = new ImageView(imageB);
        imageViewB.setFitWidth(IMAGE2_WIDTH);
        imageViewB.setFitHeight(IMAGE2_HEIGHT);

        VBox imageBox = new VBox(V_BOX);
        imageBox.getChildren().addAll(imageViewA, startButton, imageViewB);
        imageBox.setStyle("-fx-alignment: center;");
        root.getChildren().add(imageBox);

        VBox vbox = new VBox(BUTTONS_SPACING);
        vbox.setStyle("-fx-alignment: center;");

        Label selectLevelLabel = new Label("SELECT LEVEL TO START GAME");
        selectLevelLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: 'Arial Black';");

        // Create buttons with text
        level1Button = createStyledButton("EASY");
        level1Button.setOnAction(e -> startWindowGame(primaryStage, 1));

        level2Button = createStyledButton("MEDIUM");
        level2Button.setOnAction(e -> startWindowGame(primaryStage, 2));

        level3Button = createStyledButton("HARD");
        level3Button.setOnAction(e -> startWindowGame(primaryStage, 3));

        infoButton = createStyledButton("INFO");
        infoButton.setOnAction(e -> showInfoStage());

        HBox levelButtons = new HBox(BUTTONS_SPACING);
        levelButtons.getChildren().addAll(level1Button, level2Button, level3Button);
        levelButtons.setStyle("-fx-alignment: center;");

        vbox.getChildren().addAll(selectLevelLabel, levelButtons, infoButton);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(e -> {
            root.getChildren().remove(imageBox);
            root.getChildren().addAll(vbox);
            animateCarSequence(root);
            animateClouds(root);
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(DURATION_FIRST), e -> startButtonImageView.setImage(startImage1)),
                new KeyFrame(Duration.seconds(DURATION_SECOND), e -> startButtonImageView.setImage(startImage2)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private Button createStyledButton(final String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 18px;"  
                + "-fx-font-family: 'Arial Black'; -fx-padding: 10 20 10 20; -fx-border-color: white; -fx-border-width: 2px;");
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px;" 
                + "-fx-font-family: 'Arial Black'; -fx-padding: 10 20 10 20; -fx-border-color: white; -fx-border-width: 2px;"));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 18px;" 
                + "-fx-font-family: 'Arial Black'; -fx-padding: 10 20 10 20; -fx-border-color: white; -fx-border-width: 2px;"));
        return button;
    }

    private void showInfoStage() {
        Stage infoStage = new Stage();
        infoStage.setTitle("Instructions");

        // Disabilita i pulsanti dei livelli
        level1Button.setDisable(true);
        level2Button.setDisable(true);
        level3Button.setDisable(true);
        infoButton.setDisable(true);

        VBox instructionsBox = new VBox(V_BOX_SPACING);
        instructionsBox.setStyle("-fx-alignment: center-left;");
        instructionsBox.setPadding(new javafx.geometry.Insets(PADDING));

        Label instructionsTitle = new Label("INSTRUCTIONS");
        instructionsTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-family: 'Arial Black';");

        Label instructionsText = new Label("FIX ALL BROKEN WINDOWS ON EACH FLOOR TO WIN");
        instructionsText.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        VBox movementBox = new VBox(MOVE);
        movementBox.setStyle("-fx-alignment: center-left;");

        Label moveText = new Label("MOVE: W-D-S-A");
        moveText.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Label fixText = new Label("FIX: Z");
        fixText.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Label avoidText = new Label("AVOID: BRICK");
        avoidText.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Label collectText = new Label("COLLECT: CAKE & BIRD");
        collectText.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        movementBox.getChildren().addAll(moveText, fixText, avoidText, collectText);

        Button backButton = createStyledButton("BACK");
        backButton.setOnAction(e -> {
            infoStage.close();
            // Abilita i pulsanti dei livelli
            level1Button.setDisable(false);
            level2Button.setDisable(false);
            level3Button.setDisable(false);
            infoButton.setDisable(false);
        });

        instructionsBox.getChildren().addAll(instructionsTitle, instructionsText, movementBox, backButton);

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");
        root.getChildren().add(instructionsBox);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        infoStage.setScene(scene);
        infoStage.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra come modale
        infoStage.setResizable(false); // Impedisce il ridimensionamento
        infoStage.setOnCloseRequest(event -> event.consume()); // Impedisce la chiusura con la X
        infoStage.show();
    }

    private void animateCarSequence(final StackPane root) {
        ImageView car1De = new ImageView(new Image("car1De.png"));
        ImageView car2Si = new ImageView(new Image("car2Si.png"));

        car1De.setFitWidth(CAR1_DE_WIDTH);
        car1De.setFitHeight(CAR1_DE_HEIGHT);
        car2Si.setFitWidth(CAR2_SI_WIDTH);
        car2Si.setFitHeight(CAR2_SI_HEIGHT);
        car1De.setTranslateY(CAR1_DE_TRANSLATE_Y);
        car2Si.setTranslateY(CAR2_SI_TRANSLATE_Y);

        root.getChildren().add(car1De);

        animateCarLeftToRight(car1De, () -> {
            root.getChildren().remove(car1De);
            root.getChildren().add(car2Si);
            animateCarRightToLeft(car2Si, () -> {
                root.getChildren().remove(car2Si);
                animateCarSequence(root);
            });
        });
    }

    private void animateCarRightToLeft(final ImageView car, final Runnable onFinished) {
        car.setTranslateX(TRANSLATE_X);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(MOVE), car);
        transition.setFromX(TRANSLATE_X);
        transition.setToX(-TRANSLATE_X);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setOnFinished(e -> onFinished.run());
        transition.play();
    }

    private void animateCarLeftToRight(final ImageView car, final Runnable onFinished) {
        car.setTranslateX(-TRANSLATE_X);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(MOVE), car);
        transition.setFromX(-TRANSLATE_X);
        transition.setToX(TRANSLATE_X);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setOnFinished(e -> onFinished.run());
        transition.play();
    }

    private void animateClouds(final StackPane root) {
        ImageView cloudImageView = new ImageView(new Image("clouds.png"));
        cloudImageView.setFitWidth(FIT_WIDTH);
        cloudImageView.setFitHeight(FIT_HEIGHT);
        cloudImageView.setTranslateY(-TRANSLATE_Y);

        root.getChildren().add(cloudImageView);

        TranslateTransition transitionRightToLeft = new TranslateTransition(Duration.seconds(10), cloudImageView);
        transitionRightToLeft.setFromX(TRANSLATE_X);
        transitionRightToLeft.setToX(-TRANSLATE_X);
        transitionRightToLeft.setInterpolator(Interpolator.LINEAR);
        transitionRightToLeft.setOnFinished(e -> {
            TranslateTransition transitionLeftToRight = new TranslateTransition(Duration.seconds(10), cloudImageView);
            transitionLeftToRight.setFromX(-TRANSLATE_X);
            transitionLeftToRight.setToX(TRANSLATE_X);
            transitionLeftToRight.setInterpolator(Interpolator.LINEAR);
            transitionLeftToRight.setOnFinished(event -> {
                transitionRightToLeft.playFromStart();
            });
            transitionLeftToRight.play();
        });

        transitionRightToLeft.play();
    }

    private void startWindowGame(final Stage primaryStage, final int level) {
        try {
            WindowGame windowGame = new WindowGame();
            windowGame.getGameEngine().getGameController().setLevel(level);
            windowGame.start(primaryStage);
            GameState.setGameState(GameState.PLAYING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
