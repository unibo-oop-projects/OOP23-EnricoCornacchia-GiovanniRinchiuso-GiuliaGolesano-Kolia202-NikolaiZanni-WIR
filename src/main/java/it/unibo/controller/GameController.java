package it.unibo.controller;

/**
 * Main controller of the game.
 */
public class GameController {

    private RalphController ralphController;
    private FelixController felixController;
    private BrickController brickController;


    /**
     * Constructor for the GameController.
     */
    public GameController() {
        ralphController = new RalphController();
        felixController = new FelixController();
        brickController = new BrickController();
    }
 
    /**
     * Update the game, makes Ralph move and the bricks fall.
     */
    public void update() {
        ralphController.move();
        ralphController.throwBrickLeftArm();
        ralphController.throwBrickRightArm();
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