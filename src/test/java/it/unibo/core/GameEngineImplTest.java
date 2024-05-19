package it.unibo.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.utilities.GameState;


public class GameEngineImplTest {

    private GameEngineImpl gameEngine;

    @BeforeEach
    public void setUp() {
        gameEngine = new GameEngineImpl();
    }

    @Test
    void testMainLoopHomeState() {
        GameState.setGameState(GameState.HOME);
        this.gameEngine.setHasChangedTrue();

        // Run the main loop in a separate thread to avoid blocking the test
        new Thread(() -> gameEngine.mainLoop()).start();

        // Add a small sleep to allow the loop to execute
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Since drawing functions are commented out, we're verifying state change handling.
        assertEquals(GameState.HOME, GameState.getGameState(), "GameState should remain HOME");
    }
    
    @Test
    void testSetHasChangedTrue() {
        gameEngine.setHasChangedTrue();
        assertTrue(gameEngine.hasChanged(), "hasChanged should be true after calling setHasChangedTrue");
    }
}