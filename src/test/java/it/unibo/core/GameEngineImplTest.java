package it.unibo.core;

import org.junit.jupiter.api.BeforeEach;
import it.unibo.core.impl.GameEngineImpl;


public class GameEngineImplTest {

    @SuppressWarnings("unused")
    private GameEngineImpl gameEngine;

    @BeforeEach
    public void setUp() {
        gameEngine = new GameEngineImpl();
    }

    
}