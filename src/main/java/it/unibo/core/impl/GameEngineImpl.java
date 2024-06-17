package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;
import it.unibo.utilities.GameState;
import it.unibo.controller.impl.GameController;
import it.unibo.view.impl.EndGameView;
import it.unibo.view.impl.WinGameView;
import it.unibo.view.impl.WindowGame;
import javafx.stage.Stage;

/**
 * Implementation of the game engine.
 */

public class GameEngineImpl implements GameEngine {

    private final GameController gameController;
    private boolean isEndShown = false;
    private boolean isWinShown = false;
    /**
     * Constructor for the game engine.
     */
    public GameEngineImpl() {
        this.gameController = new GameController();
    }
    /**
     *Loop of the game.
     */
    @Override
    public void gameLoop(final WindowGame windowGame) {
        //System.out.println("Game loop started");
        if (gameController.gameIsNotOver() && !gameController.isWin()) {
            if (GameState.getGameState().equals(GameState.PLAYING)) {
                this.update();
                this.draw(windowGame);
            }  
        }
        if (!gameController.gameIsNotOver() && !isEndShown) {
            GameState.setGameState(GameState.GAMEOVER);
            try {   
                new EndGameView().start(new Stage());
                isEndShown = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } 
        if (gameController.isWin() && !isWinShown) {
            GameState.setGameState(GameState.WIN);
            try {
                new WinGameView().start(new Stage());
                isWinShown = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   /**
    * @InheritDoc
    */
    public void draw(final WindowGame windowGame) {
         windowGame.update();
         windowGame.updateBird();
         windowGame.updateCake();
    }
    /**
     * @InheritDoc
     */
    @Override
    public void update() {
        gameController.update();
    }
    /**
     * Getter of the GameController.
     * @return the GameController.
     */
    public GameController getGameController() {
       return this.gameController;
    }
}
