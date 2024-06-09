package it.unibo.view.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.controller.impl.BirdController;
import it.unibo.controller.impl.GameController;
import it.unibo.controller.impl.WindowsController;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.FixedWindowsComponent;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Map<Entity, CakeView> cakeViews = new HashMap<>();
    private Map<Entity, BirdView> birdViews = new HashMap<>();

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.gameController.getCakeController().scheduleCakeCreation();
        this.gameController.getBirdController().scheduleBirdCreation();
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.birdController = new BirdController(gamePerformance);

        Pane blackPane = new Pane();
        blackPane.setPrefSize(800, 600); // Imposta le dimensioni dello sfondo nero alle dimensioni della finestra
        blackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        PointsComponent pointsComponent = new PointsComponent();
        PointsView pointsView = new PointsView(pointsComponent);
        HighPointsView highPointsView = new HighPointsView(pointsComponent);

        MainMenu mainMenu = new MainMenu(primaryStage);

        LivesComponent livesComponent = new LivesComponent(gamePerformance);
        LivesView livesView = new LivesView(livesComponent);

        AnchorPane root = new AnchorPane();
        // Immagine di sfondo 1 (TopLine.png)
        Image backgroundImage = new Image("TopLine.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(25);
        AnchorPane.setTopAnchor(backgroundImageView, 53.0);
        AnchorPane.setLeftAnchor(backgroundImageView, 0.0);
        AnchorPane.setRightAnchor(backgroundImageView, 0.0);

        // Immagine di sfondo 2 (building_top.png)
        Image buildingTopImage = new Image("building_top.png");
        ImageView buildingTopImageView = new ImageView(buildingTopImage);
        buildingTopImageView.setFitWidth(buildingTopImage.getWidth() * 1.45); // Imposta la larghezza doppia
        buildingTopImageView.setFitHeight(buildingTopImage.getHeight() * 1.45);// Imposta l'altezza dell'immagine come
                                                                               // la sua dimensione originale
        AnchorPane.setTopAnchor(buildingTopImageView, 78.0); // Sposta l'immagine verso il basso di 3/4 cm
        AnchorPane.setLeftAnchor(buildingTopImageView, 0.0);
        AnchorPane.setRightAnchor(buildingTopImageView, 0.0);
        buildingTopImageView.setTranslateX(243.5); // Sposta l'immagine di 100 unità lungo l'asse x
        buildingTopImageView.setTranslateY(9); // Sposta l'immagine di 50 unità lungo l'asse y

        // Immagine di sfondo 3 (building_centre.png)
        Image newBackgroundImage = new Image("building_centre.png");
        ImageView buildingCentreImageView = new ImageView(newBackgroundImage);
        buildingCentreImageView.setFitWidth(newBackgroundImage.getWidth() * 1.45); // Imposta la larghezza doppia
        buildingCentreImageView.setFitHeight(newBackgroundImage.getHeight() * 1.45); // Imposta l'altezza doppia
        buildingCentreImageView.setTranslateX(400); // Sposta l'immagine di 100 unità lungo l'asse x
        buildingCentreImageView.setTranslateY(100);
        AnchorPane.setBottomAnchor(buildingCentreImageView, 75.0); // Sposta l'immagine verso il basso di 3/4 cm
        double centerX = (root.getWidth() - buildingCentreImageView.getFitWidth()) / 2; // Centra l'immagine
                                                                                        // orizzontalmente
        AnchorPane.setLeftAnchor(buildingCentreImageView, centerX);

        // Aggiunta delle immagini all'AnchorPane
        root.getChildren().addAll(blackPane, backgroundImageView, buildingTopImageView, buildingCentreImageView);

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
        primaryStage.setTitle("Wreck-it Ralph");
        primaryStage.show();

        switch (this.gameController.getLevel()) {
            case 1:
                addWindowsGrid(root, Constants.Windows.BROKEN_1);
                break;
            case 2:
                addWindowsGrid(root, Constants.Windows.BROKEN_2);
                break;
            case 3:
                addWindowsGrid(root, Constants.Windows.BROKEN_3);
                break;
            case 4:
                addWindowsGrid(root, Constants.Windows.BROKEN_4);
                break;
            default:
                break;
        }
        FelixView felixView = this.addFelixView(root);
        RalphView ralphView = this.addRalphView(root);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Entity currentCake = this.gameController.getCakeController().getCake();
            cakeViews.entrySet().removeIf(entry -> {
                Entity cake = entry.getKey();
                CakeView cakeView = entry.getValue();
                if (cake != currentCake) {
                    root.getChildren().remove(cakeView.getImageView());
                    return true;
                }
                return false;
            });
            if (currentCake != null && !cakeViews.containsKey(currentCake)) {
                CakeView cakeView = new CakeView(currentCake);
                cakeViews.put(currentCake, cakeView);
                root.getChildren().add(cakeView.getStandingCake());
            }
            cakeViews.values().forEach(CakeView::animeteCake);

            Entity currentBird = this.gameController.getBirdController().getBird();
            birdViews.entrySet().removeIf(entry -> {
                Entity bird = entry.getKey();
                BirdView birdView = entry.getValue();
                if (bird != currentBird) {
                    root.getChildren().remove(birdView.getImageView());
                    return true;
                }
                return false;
            });
            if (currentBird != null && !birdViews.containsKey(currentBird)) {
                BirdView birdView = new BirdView(currentBird);
                birdViews.put(currentBird, birdView);
                root.getChildren().add(birdView.getStandingBird());
            }
            birdViews.values().forEach(BirdView::animateBird);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        /*
         * Entity w = this.entityFactoryImpl.createWindows(new
         * Pair<Double,Double>(100.0, 100.0), new Random().nextBoolean());
         * System.out.println(w.getPosition());
         * WindowsView windowView = new WindowsView(w.getPosition());
         * FixedWindowsComponent fixComp = (FixedWindowsComponent)
         * w.getTheComponent(ComponentType.FIXEDWINDOWS).get();
         * if(fixComp.getFixed()) root.getChildren().add(windowView.fixedwindows());
         * else root.getChildren().add(windowView.brokenWindow());
         */

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case S:
                    gameController.moveFelixDown(event.getCode());
                    felixView.animateFelix();
                    break;
                case A:
                    gameController.moveFelixLeft(event.getCode());
                    felixView.animateFelix();
                    break;
                case D:
                    gameController.moveFelixRight(event.getCode());
                    felixView.animateFelix();
                    break;
                case W:
                    gameController.moveFelixUp(event.getCode());
                    felixView.animateFelix();
                    break;
                case Z:
                    zKeyPressed = true;
                    Thread timerThread = new Thread(() -> {
                        try {
                            Thread.sleep(3000);

                            if (zKeyPressed) {
                                HitboxComponent hitComp = (HitboxComponent) this.gameController.getFelixController()
                                        .getFelix().getTheComponent(ComponentType.FIXWINDOWS).get();
                                Optional<Pair<Double, Double>> windowPosition = hitComp.checkWindowsCollisions();
                                windowPosition.ifPresent(pos -> {
                                    gameController.getFelixController().fixWindow(windowPosition.get());
                                    System.out.print(
                                            "Key Z pressed for 3 seconds, window fixed at position: " + pos + "\n");
                                });
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
     * 
     * @param root
     * @param broken the number of broken windows.
     */
    private void addWindowsGrid(final AnchorPane root, final int broken) {
        Set<Entity> entities = new HashSet<>();
        entities = this.gameController.getWindowsController().windowsGrid(broken);

        entities.forEach(w -> {
            WindowsView windowView = new WindowsView(w.getPosition());
            FixedWindowsComponent fixComp = (FixedWindowsComponent) w.getTheComponent(ComponentType.FIXEDWINDOWS).get();
            if (fixComp.getFixed())
                root.getChildren().add(windowView.fixedwindows());
            else
                root.getChildren().add(windowView.brokenWindow());
        });
    }

    /**
     * Method that adds Felix to the main pane.
     * 
     * @param root the main pane.
     * @return the FelixView.
     */
    private FelixView addFelixView(final AnchorPane root) {
        Entity felix = this.gameController.getFelixController().getFelix();
        FelixView felixView = new FelixView(felix);
        root.getChildren().add(felixView.getStandingFelix());
        return felixView;
    }

    /**
     * Creates and adds a RalphView to the specified root pane.
     *
     * @param root The root pane to add the RalphView to.
     * @return The created RalphView instance.
     */
    private RalphView addRalphView(final AnchorPane root) {
        Entity ralph = this.gameController.getRalphController().getRalph();
        RalphView ralphView = new RalphView(ralph);
        root.getChildren().add(ralphView.getStandingRalph());
        return ralphView;
    }

    private BrickView addBrickView(final AnchorPane root, final Entity brick) {
        BrickView brickView = new BrickView(brick);
        root.getChildren().add(brickView.getImageView());
        return brickView;
    }

    public void update(final AnchorPane root) {
        Set<Entity> bricks = this.gameController.getBrickController().getBricks();
        Set<BrickView> bricksToPrint = new HashSet<>();
        bricks.forEach(b -> {
            BrickView brickView = this.addBrickView(root, b);
            bricksToPrint.add(brickView);
        });
    }
}
