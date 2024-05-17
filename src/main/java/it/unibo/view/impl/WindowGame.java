package it.unibo.view.impl;

import it.unibo.controller.impl.GameController;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.PointsComponent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * WindowGame represents the window of the game.
 * This class can be extended to customize the game window.
 */
public class WindowGame extends Application {
    private Stage primaryStage; 
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        PointsComponent pointsComponent = new PointsComponent();
        PointsView pointsView = new PointsView(pointsComponent);
        HighPointsView highPointsView = new HighPointsView(pointsComponent);

        MainMenu mainMenu = new MainMenu(primaryStage); 

        LivesComponent livesComponent = new LivesComponent();
        LivesView livesView = new LivesView(livesComponent);

        AnchorPane root = new AnchorPane();
        root.setBackground(background);

        Image backgroundImage = new Image("TopLine.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(25);

        GameController gameController = new GameController();

        AnchorPane.setTopAnchor(backgroundImageView, 53.0);
        AnchorPane.setLeftAnchor(backgroundImageView, 0.0);
        AnchorPane.setRightAnchor(backgroundImageView, 0.0);

        root.getChildren().add(backgroundImageView);

        AnchorPane.setRightAnchor(mainMenu, 7.0);
        AnchorPane.setTopAnchor(mainMenu, 7.0);
        AnchorPane.setLeftAnchor(pointsView, 150.0);
        AnchorPane.setTopAnchor(pointsView, 0.0);
        AnchorPane.setLeftAnchor(highPointsView, 0.0);
        AnchorPane.setTopAnchor(highPointsView, 0.0);
        AnchorPane.setRightAnchor(livesView, 70.0);
        AnchorPane.setTopAnchor(livesView, 7.0);
        root.getChildren().addAll(mainMenu, pointsView, highPointsView, livesView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("StartGame");
        primaryStage.show();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case S: gameController.moveFelixDown(event.getCode());
                System.out.print("Key S pressed, lets move felix down!\n");
                break;
                case A: gameController.moveFelixLeft(event.getCode());
                System.out.print("Key A pressed, lets move felix left!\n");
                break;
                case D: gameController.moveFelixRight(event.getCode());
                System.out.print("Key D pressed, lets move felix right!\n");
                break;
                case W: gameController.moveFelixUp(event.getCode());
                System.out.print("Key W pressed, lets move felix up!\n");
                break;
                case Z:
                    /*Thread timerThread = new Thread(() -> {
                        long startTime = System.currentTimeMillis();
                        long duration = 0;
                        while (event.isPressed() && duration < 3000) {
                            duration = System.currentTimeMillis() - startTime;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (duration >= 3000) {
                            gameController.fixWindows(event.getCode());
                        }
                    });
                    timerThread.setDaemon(true); 
                    timerThread.start();*/
                    break;
                default:
                    break;
            }
        });
    }
}