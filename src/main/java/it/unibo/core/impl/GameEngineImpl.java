package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;
import it.unibo.view.impl.GameView;
import it.unibo.controller.impl.GameController;

/**
 * Implementation of the game engine.
 */

public class GameEngineImpl implements GameEngine {

    private final int period;
    private final GameController gameController;
    private final GameView gameView;
    /**
     * Constructor for the game engine.
     */
    public GameEngineImpl() {
        period = 10;
        gameController = new GameController();
        gameView = new GameView(gameController);

    }

    /**
     * Main loop of the game.
     */
    @Override
    public void mainLoop() {
        long current = System.currentTimeMillis();
        while (gameController.gameIsNotOver()) {
            if (System.currentTimeMillis() - current > this.period) {
                this.update();
                this.draw();
                current = System.currentTimeMillis();
            }
        }
    }
    /**
     * Draw the game.
     */
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Draw'");
    }

    /**
     * Update the game.
     */
    @Override
    public void update() {
        gameController.update();
    }
    /**
     * Wait for the next frame.
     */
    @Override
    public void waitForNextFrame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'WaitForNextFrame'");
    }
}
