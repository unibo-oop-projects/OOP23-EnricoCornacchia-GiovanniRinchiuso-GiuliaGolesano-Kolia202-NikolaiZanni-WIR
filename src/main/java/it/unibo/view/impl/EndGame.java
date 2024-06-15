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
public final class EndGame extends Application {

    /**
     * Starts the end game screen.
     *
     * @param primaryStage the primary stage for the application
     * @throws Exception if an error occurs during startup
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        GameState.setGameState(GameState.GAMEOVER);

        ImageView background = new ImageView(new Image("losegame1.png"));
        background.setFitWidth(800);
        background.setFitHeight(600);

        ImageView imageView1 = new ImageView(new Image("gameOver3.png"));
        imageView1.setFitWidth(400);
        imageView1.setFitHeight(175);

        ImageView imageView2 = new ImageView(new Image("gameOver2.png"));
        imageView2.setFitWidth(400);
        imageView2.setFitHeight(175);

        PointsComponent pointsComponent = new PointsComponent();
        HighPointsView highPointsView = new HighPointsView(pointsComponent);
        highPointsView.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: yellow; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 0, 0);");

        StackPane root = new StackPane();
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setBottomAnchor(highPointsView, 150.0);
        AnchorPane.setLeftAnchor(highPointsView, (850.0 - 150.0) / 2);
        anchorPane.getChildren().add(highPointsView);

        root.getChildren().addAll(background, imageView1, anchorPane);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("GAME OVER");
        primaryStage.show();

        // Timeline to alternate images
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
            showButtons(root, primaryStage);
        });
        timeline.play();
    }

    /**
     * Shows the buttons after the timeline finishes.
     *
     * @param root         the root StackPane
     * @param primaryStage the primary stage of the application
     */
    private void showButtons(StackPane root, Stage primaryStage) {
        ImageView button1 = new ImageView(new Image("homeButton.png"));
        button1.setFitWidth(100);
        button1.setFitHeight(100);
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                new StartGame().start(new Stage());
                GameState.setGameState(GameState.HOME);
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ImageView button2 = new ImageView(new Image("quitButton.png"));
        button2.setFitWidth(100);
        button2.setFitHeight(100);
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            primaryStage.close();
        });

        HBox buttonBox = new HBox(20, button1, button2);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateX(15);

        VBox vbox = new VBox(20, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        StackPane.setAlignment(vbox, Pos.CENTER);

        root.getChildren().add(vbox);
    }
}