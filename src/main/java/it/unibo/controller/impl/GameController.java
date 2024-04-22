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
    private final InputManager inputManager;
    private final GameView gameView;
    private final GamePerformance gamePerformance;


    /**
     * Constructor for the GameController.
     */
    public GameController() {
        gamePerformance = new GamePerformanceImpl(this);
        ralphController = new RalphController();
        felixController = new FelixController();
        brickController = new BrickController();
        gameView = new GameView(this);
        inputManager = new InputManager(this.gamePerformance);
    }
 
    /**
     * Update the game, makes Ralph move and the bricks fall.
     */
    public void update() {
        ralphController.move();
        ralphController.throwBrickLeftArm(brickController.getBricks());
        ralphController.throwBrickRightArm(brickController.getBricks());
        brickController.fallBricks();
    }
    /**
     * Controls if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean gameOver() {
        return this.felixController.isAlive();
    } 
}
