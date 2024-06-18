package it.unibo.view.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.FixedWindowsComponent;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.PointsComponent;
import it.unibo.utilities.Constants;
import it.unibo.utilities.GameState;
import javafx.animation.AnimationTimer;
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
    private RalphView ralphView;
    private EntityFactory entityFactory = new EntityFactoryImpl(gameEngine.getGameController().getGamePerformance());
    private FelixView felixView;
    private AnchorPane root = new AnchorPane();
    private BrickView brickView;
    private Set<BrickView> bricksToPrint = new HashSet<>();
    private BirdView birdView;
    private CakeView cakeView;
    private Set<BirdView> birdsToPrint = new HashSet<>();
    private  Set<CakeView> cakesToPrint = new HashSet<>();
    private static final double WIDTH = 800.0;
    private static final double HEIGHT = 600.0;
    private static final double BACKGROUND_IMAGE_WIDTH = 800.0;
    private static final double BACKGROUND_IMAGE_HEIGHT = 25.0;
    private static final double BACKGROUND_IMAGE_TOP_ANCHOR = 53.0;
    private static final double BACKGROUND_IMAGE_LEFT_ANCHOR = 0.0;
    private static final double BACKGROUND_IMAGE_RIGHT_ANCHOR = 0.0;
    private static final double BUILDING_TOP_WIDTH_SCALE = 1.45;
    private static final double BUILDING_TOP_HEIGHT_SCALE = 1.45;
    private static final double BUILDING_TOP_TOP_ANCHOR = 78.0;
    private static final double BUILDING_TOP_TRANSLATE_X = 243.5;
    private static final double BUILDING_TOP_TRANSLATE_Y = 9.0;
    private static final double BUILDING_CENTRE_WIDTH_SCALE = 1.45;
    private static final double BUILDING_CENTRE_HEIGHT_SCALE = 1.45;
    private static final double BUILDING_CENTRE_TRANSLATE_X = 400.0;
    private static final double BUILDING_CENTRE_TRANSLATE_Y = 100.0;
    private static final double BUILDING_CENTRE_BOTTOM_ANCHOR = 75.0;
    private static final double POINTS_VIEW_LEFT_ANCHOR = 150.0;
    private static final double MAIN_MENU_RIGHT_ANCHOR = 7.0;
    private static final double MAIN_MENU_TOP_ANCHOR = 7.0;
    private static final double HIGH_POINTS_VIEW_LEFT_ANCHOR = 0.0;
    private static final double HIGH_POINTS_VIEW_TOP_ANCHOR = 0.0;
    private static final double LIVES_VIEW_RIGHT_ANCHOR = 70.0;
    private static final double LIVES_VIEW_TOP_ANCHOR = 7.0;
    private static final double POINTS_VIEW_TOP_ANCHOR = 0.0;
    private static final int Z_KEY_PRESS_DURATION_MS = 1500;
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);

        Pane blackPane = new Pane();
        blackPane.setPrefSize(WIDTH, HEIGHT); // Imposta le dimensioni dello sfondo nero alle dimensioni della finestra
        blackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        PointsComponent pointsComponent = new PointsComponent();
        PointsView pointsView = new PointsView(pointsComponent);
        HighPointsView highPointsView = new HighPointsView(pointsComponent);
        MainMenu mainMenu = new MainMenu(primaryStage);
        LivesComponent livesComponent = new LivesComponent(gameEngine.getGameController().getGamePerformance());
        LivesView livesView = new LivesView(livesComponent);
        Image backgroundImage = new Image("TopLine.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(BACKGROUND_IMAGE_HEIGHT);
        AnchorPane.setTopAnchor(backgroundImageView, BACKGROUND_IMAGE_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(backgroundImageView, BACKGROUND_IMAGE_LEFT_ANCHOR);
        AnchorPane.setRightAnchor(backgroundImageView, BACKGROUND_IMAGE_RIGHT_ANCHOR);

        // Immagine di sfondo 2 (building_top.png)
        Image buildingTopImage = new Image("building_top.png");
        ImageView buildingTopImageView = new ImageView(buildingTopImage);
        buildingTopImageView.setFitWidth(buildingTopImage.getWidth() 
                                        * BUILDING_TOP_WIDTH_SCALE); 
        buildingTopImageView.setFitHeight(buildingTopImage.getHeight() 
                                        * BUILDING_TOP_HEIGHT_SCALE);                                                                      // la sua dimensione originale
        AnchorPane.setTopAnchor(buildingTopImageView, BUILDING_TOP_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(buildingTopImageView, BACKGROUND_IMAGE_LEFT_ANCHOR);
        AnchorPane.setRightAnchor(buildingTopImageView, BACKGROUND_IMAGE_RIGHT_ANCHOR);
        buildingTopImageView.setTranslateX(BUILDING_TOP_TRANSLATE_X);
        buildingTopImageView.setTranslateY(BUILDING_TOP_TRANSLATE_Y);

        // Immagine di sfondo 3 (building_centre.png)
        Image newBackgroundImage = new Image("building_centre.png");
        ImageView buildingCentreImageView = new ImageView(newBackgroundImage);
        buildingCentreImageView.setFitWidth(newBackgroundImage.getWidth() 
                                            * BUILDING_CENTRE_WIDTH_SCALE);
        buildingCentreImageView.setFitHeight(newBackgroundImage.getHeight() 
                                             * BUILDING_CENTRE_HEIGHT_SCALE);
        buildingCentreImageView.setTranslateX(BUILDING_CENTRE_TRANSLATE_X);                                                                                          // verticalmente
        buildingCentreImageView.setTranslateY(BUILDING_CENTRE_TRANSLATE_Y);
        AnchorPane.setBottomAnchor(buildingCentreImageView, BUILDING_CENTRE_BOTTOM_ANCHOR);
        double centerX = (root.getWidth() 
            - buildingCentreImageView.getFitWidth()) / 2;                                                                              // orizzontalmente
        AnchorPane.setLeftAnchor(buildingCentreImageView, centerX);

        // Aggiunta delle immagini all'AnchorPane
        root.getChildren().addAll(blackPane, backgroundImageView, buildingTopImageView, buildingCentreImageView);
        AnchorPane.setRightAnchor(mainMenu, MAIN_MENU_RIGHT_ANCHOR);
        AnchorPane.setTopAnchor(mainMenu, MAIN_MENU_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(pointsView, POINTS_VIEW_LEFT_ANCHOR);
        AnchorPane.setTopAnchor(pointsView, POINTS_VIEW_TOP_ANCHOR);
        AnchorPane.setLeftAnchor(highPointsView, HIGH_POINTS_VIEW_LEFT_ANCHOR);
        AnchorPane.setTopAnchor(highPointsView, HIGH_POINTS_VIEW_TOP_ANCHOR);
        AnchorPane.setRightAnchor(livesView, LIVES_VIEW_RIGHT_ANCHOR);
        AnchorPane.setTopAnchor(livesView, LIVES_VIEW_TOP_ANCHOR);
        root.getChildren().addAll(mainMenu, pointsView, highPointsView, livesView);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
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
                    zKeyPressed = true;
                    Thread timerThread = new Thread(() -> {
                        try {
                            Thread.sleep(Z_KEY_PRESS_DURATION_MS);
                            if (zKeyPressed) {
                                Platform.runLater(() -> {
                                    Optional<Component> pointsComponentOptional = this.gameEngine.getGameController()
                                        .getFelixController().getFelix().getTheComponent(ComponentType.POINTS);
                                    Optional<Component> hitboxComponentOptional = this.gameEngine
                                            .getGameController().getFelixController().getFelix().getTheComponent(ComponentType.HITBOX);
                                    HitboxComponent hitComp = (HitboxComponent) hitboxComponentOptional.get();
                                    Optional<Pair<Double, Double>> windowPosition = hitComp.checkWindowsCollisions();

                                    if (windowPosition.isPresent()) {
                                        Optional<Entity> windowEntity = this.gameEngine.getGameController()
                                            .getGamePerformance().getWindows().stream()
                                                .filter(w -> w.getPosition().equals(windowPosition.get()))
                                                .findFirst();
                                        if (windowEntity.isPresent()) {
                                            FixedWindowsComponent fixedComponent = (FixedWindowsComponent) windowEntity.get()
                                                .getTheComponent(ComponentType.FIXEDWINDOWS).get();

                                            if (!fixedComponent.getFixed()) {
                                                this.gameEngine.getGameController().fixWindows(KeyCode.Z, windowPosition.get());
                                                fixedAnimation(windowPosition.get());
                                                pointsComponentOptional.ifPresent(c -> 
                                                    ((PointsComponent) c).addPoints(Constants.Felix.FIXED_WINDOW_POINTS));

                                                if (this.felixView != null) {
                                                    root.getChildren().remove(this.felixView.getImageView());
                                                }
                                                this.felixView = this.addFelixView();
                                            }
                                        }
                                    }
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

        new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (!GameState.getGameState().equals(GameState.PAUSED)) {
                    gameEngine.gameLoop(WindowGame.this);
                }
            }
        }.start();
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
            } else {
                root.getChildren().add(windowView.brokenWindow());
            }
        });
    }
    /**
     * Method for the animation of a window being fixed.
     * @param windowPosition the position of the window.
     */
    private void fixedAnimation(final Pair<Double, Double> windowPosition) {
        List<Entity> windows = gameEngine.getGameController().getGamePerformance().getWindows();

        windows.stream()
        .filter(w -> w.getPosition().equals(windowPosition))
        .findFirst()
        .ifPresent(window -> {
                    WindowsView windowView = new WindowsView(window.getPosition());
                    windowView.fixAnimation();
                    root.getChildren().add(windowView.fixedwindows());
            });
    }

    /**
     * Method that adds Felix to the main pane.
     * 
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
     * @return The created RalphView instance.
     */
    private RalphView addRalphView() {
        Entity ralph = this.gameEngine.getGameController().getRalphController().getRalph();
        RalphView ralphView = new RalphView(ralph);
        root.getChildren().add(ralphView.getStandingRalph());
        return ralphView;
    }
    /**
     * Method to add the brick view in the primary root.
     * 
     * @param brick the brick entity.
     * @return the view.
     */
    private BrickView addBrickView(final Entity brick) {
        BrickView brickView = new BrickView(brick);
        root.getChildren().add(brickView.getImageView());
        return brickView;
    }
    /**
     * Method to add the bird view in the primary root.
     * @return the view.
     */
    public void updateBird() {
        Set<Entity> birds = this.gameEngine.getGameController().getBirdController().getBirds();
        birds.forEach(bird -> {
            BirdView existingBirdView = birdsToPrint.stream()
                    .filter(view -> view.getBird().equals(bird))
                    .findFirst()
                    .orElse(null);
            if (existingBirdView == null) {
                BirdView newBirdView = new BirdView(bird);
                root.getChildren().add(newBirdView.getImageView());
                birdsToPrint.add(newBirdView);
            } else {
                existingBirdView.animateBird();
            }
        });
        birdsToPrint.removeIf(birdview -> {
            if (birdview == null) {
                return false;
            }
            if (!birds.contains(birdview.getBird())) {
                root.getChildren().remove(birdview.getImageView());
                return true;
            }
            return false;
        });
        birdsToPrint.forEach(BirdView::animateBird);
    }
    /**
     * Update cake view.
     */
    public void updateCake() {
        Set<Entity> cakes = this.gameEngine.getGameController().getCakeController().getCakes();
        cakes.forEach(cake -> {
            CakeView existCakeView = cakesToPrint.stream()
                    .filter(view -> view.getCake().equals(cake))
                    .findFirst()
                    .orElse(null);
            if (existCakeView == null) {
                CakeView newCakeView = new CakeView(cake);
                root.getChildren().add(newCakeView.getImageView());
                cakesToPrint.add(newCakeView);
            } else {
                existCakeView.animateCake();
            }
        });

        cakesToPrint.removeIf(cakeView -> {
            if (cakeView == null) {
                return false;
            }
            if (!cakes.contains(cakeView.getCake())) {
                root.getChildren().remove(cakeView.getImageView());
                return true;
            }
            return false;
        });

        cakesToPrint.forEach(CakeView::animateCake);
    }

    /**
     * Update brick view.
     */
    public void update() {
        // Ottieni i nuovi mattoni
        Set<Entity> bricks = this.gameEngine.getGameController().getBrickController().getBricks();
        bricks.forEach(brick -> {
            // Cerca se c'è già una BrickView per questo mattone
            BrickView existingBrickView = bricksToPrint.stream()
                    .filter(view -> view.getBrick().equals(brick))
                    .findFirst()
                    .orElse(null);

            if (existingBrickView == null) {
                // Se non esiste, crea una nuova BrickView e aggiungila al pane
                BrickView newBrickView = new BrickView(brick);
                root.getChildren().add(newBrickView.getImageView());
                bricksToPrint.add(newBrickView);
            } else {
                // Se esiste, aggiorna la posizione del BrickView esistente
                existingBrickView.animateBrick();
            }
        });

        // Rimuovi i BrickView non più presenti
        bricksToPrint.removeIf(brickView -> {
            if (!bricks.contains(brickView.getBrick())) {
                root.getChildren().remove(brickView.getImageView());
                return true;
            }
            return false;
        });

        // Anima Ralph
        ralphView.animateRalph();

        // Anima i mattoni
        bricksToPrint.forEach(BrickView::animateBrick);
    }

    // Altri metodi e classi, incluso il controller e il modello del gioco


    /**
     * Getter for the gameEngine.
     * @return the gameEngine.
     */
    public GameEngineImpl getGameEngine() {
        return this.gameEngine;
    }
}
