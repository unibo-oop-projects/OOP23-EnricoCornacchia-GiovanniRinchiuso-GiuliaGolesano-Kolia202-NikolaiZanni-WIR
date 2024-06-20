package it.unibo.controller.impl;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.FixWindowsComponent;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.ImmortalityComponent;
import it.unibo.model.impl.LivesComponent;
import it.unibo.model.impl.StopRalphComponent;
import it.unibo.model.impl.ThrowBrickComponent;
import javafx.scene.input.KeyCode;
import it.unibo.controller.api.Controller;
/**
 * Main controller of the game.
 */
public class GameController implements Controller {
    private final RalphController ralphController;
    private final FelixController felixController;
    private final BrickController brickController;
    private final WindowsController windowsController;
    private final CollisionManager collisionManager;
    private final GamePerformance gamePerformance;
    private final BirdController birdController;
    private final CakeController cakeController;
    private int level;
    /**
     * Constructor for the GameController.
     */
    public GameController() {
        this.gamePerformance = new GamePerformanceImpl(this);
        this.ralphController = new RalphController(this.gamePerformance);
        this.felixController = new FelixController(this.gamePerformance);
        this.brickController = new BrickController(this.gamePerformance);
        this.windowsController = new WindowsController(gamePerformance);
        this.birdController = new BirdController(gamePerformance);
        this.cakeController = new CakeController(gamePerformance);
        this.collisionManager = new CollisionManager(this.gamePerformance);
        this.level = 1; //level will be set by the settings view, if is not set it will be 1
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.brickController.update();
        this.ralphController.update(brickController.getBricks());
        this.birdController.update();
        this.cakeController.update();
        ((ImmortalityComponent) this.felixController.getFelix()
                                    .getTheComponent(ComponentType.IMMORTALITY).get())
                                    .chekStopImmortality((LivesComponent) this.felixController.getFelix()
                                                         .getTheComponent(ComponentType.LIFE).get());
        ((StopRalphComponent) this.ralphController.getRalph()
                                    .getTheComponent(ComponentType.STOPRALPH).get())
                                    .checkUnlockRalph((ThrowBrickComponent) this.ralphController.getRalph()
                                                        .getTheComponent(ComponentType.THROWBRICK).get());
        this.collisionManager.check();
    }
    /**
     * Controls if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean gameIsNotOver() {
        return this.felixController.isAlive();
    }
    /**
     * Getter for the Ralph controller.
     * @return the Ralph controller.
     */
    public RalphController getRalphController() {
        return this.ralphController;
    } 
    /**
     * Getter for the Felix controller.
     * @return the Felix controller.
     */
    public FelixController getFelixController() {
        return this.felixController;
    }
    /**
     * Getter for the Brick controller.
     * @return the Brick controller.
     */
    public BrickController getBrickController() {
        return this.brickController;
    }
    /**
     * Getter for the Windows controller.
     * @return the Windows controller.
     */
    public WindowsController getWindowsController() {
        return this.windowsController;
    }
    /**
     * Getter for the Bird controller.
     * @return the Bird Controller.
     */
    public BirdController getBirdController() {
        return this.birdController;
    }
    /**
     * Getter for the Cake controller.
     * @return the Cake controller.
     */
    public CakeController getCakeController() {
        return this.cakeController;
    }
    /**
     * Getter for the level.
     * @return the level.
     */
    public int getLevel() {
        return this.level;
    }
    /**
     * Getter for the level.
     * @return the level.
     */
    public GamePerformance getGamePerformance() {
        return this.gamePerformance;
    }
    /**
     * Setter for the level.
     * @param level the new level.
     */
    public void setLevel(final int level) {
        this.level = level;
    }
    /**
     * Getter for the winning condition.
     * @return true if the game is won, false otherwise.
     */
    public boolean isWin() {
        return this.windowsController.won();
    }
    /**
     * Method to move the player after receiving keyboard input "down".
     * @param e the keyCode
     */
    public void moveFelixDown(final KeyCode e) {
        this.felixController.moveDown();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "left".
     * @param e the keyCode
     */
    public void moveFelixLeft(final KeyCode e) {
        this.felixController.moveLeft();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "right".
     * @param e the KeyCode
     */
    public void moveFelixRight(final KeyCode e) { 
        this.felixController.moveRight();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "up".
     * @param e the KeyCode
     */
    public void moveFelixUp(final KeyCode e) {
        this.felixController.moveUp();
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to fix a window calling the hiboxcomponent of felix to know the position.
     * @param e the KeyCode
     * @param pos the position of the window to fix.
     */
    public void fixWindows(final KeyCode e, final Pair<Double, Double> pos) {
        final FixWindowsComponent fixComp = (FixWindowsComponent) this.felixController.getFelix()
                                                                                .getTheComponent(ComponentType.FIXWINDOWS)
                                                                                .get();
        fixComp.fixing(pos, this.gamePerformance);
        this.gamePerformance.addKey(e);
    }
}
