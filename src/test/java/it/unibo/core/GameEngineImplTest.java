package it.unibo.core;

import org.junit.jupiter.api.BeforeEach;
import it.unibo.core.impl.GameEngineImpl;
/**
 * Test class for the GameEngineImpl.
 */
public class GameEngineImplTest {
    @SuppressWarnings("unused")
    private GameEngineImpl gameEngine;

    /**
     * Method that .
     */
    @BeforeEach
    public void setUp() {
        gameEngine = new GameEngineImpl();
    } 
}
