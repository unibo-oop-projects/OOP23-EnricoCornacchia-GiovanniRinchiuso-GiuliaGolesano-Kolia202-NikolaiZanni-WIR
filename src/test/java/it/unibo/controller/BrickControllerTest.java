package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.controller.impl.BrickController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.EntityImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.MovementComponent;

class BrickControllerTest {

    private BrickController brickController;
    private GamePerformance gamePerformance;
    private GameController gameController;
    private EntityFactoryImpl entityFactory;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        brickController = new BrickController(this.gamePerformance);
        entityFactory = new EntityFactoryImpl(this.gamePerformance);
    }

    @Test
    void testGetBricks() {
        Set<Entity> bricks = brickController.getBricks();
        assertEquals(0, bricks.size(), "The initial set of bricks should be empty");
    }

    @Test
    void testFallBricks() {
        // Creiamo una nuova istanza di MovementComponent
        MovementComponent movementComponent = new MovementComponent();

        // Creiamo una nuova Entity con il MovementComponent
        Entity brick = entityFactory.createBrick(new Pair<>(10.0, 10.0));

        // Aggiungiamo il mattoncino al BrickController
        this.brickController.getBricks().add(brick);

        // Chiamiamo fallBricks
        this.brickController.fallBricks();

        // Verifichiamo che il mattoncino si sia mosso correttamente
        assertEquals(10.0, brick.getPosition().getX(), "The brick should not have moved horizontally");
        assertEquals(9.0, brick.getPosition().getY(), "The brick should have moved vertically");
        this.gameController.setLevel(2);
        this.brickController.fallBricks();
        assertEquals(10.0, brick.getPosition().getX(), "The brick should not have moved horizontally");
        assertEquals(7.0, brick.getPosition().getY(), "The brick should have moved vertically");
    }
}
