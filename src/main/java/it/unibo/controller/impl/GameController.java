package it.unibo.controller.impl;

import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;

/**
 * Main controller of the game.
 */
public class GameController {

    private final RalphController ralphController;
    private final FelixController felixController;
    private final BrickController brickController;
    private final WindowsController windowsController;
    private final CollisionManager collisionManager;
    private final InputManager inputManager;
    private final GamePerformance gamePerformance;
    private int level;
    /**
     * Constructor for the GameController.
     */
    public GameController() {
        gamePerformance = new GamePerformanceImpl(this);
        ralphController = new RalphController(this.gamePerformance);
        felixController = new FelixController(this.gamePerformance);
        brickController = new BrickController(this.gamePerformance);
        windowsController = new WindowsController(gamePerformance);
        collisionManager = new CollisionManager(this.gamePerformance.getEntity());
        inputManager = new InputManager(this.gamePerformance);
        level = 1; //level will be set by the settings view, if is not set it will be 1
    }
    /**
     * Update the game, makes Ralph move and the bricks fall.
     */
    public void update() {
        brickController.fallBricks();
        ralphController.update(brickController.getBricks());
        gamePerformance.update();
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


}
