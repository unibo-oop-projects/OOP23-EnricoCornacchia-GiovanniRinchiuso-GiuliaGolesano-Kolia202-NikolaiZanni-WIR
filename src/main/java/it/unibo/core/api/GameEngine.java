package it.unibo.core.api;
/**
 * Interface for the game engine.
 */
public interface GameEngine {
    /**
     * Loop of the game.
     */
    void mainLoop();
    /**
     * Update the game.
     */
    void update();
}

