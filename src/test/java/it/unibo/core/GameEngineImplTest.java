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

    
}