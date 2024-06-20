package it.unibo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.impl.BirdController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.Constants;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BirdControllerTest {
    private GameController gameController;
    private BirdController birdController;
    private GamePerformance gamePerformance;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        birdController = new BirdController(gamePerformance);
    }

    @Test
    void testGetBird() {
        Set<Entity> bird = birdController.getBirds();
        assertNotNull(bird, "Birds should not be null after initialization");
    }

    @Test
    void testMoveBird() {
        birdController.update();
        birdController.moveBird();
        for (Entity bird : birdController.getBirds()) {
            double initialX = bird.getPosition().getX();
            birdController.moveBird();
            double newX = bird.getPosition().getX();
            assertTrue(newX > initialX, "Bird's X position should increase after moving");
        }
    }

    @Test
    void testUpdate() {
        long initialTime = System.currentTimeMillis();
        birdController.update();
        long currentTime = System.currentTimeMillis();
        if (currentTime - initialTime >= Constants.Bird.CREATION_INTERVA_1_B) {
            assertTrue(birdController.getBirds().size() > 0, "Bird should be created after the time interval");
        }
    }
}