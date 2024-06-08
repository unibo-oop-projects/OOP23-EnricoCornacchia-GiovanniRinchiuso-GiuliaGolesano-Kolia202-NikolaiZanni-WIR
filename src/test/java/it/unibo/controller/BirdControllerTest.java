package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.controller.impl.BirdController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.MovementComponent;
import it.unibo.utilities.Constants;

class BirdControllerTest {
    private BirdController birdController;
    private GamePerformance gamePerformance;
    private GameController gameController;
    private EntityFactoryImpl entityFactory;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        birdController = new BirdController(this.gamePerformance);
        entityFactory = new EntityFactoryImpl(this.gamePerformance);
    }

    @Test
    void testGetBirds() {
        List<Entity> birds = birdController.getBirds();
        assertEquals(0, birds.size(), "The initial set of birds should be empty");
    }

    @Test
    public void testBirdCreation() throws InterruptedException {
        birdController.scheduleBirdCreation();
        TimeUnit.SECONDS.sleep(Constants.PowerUps.INITIAL_DELAY + Constants.PowerUps.PERIOD + 1); 
        List<Entity> birds = birdController.getBirds();
        assertTrue(birds.size() > 0, "At least one bird should have been created");
        birdController.stopBirdCreation(); 
    }

    @Test
    public void testMoveRightBird() {
        Entity bird = entityFactory.createBird(new Pair<Double, Double>(0.0, 300.0));
        this.birdController.getBirds().add(bird);
        this.birdController.moveBird();
        assertEquals(1.0, bird.getPosition().getX(), "The bird should have moved to the right");
        assertEquals(300.0, bird.getPosition().getY(), "The bird should not have moved vertically");
    }

    @Test
    public void testMoveLeftBird() {
    Entity bird = entityFactory.createBird(new Pair<Double, Double>(0.0, 1.0));
    BirdPositionComponent positionComponent = new BirdPositionComponent();
    positionComponent.randomPosition();
    positionComponent.hasToMoveRight(); 
    bird.addComponent(positionComponent);
    MovementComponent movementComponent = new MovementComponent(new Pair<Double, Double>(0.0, 1.0));
    bird.addComponent(movementComponent);
    this.birdController.getBirds().add(bird);
    this.birdController.moveBird();
    assertEquals(1.0, bird.getPosition().getX(), "The bird should have moved to the right");
    assertEquals(1.0, bird.getPosition().getY(), "The bird should not have moved vertically");
}

    @Test
    public void testRemoveBird() {
        Entity bird = entityFactory.createBird(new Pair<Double, Double>(0.0, 50.0));
        BirdPositionComponent positionComponent = new BirdPositionComponent();
        bird.addComponent(positionComponent);
        this.birdController.getBirds().add(bird);
        this.birdController.moveBird();
        this.birdController.moveBird(); 
        assertEquals(0, this.birdController.getBirds().size(), "The bird should have been removed from the list");
        assertEquals(0, this.gamePerformance.getEntity().size(), "The bird should have been removed from the gamePerformance");
    }
}
