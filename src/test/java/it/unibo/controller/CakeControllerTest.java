package it.unibo.controller;

import it.unibo.controller.impl.CakeController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CakeControllerTest {
    private GameController gameController;
    private CakeController cakeController;
    private GamePerformance gamePerformance;
    private ScheduledExecutorService scheduler;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(gameController);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        cakeController = new CakeController(gamePerformance) {
            @Override
            protected ScheduledExecutorService createScheduler() {
                return scheduler;
            }
        };
    }

    @Test
    void testScheduleCakeCreation() throws InterruptedException {
        cakeController.scheduleCakeCreation();
        TimeUnit.SECONDS.sleep(1);
        Entity cake = cakeController.getCake();
        assertNotNull(cake, "La torta dovrebbe essere stata creata");
        TimeUnit.SECONDS.sleep(5);
        Entity cakeAfterRemoval = cakeController.getCake();
        assertNull(cakeAfterRemoval, "La torta dovrebbe essere stata rimossa");
        cakeController.stopCakeCreation();
    }

    @Test
    void testMultipleCakeCreations() throws InterruptedException {
        cakeController.scheduleCakeCreation();
        TimeUnit.SECONDS.sleep(1);
        Entity firstCake = cakeController.getCake();
        assertNotNull(firstCake, "La prima torta dovrebbe essere stata creata");
        TimeUnit.SECONDS.sleep(10);
        Entity secondCake = cakeController.getCake();
        assertNotNull(secondCake, "La seconda torta dovrebbe essere stata creata");
        assertNotEquals(firstCake, secondCake, "La seconda torta dovrebbe essere diversa dalla prima");
        cakeController.stopCakeCreation();
    }
}