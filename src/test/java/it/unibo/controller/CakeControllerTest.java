package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.impl.CakeController;
import it.unibo.controller.impl.GameController;
import it.unibo.controller.impl.WindowsController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.Constants;

/**
 * Test for the CakeController.
 */
class CakeControllerTest {
    private static final long TIME_SLEEP = 6000;

    private GameController gameController;
    private CakeController cakeController;
    private GamePerformance gamePerformance;
    private WindowsController windowsController;


    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        cakeController = new CakeController(gamePerformance);
        windowsController = new WindowsController(gamePerformance);
        windowsController.windowsGrid(Constants.Windows.NUM_WINDOWS);
    }

    @Test
    void testGetCake() {
        Set<Entity> cake = cakeController.getCakes();
        assertNotNull(cake, "Birds should not be null after initialization");
    }

    @Test
    void testUpdate() throws InterruptedException {
        long initialTime = System.currentTimeMillis();
        cakeController.update();
        long currentTime = System.currentTimeMillis();
        if (currentTime - initialTime >= Constants.Cake.CREATION_INTERVA_1_C) {
            assertTrue(cakeController.getCakes().size() > 0, "Cake should be created after the time interval");
        }
        Thread.sleep(TIME_SLEEP);
        cakeController.update();
        Set<Entity> cakes = cakeController.getCakes();
        assertTrue(cakes.isEmpty(), "All cakes should be removed after their active duration");
    }
}
