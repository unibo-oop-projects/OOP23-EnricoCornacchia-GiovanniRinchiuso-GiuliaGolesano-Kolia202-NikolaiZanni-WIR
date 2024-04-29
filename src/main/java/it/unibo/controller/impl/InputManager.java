package it.unibo.controller.impl;
/**
 * Controller for the input.
 */

import it.unibo.model.api.GamePerformance;
/**
 * Controller for the input.
 */
public class InputManager {

    private final GamePerformance gamePerformance;
    /**
     * Constructor for the InputManager.
     * @param gamePerformance the game performance
     */
    public InputManager(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * Method that notify the Game Performance that a key from keyboard has been pressed.
     * 
     * @param keyCode
     */
    public void notifyKeyPressed(final int keyCode) {
        this.gamePerformance.addKey(keyCode);
    }

    /**
     * Method that notify the Game Performance that a key from keyboard has been released.
     * 
     * @param keyCode
     */
    public void notifyKeyReleased(final int keyCode) {
        this.gamePerformance.removeKey(keyCode);
    }

} 
