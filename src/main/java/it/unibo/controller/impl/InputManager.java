package it.unibo.controller.impl;
/**
 * Controller for the input.
 */

import it.unibo.model.api.GamePerformance;

public class InputManager {

    private final GamePerformance gamePerformance;

    public InputManager(final GamePerformance gamePerformance){
        this.gamePerformance = gamePerformance;
    }

    /**
     * Method that notify the Game Performance that a key from keyboard has been pressed.
     * 
     * @param KeyCode
     */
    public void notifyKeyPressed(final int KeyCode){
        this.gamePerformance.addKey(KeyCode);
    }

    /**
     * Method that notify the Game Performance that a key from keyboard has been released.
     * 
     * @param KeyCode
     */
    public void notifyKeyReleased(final int KeyCode){
        this.gamePerformance.removeKey(KeyCode);
    }

} 
