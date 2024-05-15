package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;
import it.unibo.utilities.GameState;
import it.unibo.view.impl.GameView;
import it.unibo.controller.impl.GameController;

/**
 * Implementation of the game engine.
 */

public class GameEngineImpl implements GameEngine {

    private final int period;
    private final GameController gameController;
    private final GameView gameView;
    private boolean hasChanged;
    /**
     * Constructor for the game engine.
     */
    public GameEngineImpl() {
        period = 10;
        gameController = new GameController();
        gameView = new GameView(gameController);
        hasChanged = false;
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
     * Start the game.
     */
    @Override
    public void mainLoop() {
        while(true){
            do {
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
            } while (hasChanged);
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
}
