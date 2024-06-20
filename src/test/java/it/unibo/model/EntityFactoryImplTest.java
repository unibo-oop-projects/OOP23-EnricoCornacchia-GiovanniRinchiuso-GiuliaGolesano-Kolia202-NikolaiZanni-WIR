package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.EntityType;

public class EntityFactoryImplTest {
    EntityFactoryImpl entityFactoryImpl;
    private GameController gameController;
    private GamePerformance gamePerformance;

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(this.gameController);
        entityFactoryImpl = new EntityFactoryImpl(gamePerformance);
    }

    @Test
    void testCreateFelix() {
    }

    @Test
    void testCreateRalph() {
    }

    @Test
    void testCreateWindows() {
    }

    @Test
    void testCreateBrick() {
    }

    @Test
    void testCreateCake() {
        Pair<Double, Double> position = new Pair<>(10.0, 10.0);
        Entity cake = entityFactoryImpl.createCake(position);
        assertNotNull(cake, "Cake entity should not be null");
        assertEquals(EntityType.CAKE, cake.getEntityType(), "Entity type should be CAKE");
        assertEquals(position, cake.getPosition(), "Cake position should be as initialized");
        assertTrue(cake.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                "Cake should have a MovementComponent");
        assertTrue(cake.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.CAKEPOSITION),
                "Cake should have a CakePositionComponent");
        assertTrue(cake.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                "Cake should have a HitboxComponent");
    }

    @Test
    void testCreateBird() {
        Pair<Double, Double> position = new Pair<>(10.0, 10.0);
        Entity bird = entityFactoryImpl.createBird(position);
        assertNotNull(bird, "Bird entity should not be null");
        assertEquals(EntityType.BIRD, bird.getEntityType(), "Entity type should be BIRD");
        assertEquals(position, bird.getPosition(), "Bird position should be as initialized");
        assertTrue(bird.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.MOVEMENT),
                "Bird should have a MovementComponent");
        assertTrue(bird.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.BIRDPOSITION),
                "Bird should have a BirdPositionComponent");
        assertTrue(bird.getComponents().stream().anyMatch(c -> c.getComponent() == ComponentType.HITBOX),
                "Bird should have a HitboxComponent");
    }
}
