package it.unibo.view.impl;

import it.unibo.utilities.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import it.unibo.model.impl.PointsComponent;

/**
 * EndGame represents the end game screen.
 * This class provides a simple "Game Over" message.
 */
public final class EndGameView extends Application {
    private PointsComponent pointsComponent;
    private HighPointsView highPointsView;

    /**
     * Starts the end game screen.
     *
     * @param primaryStage the primary stage for the application
     * @throws Exception if an error occurs during startup
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        GameState.setGameState(GameState.GAMEOVER);

        pointsComponent = new PointsComponent();

        highPointsView = new HighPointsView(pointsComponent);
        pointsComponent.setHighPointsView(highPointsView);

        ImageView background = new ImageView(new Image("losegame1.png"));
        background.setFitWidth(700);
        background.setFitHeight(500);

        ImageView imageView1 = new ImageView(new Image("gameOver3.png"));
        imageView1.setFitWidth(400);
        imageView1.setFitHeight(175);

        ImageView imageView2 = new ImageView(new Image("gameOver2.png"));
        imageView2.setFitWidth(400);
        imageView2.setFitHeight(175);

        StackPane root = new StackPane();
        root.getChildren().addAll(background, imageView1);

        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setTitle("GAME OVER");
        primaryStage.show();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> {
                    if (root.getChildren().contains(imageView1)) {
                        root.getChildren().remove(imageView1);
                        root.getChildren().add(imageView2);
                    } else {
                        root.getChildren().remove(imageView2);
                        root.getChildren().add(imageView1);
                    }
                }));

        timeline.setCycleCount(10);
        timeline.setOnFinished(event -> {
            root.getChildren().remove(imageView1);
            root.getChildren().remove(imageView2);
            showButtonsAndPoints(root, primaryStage);
        });
        timeline.play();
    }

    /**
     * Shows the buttons and points section after the timeline finishes.
     *
     * @param root         the root StackPane
     * @param primaryStage the primary stage of the application
     */
    private void showButtonsAndPoints(StackPane root, Stage primaryStage) {

        pointsComponent = new PointsComponent();

        highPointsView = new HighPointsView(pointsComponent);
        pointsComponent.setHighPointsView(highPointsView);

        highPointsView.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: yellow; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 0, 0);");

        // Update the high points label
        highPointsView.updateHighPointsLabel();

        HBox pointsBox = new HBox(20, highPointsView);
        pointsBox.setAlignment(Pos.CENTER);

        AnchorPane pointsPane = new AnchorPane();
        pointsPane.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 3;");
        pointsPane.setMaxWidth(400);
        pointsPane.setMaxHeight(75);
        pointsPane.setMinWidth(400);
        pointsPane.setMinHeight(75);

        AnchorPane.setTopAnchor(pointsBox, 10.0);
        AnchorPane.setLeftAnchor(pointsBox, 10.0);
        pointsPane.getChildren().add(pointsBox);

        ImageView button1 = new ImageView(new Image("homeB.png"));
        button1.setFitWidth(125);
        button1.setFitHeight(50);
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                new StartGameView().start(new Stage());
                GameState.setGameState(GameState.HOME);
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ImageView button2 = new ImageView(new Image("quitB.png"));
        button2.setFitWidth(125);
        button2.setFitHeight(50);
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            primaryStage.close();
        });

        HBox buttonBox = new HBox(20, button1, button2);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20, pointsPane, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setTranslateY(50);

        StackPane.setAlignment(vbox, Pos.CENTER);

        root.getChildren().add(vbox);
    }
}