package it.unibo.controller;

import it.unibo.controller.impl.BirdController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.Constaints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BirdControllerTest {
    private GameController gameController;
    private BirdController birdController;
    private GamePerformance gamePerformance;
    private EntityFactoryImpl entityFactory;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        birdController = new BirdController(gamePerformance);
        entityFactory = new EntityFactoryImpl(gamePerformance);
    }

    @Test
    void testGetBirds() {
        List<Entity> birds = birdController.getBirds();
        assertEquals(0, birds.size(), "The initial list of birds should be empty");
    }

    @Test
    void testBirdCreation() throws InterruptedException {
        // Wait for a bit more than the initial delay to allow a bird to be created
        Thread.sleep((Constaints.PowerUps.INITIAL_DELAY + 1) * 1000);
        List<Entity> birds = birdController.getBirds();
        assertEquals(1, birds.size(), "There should be one bird after the initial delay");
        // Wait for another period to allow another bird to be created
        Thread.sleep(Constaints.PowerUps.PERIOD * 1000);
        birds = birdController.getBirds();
        assertEquals(2, birds.size(), "There should be two birds after another period");
    }

    @Test
    void testMoveBirdLeft() {
        Entity bird = entityFactory.createBird(null);
        birdController.getBirds().add(bird);
        double initialX = bird.getPosition().getX();
        birdController.moveBirdLeft();
        double newX = bird.getPosition().getX();
        assertEquals(initialX - 1.0, newX, 0.01, "The bird should have moved left by 1.0 units");
    }

    @Test
    void testMoveBirdRight() {
        Entity bird = entityFactory.createBird(null);
        birdController.getBirds().add(bird);
        double initialX = bird.getPosition().getX();
        birdController.moveBirdRight();
        double newX = bird.getPosition().getX();
        assertEquals(initialX + 1.0, newX, 0.01, "The bird should have moved right by 1.0 units");
    }

    @Test
    void testStopBirdCreation() throws InterruptedException {
        birdController.stopBirdCreation();
        // Wait for a bit more than the initial delay to check no bird is created
        Thread.sleep((Constaints.PowerUps.INITIAL_DELAY + 1) * 1000);
        List<Entity> birds = birdController.getBirds();
        assertEquals(0, birds.size(), "There should be no birds since creation was stopped");
    }
}