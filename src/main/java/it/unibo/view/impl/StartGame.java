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
import javafx.stage.Stage;
import javafx.util.Duration;
import it.unibo.controller.impl.GameController;
import javafx.animation.Interpolator;

public final class StartGame extends Application {
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

        Label selectLevelLabel = new Label("SELEZIONA IL LIVELLO PER AVVIARE IL GIOCO");
        selectLevelLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: 'Arial Black';");

        // Create buttons with text
        Button level1Button = createStyledButton("EASY");
        level1Button.setOnAction(e -> startWindowGame(primaryStage, 1));

        Button level2Button = createStyledButton("MEDIUM");
        level2Button.setOnAction(e -> startWindowGame(primaryStage, 2));

        Button level3Button = createStyledButton("HARD");
        level3Button.setOnAction(e -> startWindowGame(primaryStage, 3));

        HBox levelButtons = new HBox(20);
        levelButtons.getChildren().addAll(level1Button, level2Button, level3Button);
        levelButtons.setStyle("-fx-alignment: center;");

        vbox.getChildren().addAll(selectLevelLabel, levelButtons);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(e -> {
            root.getChildren().remove(imageBox);
            root.getChildren().add(vbox);
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
            GameController gameController = new GameController();

            System.out.println("Livello passato: " + level);
            gameController.setLevel(level);
            System.out.println("Livello impostato: " + gameController.getLevel());
            windowGame.start(primaryStage);
            System.out.println("Livello nel controller: " + gameController.getLevel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}