package it.unibo.controller;

/**
 * Main controller of the game.
 */
public class GameController {

    private final RalphController ralphController;
    private final FelixController felixController;
    private final BrickController brickController;


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
