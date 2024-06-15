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

public final class StartGameView extends Application {
    private Button level1Button;
    private Button level2Button;
    private Button level3Button;
    private Button infoButton;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("StartGame");
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: black;");

        Image imageA = new Image("fix-it_felix_title.png");
        ImageView imageViewA = new ImageView(imageA);
        imageViewA.setFitWidth(350);
        imageViewA.setFitHeight(150);

        Image startImage1 = new Image("press_start.png");
        Image startImage2 = new Image("press_start2.png");
        ImageView startButtonImageView = new ImageView(startImage1);
        startButtonImageView.setFitWidth(125);
        startButtonImageView.setFitHeight(10);

        Button startButton = new Button("", startButtonImageView);
        startButton.setStyle("-fx-background-color: transparent;");

        Image imageB = new Image("tobikomi.png");
        ImageView imageViewB = new ImageView(imageB);
        imageViewB.setFitWidth(225);
        imageViewB.setFitHeight(25);

        VBox imageBox = new VBox(50);
        imageBox.getChildren().addAll(imageViewA, startButton, imageViewB);
        imageBox.setStyle("-fx-alignment: center;");
        root.getChildren().add(imageBox);

        VBox vbox = new VBox(20);
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

        HBox levelButtons = new HBox(20);
        levelButtons.getChildren().addAll(level1Button, level2Button, level3Button);
        levelButtons.setStyle("-fx-alignment: center;");

        vbox.getChildren().addAll(selectLevelLabel, levelButtons, infoButton);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(e -> {
            root.getChildren().remove(imageBox);
            root.getChildren().addAll(vbox);
            animateCarSequence(root);
            animateClouds(root);
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> startButtonImageView.setImage(startImage1)),
                new KeyFrame(Duration.seconds(1.0), e -> startButtonImageView.setImage(startImage2)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-family: 'Arial Black'; -fx-padding: 10 20 10 20; -fx-border-color: white; -fx-border-width: 2px;");
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-font-family: 'Arial Black'; -fx-padding: 10 20 10 20; -fx-border-color: white; -fx-border-width: 2px;"));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-family: 'Arial Black'; -fx-padding: 10 20 10 20; -fx-border-color: white; -fx-border-width: 2px;"));
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

        VBox instructionsBox = new VBox(10);
        instructionsBox.setStyle("-fx-alignment: center-left;");
        instructionsBox.setPadding(new javafx.geometry.Insets(20));

        Label instructionsTitle = new Label("INSTRUCTIONS");
        instructionsTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-family: 'Arial Black';");

        Label instructionsText = new Label("FIX ALL BROKEN WINDOWS ON EACH FLOOR TO WIN");
        instructionsText.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        VBox movementBox = new VBox(5);
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

        Scene scene = new Scene(root, 500, 400);
        infoStage.setScene(scene);
        infoStage.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra come modale
        infoStage.setResizable(false); // Impedisce il ridimensionamento
        infoStage.setOnCloseRequest(event -> event.consume()); // Impedisce la chiusura con la X
        infoStage.show();
    }

    private void animateCarSequence(StackPane root) {
        ImageView car1De = new ImageView(new Image("car1De.png"));
        ImageView car2Si = new ImageView(new Image("car2Si.png"));

        car1De.setFitWidth(75);
        car1De.setFitHeight(50);
        car2Si.setFitWidth(100);
        car2Si.setFitHeight(75);
        car1De.setTranslateY(225);
        car2Si.setTranslateY(210);

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

    private void animateCarRightToLeft(ImageView car, Runnable onFinished) {
        car.setTranslateX(700);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5), car);
        transition.setFromX(700);
        transition.setToX(-700);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setOnFinished(e -> onFinished.run());
        transition.play();
    }

    private void animateCarLeftToRight(ImageView car, Runnable onFinished) {
        car.setTranslateX(-700);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5), car);
        transition.setFromX(-700);
        transition.setToX(700);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setOnFinished(e -> onFinished.run());
        transition.play();
    }

    private void animateClouds(StackPane root) {
        ImageView cloudImageView = new ImageView(new Image("clouds.png"));
        cloudImageView.setFitWidth(200);
        cloudImageView.setFitHeight(100);
        cloudImageView.setTranslateY(-200);

        root.getChildren().add(cloudImageView);

        TranslateTransition transitionRightToLeft = new TranslateTransition(Duration.seconds(10), cloudImageView);
        transitionRightToLeft.setFromX(700);
        transitionRightToLeft.setToX(-700);
        transitionRightToLeft.setInterpolator(Interpolator.LINEAR);
        transitionRightToLeft.setOnFinished(e -> {
            TranslateTransition transitionLeftToRight = new TranslateTransition(Duration.seconds(10), cloudImageView);
            transitionLeftToRight.setFromX(-700);
            transitionLeftToRight.setToX(700);
            transitionLeftToRight.setInterpolator(Interpolator.LINEAR);
            transitionLeftToRight.setOnFinished(event -> {
                transitionRightToLeft.playFromStart();
            });
            transitionLeftToRight.play();
        });

        transitionRightToLeft.play();
    }

    private void startWindowGame(Stage primaryStage, int level) {
        try {
            WindowGame windowGame = new WindowGame();
            windowGame.getGameEngine().getGameController().setLevel(level);
            windowGame.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}