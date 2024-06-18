package it.unibo.view.impl;

import it.unibo.utilities.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is used to show the view of the game when the player wins.
 */
public class WinGameView extends Application {

    private final int width = 700;
    private final int height = 500;
    private final int imageWidth = 200;
    private final int imageHeight = 25;
    private final double animationDurationFirst = 0.25;
    private final double animationDurationSecond = 0.5;
    private final double animationDurationThird = 0.75;
    private final int translateY = 100;

    /**
     * This method is used to show the view of the game when the player wins.
     * @param primaryStage the stage of the game.
     * @throws Exception if an error occurs.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        GameState.setGameState(GameState.WIN);
        StackPane root = new StackPane();

        Image newBackgroundImage = new Image("winEndview.png");
        ImageView newBackgroundImageView = new ImageView(newBackgroundImage);
        newBackgroundImageView.setFitWidth(width);
        newBackgroundImageView.setFitHeight(height);

        Image image1 = new Image("fix.png");
        Image image2 = new Image("fix2.png");
        Image image3 = new Image("fix3.png");

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);



        imageView1.setFitWidth(imageWidth);
        imageView1.setFitHeight(imageHeight);
        imageView2.setFitWidth(imageWidth);
        imageView2.setFitHeight(imageHeight);
        imageView3.setFitWidth(imageWidth);
        imageView3.setFitHeight(imageHeight);

        imageView1.setVisible(false);
        imageView2.setVisible(false);
        imageView3.setVisible(false);

        StackPane imagesStack = new StackPane();
        imagesStack.getChildren().addAll(imageView1, imageView2, imageView3);

        VBox topLayout = new VBox();
        topLayout.getChildren().add(imagesStack);
        topLayout.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        topLayout.setTranslateY(translateY);
        root.getChildren().addAll(newBackgroundImageView, topLayout);

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(animationDurationFirst), event -> {
                imageView1.setVisible(true);
                imageView2.setVisible(false);
                imageView3.setVisible(false);
            }),
            new KeyFrame(Duration.seconds(animationDurationSecond), event -> {
                imageView1.setVisible(false);
                imageView2.setVisible(true);
                imageView3.setVisible(false);
            }),
            new KeyFrame(Duration.seconds(animationDurationThird), event -> {
                imageView1.setVisible(false);
                imageView2.setVisible(false);
                imageView3.setVisible(true);
            }),
            new KeyFrame(Duration.seconds(1), event -> {
            })
        );

        timeline.setCycleCount(10);
        timeline.setOnFinished(event -> {
            try {
                new StartGameView().start(new Stage());
                GameState.setGameState(GameState.HOME);
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        timeline.play();

        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.setTitle("WinGame");
        primaryStage.show();
    }
}
