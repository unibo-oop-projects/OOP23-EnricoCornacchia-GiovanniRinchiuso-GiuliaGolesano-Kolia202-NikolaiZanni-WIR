package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;
import it.unibo.utilities.GameState;
import it.unibo.controller.impl.GameController;
import it.unibo.view.impl.WindowGame;

/**
 * Implementation of the game engine.
 */

public class GameEngineImpl implements GameEngine {

    private final int period;
    private final GameController gameController;
    /**
     * Constructor for the game engine.
     */
    public GameEngineImpl() {
        this.period = 20;
        this.gameController = new GameController();
    }
    /**
     *Loop of the game.
     */
    @Override
    public void gameLoop(final WindowGame windowGame) {
        System.out.println("Game loop started");
        long current = System.currentTimeMillis();
        while (gameController.gameIsNotOver() && !gameController.isWin()) {
            if (System.currentTimeMillis() - current > this.period) {
                System.out.println("Inside if");
                this.update();
                this.draw(windowGame);
                current = System.currentTimeMillis();
            }
        }
        if (!gameController.gameIsNotOver()) {
            GameState.setGameState(GameState.WIN);
        } else {
        GameState.setGameState(GameState.GAMEOVER);
        }
    }
    /**
     * Draw the game.
     */
    public void draw(WindowGame windowGame) {
       windowGame.update();
    }
    /**
     * Update the game.
     */
    @Override
    public void update() {
        gameController.update();
    }
    /*
     * Getter of the GameController.
     * @return the GameController.
     */
    public GameController getGameController() {
       return this.gameController;
    }
}
