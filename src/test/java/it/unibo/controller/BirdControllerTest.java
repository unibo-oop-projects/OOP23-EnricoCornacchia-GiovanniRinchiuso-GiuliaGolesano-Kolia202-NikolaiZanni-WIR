package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.impl.BirdController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;

class BirdControllerTest {
    private BirdController birdController;
    private GamePerformance gamePerformance;
    private GameController gameController;
    private EntityFactoryImpl entityFactory;
    private BirdPositionComponent birdPositionComponent;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        birdController = new BirdController(this.gamePerformance);
        entityFactory = new EntityFactoryImpl(this.gamePerformance);
        birdPositionComponent = new BirdPositionComponent();
    }

    @Test
    void testGetBird() {
        List<Entity> birds = birdController.getBirds();
        assertEquals(0, birds.size(), "The initial set of birds should be empty");
    }

    @Test
    void testBirdMovement() {
    }
       

    @Test
    void testStopBirdCreation() {
        birdController.stopBirdCreation();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int initialBirdCount = birdController.getBirds().size();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertEquals(initialBirdCount, birdController.getBirds().size(), "No new birds should be created after stopping");
    }

}