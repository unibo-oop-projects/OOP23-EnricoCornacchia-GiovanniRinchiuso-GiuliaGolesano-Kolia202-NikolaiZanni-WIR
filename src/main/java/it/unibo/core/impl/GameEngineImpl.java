package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;
import it.unibo.utilities.GameState;
import it.unibo.controller.impl.GameController;

/**
 * Implementation of the game engine.
 */

public class GameEngineImpl implements GameEngine {

    private final int period;
    private final GameController gameController;
    private boolean hasChanged;
    /**
     * Constructor for the game engine.
     */
    public GameEngineImpl() {
        period = 10;
        gameController = new GameController();
        hasChanged = true;
    }
    /**
     *Loop of the game.
     */
    private void gameLoop() {
        long current = System.currentTimeMillis();
        while (gameController.gameIsNotOver() && gameController.isWin()) {
            if (System.currentTimeMillis() - current > this.period) {
                this.update();
                this.draw();
                current = System.currentTimeMillis();
            }
        }
        if (!gameController.gameIsNotOver()) {
            GameState.setGameState(GameState.WIN);
        } else {
        GameState.setGameState(GameState.GAMEOVER);
        }
        this.hasChanged = true;
    }
    /**
     * Main loop, it delegates the drawing to the view based on the game state.
     */
    @Override
    public void mainLoop() {
        while(true) {
            while (hasChanged) {
                switch(GameState.getGameState()) {
                    case HOME:
                        //gameView.drawHome();
                        hasChanged = false;
                        break;
                    case PLAYING:
                        gameLoop();
                        break;
                    case WIN:
                        //gameView.drawWin();
                        hasChanged = false;
                        break;
                    case GAMEOVER:
                        //gameView.drawGameOver();
                        hasChanged = false;
                        break;
                    case SETTINGS:
                        //gameView.drawSettings();
                        hasChanged = false;
                        break;
                    case PAUSED:
                        //gameView.drawPaused();
                        hasChanged = false;
                        break;
                    default:
                        break;
                }   
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
    /*
     * Setter true for hasChanged.
     */
    public void setHasChangedTrue() {
        this.hasChanged = true;
    }
    /*
     * Getter of the GameController.
     * @return the GameController.
     */
    public GameController getGameController() {
       return this.gameController;
    }
    /*
     * Getter of the hasChanged.
     * @return true if the GameState has been changed, false otherwise.
     */
    public boolean hasChanged() {
        return this.hasChanged;
    }
}
