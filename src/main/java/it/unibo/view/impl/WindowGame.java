package it.unibo.view.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.FixWindowsComponent;
import it.unibo.model.impl.FixedWindowsComponent;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constants;
import javafx.application.Application;
import javafx.application.Platform;
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
import it.unibo.core.impl.GameEngineImpl;

/**
 * WindowGame represents the window of the game.
 * This class can be extended to customize the game window.
 */
public class WindowGame extends Application {
    private Stage primaryStage;
    private boolean zKeyPressed = false;
    private GameEngineImpl gameEngine = new GameEngineImpl();
    @SuppressWarnings("unused")
    private Map<Entity, CakeView> cakeViews = new HashMap<>();
    @SuppressWarnings("unused")
    private Map<Entity, BirdView> birdViews = new HashMap<>();
    private RalphView ralphView;
    private FelixView felixView;
    private AnchorPane root = new AnchorPane();
    /*private static final double WIDTH = 800.0;
    private static final double HEIGHT = 600.0;
    private static final double BACKGROUND_IMAGE_HEIGHT = 25.0;
    private static final double BUILDING_TOP_WIDTH_SCALE = 1.45;
    private static final double BUILDING_TOP_HEIGHT_SCALE = 1.45;
    private static final double BUILDING_TOP_TOP_ANCHOR = 78.0;
    private static final double BUILDING_TOP_TRANSLATE_X = 243.5;
    private static final double BUILDING_TOP_TRANSLATE_Y = 9.0;
    private static final double BUILDING_CENTRE_WIDTH_SCALE = 1.45;
    private static final double BUILDING_CENTRE_HEIGHT_SCALE = 1.45;
    private static final double BUILDING_CENTRE_TRANSLATE_X = 400.0;
    private static final double BUILDING_CENTRE_BOTTOM_ANCHOR = 75.0;
    private static final double POINTS_VIEW_LEFT_ANCHOR = 150.0;
    private static final double MAIN_MENU_RIGHT_ANCHOR = 7.0;
    private static final double MAIN_MENU_TOP_ANCHOR = 7.0;
    private static final double HIGH_POINTS_VIEW_LEFT_ANCHOR = 0.0;
    private static final double HIGH_POINTS_VIEW_TOP_ANCHOR = 0.0;
    private static final double LIVES_VIEW_RIGHT_ANCHOR = 70.0;
    private static final double LIVES_VIEW_TOP_ANCHOR = 7.0;
    private static final int Z_KEY_PRESS_DURATION_MS = 3000;*/

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.gameEngine.getGameController().getCakeController().scheduleCakeCreation();
        this.gameEngine.getGameController().getBirdController().scheduleBirdCreation();
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);

        Pane blackPane = new Pane();
        blackPane.setPrefSize(800, 600); // Imposta le dimensioni dello sfondo nero alle dimensioni della finestra
        blackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        PointsComponent pointsComponent = new PointsComponent();
        PointsView pointsView = new PointsView(pointsComponent);
        HighPointsView highPointsView = new HighPointsView(pointsComponent);
        MainMenu mainMenu = new MainMenu(primaryStage);
        LivesComponent livesComponent = new LivesComponent(gameEngine.getGameController().getGamePerformance());
        LivesView livesView = new LivesView(livesComponent);
        Image backgroundImage = new Image("TopLine.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(25);
        AnchorPane.setTopAnchor(backgroundImageView, 53.0);
        AnchorPane.setLeftAnchor(backgroundImageView, 0.0);
        AnchorPane.setRightAnchor(backgroundImageView, 0.0);

        // Immagine di sfondo 2 (building_top.png)
        Image buildingTopImage = new Image("building_top.png");
        ImageView buildingTopImageView = new ImageView(buildingTopImage);
        buildingTopImageView.setFitWidth(buildingTopImage.getWidth() * 1.45); 
        buildingTopImageView.setFitHeight(buildingTopImage.getHeight() * 1.45);                                                                      // la sua dimensione originale
        AnchorPane.setTopAnchor(buildingTopImageView, 78.0);
        AnchorPane.setLeftAnchor(buildingTopImageView, 0.0);
        AnchorPane.setRightAnchor(buildingTopImageView, 0.0);
        buildingTopImageView.setTranslateX(243.5);
        buildingTopImageView.setTranslateY(9);

        // Immagine di sfondo 3 (building_centre.png)
        Image newBackgroundImage = new Image("building_centre.png");
        ImageView buildingCentreImageView = new ImageView(newBackgroundImage);
        buildingCentreImageView.setFitWidth(newBackgroundImage.getWidth() * 1.45);
        buildingCentreImageView.setFitHeight(newBackgroundImage.getHeight() * 1.45);
        buildingCentreImageView.setTranslateX(400);
        buildingCentreImageView.setTranslateY(100);

        AnchorPane.setBottomAnchor(buildingCentreImageView, 75.0);
        double centerX = (root.getWidth() - buildingCentreImageView.getFitWidth()) / 2;                                                                              // orizzontalmente
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

        switch (this.gameEngine.getGameController().getLevel()) {
            case 1:
                addWindowsGrid(Constants.Windows.BROKEN_1);
                break;
            case 2:
                addWindowsGrid(Constants.Windows.BROKEN_2);
                break;
            case 3:
                addWindowsGrid(Constants.Windows.BROKEN_3);
                break;
            default:
                break;
        }
        this.felixView = this.addFelixView();
        this.ralphView = this.addRalphView();
      /*   Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
          //  Entity currentCake = this.gameController.getCakeController().getCake();
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
        timeline.play(); */

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case S:
                    gameEngine.getGameController().moveFelixDown(event.getCode());
                    felixView.animateFelix();
                    break;
                case A:
                    gameEngine.getGameController().moveFelixLeft(event.getCode());
                    felixView.animateFelix();
                    break;
                case D:
                    gameEngine.getGameController().moveFelixRight(event.getCode());
                    felixView.animateFelix();
                    break;
                case W:
                    gameEngine.getGameController().moveFelixUp(event.getCode());
                    felixView.animateFelix();
                    break;
                case Z:
                    zKeyPressed=true;
                    gameEngine.getGameController().getGamePerformance().addKey(KeyCode.Z);
                    Optional<Component> fixComponentOptional = this.gameEngine.getGameController().getFelixController()
                                    .getFelix().getTheComponent(ComponentType.FIXWINDOWS);
                    Optional<Component> hitboxComponentOptional = this.gameEngine.getGameController().getFelixController()
                                    .getFelix().getTheComponent(ComponentType.HITBOX);
                    HitboxComponent hitComp = (HitboxComponent) hitboxComponentOptional.get();
                    Optional<Pair<Double, Double>> windowPosition = hitComp.checkWindowsCollisions(); 


                    Thread timerThread = new Thread(() -> {
                        try {
                            Thread.sleep(1500);

                            if (zKeyPressed) {
                                if (fixComponentOptional.isPresent() && hitboxComponentOptional.isPresent() &&
                                    fixComponentOptional.get() instanceof FixWindowsComponent &&
                                    hitboxComponentOptional.get() instanceof HitboxComponent) {
                                    
                                    windowPosition.ifPresent(pos -> {
                                        Platform.runLater(() -> {
                                            this.gameEngine.getGameController().fixWindows(event.getCode(), pos);
                                            fixedAnimation(pos);
                                            this.felixView = this.addFelixView();
                                        });
                                    });
                                }
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
        //this.gameEngine.gameLoop(this);
    }
    /**
     * Method that creates a windows grid on the main pane.
     * 
     * @param broken the number of broken windows.
     */
    private void addWindowsGrid(final int broken) {
        Set<Entity> entities = new HashSet<>();
        entities = this.gameEngine.getGameController().getWindowsController().windowsGrid(broken);

        entities.forEach(w -> {
            WindowsView windowView = new WindowsView(w.getPosition());
            FixedWindowsComponent fixComp = (FixedWindowsComponent) w.getTheComponent(ComponentType.FIXEDWINDOWS).get();
            if (fixComp.getFixed()) {
                root.getChildren().add(windowView.fixedwindows());
            }else
                root.getChildren().add(windowView.brokenWindow());
        });
    }
    /**
     * Method for the animation of a window being fixed.
     */
    private void fixedAnimation(final Pair<Double, Double> windowPosition) {
        List<Entity> windows = gameEngine.getGameController().getGamePerformance().getWindows();
    
        windows.stream()
            .filter(w -> {
                boolean positionMatches = w.getPosition().equals(windowPosition);
                if (positionMatches) {
                    System.out.println("Position matches for window: " + windowPosition);
                }
                return positionMatches;
            })
            .map(w -> w.getTheComponent(ComponentType.FIXEDWINDOWS))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter(c -> {
                boolean isFixedWindowsComponent = c instanceof FixedWindowsComponent;
                if (!isFixedWindowsComponent) {
                    System.out.println("Component is not an instance of FixedWindowsComponent");
                }
                return isFixedWindowsComponent;
            })
            .map(c -> (FixedWindowsComponent) c)
            .filter(fixedWindowsComponent -> {
                boolean isNotFixed = !fixedWindowsComponent.getFixed();
                if (!isNotFixed) {
                    System.out.println("Window is already fixed " + fixedWindowsComponent.getFixed());
                }
                return isNotFixed;
            })
            .findFirst()
            .ifPresent(windowComponent -> {
                System.out.println("Fixing window at position: " + windowPosition);
                WindowsView windowView = new WindowsView(windowPosition);
                windowView.fixAnimation();
                Platform.runLater(() -> root.getChildren().add(windowView.fixedwindows())); // Ensure this runs on the JavaFX application thread
            });
    }
    
    

    /**
     * Method that adds Felix to the main pane.
     * 
     * @param root the main pane.
     * @return the FelixView.
     */
    private FelixView addFelixView() {
        Entity felix = this.gameEngine.getGameController().getFelixController().getFelix();
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
    private RalphView addRalphView() {
        Entity ralph = this.gameEngine.getGameController().getRalphController().getRalph();
        RalphView ralphView = new RalphView(ralph);
        root.getChildren().add(ralphView.getStandingRalph());
        return ralphView;
    }
    private BrickView addBrickView(final Entity brick) {
        BrickView brickView = new BrickView(brick);
        root.getChildren().add(brickView.getImageView());
        return brickView;
    }
    public void update() {
        Set<Entity> bricks = this.gameEngine.getGameController().getBrickController().getBricks();
        Set<BrickView> bricksToPrint = new HashSet<>();
        bricks.forEach(b -> {
            BrickView brickView = this.addBrickView(b);
            bricksToPrint.add(brickView);
        });
    }

    public GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }
    
}
