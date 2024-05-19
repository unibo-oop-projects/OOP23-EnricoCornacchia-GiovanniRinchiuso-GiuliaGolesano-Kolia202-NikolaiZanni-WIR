package it.unibo.controller;

import it.unibo.controller.impl.FelixController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FelixControllerTest {
    private GameController gameController;
    private FelixController felixController;
    private GamePerformance gamePerformance;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        felixController = new FelixController(gamePerformance);
    }

    @Test
    void testGetFelix() {
        Entity felix = felixController.getFelix();
        assertNotNull(felix, "Felix should not be null after initialization");
    }

    @Test
    void testMoveFelixLeft() {
        Entity felix = felixController.getFelix();
        double initialX = felix.getPosition().getX();
        felixController.moveLeft();
        double newX = felix.getPosition().getX();
        assertTrue(newX < initialX, "Felix should have moved left");
    }

    @Test
    void testMoveFelixRight() {
        Entity felix = felixController.getFelix();
        double initialX = felix.getPosition().getX();
        felixController.moveRight();
        double newX = felix.getPosition().getX();
        assertTrue(newX > initialX, "Felix should have moved right");
    }
}