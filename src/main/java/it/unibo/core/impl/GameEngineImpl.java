package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;
import it.unibo.controller.GameController;
import it.unibo.utilities.GameState;


/**
 * Implementation of the game engine.
 */

public class GameEngineImpl implements GameEngine {

    private int period;
    private GameController gameController;
    /**
     * Constructor for the game engine.
     */
    public GameEngineImpl() {
        period = 10;
    }

    /**
     * Main loop of the game.
     */
    @Override
    public void mainLoop() {
        gameController = new GameController();
        long current = System.currentTimeMillis();
        while (!gameController.gameOver()) {
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
        switch (GameState.getGameState()) {
            case HOME:
                //draw preview
                break;
            case PLAYING:
                //draw settingsview
                break;
            case PAUSED:
                //draw game
                break;
            case GAMEOVER:
                //draw pause
                break;
            case SETTINGS:
                //draw endgame
                break;
            default:
                break;
        }
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
