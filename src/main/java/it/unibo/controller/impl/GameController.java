package it.unibo.controller.impl;

import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import javafx.scene.input.KeyCode;

/**
 * Main controller of the game.
 */
public class GameController {

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
        this.collisionManager = new CollisionManager(this.gamePerformance.getEntity());
        this.birdController = new BirdController(gamePerformance);
        this.cakeController = new CakeController(gamePerformance);
        this.level = 1; //level will be set by the settings view, if is not set it will be 1
    }
    /**
     * Update the game, makes Ralph move and the bricks fall.
     */
    public void update() {
        this.brickController.fallBricks();
        this.ralphController.update(brickController.getBricks());
        this.cakeController.update();
        this.birdController.update();
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
        return this.windowsController.isWon();
    }
    /**
     * Method to move the player after receiving keyboard input "down".
     * @param e the keyCode
     */
    public void moveFelixDown(final KeyCode e){
        this.felixController.moveDown();
        //System.out.print("game controller called\n");
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "left".
     * @param e the keyCode
     */
    public void moveFelixLeft(final KeyCode e){
        this.felixController.moveLeft();
        //System.out.print("game controller called\n");
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "right".
     * @param e the KeyCode
     */
    public void moveFelixRight(final KeyCode e){ 
        this.felixController.moveRight();
        //System.out.print("game controller called\n");
        this.gamePerformance.addKey(e);
    }
    /**
     * Method to move the player after receiving keyboard input "up".
     * @param e the KeyCode
     */
    public void moveFelixUp(final KeyCode e){
        this.felixController.moveUp();
        //System.out.print("game controller called\n");
        this.gamePerformance.addKey(e);
    }

    public void fixWindows(final KeyCode e){
        this.felixController.fix();
        //System.out.print("game controller called\n");
        this.gamePerformance.addKey(e);
    }
}
