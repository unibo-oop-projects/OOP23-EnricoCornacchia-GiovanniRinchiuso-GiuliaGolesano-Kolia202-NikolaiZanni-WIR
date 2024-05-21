package it.unibo.view.impl;

import java.util.Set;

import it.unibo.controller.impl.BirdController;
import it.unibo.controller.impl.GameController;
import it.unibo.controller.impl.WindowsController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constaints;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    @SuppressWarnings("unused")
    private Stage primaryStage;
    private boolean zKeyPressed = false;
    @SuppressWarnings("unused")
    private BirdController birdController;
    GameController gameController = new GameController();
    GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
    WindowsController windowsController = new WindowsController(gamePerformance);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.birdController = new BirdController(gamePerformance);

        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        PointsComponent pointsComponent = new PointsComponent();
        PointsView pointsView = new PointsView(pointsComponent);
        HighPointsView highPointsView = new HighPointsView(pointsComponent);

        MainMenu mainMenu = new MainMenu(primaryStage);

        LivesComponent livesComponent = new LivesComponent(gamePerformance);
        LivesView livesView = new LivesView(livesComponent);

        AnchorPane root = new AnchorPane();
        root.setBackground(background);

        Image backgroundImage = new Image("TopLine.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(25);

        switch (this.gameController.getLevel()) {
            case 1: addWindowsGrid(root, Constaints.Windows.BROKEN_1); 
                break;
            case 2: addWindowsGrid(root, Constaints.Windows.BROKEN_2); 
                break;
            case 3: addWindowsGrid(root, Constaints.Windows.BROKEN_3); 
                break;
            case 4: addWindowsGrid(root, Constaints.Windows.BROKEN_4); 
                break;
            default:
                break;
        }

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
                case S:
                    gameController.moveFelixDown(event.getCode());
                    // System.out.print("Key S pressed, lets move felix down!\n");
                    break;
                case A:
                    gameController.moveFelixLeft(event.getCode());
                    // System.out.print("Key A pressed, lets move felix left!\n");
                    break;
                case D:
                    gameController.moveFelixRight(event.getCode());
                    // System.out.print("Key D pressed, lets move felix right!\n");
                    break;
                case W:
                    gameController.moveFelixUp(event.getCode());
                    // System.out.print("Key W pressed, lets move felix up!\n");
                    break;
                // AGGIUNGERE COLLISSIONE CON FINESTRA E COORDINATE FINESTRA CHE STA SISTEMANDO
                case Z:
                    zKeyPressed = true;

                    Thread timerThread = new Thread(() -> {
                        try {
                            Thread.sleep(5000);

                            if (zKeyPressed) {
                                gameController.fixWindows(event.getCode());
                                // System.out.print("Key Z pressed for 5 seconds!\n");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            zKeyPressed = false;
                        }
                    });
                    timerThread.start();

                    scene.setOnKeyReleased(releasedEvent -> {
                        if (releasedEvent.getCode() == KeyCode.Z) {
                            zKeyPressed = false;
                        }
                    });
                    break;

                default:
                    break;
            }
        });
    }
    /**
     * Method that creates a windows grid on the main pane.
     * @param root
     * @param broken the number of broken windows.
     */
    private void addWindowsGrid(final AnchorPane root, final int broken) {
        Set<Entity> windows = windowsController.windowsGrid(broken);
        windows.forEach(window -> {
            WindowsView windowView = new WindowsView();
            windowView.getImageView().setLayoutX(window.getPosition().getX());
            windowView.getImageView().setLayoutY(window.getPosition().getY());
            root.getChildren().add(windowView.getImageView());
        });
    }
}