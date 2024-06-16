package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.controller.impl.BrickController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;

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
        Entity brick = entityFactory.createBrick(new Pair<>(300.0, 300.0));
        this.brickController.addBrick(brick);
        this.brickController.fallBricks();
        assertEquals(300.0, brick.getPosition().getX(), "The brick should not have moved horizontally");
        assertEquals(301.0, brick.getPosition().getY(), "The brick should have moved vertically");
        this.gameController.setLevel(2);
        this.brickController.fallBricks();
        assertEquals(300.0, brick.getPosition().getX(), "The brick should not have moved horizontally");
        assertEquals(303.0, brick.getPosition().getY(), "The brick should have moved vertically");
    }

    @Test
    void testRemoveBrick() {
        Entity brick = entityFactory.createBrick(new Pair<>(10000.0, 300.0));
        this.brickController.addBrick(brick);
        assertEquals(1, this.brickController.getBricks().size(), "The set of bricks should contain one brick");
        for(int i = 0; i < 1000; i++) {
            this.brickController.fallBricks();
        }
        assertEquals(0, this.brickController.getBricks().size(), "The set of bricks should be empty");
    }
}
