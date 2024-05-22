package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.Component;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.Constaints;
import it.unibo.utilities.EntityType;

public class EntityFactoryImplTest {

    EntityFactoryImpl entityFactoryImpl;
    BirdPositionComponent birdPosition;

    @BeforeEach
    public void setUp() {
        GamePerformance gamePerformance = new GamePerformanceImpl(null);
        entityFactoryImpl = new EntityFactoryImpl(gamePerformance);
        birdPosition = new BirdPositionComponent();
    }

    @Test
    public void testCreateCake() {
        Entity cake = entityFactoryImpl.createCake(null);
        assertNotNull(cake);
        assertEquals(EntityType.CAKE, cake.getEntityType());
        assertTrue(cake.getPosition().getX() >= Constaints.PowerUps.CAKE_MIN_X
                && cake.getPosition().getX() <= Constaints.PowerUps.CAKE_MAX_X);
        assertTrue(cake.getPosition().getY() == Constaints.PowerUps.CAKE_FLOOR_1_Y
                || cake.getPosition().getY() == Constaints.PowerUps.CAKE_FLOOR_2_Y
                || cake.getPosition().getY() == Constaints.PowerUps.CAKE_FLOOR_3_Y);
    }

    @Test
    public void testCreateBird() {
        Entity bird = entityFactoryImpl.createBird(null);
        assertNotNull(bird);
        assertEquals(EntityType.BIRD, bird.getEntityType());
        assertTrue(bird.getPosition().getX() == Constaints.PowerUps.BIRD_MIN_x
                || bird.getPosition().getX() == Constaints.PowerUps.BIRD_MAX_X);
        assertTrue(bird.getPosition().getY() >= Constaints.PowerUps.BIRD_MIN_Y
                && bird.getPosition().getY() <= Constaints.PowerUps.BIRD_MAX_Y);
                Set<Component> components = bird.getComponents();
                assertNotNull(components);
                assertTrue(components.stream().anyMatch(c -> c instanceof BirdPositionComponent));
    }
}
