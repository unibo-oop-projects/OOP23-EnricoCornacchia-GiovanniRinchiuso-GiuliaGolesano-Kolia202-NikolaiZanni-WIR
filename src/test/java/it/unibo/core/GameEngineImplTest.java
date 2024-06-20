package it.unibo.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.core.impl.GameEngineImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the {@link GameEngineImpl}.
 */
class GameEngineImplTest {

    private GameEngineImpl gameEngine;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        gameEngine = new GameEngineImpl();
    }

    /**
     * Tests if the game engine is initialized correctly.
     */
    @Test
    void testGameEngineInitialization() {
        assertNotNull(gameEngine, "GameEngine should be initialized");
    }
}
