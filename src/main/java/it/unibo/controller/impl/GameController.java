package it.unibo.controller.impl;

import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.view.impl.GameView;

/**
 * Main controller of the game.
 */
public class GameController {

    private final RalphController ralphController;
    private final FelixController felixController;
    private final BrickController brickController;
    private final CollisionManager collisionManager;
    private final InputManager inputManager;
    private final GameView gameView;
    private final GamePerformance gamePerformance;


    /**
     * Constructor for the GameController.
     */
    public GameController() {
        gamePerformance = new GamePerformanceImpl(this);
        ralphController = new RalphController(gamePerformance.getLevel(), this.gamePerformance);
        felixController = new FelixController(this.gamePerformance);
        brickController = new BrickController(this.gamePerformance);
        collisionManager = new CollisionManager(this.gamePerformance.getEntity());
        gameView = new GameView(this);
        inputManager = new InputManager(this.gamePerformance);
    }
 
    /**
     * Update the game, makes Ralph move and the bricks fall.
     */
    public void update() {
        gamePerformance.update();
    }
    /**
     * Controls if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean gameOver() {
        return this.gamePerformance.isLost();
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
}
